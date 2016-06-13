import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    // cache (m -> set[ {k -> count} ])
    private static ConcurrentHashMap<Integer, Set<Map<Integer, Integer>>> cache = new ConcurrentHashMap<>();
    
    public static void initCache(List<Integer> kList) {
        Map<Integer, Integer> tmpHM = new HashMap<>();
        Set<Map<Integer, Integer>> tmpSet = new HashSet<>();
        
        for (int k: kList) {
            tmpHM.put(k, 0);
        }
        tmpSet.add(tmpHM);
        
        cache.put(0, tmpSet);
    }
    
    public static Set<Map<Integer, Integer>> calcSolution(List<Integer> kList, int m) {
        if (cache.containsKey(m)) {
            // memoization
            return cache.get(m);
        } else {
            Set<Map<Integer, Integer>> tmpSet = new HashSet<>();
            
            for (int k : kList) {
                if (m - k >= 0) {
                    for (Map<Integer, Integer> solution : calcSolution(kList, m - k)) {
                        // Deep copy solution
                        Map<Integer, Integer> tmpSolution = new HashMap<Integer, Integer>();
                        for (Map.Entry<Integer, Integer> entry : solution.entrySet()) {
                            tmpSolution.put(entry.getKey(), entry.getValue());
                        }
                        tmpSolution.put(k, tmpSolution.get(k) + 1);
                        tmpSet.add(tmpSolution);
                    }
                }
            }
            
            // Cache result for later usage
            cache.put(m, tmpSet);
            
            // System.out.println("" + m + tmpSet);
            
            return tmpSet;
        }
    }
    
    public static void bottomUp(List<Integer> kList, int m) {
        for (int i=0; i<=m; i++) {
            calcSolution(kList, i);
        }
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        List<Integer> kList = new ArrayList<Integer>();
        for (int i=0; i<m; i++) {
            kList.add(sc.nextInt());
        }
        
        initCache(kList);
        
        bottomUp(kList, n);
        
        System.out.println(calcSolution(kList, n).size());
    }
}