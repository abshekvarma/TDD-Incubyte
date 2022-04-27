import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import service.AdditionImpl;
import service.IAddition;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestCases {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    IAddition addition;

    @Before
    public void setUp() {
        addition = new AdditionImpl();
        System.setOut(new PrintStream(outputStreamCaptor));
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

    @Test
    public void Test7_SuccessWithOptionalDelim(){
        assertEquals(addition.sum("//;\n1;2"), 3);
    }

    @Test
    public void Test8_SuccessWithOptionalDelim(){
        assertEquals(addition.sum(";\n1;2"), 3);
    }

    @Test
    public void Test9_FailWithNegativeNumber(){
        assertEquals(addition.sum(";\n1;-3"), 0);
        assertEquals("negatives not allowed, Numbers are [-3]", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void Test10_FailWithNegativeNumbers(){
        assertEquals(addition.sum(";\n1;-3;-4"), 0);
        assertEquals("negatives not allowed, Numbers are [-3, -4]", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void Test11_SuccessIgnoreLargerNumber(){
        assertEquals(addition.sum("2+1001"), 2);
    }

    @Test
    public void Test12_SuccessAnyLengthDelim(){
        assertEquals(addition.sum("//[***]\n1***2***3"), 6);
    }

}