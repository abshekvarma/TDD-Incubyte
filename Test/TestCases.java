import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import service.AdditionImpl;
import service.IAddition;

public class TestCases {

    IAddition addition;

    @Before
    public void setUp() {
        addition = new AdditionImpl();
    }

    @Test
    public void Test1_SuccessWithTwoNumbers(){
        assertEquals(addition.sum("1,2"), 3);
    }

    @Test
    public void Test2_SuccessWithThreeNumbers(){
        assertEquals(addition.sum("1,2,3"), 6);
    }

    @Test
    public void Test3_SuccessWithOneNumber(){
        assertEquals(addition.sum("1"), 0);
    }

    @Test
    public void Test4_SuccessWithEmptyString(){
        assertEquals(addition.sum(""), 0);
    }

    @Test
    public void Test5_SuccessWithMultipleDelim(){
        assertEquals(addition.sum("1\n2,3"), 6);
    }

    @Test
    public void Test6_SuccessWithMultipleDelim(){
        assertEquals(addition.sum("1,\n"), 0);
    }

}