import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.example.Calc;

public class AppTest {
    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");
    }

    @BeforeEach
    void setupThis(){
        System.out.println("@BeforeEach executed");
    }

    @Tag("DEV")
    @Test
    void testCalcCount()
    {
        System.out.println("======TEST wrong count of args=======");
        Calc sum1 = new Calc();
        String args[] = {"10", "+", "15", "="};
        Assertions.assertEquals( "usage: arg1 + arg2" , sum1.calculate(args));
    }

    @Tag("DEV")
    @Test
    void testCalcFirst()
    {
        System.out.println("======TEST wrong first arg=======");
        Calc sum1 = new Calc();
        String args[] = {"a", "-", "15"};
        Assertions.assertEquals( "usage: arg1 + arg2" , sum1.calculate(args));
    }

    @Tag("PROD")
    @Test
    void testCalcSecond()
    {
        System.out.println("======TEST wrong second arg=======");
        Calc sum1 = new Calc();
        String args[] = {"10", "-", "15"};
        Assertions.assertEquals( "usage: arg1 + arg2" , sum1.calculate(args));
    }

    @Tag("DEV")
    @Test
    void testCalcThird()
    {
        System.out.println("======TEST wrong third arg=======");
        Calc sum1 = new Calc();
        String args[] = {"10", "+", "b"};
        Assertions.assertEquals( "usage: arg1 + arg2" , sum1.calculate(args));
    }

    @Tag("DEV")
    @Test
    void testCalcValid()
    {
        System.out.println("======TEST wrong third arg=======");
        Calc sum1 = new Calc();
        String args[] = {"10", "+", "15"};
        Assertions.assertEquals( "25" , sum1.calculate(args));
    }

    @AfterEach
    void tearThis(){
        System.out.println("@AfterEach executed");
    }

    @AfterAll
    static void tear(){
        System.out.println("@AfterAll executed");
    }
}
