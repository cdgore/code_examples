import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static void printYesNo(char[] charArray) {
        Map<Character, Long> charCounts = getCharCounts(charArray);
        
        Map<Long, Long> countCounts = getCountCounts(charCounts);
        
        boolean printYes = delOneOrFewer(countCounts);
        
        if (printYes) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
    
    // Count characters
    private static Map<Character, Long> getCharCounts(char[] charArray) {
        Map<Character, Long> charCounts = new ConcurrentHashMap<>();
        
        for (int i = 0; i < charArray.length; i++) {
            Long charCount = charCounts.get(charArray[i]);
            charCount = charCount == null ? 0 : charCount;
            charCounts.put(charArray[i], charCount + 1);
        }
        
        return charCounts;
    }
    
    // Count character counts
    private static Map<Long, Long> getCountCounts(Map<Character, Long> charCounts) {
        Map<Long, Long> countCounts = new ConcurrentHashMap<>();
        
        for (Map.Entry<Character, Long> entry : charCounts.entrySet()) {
            long charCount = entry.getValue();
            Long charCountCount = countCounts.get(charCount);
            charCountCount = charCountCount == null ? 0 : charCountCount;
            
            countCounts.put(charCount, charCountCount + 1);
        }
        
        return countCounts;
    }
    
    private static boolean delOneOrFewer(Map<Long, Long> countCounts) {
        if (countCounts.size() < 2) {
            return true;
        } else if (countCounts.size() == 2) {
            // At least one value should be one
            boolean atLeastOneOne = false;
            for (long v : countCounts.values()) {
                if (v == 1) {
                    atLeastOneOne = true;
                }
            }
            
            if (atLeastOneOne) {
                boolean OneOneInSet = false;
                long minCount = Long.MAX_VALUE;
                long maxCount = 0;
                
                for (Map.Entry<Long, Long> entry : countCounts.entrySet()) {
                    if(entry.getKey() == 1 && entry.getValue() == 1) {
                        OneOneInSet = true;
                    }
                    
                    if(entry.getKey() < minCount) {
                        minCount = entry.getKey();
                    }
                    if(entry.getKey() > maxCount) {
                        maxCount = entry.getKey();
                    }
                }
                
                if (OneOneInSet || ((maxCount - 1 == minCount) && countCounts.get(maxCount) == 1)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        String inputString = scan.nextLine();
        char[] inputCharArray = inputString.toCharArray();
        printYesNo(inputCharArray);
    }
}