import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static class Candies {
        private static Map<Integer, Integer> cache = new ConcurrentHashMap<>();
        private final int N;
        private final List<Integer> rating;
        
        public Candies(int N, List<Integer> rating) {
            this.N = N;
            this.rating = rating;
        }
        
        public int cand(int i) {
            if (cache.containsKey(i)) {
                return cache.get(i);
            } else {
                int leftVal = 0;
                int rightVal = 0;
                
                if (i - 1 < 0 || rating.get(i - 1) >= rating.get(i)) {
                    leftVal = 1;
                } else {
                    leftVal = cand(i - 1) + 1;
                }
                
                if (i + 1 > N - 1 || rating.get(i + 1) >= rating.get(i)) {
                    rightVal = 1;
                } else {
                    rightVal = cand(i + 1) + 1;
                }
                
                int result = Math.max(leftVal, rightVal);
                
                cache.put(i, result);
                
                return result;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        List<Integer> rating = new ArrayList<>();
        
        for (int i=0; i<N; i++) {
            rating.add(sc.nextInt());
        }
        
        Candies candy = new Candies(N, rating);
        
        int totalSum = 0;
        for (int i=0; i<N; i++) {
            totalSum += candy.cand(i);
        }
        
        System.out.println(totalSum);
    }
}