package my.first.packadge;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    //проверяю, что результат должен быть одинаковый, независимо от того, положительные или отрицательные значения вводятся:
    // точки перемещаются в другую часть системы координат, но расстояние от этого между ними не меняется
    @Test
    public void testPoint1() {

        Point p1 = new Point(8,15);
        Point p2 = new Point(4,9);
        Point p3 = new Point(-8,-15);
        Point p4 = new Point(-4,-9);
        Assert.assertEquals(p1.distance(p2), p3.distance(p4));
    }
    //проверка, что расчёты программой производятся правильно
    @Test
    public void testPoint2() {

        Point p1 = new Point(5,5);
        Point p2 = new Point(5,0);
        Assert.assertEquals(p1.distance(p2), 5.0);
    }
    //в данном тесте проверяем, что расстояние от точки до самой себя = 0
    @Test
    public void testPoint3() {

        Point p1 = new Point(3,3);
        Point p2 = new Point(3,3);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }

}
