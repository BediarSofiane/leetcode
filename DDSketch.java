import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DDSketch {
    long totalCount = 0;
    long zeroCount = 0;
    double gamma = 1.1;
    double alpha;
    Map<Integer, Long> bucketsMap = new HashMap<>();
    int minIndex = Integer.MAX_VALUE;
    int maxIndex = Integer.MIN_VALUE;
    // a value to use to differenciate negative indices coming from negative values
    // (index > -1000) -> negative indices coming from values between 0 and 1
    // (index < -1000) -> negative indeices coming from negative values;
    int negativeIndicesSplitter = -1000;
    List<Integer> sortedIndices;

    public DDSketch(double gamma) {
        this.gamma = gamma;
        this.alpha = Math.log(this.gamma);
    }

    /*
     * Inserts the new value into the sketch.
     */

    /*
     * DEBUGING
     * Inserting -10 then 0 then 10.
     * bucketsMap: [] -> [-100000000:1] -> [-1000000000:1] -> [-1000000000:1, 24:1]
     * minIndex: MAX_VALUE -> -1000000 -> -1000000 -> -10000000
     * maxIndex: MIN_VALUE -> -1000000 -> 0 -> 24
     * zeroCount: 0 -> 0 -> 1 -> 1
     * totalCount: 0 -> 1 -> 2 -> 3
     */
    // Time complexity: O(1)
    // Space complexity: O(n) where n is the number of indexes. not inserted values
    // -> can we estimate relationship between number of values and number of
    // indices? as a worst scenario case I'd say 1 to 1. but tha's not realistic
    // since the whole point of this approach is to reuse indices for different
    // values that are in the same range
    public void insert(double value) {
        if (value == 0) {
            zeroCount++;
            totalCount++;
            minIndex = Math.min(0, minIndex);
            maxIndex = Math.max(0, maxIndex);
            return;
        }

        // Compute offset so that negative values have negative indices that farest
        // possible from (don't overlap with) values between 0 and 1.
        int offset = value < 0 ? Integer.MIN_VALUE : 0;

        int index = (int) Math.floor(Math.log(Math.abs(value)) / alpha) + offset;
        this.bucketsMap.merge(index, 1L, Long::sum);
        minIndex = Math.min(index, minIndex);
        maxIndex = Math.max(index, maxIndex);
        totalCount++;
        this.sortedIndices = null;
    }

    // Time complexity: O(n) as worst case scenario, the percentile is not reached
    // before looping over all possible indices.
    // Space complexity: O(n) because of the list of keys where we sort indices.
    public double getPercentile(int percentile) {
        if (percentile > 100) {
            throw new UnsupportedOperationException();
        }
        double percentileValue = totalCount * (double) percentile / 100;

        // O(nlogn)
        if (this.sortedIndices == null) {
            this.sortedIndices = bucketsMap.keySet().stream().sorted().toList();
        }
        int i = 0;
        int minIndexFromBucketsMap = sortedIndices.get(0);
        long accumulator = 0;
        // If minIndexFromBucketsMap is less than the negativeIndicesSplitter that means
        // that we have negative values, so we need to count them first then add
        // zeroCount, otherwise we add zeroCount directly
        if (minIndexFromBucketsMap > negativeIndicesSplitter) { // no negative values
            if (percentileValue <= zeroCount) {
                return 0.0;
            }
            accumulator = zeroCount;
        } else {
            // we have negative values, should start with them before adding zeroCount to
            // accumulator
            // O(nNegativeValues)
            while (i < sortedIndices.size() && sortedIndices.get(i) < negativeIndicesSplitter) {
                int index = sortedIndices.get(i);
                int offset = Integer.MIN_VALUE;
                accumulator += bucketsMap.get(index);
                // System.out.println(index + " " + accumulator);
                if (accumulator >= percentileValue) {
                    // System.out.println("DEBUG: index=" + index + ", gamma=" + gamma);
                    double lowerRange = -Math.pow(gamma, index - offset);
                    double upperRange = -Math.pow(gamma, index + 1 + -offset);
                    return (lowerRange + upperRange) / 2;
                }
                i++;
            }
            accumulator += zeroCount;
            if (accumulator >= percentileValue) {
                return 0.0;
            }
        }
        // System.out.println(bucketsMap.toString());
        while (i < sortedIndices.size()) {

            int index = sortedIndices.get(i);
            accumulator += bucketsMap.get(index);

            // System.out.println(index + " " + accumulator);
            if (accumulator >= percentileValue) {
                // System.out.println("DEBUG: index=" + index + ", gamma=" + gamma);
                double lowerRange = Math.pow(gamma, index);
                double upperRange = Math.pow(gamma, index + 1);
                return (lowerRange + upperRange) / 2;
            }
            i++;
        }
        throw new UnknownError();

    }

    public void merge(DDSketch other) {
        if (other.gamma != this.gamma) {
            throw new UnsupportedOperationException();
        }
        this.minIndex = Math.min(this.minIndex, other.minIndex);
        this.maxIndex = Math.max(this.maxIndex, other.maxIndex);
        zeroCount = this.zeroCount + other.zeroCount;
        for (int index : other.bucketsMap.keySet()) {
            this.bucketsMap.merge(index, other.bucketsMap.get(index), Long::sum);
        }
        this.totalCount = this.totalCount + other.totalCount;
        this.sortedIndices = null;
    }

    public static void main(String[] args) {
        DDSketch sketch = new DDSketch(1.1);

        // Test 1: Simple case
        sketch.insert(10);
        sketch.insert(20);
        sketch.insert(30);
        System.out.println("p50 of [10,20,30]: " + sketch.getPercentile(50));

        // Test 2: With zeros
        DDSketch sketch2 = new DDSketch(1.1);
        sketch2.insert(0);
        sketch2.insert(0);
        sketch2.insert(100);
        System.out.println("p50 of [0,0,100]: " + sketch2.getPercentile(50));

        // Test 3: What do you predict this will return?
        DDSketch sketch3 = new DDSketch(1.1);
        sketch3.insert(1);
        sketch3.insert(10);
        sketch3.insert(100);
        System.out.println("p95 of [1,10,100]: " + sketch3.getPercentile(95));

        // Test merge
        System.out.println("\n--- Merge Test ---");
        DDSketch sketchA = new DDSketch(1.1);
        sketchA.insert(10);
        sketchA.insert(20);

        DDSketch sketchB = new DDSketch(1.1);
        sketchB.insert(30);
        sketchB.insert(40);

        System.out.println("Before merge - sketchA p50: " + sketchA.getPercentile(50));

        sketchA.merge(sketchB);
        System.out.println("After merge [10,20,30,40]:");
        System.out.println("  p50: " + sketchA.getPercentile(50)); // Should be ~25
        System.out.println("  p75: " + sketchA.getPercentile(75)); // Should be ~35

        // Test merging with zeros
        DDSketch sketchC = new DDSketch(1.1);
        sketchC.insert(0);
        sketchC.insert(0);

        DDSketch sketchD = new DDSketch(1.1);
        sketchD.insert(100);
        sketchD.insert(200);

        sketchC.merge(sketchD);
        System.out.println("\nMerge with zeros [0,0,100,200]:");
        System.out.println("  p50: " + sketchC.getPercentile(50)); // Should be 0

        // Test negative Values
        // Test 1: Mix of negative, zero, positive
        DDSketch sketchN1 = new DDSketch(1.1);
        sketchN1.insert(-10);
        sketchN1.insert(0);
        sketchN1.insert(10);
        System.out.println("p50 of [-10, 0, 10]: " + sketchN1.getPercentile(50));
        // Expected: ~0 (median is zero)

        // Test 2: Only negative values
        DDSketch sketchN2 = new DDSketch(1.1);
        sketchN2.insert(-50);
        sketchN2.insert(-10);
        sketchN2.insert(-5);
        System.out.println("p50 of [-50, -10, -5]: " + sketchN2.getPercentile(50));
        // Expected: ~-10

        // Test 3: Merge with negatives
        DDSketch sketchN3 = new DDSketch(1.1);
        sketchN3.insert(-100);

        DDSketch sketchN4 = new DDSketch(1.1);
        sketchN4.insert(100);

        sketchN3.merge(sketchN4);
        System.out.println("p50 of [-100, 100]: " + sketchN3.getPercentile(50));
        // Expected: somewhere between -100 and 100

        // Test caching of sorted indices
        DDSketch sketchCache = new DDSketch(1.1);

        // Insert data
        for (int i = 0; i < 1000; i++) {
            sketchCache.insert(i);
        }

        System.out.println("First query...");
        long start1 = System.nanoTime();
        double p50_1 = sketchCache.getPercentile(50);
        long time1 = System.nanoTime() - start1;
        System.out.println("p50 = " + p50_1 + ", time = " + time1 + "ns");

        // No inserts between queries - should use cache!
        System.out.println("\nSecond query (should be faster)...");
        long start2 = System.nanoTime();
        double p95 = sketchCache.getPercentile(95);
        long time2 = System.nanoTime() - start2;
        System.out.println("p95 = " + p95 + ", time = " + time2 + "ns");

        System.out.println("\nThird query (should be faster)...");
        long start3 = System.nanoTime();
        double p99 = sketchCache.getPercentile(99);
        long time3 = System.nanoTime() - start3;
        System.out.println("p99 = " + p99 + ", time = " + time3 + "ns");

        // Insert invalidates cache
        sketchCache.insert(5000);

        System.out.println("\nFourth query (after insert, should be slower)...");
        long start4 = System.nanoTime();
        double p50_2 = sketchCache.getPercentile(50);
        long time4 = System.nanoTime() - start4;
        System.out.println("p50 = " + p50_2 + ", time = " + time4 + "ns");
    }
}
