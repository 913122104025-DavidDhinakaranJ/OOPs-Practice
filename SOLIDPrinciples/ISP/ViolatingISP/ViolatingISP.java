public class ViolatingISP {
    public static void main(String args[]) {
        Machine printer = new Printer();
        Machine scanner = new Scanner();
        
        printer.print();
        scanner.scan();
    }
}