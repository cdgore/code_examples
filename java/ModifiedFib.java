import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {
    
    private static class FibRunner {
        private final BigInteger a;
        private final BigInteger b;
        private final int n;
        
        public FibRunner(int a, int b, int n) {
            this.a = BigInteger.valueOf(new Integer(a).longValue());
            this.b = BigInteger.valueOf(new Integer(b).longValue());
            this.n = n;
        }
        
        public void runFib() {
            runFibAccum(this.a, this.b, 3);
        }
        
        private void runFibAccum(BigInteger aAccum, BigInteger bAccum, int i) {
            BigInteger nextVal = aAccum.add(bAccum.pow(2));
                        
            if (i == this.n) {
                // base case
                System.out.println(nextVal.toString());
            } else {
                this.runFibAccum(bAccum, nextVal, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        
        int a = sc.nextInt();
        int b = sc.nextInt();
        int n = sc.nextInt();
        
        FibRunner fibRunner = new FibRunner(a, b, n);
        fibRunner.runFib();
    }
}