import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DataDogMetricAggregatorFromClaude {
    
    Map<Integer, Metric> identityMap = new HashMap();
    Map<String, Map<Integer, Map<String, Set<Integer>>>> metricsMap = new HashMap<>();
    int counter = 0;
    int bucketSize = 10;
    //ingest(100, "cpu", 50, {"host": "s1", "env": "prod"})
    //ingest(105, "cpu", 60, {"host": "s1", "env": "prod"})
    public void ingest(int timestamp, String metricName, int value, Map<String, String> tags) {
        //Generate an ID
        int metricId = counter++;
        //Store the pair Value and timestamp in the identityMap
        Metric metric = new Metric(value, timestamp);
        identityMap.put(metricId, metric);
        //Store metric inside the metric map.
        Map<Integer, Map<String, Set<Integer>>> buckets = metricsMap.get(metricName);
        if (buckets == null) {
            buckets = new HashMap<>();
            metricsMap.put(metricName, buckets);
        }
        int bucketIndex = (int)(timestamp / bucketSize);
        Map<String, Set<Integer>> tagsMap = buckets.get(bucketIndex);
        if(tagsMap == null){
            tagsMap = new HashMap<>();
            buckets.put(bucketIndex, tagsMap);
        }
        for (Entry<String, String> tag : tags.entrySet()) {
            String tagAsString = tag.getKey() + "=" + tag.getValue();
            Set<Integer> metricsSet = tagsMap.get(tagAsString);
            if(metricsSet == null){
                metricsSet = new HashSet<>();
                tagsMap.put(tagAsString, metricsSet);
            }
            metricsSet.add(metricId);
        }
    }
    //query("cpu", 110, 95, {"host": "s1", "env": "prod"})
    public double query(String metricName, int now, int since, Map<String, String> tags) {
        var metricBuckets = metricsMap.get(metricName);
        if (metricBuckets == null) {
            return -1.0;
        } else {
            Set<Integer> selectedMetricIds = new HashSet<>();
            var startBucket = since / bucketSize;
            var endBucket = now / bucketSize;
            //get IDs of queries for buckets that are in the middle
            for (int i = startBucket; i <= endBucket; i++) {
                Set<Integer> perBucketSelectedMetricIds = new HashSet();
                var currentBucket = metricBuckets.get(i);
                if (currentBucket == null) {
                    continue;
                }
                for (Entry<String, String> tag : tags.entrySet()) {
                    String tagAsString = tag.getKey() + "=" + tag.getValue();

                    var currentBucketAndTagIds = currentBucket.get(tagAsString);
                    if (currentBucketAndTagIds != null) {
                        if (i == startBucket || i == endBucket) {
                            currentBucketAndTagIds = currentBucketAndTagIds.stream()
                                    .filter(id -> identityMap.get(id).timestamp <= now
                                            && identityMap.get(id).timestamp >= since)
                                    .collect(Collectors.toSet());
                        }
                        if (perBucketSelectedMetricIds.isEmpty()) {
                            perBucketSelectedMetricIds = new HashSet(currentBucketAndTagIds);
                        } else {
                            perBucketSelectedMetricIds.retainAll(currentBucketAndTagIds);
                        }
                    } else {
                        perBucketSelectedMetricIds = new HashSet<>();
                        break;
                    }
                }
                selectedMetricIds.addAll(perBucketSelectedMetricIds);
            }

            //compute average
            return selectedMetricIds.stream().map(id -> identityMap.get(id)).mapToDouble(metric -> metric.value).average().orElse(-1.0);

        }        
    }

}

class Metric {
    public Metric(int value, int timestamp){
        this.value = value;
        this.timestamp = timestamp;
    }
    public int value;
    public int timestamp;
}