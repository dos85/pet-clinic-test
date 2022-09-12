import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.example.Calc;

public class CalcTest {
    Calc it;
    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll executed");
    }

    @BeforeEach
    void setupThis() {
        System.out.println("@BeforeEach executed");
        it = new Calc();
    }

    @Tag("DEV")
    @Test
    void itFailsFourArgs()
    {
        System.out.println("======TEST wrong count of args=======");
        String args[] = {"10", "+", "15", "="};
        Assertions.assertEquals( "usage: arg1 + arg2" , it.calculate(args));
    }

    @Tag("DEV")
    @Test
    void testCalcFirst()
    {
        System.out.println("======TEST wrong first arg=======");
        String args[] = {"a", "-", "15"};
        Assertions.assertEquals( "usage: arg1 + arg2" , it.calculate(args));
    }

    @Tag("PROD")
    @Test
    void testCalcSecond()
    {
        System.out.println("======TEST wrong second arg=======");
        String args[] = {"10", "-", "15"};
        Assertions.assertEquals( "usage: arg1 + arg2" , it.calculate(args));
    }

    @Tag("DEV")
    @Test
    void testCalcThird()
    {
        System.out.println("======TEST wrong third arg=======");
        String args[] = {"10", "+", "b"};
        Assertions.assertEquals( "usage: arg1 + arg2" , it.calculate(args));
    }

    @Tag("DEV")
    @Test
    void testCalcValid()
    {
        System.out.println("======TEST wrong third arg=======");
        String args[] = {"10", "+", "15"};
        Assertions.assertEquals( "25" , it.calculate(args));
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
