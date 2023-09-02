package exercise;

import java.util.*;

// BEGIN
class App {
//
//    public static void main(String[] args) {
//        Map<String, Object> data1 = new HashMap<>(
//                Map.of("one", "eon", "two", "two", "four", true)
//        );
//        System.out.println(data1); //=> {two=two, four=true, one=eon}
//
//        Map<String, Object> data2 = new HashMap<>(
//                Map.of("two", "own", "zero", 4, "four", true)
//        );
//        System.out.println(data2); //=> {zero=4, two=own, four=true}
//
//        Map<String, Object> result = App.genDiff(data1, data2);
//        System.out.println(result); //=> {four=unchanged, one=deleted, two=changed, zero=added}
//    }
    public static LinkedHashMap<String,Object> genDiff(Map<String,Object> data1,
                                        Map<String,Object> data2) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        Set<String> union = new HashSet<>(data1.keySet());
        Set<String> intersection = new HashSet<>(data1.keySet());
        Set<String> difference = new HashSet<>(data1.keySet());

        union.addAll(data2.keySet());
        intersection.retainAll(data2.keySet());
        difference.removeAll(data2.keySet());

        for (String s: union) {
        if (intersection.contains(s)) {
                result.put(s,
                        data1.get(s).equals(data2.get(s)) ?
                                "unchanged" : "changed");
            } else {
                result.put(s,
                        difference.contains(s)? "deleted": "added");
            }
        }
        SortedMap<String,Object> resultSorted = new TreeMap<>(result);
        result.clear();
        result.putAll(resultSorted);
        return result;
        }
}
//END
