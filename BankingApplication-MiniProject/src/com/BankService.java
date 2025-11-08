package com;

public class BankService {
    public void deposit(Account acc, double amt) throws InvalidAmountException {
        if (amt <= 0) throw new InvalidAmountException("Amount must be greater than 0.!");
        acc.deposit(amt);
    }

    public void withdraw(Account acc, double amt)
            throws InvalidAmountException, InsufficientBalanceException {
        if (amt <= 0) throw new InvalidAmountException("Amount must be greater than 0.!");
        if (amt > acc.getBalance()) throw new InsufficientBalanceException("Not enough balance!");
        acc.withdraw(amt);
    }

    public void transfer(Account src, Account dest, double amt)
            throws InvalidAmountException, InsufficientBalanceException {
        withdraw(src, amt);
        dest.deposit(amt);
    }
}
