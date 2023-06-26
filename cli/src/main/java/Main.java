import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to PrintScript CLI");
        System.out.println("Please enter your code snippet:");
        System.out.println();
        Scanner myObj = new Scanner(System.in);
        String snippet = myObj.nextLine();
        System.out.println();
        System.out.println("What would ypu like to do with the snippet?");
        System.out.println("1) Interpret it");
        System.out.println("2) Validate it");
        System.out.println();
        System.out.println("Your Option:");
        Scanner myObj2 = new Scanner(System.in);
        int option = myObj2.nextInt();
    }
}
