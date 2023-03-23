import java.util.Scanner;

public class MyCalculator {
    
    public static String promptUserForCalculatorType() {
        String choice;
        
        Scanner userInput = new Scanner(System.in);
        while(true) {
            System.out.println("Enter the calculator mode: Standard/Scientific?");
            choice = userInput.nextLine();
            if (choice.toLowerCase().equals("standard") || choice.toLowerCase().equals("scientific")) {
                break;
            }
            System.out.println("Please enter a valid calculator type.");
        }
        userInput.close();
        return choice.toLowerCase();
    }
    
    public static void main(String[] args) {
        final String calculatorType = promptUserForCalculatorType();
    }
}