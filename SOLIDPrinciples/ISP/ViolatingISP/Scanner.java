class Scanner implements Machine {
    
    //Here Scanner has to implement the print() method which it doesn't need. So it violates ISP.
    @Override
    public void print() {
        throw new UnsupportedOperationException("Scanner does not support print");
    }

    @Override
    public void scan() {
        System.out.println("Scanner");
    }
    
}