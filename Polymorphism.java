public class Polymorphism {
    public static void main(String args[]) {
        Payment payment;
        
        payment = new UPIPayment();
        payment.pay(1000);
        
        payment = new CardPayment();
        payment.pay(2000);
        
        payment = new NetBankingPayment();
        payment.pay(500);
    }
}

abstract class Payment {
    abstract void pay(double amount);
}

class UPIPayment extends Payment {
    @Override
    void pay(double amount) {
        System.out.println(amount + " paid on upi payment.");
    }
}

class CardPayment extends Payment {
    @Override
    void pay(double amount) {
        System.out.println(amount + " paid on card payment.");
    }
}

class NetBankingPayment extends Payment {
    @Override
    void pay(double amount) {
        System.out.println(amount + " paid on net banking payment.");
    }
}