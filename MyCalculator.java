import java.util.Scanner;

public class MyCalculator {
    
    public static String promptUserForCalculatorType(Scanner scanner) {
        String calculatorType;
        
        while(true) {
            System.out.println("Enter the calculator mode: Standard/Scientific?");
            calculatorType = scanner.nextLine();
            if (validateCalculatorType(calculatorType)) {
                break;
            }
            System.out.println("Please enter a valid calculator type.");
        }
        return calculatorType;
    }

    public static boolean validateCalculatorType(String calculatorType) {
        return calculatorType.equals("standard") || calculatorType.equals("scientific");
    }
    
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        
        final String calculatorType = promptUserForCalculatorType(userInput);
        
        userInput.close();
    }
}