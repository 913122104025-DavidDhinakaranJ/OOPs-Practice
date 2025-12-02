public class Polymorphism {
    public static void main(String args[]) {
        
        //Compile-time polymorphism - here compiler decides which method to run at compile time based on number of parameters and its types
        PaymentGateway paymentGateway = new PaymentGateway();
        paymentGateway.pay("upi@1234");
        paymentGateway.pay("9876 5432 1987", "123");
        System.out.println();
        
        //Runtime Polymorphism(Method overriding) - here the overridden method is chosen at runtime based on the actual object
        Payment payment = null;
        String paymentMethod = "UPI";
        
        switch (paymentMethod) {
            case "UPI" -> payment = new UPIPayment();
            case "Card" -> payment = new CardPayment();
            case "Net Banking" -> payment = new NetBankingPayment();
            default -> {
            }
        }
        payment.pay(500);
    }
}

class PaymentGateway {
    public void pay(String cardNo, String cvv) {
        System.out.println("Payment done using card.");
    }
    
    public void pay(String upiId) {
        System.out.println("Payment done using UPI.");
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