import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DatadogLogMatcher {
    Map<String, Set<Integer>> termsToQueries = new HashMap();
    Map<Integer, String> queriesToTerms = new HashMap();

    public void addQuery(int queryId, String term) {
        String lowerCaseTerm = term.toLowerCase();
        String oldTerm = queriesToTerms.get(queryId);
        if (oldTerm != null) {
            termsToQueries.get(oldTerm).remove(queryId);
        }
        Set<Integer> queries = termsToQueries.get(lowerCaseTerm);
        if (queries == null) {
            queries = new HashSet<>();
            termsToQueries.put(lowerCaseTerm, queries);
        }
        queriesToTerms.put(queryId, lowerCaseTerm);
        queries.add(queryId);
    }

    public Set<Integer> processMessage(String message) {
        String[] terms = message.toLowerCase().split("\\W");
        Set<Integer> queriesIds = new HashSet<>();
        for (String term : terms) {
            if(termsToQueries.get(term) != null){
                queriesIds.addAll(termsToQueries.get(term));
            }
        }
        return queriesIds;
    }
    
}
