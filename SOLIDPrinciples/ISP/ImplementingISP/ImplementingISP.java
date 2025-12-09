public class ImplementingISP {
    public static void main(String args[]) {
        //Now the Printer and Scanner can only implement the methods that it needs. So it satisfies ISP
        Printable printer = new Printer();
        Scannable scanner = new Scanner();
        
        printer.print();
        scanner.scan();
        
        HybridMachine machine = new HybridMachine();
        machine.print();
        machine.scan();
    }
}