package com;

class InvalidNameException extends Exception {
    public InvalidNameException(String msg) { super(msg); }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String msg) { super(msg); }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) { super(msg); }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String msg) { super(msg); }
}
class InvalidInputTypeException extends Exception {
    public InvalidInputTypeException(String msg) { super(msg); }
}
