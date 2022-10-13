import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class arrayList {
    
    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>(); // create new array list
        Scanner sc = new Scanner(System.in); // create new scanner object

        String addNewUser = "y";

        while (addNewUser.equals("y")) { // as long as user wants to add a new user, repeat
            System.out.println("Enter your name -");
            arrayList.add("Name: " + sc.nextLine()); // take name input
    
            System.out.println("Enter your Github ID -");
            arrayList.add("Github ID: " + sc.nextLine()); // take ghID input
        
            System.out.println("Would you like to input another user? (y/n)");
            addNewUser = sc.nextLine(); // input y/n to add another user
        }

        System.out.println();
        System.out.println("================================="); // new line

        for (int num = 0; num < arrayList.size(); num +=2){ // print every 2 indexes and leave a space
            System.out.println(arrayList.get(num));
            System.out.println(arrayList.get(num+1));
            System.out.println();
        }
    }
}


arrayList.main(null);
