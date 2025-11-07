package com;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountManager manager = new AccountManager();
        BankService service = new BankService();

        while (true) {
            System.out.println("\n===== Welcome to Banking System =====");
            System.out.println("Do you have an existing account?");
            System.out.println("1. Yes");
            System.out.println("2. No (Create New Account)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // clear buffer
                continue;
            }

            try {
                switch (choice) {
                    case 1: {
                        System.out.print("Enter your Account Number: ");
                        String accNo = sc.nextLine();

                        Account account;
                        try {
                            account = manager.getAccount(accNo);
                        } catch (AccountNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                            System.out.println("Please continue your operations.");
                            break;
                        }

                        System.out.println("Welcome, " + account.getHolderName() + "!");

                        boolean continueOps = true;
                        while (continueOps) {
                            System.out.println("\n----- Account Operations -----");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Transfer");
                            System.out.println("4. Show Balance");
                            System.out.println("5. Exit");
                            System.out.print("Enter your choice: ");

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
                                        System.out.print("Enter amount to deposit: ");
                                        double depAmt = sc.nextDouble();
                                        sc.nextLine();
                                        service.deposit(account, depAmt);
                                        System.out.println("Deposit successful!");
                                        break;

                                    case 2:
                                        System.out.print("Enter amount to withdraw: ");
                                        double wdAmt = sc.nextDouble();
                                        sc.nextLine();
                                        service.withdraw(account, wdAmt);
                                        System.out.println("Withdrawal successful!");
                                        break;

                                    case 3:
                                        System.out.print("Enter destination account number: ");
                                        String destAccNo = sc.nextLine();
                                        System.out.print("Enter amount to transfer: ");
                                        double amt = sc.nextDouble();
                                        sc.nextLine();
                                        Account destAccount = manager.getAccount(destAccNo);
                                        service.transfer(account, destAccount, amt);
                                        System.out.println("Transfer successful!");
                                        break;

                                    case 4:
                                        System.out.println("Account Details: " + account);
                                        break;

                                    case 5:
                                        System.out.println("\nThank you, " + account.getHolderName() + "!");
                                        System.out.println("Your Final Balance: ₹" + account.getBalance());
                                        System.out.println("Thanks for using our Bank Services!");
                                        continueOps = false;
                                        break;

                                    default:
                                        System.out.println("Invalid choice! Try again.");
                                }
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                                System.out.println("Please continue your operations.");
                            }

                            if (continueOps) {
                                System.out.print("\nDo you want to continue with your account? (yes/no): ");
                                String again = sc.nextLine();
                                if (!again.equalsIgnoreCase("yes")) {
                                    System.out.println("\nThank you, " + account.getHolderName() + "!");
                                    System.out.println("Your Final Balance: ₹" + account.getBalance());
                                    System.out.println("Thanks for using our Bank Services!");
                                    continueOps = false;
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
                        System.out.println("Your Current Balance: ₹" + acc.getBalance());

                        // Ask for initial deposit
                        System.out.print("Would you like to deposit an initial amount? (yes/no): ");
                        String ans = sc.nextLine();
                        if (ans.equalsIgnoreCase("yes")) {
                            try {
                                System.out.print("Enter amount to deposit: ");
                                double amt = sc.nextDouble();
                                sc.nextLine();
                                service.deposit(acc, amt);
                                System.out.println("Initial deposit successful!");
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                                System.out.println("Please continue your operations.");
                                sc.nextLine();
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
                System.out.println("Please continue your operations.");
            }
        }
    }
}
