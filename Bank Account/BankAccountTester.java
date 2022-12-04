import java.util.Scanner;

public class BankAccountTester {


    public static void main(String[] args) {

        BankAccount gregChecking = new BankAccount(1000);
        BankAccount account1 = new BankAccount(1000); 
        BankAccount account2 = new BankAccount(500); 
        int input = BankAccount.menu(); 

        if (input == 1) { 
            System.out.println("Here is your balance: " + gregChecking.getBalance());
            // BankAccount abc = new BankAccount(); 
            // System.out.println(abc);
            
        }
        else if (input == 2) { 
            System.out.println("How much would you like to deposit?");
            gregChecking.deposit(input);
        }
        else if (input == 3) { 
            System.out.println("How much would you like to withdraw?");
            gregChecking.withdraw(input);
        }
        else if (input == 0) { 
            System.out.println("Quitting");
            
        } 

        else if (input == 4) {

            System.out.println("Where do you want to transfer your money to? Choose either BankAccount1(enter 1) or BankAccount2(enter 2).");
            Scanner help = new Scanner(System.in);
            int user = help.nextInt(); 
            if (user == 1) {
                account1.transfer(account1, account2, input);
            }
            else if (user == 2) {
                account2.transfer(account1, account2, input);
            }
            else {
                System.out.println("Invalid Input");
                System.exit(0);
            }


            //int user = scanner.nextInt(); 
            // if (user == 1) {
            //     System.out.println("BankAccount1's balance: " + account1.getBalance());
            //     account1.transfer(account1, account2, input);

            // }
            // if (user == 2) {
            //     System.out.println("BankAccount2's balance: " + account2.getBalance());
            //     account2.transfer(account1, account2, input);
            // }

            // else {
            //     System.out.println("Invalid input.");
            //     System.exit(0); 
            // }

        }
        else if (input == 5) {
            Scanner scan = new Scanner(System.in);
            System.out.println("In order to start savings account, you must deposit $1000.");
            System.out.println("How much would you like to deposit into your savings account?");
        double savingStart = scan.nextDouble(); 
        if (savingStart >= 1000) {
            System.out.println("Current Savings Account balance: $" + savingStart);

            System.out.println("Would you like a compound(APY is 0.0001) or flat(APY is 0.001) interest rate?" + "\n" + "For compound enter a 1 and for flat enter a 2: ");
            int enter = scan.nextInt(); 
            if (enter==1) {
                System.out.println("You have chosen compound interest rate.");
                gregChecking.calcInterest(gregChecking.getBalance(), 1, .001, 4);
                // double P = amount; 
                // double R = .0001;
                // int t = 1; 
                // int n = 4; 
                // amount = P*(1 + R/n)^(n*t) - P; 
            }
            else if (enter==2) {
                System.out.println("You have chosen flat interest rate");
                gregChecking.calcFlat(gregChecking.getBalance(), 1, .0001);
            }
            else {
                System.out.println("Invalid input.");
                }
            }

        else{
            System.out.println("Sorry unable to start new savings account. Insufficient funds.");
            }
        }
                
        else {

            System.out.println("Error: Please select a valid choice.");
            System.exit(0); 
        }
                
          
//         BankAccount bobChecking = new BankAccount(10000);
            
// //         System.out.println(gregChecking.getBalance());

//         System.out.println("Balance Starting");
//         System.out.println(bobChecking.getBalance());

//         bobChecking.withdraw(500);
//         System.out.println("Balance After withdraw");
//         System.out.println(bobChecking.getBalance());
        
//         bobChecking.deposit(100);
//         System.out.println("Balance After Deposit");
//         System.out.println(bobChecking.getBalance());

//         // gregChecking.monthlyFee();

//         System.out.println(gregChecking.getBalance());

//         gregChecking.deposit(50000);
//         gregChecking.calcInterest(gregChecking.getBalance(), 10, 0.40, 12);
//         //+
 //       BankAccount b = new BankAccount();
    }
}
