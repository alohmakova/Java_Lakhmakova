package my.first.packadge;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    //в данном тесте в ожидаемом результате указан тип данных, который не соответствует актуальному результату
    @Test
    public void testPoint1() {

        Point p1 = new Point(5,5);
        Point p2 = new Point(5,0);
        Assert.assertEquals(p1.distance(p2), 5);
    }
    //тест должен пройти, так как ожидаемый результат соответствует актуальному
    @Test
    public void testPoint2() {

        Point p1 = new Point(5,5);
        Point p2 = new Point(5,0);
        Assert.assertEquals(p1.distance(p2), 5.0);
    }
    //в данном тесте указан неверный второй параметр для функции distance, поэтому тест должен провалиться
    @Test
    public void testPoint3() {

        Point p1 = new Point(5,5);
        Point p2 = new Point(5,0);
        Assert.assertEquals(p1.distance(p1), 5.0);
    }

}
