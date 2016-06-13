import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static class Stocks {
        private static List<Integer> prices;
        // The next highest number
        private static List<Long> nextMax;
        private static List<Long> stepsUntilMax;
        
        public Stocks(List<Integer> prices) {
            List<Long> tmpList = new ArrayList<>();
            List<Long> tmpList2 = new ArrayList<>();
            List<Integer> tmpPrices = new ArrayList<>(prices);
            Collections.reverse(tmpPrices);
            long tmpMax = -100;
            long sUntilMax = 0;
            
            for (Integer p: tmpPrices) {
                
                tmpList2.add(sUntilMax);
                tmpList.add(tmpMax);
                
                if (p > tmpMax) {
                    tmpMax = (long)p;
                    sUntilMax = (long)0;
                }
                
                sUntilMax++;
            }
            
            Collections.reverse(tmpList);
            Collections.reverse(tmpList2);
            
            this.nextMax = new ArrayList<>(tmpList);
            this.prices = prices;
            this.stepsUntilMax = tmpList2;
            
            /*
            System.out.println(this.nextMax);
            System.out.println(this.prices);
            System.out.println(this.stepsUntilMax);
            */
        }
        
        /*
        [1, 3, 1, 2]
        [3, 3, 2, 2]
        [1, 0, 1, 0]
        
        k=1
        c=1
        tp=-1;
        */
        
        public static long getMax() {
            long k = 0;// num stocks currently purchased
            long c = 0;// sum of stocks currently purchased
            long totalP = 0;
            for (int i=0; i<prices.size(); i++) {
                long p = (long)prices.get(i);
                long curNextMax = nextMax.get(i);
                long curStepsUntilMax = stepsUntilMax.get(i);
                
                long buy = (k + curStepsUntilMax) * curNextMax - (c + p);
                long doNothing = (k + curStepsUntilMax - 1) * curNextMax - c;
                long sellAll = k * p + (curStepsUntilMax - 1) * curNextMax - c;
                
                /*
                System.out.println("buy: " + buy);
                System.out.println("doNothing: " + doNothing);
                System.out.println("sellAll: " + sellAll);
                */
                
                if (buy > Math.max(doNothing, sellAll)) {
                    // buy 1 stock
                    totalP -= p;
                    k++;
                    c += p;
                    
                    // System.out.println("*** buy: " + i);
                } else if(sellAll > Math.max(doNothing, buy)) {
                    // sell all stocks
                    totalP += k * p;
                    k = 0;
                    c = 0;
                    
                    // System.out.println("*** sell: " + i);
                }
            }
            
            return totalP;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int numExamples = sc.nextInt();
        for (int i=0; i<numExamples; i++) {
            
            int numPrices = sc.nextInt();
            List<Integer> stockPrices = new ArrayList<>();
            for (int j=0; j<numPrices; j++) {
                stockPrices.add(sc.nextInt());
            }
            
            Stocks stocks = new Stocks(stockPrices);
            System.out.println(stocks.getMax());
        }
    }
}