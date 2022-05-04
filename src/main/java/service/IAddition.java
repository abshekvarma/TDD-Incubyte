package service;

import exception.NegativeNumberException;

public interface IAddition {
    int Add(String numbers) throws NegativeNumberException;
}