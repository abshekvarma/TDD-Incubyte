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
        assert addition.Add("1,2") == 3;
    }

    @Test
    public void Test2_SuccessWithThreeNumbers() throws Exception {
        assert addition.Add("1,2,3") == 6;
    }

    @Test
    public void Test3_SuccessWithOneNumber() throws Exception {
        assert addition.Add("1") == 0;
    }

    @Test
    public void Test4_SuccessWithEmptyString() throws Exception {
        assert addition.Add("") == 0;
    }

    @Test
    public void Test5_SuccessWithMultipleDelim() throws Exception {
        assert addition.Add("1\n2,3") == 6;
    }

    @Test
    public void Test6_SuccessWithMultipleDelim() throws Exception {
        assert addition.Add("1,\n") == 0;
    }

    @Test
    public void Test7_SuccessWithOptionalDelim() throws Exception {
        assert addition.Add("//;\n1;2") == 3;
    }

    @Test
    public void Test8_SuccessWithOptionalDelim() throws Exception {
        assert addition.Add(";\n1;2") == 3;
    }

    @Test
    public void Test9_FailWithNegativeNumber() throws Exception {
        Exception exception = assertThrows(
                NegativeNumberException.class,
                () -> addition.Add(";\n1;-3"));
        assertTrue(exception.getMessage().contains("negatives not allowed, Numbers are [-3]"));
    }

    @Test
    public void Test10_FailWithNegativeNumbers() throws Exception {
        Exception exception = assertThrows(
                NegativeNumberException.class,
                () -> addition.Add(";\\n1;-3;-4"));
        assertTrue(exception.getMessage().contains("negatives not allowed, Numbers are [-3, -4]"));
    }

    @Test
    public void Test11_SuccessIgnoreLargerNumber() throws Exception {
        assert addition.Add("2+1001") == 2;
    }

    @Test
    public void Test12_SuccessAnyLengthDelim() throws Exception {
        assert addition.Add("//[***]\n1***2***3") == 6;
    }

    @Test
    public void Test13_SuccessMultipleDelim() throws Exception {
        assert addition.Add("//[*][%]\n1*2%3") == 6;
    }

}