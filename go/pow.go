package main

import "os"
import "strconv"

func main() {
    var args = os.Args[1:]
    
    if len(args) != 2 {
        println("pow requires two arguments: x and n, returns x^n")
        os.Exit(1)
    }
    
    x, _ := strconv.ParseFloat(args[0], 64)
    n, _ := strconv.Atoi(args[1])
    
    println(strconv.FormatFloat(myPow(x, n), 'f', 8, 64))
}

func myPow(x float64, n int) float64 {
    // Base cases
    if n == -1 {
        return 1 / x
    } else if n == 0 {
        return 1
    } else if n == 1 {
        return x
    } else {
        // Recursively break down the exponent
        var halfpow float64 = myPow(x, n / 2)
        var modpow float64 = myPow(x, n % 2)
        return halfpow * halfpow * modpow
    }
}