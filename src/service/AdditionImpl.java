package service;

import exception.NegativeNumberException;

import java.util.ArrayList;
import java.util.List;

public class AdditionImpl implements IAddition {
    @Override
    public int sum(String numbers) {
        String[] integers = numbers.split("[//]*[[***]]*[*,+;\n]");
        List<Integer> negativeNums = new ArrayList<>();
        int sum = 0;
        if (integers.length == 1) {
            return 0;
        }
        for (int i = 0; i < integers.length; i++)
            try {
                int val = Integer.parseInt(integers[i]);
                if(val<0){
                    negativeNums.add(val);
                    throw new NegativeNumberException("negatives not allowed");
                }if(val>1000){
                    continue;
                }
                sum+=val;
            }catch(NegativeNumberException e){
                sum=0;
                if(i+1==integers.length){
                    System.out.println(e.getMessage()+", Numbers are "+negativeNums);}
            } catch (NumberFormatException e) {
                continue;
            }
        return sum;
    }
}