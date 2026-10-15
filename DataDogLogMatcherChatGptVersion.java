import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DataDogLogMatcherChatGptVersion {
    static Map<String, List<Integer>> termsToQueries = new HashMap();
    static List<Integer> queriesSizes = new ArrayList<>();

    

    public static void main(String[] args){
        List<String> inputStream = Arrays.asList(
            "Q: service=auth status=error", 
                "Q: service=payments", 
                "L: service=auth status=error latency=120", 
                "L: service=payments status=ok" 
        );
        processStream(inputStream);
    }

    private static void processStream(List<String> inputStream) {
        List<List<String>> result = new ArrayList();
        for (String s : inputStream) {
            if (s.startsWith("Q")) {
                String[] queryTerms = s.split(" ");
                int queryId = queriesSizes.size() + 1;
                for (int i = 1; i < queryTerms.length; i++) {
                    List<Integer> existingQueries = termsToQueries.get(queryTerms[i]);
                    if (existingQueries != null) {
                        existingQueries.add(queryId);
                    } else {
                        existingQueries = new ArrayList<Integer>();
                        existingQueries.add(queryId);
                        termsToQueries.put(queryTerms[i], existingQueries);
                    }
                }
                queriesSizes.add(queryTerms.length - 1);
            } else if (s.startsWith("L")) {
                result.add(new ArrayList<>());
                List<Integer> touchedQueries = new ArrayList<>();
                int[] queriesTermsCounters = new int[queriesSizes.size()];
                String[] logTerms = s.split("\\s+");
                for (int i = 1; i < logTerms.length; i++) {
                    String logTerm = logTerms[i];
                    List<Integer> matchedQueries = termsToQueries.get(logTerm);
                    if (matchedQueries != null) {
                        for (int queryId : matchedQueries) {
                            if (queriesTermsCounters[queryId - 1] == 0) {
                                touchedQueries.add(queryId);
                            }
                            queriesTermsCounters[queryId - 1]++;
                        }
                    }
                }
                for (int touchedQueryId: touchedQueries) {
                    if (queriesTermsCounters[touchedQueryId - 1] == queriesSizes.get(touchedQueryId)) {
                        result.get(result.size() - 1).add(String.valueOf(touchedQueryId));
                    }
                }
            }
        }
        System.out.println(result.toString());
    }
}

/*
3. Follow-up questions you will get in the real interview

Now the real Datadog-style grilling starts. You should be able to answer these.

Performance & Scaling

What happens if you have 50 million queries?

    Memory of term -> list<queryId> explodes.

    Need compressed posting lists (RoaringBitmap, Elias–Fano).

    Possibly shard by key hash.

What if logs arrive at 1M/sec?

    You cannot allocate new HashMaps per log.

    Need object pooling or primitive arrays.

    Possibly pre-assign query IDs to contiguous ranges and use int arrays.

How would you support query deletion / TTL?

    Reference counting in posting lists.

    Lazy deletion with tombstones.

    Versioned indexes.

What if queries have 100 terms?

    Use shortest posting list first (like search engines).

    Early abort if candidate count drops to zero.

What if a term is extremely high-cardinality (e.g., host=web1 appears in 90% of queries)?

    Skip indexing overly frequent terms.

    Two-phase filtering: rare terms first.
*/

/*
Data Structure Tradeoffs

You should be able to explain:

Option	                                            Pros	                                        Cons
HashMap<String, List<Integer>>	                    Simple	                                        Memory heavy, slow intersections
BitSets per term	                                Fast AND	                                    Huge memory for sparse IDs
RoaringBitmap	                                    Compressed, fast	                            Complex implementation
Trie per key	                                    Prefix queries	                                Harder updates
Bloom filters	                                    Fast rejection	                                False positives need verification
*/

/*
System Design Twist

Then comes the killer question:

How would you design this if queries and logs are distributed across machines?

Expected answer:

    Partition by term hash.

    Local inverted indexes.

    Fan-out log to shards for candidate generation.

    Reduce phase for intersection.

Cache hot queries.
*/

/*
5. Tradeoffs you should explain explicitly

If I were interviewing you, I’d expect you to say something like this:

    Why arrays instead of maps?

        Faster lookups

        Better cache locality

        Less GC pressure

        Requires contiguous IDs (acceptable here)

    Why inverted index?

        Avoids comparing logs against all queries

        Scales with matches, not total queries

    Memory vs speed tradeoff

        More memory for indexes

        Much lower per-log CPU cost

        Correct choice for log ingestion systems

    When this breaks down

        Extremely high-cardinality terms

        Very large query counts without pruning

        No query expiration

6. Final interviewer questions you should be ready for

If this were Datadog, next would be:

    How would you handle 100M queries?

        Bitmaps / RoaringBitmap

        Sharding

        Disk-backed indexes

    What if logs arrive at 1M/sec?

        Object reuse

        Avoid per-log allocations

        Preallocated buffers

    How would you support OR or NOT queries?

        DNF normalization

        Multiple inverted indexes

        Query rewriting

    How would you distribute this?

        Shard by term

        Fan-out logs

        Reduce matches
*/