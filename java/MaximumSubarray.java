import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        int numCases = sc.nextInt();
        
        for (int i=0; i<numCases; i++) {
            runOneCase();
        }
    }
    
    public static void runOneCase() {
        int numElements = sc.nextInt();
        List<Integer> elements = new ArrayList<Integer>();
        for (int i=0; i<numElements; i++) {
            elements.add(sc.nextInt());
        }
        
        System.out.println(
            String.valueOf(runContiguous(elements)) +
            " " +
            String.valueOf(runNonContiguous(elements))
        );
    }
    
    public static int runContiguous(List<Integer> elements) {
        double x = Double.NEGATIVE_INFINITY;
        double xx = Double.NEGATIVE_INFINITY;
        
        for (int a_n : elements) {            
            double x_n_1 = x;
            double xx_n_1 = xx;
            
            x = Math.max(x_n_1, Math.max(xx_n_1 + a_n, a_n));
            xx = Math.max(xx_n_1 + a_n, a_n);
        }
        
        return (int) Math.max(x, xx);
    }
    
    public static int runNonContiguous(List<Integer> elements) {
        double y = Double.NEGATIVE_INFINITY;
        double yy = Double.NEGATIVE_INFINITY;
        
        for (int a_n : elements) {            
            double y_n_1 = y;
            
            y = Math.max(y_n_1, Math.max(y_n_1 + a_n, a_n));
        }
        
        return (int) y;
    }
}