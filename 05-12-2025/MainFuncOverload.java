public class MainFuncOverload {
    //When the main function gets overloaded, the compiler will chose the ones with (String[]) as parameter.
    public static void main(String args[]) {
        System.out.println("1");
    }
    
    public static void main(int arg) {
        System.out.println("2");
    }
}