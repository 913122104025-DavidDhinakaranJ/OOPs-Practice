public class Encapsulation {
    public static void main(String args[]) {
        BankAccount acc1 = new BankAccount();
        //acc1.balance += 1000;      This line throws an error as direct access to the 'balance' field from outside of the class BankAccount is not allowed.
        
        //We can access the field 'balance' using the methods provided by that class
        acc1.deposit(1000);     
        acc1.withdraw(500);
        System.out.println(acc1.getBalance());
    }
}

//Encapsulation
//Here field 'balance' is encapsuled so that it cannot be directly accesssed or modified by other classes
class BankAccount {
    private int balance = 0;
    
    public void deposit(int amount) {
        balance += amount;
    }
    
    public boolean withdraw(int amount) {
        if(balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    public int getBalance() {
        return balance;
    }
}