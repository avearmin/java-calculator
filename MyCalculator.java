import java.util.Scanner;

public class MyCalculator {
    
    public static String promptUserForCalculatorType(Scanner scanner) {
        String calculatorType;
        
        while(true) {
            System.out.println("Enter the calculator mode: Standard/Scientific?");
            calculatorType = scanner.nextLine().toLowerCase();
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

    public static String promptUserForStandardOperator(Scanner scanner) {
        String operator;

        while(true) {
            System.out.println(
                "Enter '+' for addition, '-' for subtractions, "
                + "'*' for multiplication, '/' for division:"
                );
            operator = scanner.nextLine(); // 
            if (validateStandardOperator(operator)) {
                break;
            }
            System.out.println("Invalid operator: " + operator);
        }
        return operator;
    }

    public static boolean validateStandardOperator(String operator) {
        return operator.equals("+") || operator.equals("-")
        || operator.equals("/") || operator.equals("+");
    }

    public static String promptUserForScientificOperator(Scanner scanner) {
        String operator;

        while(true) {
            System.out.println(
                "Enter '+' for addition, '-' for subtractions, "
                + "'*' for multiplication, '/' for division, "
                + "'sin' for sin x, 'cos' for cos x, 'tan' for tan x:"
                );
            operator = scanner.nextLine();
            if (validateScientificOperator(operator)) {
                break;
            }
            System.out.println("Invalid operator: " + operator);
        }
        return operator;
    }

    public static boolean validateScientificOperator(String operator) {
        return operator.equals("+") || operator.equals("-") || operator.equals("/") 
        || operator.equals("*") || operator.toLowerCase().equals("sin")
        || operator.toLowerCase().equals("cos") || operator.toLowerCase().equals("tan");
    }

    public static int promptUserForNumOfOperands(Scanner scanner, String operator) {
        int numOfOperands = 0;

        while(true) {
            System.out.println(
                "How many numbers do you wish to perform this operation on?"
                );
            numOfOperands = scanner.nextInt();
            if (validateNumOfOperands(numOfOperands, operator)) {
                break;
            }
            System.out.println("Please enter a valid number of operands.");
        }
        return numOfOperands;
    }

    public static boolean validateNumOfOperands(int numOfOperands, String operator) {
        if (operator.toLowerCase().equals("sin") || operator.toLowerCase().equals("cos") || operator.toLowerCase().equals("tan")) {
            return numOfOperands == 1;
        }
        else {
            return numOfOperands > 1;
        }
    }
    
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        
        final String calculatorType = promptUserForCalculatorType(userInput);
        final String operator;
        final int numOfOperands;

        if (calculatorType.equals("standard")) {
            operator = promptUserForStandardOperator(userInput);
            numOfOperands = promptUserForNumOfOperands(userInput, operator);
        }
        else if (calculatorType.equals("scientific")) {
            operator = promptUserForScientificOperator(userInput);
            numOfOperands = promptUserForNumOfOperands(userInput, operator);
        }
        userInput.close();
    }
}