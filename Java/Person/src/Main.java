public class Main {
    public static void main(String[] args) {
        Person Bob = new Person(true, "Bob");
        Person Sam = new Person(true, "Sam");
        Person Ann = new Person(false, "Ann");
        Person Kate = new Person(false, "Kate");

        System.out.println("Bob + Sam = " + Bob.marry(Sam));
        System.out.println("Bob + Ann = " + Bob.marry(Ann));
        System.out.println("Bob + Ann = " + Bob.marry(Ann));

        System.out.println("Sam + Kate = " + Sam.marry(Kate));
        System.out.println("Sam + Ann = " + Sam.marry(Ann));
    }
}
