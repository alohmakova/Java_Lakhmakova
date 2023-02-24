package my.first.packadge;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTests {
    @Test
    public void testEquation0() {

        Equation e = new Equation(1,1,1);
        Assert.assertEquals(e.rootNumber(), 0);
    }
    @Test
    public void testEquation1() {

        Equation e = new Equation(1,2,1);
        Assert.assertEquals(e.rootNumber(), 1);
    }
    @Test
    public void testEquation2() {

        Equation e = new Equation(1,5,6);
        Assert.assertEquals(e.rootNumber(), 2);
    }
}
