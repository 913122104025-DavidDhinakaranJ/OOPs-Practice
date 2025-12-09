class Printer implements Machine {

    @Override
    public void print() {
        System.out.println("Printing");
    }

    //Here Printer has to implement the scan() method which it doesn't need. So it violates ISP.
    @Override
    public void scan() {
        throw new UnsupportedOperationException("Printer does not support scan"); 
    }
    
}
