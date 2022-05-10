package service;

import exception.InvalidInputException;
import exception.NegativeNumberException;

import java.util.ArrayList;
import java.util.List;

public class AdditionImpl implements IAddition {

    boolean flagMultiply = false;
    @Override
    public int Add(String numbers) throws Exception {
        if(numbers.isEmpty()){
            return 0;
        }
        String[] integers = delimitersValidations(numbers);
        List<Integer> negativeNums = new ArrayList<>();
        int sum = 0, multiply =1;
        for (int i = 0; i < integers.length; i++)
            try {
                int val = Integer.parseInt(integers[i]);
                if (val < 0) {
                    negativeNums.add(val);
                    throw new NegativeNumberException("negatives not allowed");
                }
                if (val > 1000) {
                    continue;
                }
                if(flagMultiply){
                    multiply *= val;
                }else{
                    sum += val;
                }
            } catch (NegativeNumberException e) {
                if (i + 1 == integers.length) {
                    throw new NegativeNumberException(e.getMessage() + ", Numbers are " + negativeNums);
                }
            }
        if(flagMultiply){
            return multiply;
        }
        return sum;
    }

    private String[] delimitersValidations(String numbers) throws InvalidInputException {
        String delimeters = ",";
        if (numbers.startsWith("//")) {
            String[] num = numbers.split("\n");
            delimeters = num[0];
            if (num.length > 2) {
                StringBuilder numbersBuilder = new StringBuilder();
                for (int i = 1; i < num.length; i++) {
                    numbersBuilder.append(num[i]);
                    numbersBuilder.append(delimeters.charAt(1));
                }
                numbers = numbersBuilder.toString();
            } else {
                numbers = num[1];
            }
        }else if(numbers.endsWith("\n")){
            throw new InvalidInputException("Invalid Input");
        }
        if(delimeters.contains("*")){
            flagMultiply = true;
        }
        return numbers.split("["+delimeters+"\n]+");
    }
}