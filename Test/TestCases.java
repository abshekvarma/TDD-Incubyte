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
    public void Test1SuccessWithTwoNumbers(){
        assertEquals(addition.sum("1,2"), 3);
    }

    @Test
    public void Test1SuccessWithOneNumber(){
        assertEquals(addition.sum("1"), 0);
    }

    @Test
    public void Test2SuccessWithEmptyString(){
        assertEquals(addition.sum(""), 0);
    }

}