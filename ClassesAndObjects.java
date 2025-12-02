public class ClassesAndObjects {
    public static void main(String args[]) {
        //Creating objects for Application Form
        ApplicationForm af1 = new ApplicationForm();
        af1.name = "David";
        af1.age = 20;
        af1.display();
    }
}

//Class(Template) for Application Form
class ApplicationForm {
    String name;
    int age;
    
    void display() {
        System.out.println(name);
        System.out.println(age);
    }
}