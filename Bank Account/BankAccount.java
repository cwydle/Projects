import java.util.Scanner;

public class BankAccount {
    Scanner scan = new Scanner(System.in);

    private double balance;

    public BankAccount(){
        balance = 0;
    }

    public BankAccount(double initalBalance) {
        balance = initalBalance;
    }

    public void deposit(double amount){
        amount = scan.nextDouble(); 
            balance = balance + amount;
            System.out.println("Current balance: " + balance);
        }
        // 1. Overdraft method
        // The Overdraft Method should be used when I withdraw money 
        // and if I have $0 or less in my account you it will charge me a 
        // fee of $35. It should also see if the amount withdrawn puts me 
        // in the negative and prompt the user if this is okay and warn them
        // that they will be charged a fee.
    public void withdraw(double amount){
        double overdraft = 35; 
        amount = scan.nextDouble(); 

        if (amount > 0) {
            balance = balance - amount;
            if (balance < 0) {
                System.out.println("Your account will be in the negative. Are you sure you want to withdraw? There will be a $35 fee." + "\n" + "Enter 1 for 'yes' or 2 for 'no'" );
                int user = scan.nextInt(); 
                if (user == 1) {
                    balance = balance - overdraft; 
                    System.out.println("Current balance: " + balance );
                }
                if (user == 2) {
                    BankAccount.menu();  
                    // System.out.println("Would you like to return back to menu?" + "\n" + "Enter 1 for 'yes' or 2 for 'no'" );
                    // int done = scan.nextInt(); 
                    // if (done == 1) {
                    //     menu(); 
                    // }
                    // if (done == 2) {
                    //     System.out.println("Goodbye");

                    // }

            }
            else {
                System.out.println("Invalid input.");
                System.exit(0); 
            }
        }
        if (amount <= 0) {

              System.out.println("Sorry unable to add negatives/zeros into your balance.");
          }
        }
        
    }
    public double getBalance(){
        return balance;
    }
    public void monthlyFee(){
        withdraw(10);
    }

    // 2. Create a method for transfer Money 
    // Hint 1. This will require you to take in 3 parameters at min
    //     Bank Account 1, Bank Account 2, Amount to transfer
    // Hint 2. The math involved would be two folded.
    //      You need to subtract from one bank account and add to another

    public void transfer(BankAccount account1, BankAccount account2, double amount) {
        System.err.println("Which account do you want to use?");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt(); 
        if (input == 1) {
            System.out.println("How much do you want to transfer from BankAccount1?");
            //Scanner scanner = new Scanner(System.in);
            account1.withdraw(amount);
            System.out.println("How much do you want to transfer into BankAccount2?");
            account2.deposit(amount);
            System.out.println("New balance of Account 1: $"+ account1.balance);
            System.out.println("New balance of Account 2: $"+ account2.balance);
        }
        else if (input == 2) {
            System.out.println("How much do you want to transfer from BankAccount2?");
            //Scanner scanner = new Scanner(System.in);
            account2.withdraw(amount);
            System.out.println("How much do you want to transfer into BankAccount1?");
            account1.deposit(amount);
            System.out.println("New balance of Account 1: $"+ account1.balance);
            System.out.println("New balance of Account 2: $"+ account2.balance);

        }
        else {
            System.out.println("Invalid.");
            System.exit(0); 
        }

        }
        
//     4. Create a savings account option. This will accept an inital value that must
// be $1000 or more and a selection on compound or flat rate interest. The selection will 
// then ask the user for the variables they would like for instance the Principal and years
// but with fixed amounts for the apy. For the compount the APY is 0.0001 
// and for the simple interest account it is 0.001. 
// NOTE: The value of n im compounded must be 4 for quarterly 
    public void savings(double amount) {
        System.out.println("How much would you like to deposit into your savings account?");
        double savingStart = scan.nextDouble(); 
        if (savingStart >= 1000) {
            System.out.println("Current Savings Account balance: $" + savingStart);

            System.out.println("Would you like a compound(APY is 0.0001) or flat(APY is 0.001) interest rate?" + "\n" + "For compound enter a 1 and for flat enter a 2: ");
            int enter = scan.nextInt(); 
            if (enter==1) {
                System.out.println("You have chosen compound interest rate.");
                // double P = amount; 
                // double R = .0001;
                // int t = 1; 
                // int n = 4; 
                // amount = P*(1 + R/n)^(n*t) - P; 
            }
            else if (enter==2) {
                System.out.println("You have chosen flat interest rate");
                // double P = amount; 
                // double R = .001; 
                // int t = 1; 
                // amount = (p*r*t)/100; 
                // System.out.println("Your balance after 1 year is: " + amount);
            }
            else {
                System.out.println("Invalid input.");
            }
        }
        else {
            System.out.println("Sorry unable to start new savings account. Insufficient funds.");
        }

    }
    // P (1 + R/n)^(nt) - P
    // P = principal
    // t = years
    // r = annual interest rate
    // n = # of times its compounded
    // PEMDAS
    public void calcInterest(double p, int t, double r, int n){
        double amount = p * Math.pow(1 + (r / n), n*t);
        double compinterest = amount - p;
        System.out.println("Compound Interest after "+ t + " years: "+ compinterest);
        System.out.println("Amount after "+ t + " years: "+ amount);
    }
    public void calcFlat(double p, int t, double r) {
        double flatAmount;
        flatAmount = (p * t * r) / 100;
        System.out.println("Simple Interest after "+ t + " years: "+ flatAmount);
        flatAmount = p + flatAmount; 
        System.out.println("Amount after " + t + " years: $" + flatAmount);

    }
    public void investment(double balance, double r, double targetBalance){
        int year = 0; 
        while(balance<= targetBalance){
            year++;
            double interest = balance * r / 100;
            balance = balance + interest;
        }
        System.out.println("Total Balance: $"+ balance);
        System.out.println("Investment doubled After "+ year + " years.");
    }



    public static int menu() {
        int selection;
        Scanner input = new Scanner(System.in);

        /*******************************************/
        System.out.println("Please select an option:");
        System.out.println("------------------------\n");
        System.out.println("1: Get Balance");
        System.out.println("2: Deposit");
        System.out.println("3: Withdraw");
        System.out.println("4: Transfer");
        System.out.println("5: Set up new savings account");
        System.out.println("0: Quit");
        System.out.println("------------------------\n");

        selection = input.nextInt();
          
        return selection;

        }
 
        
   // }

}
