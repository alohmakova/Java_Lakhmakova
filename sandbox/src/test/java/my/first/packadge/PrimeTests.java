package my.first.packadge;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

    @Test(enabled = false)
    public void testPrime(){
        Assert.assertTrue (Primes.isPrime(Integer.MAX_VALUE));
    }//Тест отработал за 9 sec
    @Test(enabled = false)
    public void testNotPrime(){
        Assert.assertFalse (Primes.isPrime(Integer.MAX_VALUE - 2));
    }//Тест отработал за 24 ms

    @Test(enabled = false)
    public void testPrimeWhile1(){
        Assert.assertTrue (Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test (enabled = false)//Указание на то что этот тест не запускать. Тест отработал за 29 sec, так как более длинное число long
    public void testPrimeLong(){
        long n = Integer.MAX_VALUE;
        Assert.assertTrue (Primes.isPrime(n));
    }

    @Test
    public void testPrimeFast(){
        Assert.assertTrue (Primes.isPrimeFast(Integer.MAX_VALUE));
    }//Тест отработал за 24 ms

}
