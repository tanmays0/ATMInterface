import java.util.*;
class BankAccount
{
    private double balance;
    public BankAccount(double initialBalance)
    {
        if(initialBalance>=0)
        {
            this.balance=initialBalance;
        }
        else
        {
            this.balance=0;
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
        }
    }
    public double getBalance()
    {
        return balance;
    }
    public void deposit(double amount)
    {
        if(amount>0)
        {
            balance += amount;
            System.out.printf("Successfully deposited: $%.2f%n", amount);
        }
        else
        {
            System.out.println("Deposit amount must be positive.");
        }
    }
    public boolean withdraw(double amount)
    {
        if(amount>0 && amount<=balance)
        {
            balance -= amount;
            System.out.printf("Successfully withdrew: $%.2f%n", amount);
            return true;
        }
        else if(amount>balance)
        {
            System.out.println("Insufficient balance for this withdrawal.");
            return false;
        }
        else
        {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
    }
}
class ATM
{
    private BankAccount account;
    private Scanner scanner;
    public ATM(BankAccount account)
    {
        this.account=account;
        this.scanner=new Scanner(System.in);
    }
    public void displayMenu()
    {
        while(true)
        {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            int choice=getUserChoice();
            switch(choice)
            {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private int getUserChoice()
    {
        while(true)
        {
            try
            {
                return Integer.parseInt(scanner.nextLine());
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    private void checkBalance()
    {
        System.out.printf("Your current balance is: $%.2f%n", account.getBalance());
    }
    private void deposit()
    {
        System.out.print("Enter amount to deposit: $");
        double amount=getPositiveAmount();
        account.deposit(amount);
    }
    private void withdraw()
    {
        System.out.print("Enter amount to withdraw: $");
        double amount=getPositiveAmount();
        account.withdraw(amount);
    }
    private double getPositiveAmount()
    {
        while(true)
        {
            try
            {
                double amount=Double.parseDouble(scanner.nextLine());
                if(amount>0)
                {
                    return amount;
                }
                else
                {
                    System.out.println("Amount must be positive. Please try again.");
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
public class ATMInterface
{
    public static void main(String[]args)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter initial balance for your account: $");
        double initialBalance = 0;
        while(true)
        {
            try
            {
                initialBalance=Double.parseDouble(scanner.nextLine());
                if(initialBalance>=0)
                {
                    break;
                }
                else
                {
                    System.out.println("Initial balance cannot be negative. Please enter a valid amount.");
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please enter a valid number.");
            }
        }
        BankAccount account=new BankAccount(initialBalance);
        ATM atm=new ATM(account);
        atm.displayMenu();
        scanner.close();
    }
}