import java.util.Scanner;


class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    // Constructor
    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(" Deposited: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println(" Deposit amount must be positive!");
        }
    }

    // withdraw
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println(" Withdrawal amount must be positive!");
        } else if (amount > balance) {
            System.out.println(" Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println(" Withdrawn: " + amount + " | Remaining Balance: " + balance);
        }
    }

    // display
    public void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance        : " + balance);
        System.out.println("Email          : " + email);
        System.out.println("Phone Number   : " + phoneNumber);
    }

    // update
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println(" Contact details updated successfully!");
    }

    // getter function

    public int getAccountNumber() {
        return accountNumber;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Account[] accounts = new Account[100];
        int accountCount = 0;

        int choice = 0;
        do {
            System.out.println("\n### Banking Application ###");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(" Invalid input! Please enter numbers only.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1: // Create Account

                    System.out.print("Enter account holder name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter initial deposit amount: ");
                    double balance = sc.nextDouble();
                    sc.nextLine();

                    if (balance < 0) {

                        System.out.println(" Initial deposit cannot be negative!");
                        break;
                    }

                    System.out.print("Enter email address: ");
                    String email = sc.nextLine();

                    System.out.print("Enter phone number: ");
                    String phone = sc.nextLine();

                    int accNumber = 1000 + accountCount + 1;
                    accounts[accountCount] = new Account(accNumber, name, balance, email, phone);
                    accountCount++;

                    System.out.println(" Account created successfully! Account Number: " + accNumber);
                    break;

                case 2:// Deposit
                    System.out.print("Enter account number: ");
                    int depAccNo = sc.nextInt();
                    System.out.print("Enter deposit amount: ");
                    double depAmount = sc.nextDouble();
                    sc.nextLine();

                    Account depositAcc = findAccount(accounts, accountCount, depAccNo);
                    if (depositAcc != null) {
                        depositAcc.deposit(depAmount);
                    } else {
                        System.out.println(" Account not found!");
                    }
                    break;

                case 3: // Withdraw
                    System.out.print("Enter account number: ");
                    int wAccNo = sc.nextInt();
                    System.out.print("Enter withdrawal amount: ");
                    double wAmount = sc.nextDouble();
                    sc.nextLine();

                    Account withdrawAcc = findAccount(accounts, accountCount, wAccNo);
                    if (withdrawAcc != null) {
                        withdrawAcc.withdraw(wAmount);
                    } else {
                        System.out.println(" Account not found!");
                    }
                    break;

                case 4: // display details
                    System.out.print("Enter account number: ");
                    int dAccNo = sc.nextInt();
                    sc.nextLine();

                    Account detailAcc = findAccount(accounts, accountCount, dAccNo);
                    if (detailAcc != null) {
                        detailAcc.displayAccountDetails();
                    } else {
                        System.out.println(" Account not found!");
                    }
                    break;

                case 5: // Update contact
                    System.out.print("Enter account number: ");
                    int uAccNo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new email: ");
                    String newEmail = sc.nextLine();

                    System.out.print("Enter new phone number: ");
                    String newPhone = sc.nextLine();

                    Account updateAcc = findAccount(accounts, accountCount, uAccNo);
                    if (updateAcc != null) {
                        updateAcc.updateContactDetails(newEmail, newPhone);
                    } else {
                        System.out.println(" Account not found!");
                    }
                    break;

                case 6:// exiting
                    System.out.println(" Thank you for using Banking Application!");
                    break;

                default:
                    System.out.println(" Invalid choice! Try again.");
            }
        } while (choice != 6);

        sc.close();
    }

    // method to search account by number
    private static Account findAccount(Account[] accounts, int count, int accNo) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountNumber() == accNo) {
                return accounts[i];
            }
        }
        return null;
    }
}
