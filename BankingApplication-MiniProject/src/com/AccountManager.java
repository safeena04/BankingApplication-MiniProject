package com;
import java.util.*;

public class AccountManager {
    private Map<String, Account> accounts = new HashMap<>();

    public Account createAccount(String name) throws InvalidNameException {
        if (name == null || name.trim().isEmpty())
            throw new InvalidNameException("Name cannot be empty.");

        String accNo = name.substring(0, 2).toUpperCase() + (int)(Math.random() * 10000);
        Account acc = new Account(name, accNo);
        accounts.put(accNo, acc);
        return acc;
    }

    public Account getAccount(String accNo) throws AccountNotFoundException {
        if (!accounts.containsKey(accNo))
            throw new AccountNotFoundException("Account not found!");
        return accounts.get(accNo);
    }

    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }
}
