import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    // cache (m -> set[ {k -> count} ])
    private static ConcurrentHashMap<Integer, List<Long>> cache = new ConcurrentHashMap<>();
    
    public static void initCache(List<Integer> kList) {
        List<Long> tmpList = new ArrayList<Long>();
        
        for (int k: kList) {
            tmpList.add((long)1);
        }
        tmpList.add((long)1);
        
        cache.put(0, tmpList);
    }
    
    public static List<Long> calcSolution(List<Integer> kList, int n) {
        if (cache.containsKey(n)) {
            // memoization
            return cache.get(n);
        } else {
            List<Long> tmpList = new ArrayList<Long>();
            tmpList.add((long)0);
            
            int c = 1;
            for (int k : kList) {
                long tmpValue = tmpList.get(c - 1);
                
                if (n - k >= 0) {
                    tmpValue += calcSolution(kList, n - k).get(c);
                }
                
                tmpList.add(tmpValue);
                
                c += 1;
            }
            
            // Cache result for later usage
            cache.put(n, tmpList);
                        
            return tmpList;
        }
    }
    
    public static List<Long> bottomUp(List<Integer> kList, int m) {
        int maxK = -1;
        for (int k : kList) {
            if(k > maxK) {
                maxK = k;
            }
        }
        List<Long> solution = new ArrayList<>();
        for (int i=0; i<=m; i++) {
            solution = calcSolution(kList, i);
            // System.out.print("" + i + " : " + solution.get(solution.size() - 1) + " | ");
            if (i > maxK + 1) {
                cache.remove(i - (maxK + 1));
            }
        }
        return solution;
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
        
        List<Long> solution = new ArrayList<>();
        
        // solution = bottomUp(kList, n);
        solution = calcSolution(kList, n);
        
        System.out.println(solution.get(solution.size() - 1));
    }
}