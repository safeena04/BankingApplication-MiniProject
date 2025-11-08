package com;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountManager manager = new AccountManager();
        BankService service = new BankService();

        while (true) {
            System.out.println("\n===== Welcome to your Banking Application =====");
            System.out.println("Do you have an existing account?");
            System.out.println("1. Yes");
            System.out.println("2. No (Create New Account)");
            System.out.println("3. Exit");
            System.out.print("Choose your option: ");

            int option = 0;
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input!  Please select a valid option!");
                sc.nextLine();
                continue;
            }

            try {
                switch (option) {
                    case 1: {
                        System.out.print("Enter your Account Number: ");
                        String accNo = sc.nextLine();

                        Account account;
                        try {
                            account = manager.getAccount(accNo);
                        } catch (AccountNotFoundException e) {
                            System.out.print("Error: " + e.getMessage());
                            System.out.println("Enter valid account number!");
                            break;
                        }

                        System.out.println("Welcome Back, " + account.getHolderName() + "!");

                        boolean chooseToContinue = true;
                        while (chooseToContinue) {
                            System.out.println("\n----- Select a operation -----");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Transfer");
                            System.out.println("4. Check Balance");
                            System.out.println("5. Exit");
                            System.out.print("Enter your option: ");

                            int op = 0;
                            try {
                                op = sc.nextInt();
                                sc.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input! Please enter a number.");
                                sc.nextLine();
                                continue;
                            }

                            try {
                                switch (op) {
                                    case 1:
                                        while (true) {
                                            try {
                                                System.out.print("Enter amount to deposit: ");
                                                if (!sc.hasNextDouble()) {
                                                    sc.next();
                                                    throw new InvalidInputTypeException("Enter amount in numbers only!");
                                                }
                                                double depAmt = sc.nextDouble();
                                                sc.nextLine();
                                                service.deposit(account, depAmt);
                                                System.out.println("Deposit successful!");
                                                break; // exit deposit loop
                                            } catch (InvalidInputTypeException e) {
                                                System.out.println(e.getMessage());
                                                System.out.println("Now please resume to your option......");
                                            }
                                        }
                                        break;

                                    case 2:
                                        while (true) {
                                            try {
                                                System.out.print("Enter amount to withdraw: ");
                                                if (!sc.hasNextDouble()) {
                                                    sc.next();
                                                    throw new InvalidInputTypeException("Enter amount in numbers only!");
                                                }
                                                double wdAmt = sc.nextDouble();
                                                sc.nextLine();
                                                service.withdraw(account, wdAmt);
                                                System.out.println("Withdrawal successful!");
                                                break;
                                            } catch (InvalidInputTypeException e) {
                                                System.out.println(e.getMessage());
                                                System.out.println("Now please resume to your option......");
                                            } catch (InsufficientBalanceException e) {
                                                System.out.println("Error: " + e.getMessage());
                                                break;
                                            }
                                        }
                                        break;

                                    case 3:
                                        while (true) {
                                            System.out.print("Enter destination account number: ");
                                            String destAccNo = sc.nextLine();
                                            Account destAccount = null;

                                            try {
                                                destAccount = manager.getAccount(destAccNo);
                                            } catch (AccountNotFoundException e) {
                                                System.out.println("Error: " + e.getMessage());
                                                System.out.println("Enter valid destination account number!");
                                                continue;
                                            }

                                            boolean validAmount = false;
                                            while (!validAmount) {
                                                System.out.print("Enter amount to transfer: ");
                                                if (!sc.hasNextDouble()) {
                                                    sc.next();
                                                    System.out.println("Enter amount in numbers only!");
                                                    System.out.println("Now please resume to your option......");
                                                    continue;
                                                }

                                                double amt = sc.nextDouble();
                                                sc.nextLine();

                                                try {
                                                    service.transfer(account, destAccount, amt);
                                                    System.out.println("Transfer successful!");
                                                    validAmount = true;
                                                } catch (InsufficientBalanceException e) {
                                                    System.out.println("Error: " + e.getMessage());
                                                    System.out.println("Please check balance and Enter amount less than your balance!!");
                                                    continue;
                                                }
                                            }

                                            break;
                                        }
                                        break;




                                    case 4:
                                        System.out.println("Account Details: " + account);
                                        break;

                                    case 5:
                                        System.out.println("\nThank you, " + account.getHolderName() + "!");
                                        System.out.println("Your Current Balance: " + account.getBalance());
                                        System.out.println("Thanks for using our Banking Services!");
                                        chooseToContinue = false;
                                        break;

                                    default:
                                        System.out.println("Invalid option! Try again.");
                                }
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                                System.out.print("Enter amount in numbers!!");
                                System.out.println("Now please resume to your option......");
                            }

                            if (chooseToContinue) {
                                System.out.print("\nDo you want to continue with your account? (yes/no): ");
                                String again = sc.nextLine();
                                if (!again.equalsIgnoreCase("yes")) {
                                    System.out.println("\nThank you, " + account.getHolderName() + "!");
                                    System.out.println("Your Current Balance: " + account.getBalance());
                                    System.out.println("Thanks for choosing us!!");
                                    chooseToContinue = false;
                                }
                            }
                        }
                        break;
                    }

                    case 2: {
                        System.out.print("Enter your full name: ");
                        String name = sc.nextLine();
                        Account acc = manager.createAccount(name);
                        System.out.println("Account created successfully!");
                        System.out.println("Your Account Number: " + acc.getAccountNumber());
                        System.out.println("Your Current Balance: " + acc.getBalance());

                        System.out.print("Would you like to deposit an initial amount? (yes/no): ");
                        String ans = sc.nextLine();
                        if (ans.equalsIgnoreCase("yes")) {
                            while (true) {
                                try {
                                    System.out.print("Enter amount to deposit: ");
                                    if (!sc.hasNextDouble()) {
                                        sc.next();
                                        throw new InvalidInputTypeException("Enter amount in numbers only!");
                                    }
                                    double amt = sc.nextDouble();
                                    sc.nextLine();
                                    service.deposit(acc, amt);
                                    System.out.println("Initial deposit successful!");
                                    break;
                                } catch (InvalidInputTypeException e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Please continue exploring our services!");
                                }
                            }
                        }

                        System.out.println("\nAccount Setup Complete!");
                        System.out.println("You can now log in using your Account Number.");
                        break;
                    }

                    case 3:
                        System.out.println("\nThank you for visiting our bank!");
                        System.out.println("We hope to serve you again soon!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please continue with our services.");
            }
        }
    }
}
