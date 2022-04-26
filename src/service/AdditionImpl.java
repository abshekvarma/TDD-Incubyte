package service;

public class AdditionImpl implements IAddition {
    @Override
    public int sum(String numbers) {
        String[] integers = numbers.split(",");
        int sum = 0;
        if (integers.length == 1) {
            return 0;
        }
        for (int i = 0; i < integers.length; i++)
            sum += Integer.parseInt(integers[i]);
        return sum;
    }
}