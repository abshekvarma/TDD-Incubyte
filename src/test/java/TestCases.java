import exception.NegativeNumberException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.AdditionImpl;
import service.IAddition;

import static org.junit.jupiter.api.Assertions.*;


public class TestCases {

    static IAddition addition;

    @BeforeAll
    static void setUp() {
        addition = new AdditionImpl();
    }

    @Test
    public void Test1_SuccessWithTwoNumbers() throws Exception {
        assertEquals(3, addition.Add("1,2"));
    }

    @Test
    public void Test2_SuccessWithThreeNumbers() throws Exception {
        assertEquals(6, addition.Add("1,2,3"));
    }

    @Test
    public void Test3_SuccessWithOneNumber() throws Exception {
        assertEquals(0, addition.Add("1"));
    }

    @Test
    public void Test4_SuccessWithEmptyString() throws Exception {
        assertEquals(0, addition.Add(""));
    }

    @Test
    public void Test5_SuccessWithMultipleDelim() throws Exception {
        assertEquals(6, addition.Add("1\n2,3"));
    }

    @Test
    public void Test6_SuccessWithMultipleDelim() throws Exception {
        assertEquals(0, addition.Add("1,\n"));
    }

    @Test
    public void Test7_SuccessWithOptionalDelim() throws Exception {
        assertEquals(3, addition.Add("//;\n1;2"));
    }

    @Test
    public void Test8_FailWithNegativeNumbers() throws Exception {
        Exception exception = assertThrows(
                NegativeNumberException.class,
                () -> addition.Add("1,-2,-3"));
        assertTrue(exception.getMessage().contains("negatives not allowed, Numbers are [-2, -3]"));
    }

    @Test
    public void Test9_FailWithNegativeNumber() throws Exception {
        Exception exception = assertThrows(
                NegativeNumberException.class,
                () -> addition.Add("//;\n1;-3"));
        assertTrue(exception.getMessage().contains("negatives not allowed, Numbers are [-3]"));
    }

    @Test
    public void Test10_FailWithNegativeNumbers() throws Exception {
        Exception exception = assertThrows(
                NegativeNumberException.class,
                () -> addition.Add("//;\n1;-3;-4"));
        assertTrue(exception.getMessage().contains("negatives not allowed, Numbers are [-3, -4]"));
    }

    @Test
    public void Test11_SuccessIgnoreLargerNumber() throws Exception {
        assertEquals(2, addition.Add("2,1001"));
    }

    @Test
    public void Test12_SuccessAnyLengthDelim() throws Exception {
        assertEquals(6, addition.Add("//[***]\n1***2***3"));
    }

    @Test
    public void Test13_SuccessMultipleDelim() throws Exception {
        assertEquals(6, addition.Add("//[*][%]\n1*2%3"));
    }

    @Test
    public void Test14_SuccessMultipleDelim() throws Exception {
        assertEquals(6, addition.Add("//[*][%]\n1*2\n3"));
    }

}