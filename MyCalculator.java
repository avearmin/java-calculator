import java.util.Scanner;

public class MyCalculator {
    
    public static String promptUserForCalculatorType(Scanner scanner) {
        String calculatorType;
        
        while(true) {
            System.out.println("Enter the calculator mode: Standard/Scientific?");
            calculatorType = scanner.next().toLowerCase();
            if (validateCalculatorType(calculatorType)) {
                break;
            }
            System.out.println("Please enter a valid calculator type.");
        }
        System.out.printf("The calculator will operate in %s mode.\n", calculatorType);
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
                + "'*' for multiplication, '/' for division"
                );
            operator = scanner.next(); // 
            if (validateStandardOperator(operator)) {
                break;
            }
            System.out.println("Invalid operator " + operator);
        }
        return operator;
    }

    public static boolean validateStandardOperator(String operator) {
        return operator.equals("+") || operator.equals("-")
        || operator.equals("/") || operator.equals("*");
    }

    public static String promptUserForScientificOperator(Scanner scanner) {
        String operator;

        while(true) {
            System.out.println(
                "Enter '+' for addition, '-' for subtractions, "
                + "'*' for multiplication, '/' for division, "
                + "'sin' for sin x, 'cos' for cos x, 'tan' for tan x:"
                );
            operator = scanner.next();
            if (validateScientificOperator(operator)) {
                break;
            }
            System.out.println("Invalid operator: " + operator);
        }
        return operator;
    }

    public static boolean validateScientificOperator(String operator) {
        return validateStandardOperator(operator)|| validateTrigFunction(operator);
    }

    public static boolean validateTrigFunction(String operator) {
        return operator.toLowerCase().equals("sin")
        || operator.toLowerCase().equals("cos") 
        || operator.toLowerCase().equals("tan");
    }

    public static int promptUserForNumOfOperands(Scanner scanner, String operator) {
        int numOfOperands = 0;

        while(true) {
            printStandardOperandPromptBasedOnOperator(operator);
            numOfOperands = scanner.nextInt();
            if (validateNumOfOperands(numOfOperands, operator)) {
                break;
            }
            System.out.println("Please enter a valid number of operands.");
        }
        return numOfOperands;
    }

    public static void printStandardOperandPromptBasedOnOperator(String operator) {
        if (operator.equals("+")) {
            System.out.println("How many numbers do you want to add?");
        }
        else if (operator.equals("-")) {
            System.out.println("How many numbers do you want to subtract?");
        }
        else if (operator.equals("/")) {
            System.out.println("How many numbers do you want to divide?");
        }
        else if (operator.equals("*")) {
            System.out.println("How many numbers do you want to multiply?");
        }
    }

    public static boolean validateNumOfOperands(int numOfOperands, String operator) {
        if (validateTrigFunction(operator)) {
            return numOfOperands == 1;
        }
        else {
            return numOfOperands > 1;
        }
    }

    public static double performStandardOperation(Scanner scanner, int numOfOperands, String operator) {
        double result = 0; // if result is not given a value java will complain that 'local variable may not have been initialized.'
        System.out.printf("Enter %d numbers\n", numOfOperands);
        for (int i = 0; i < numOfOperands; i++) {
            double operand = scanner.nextDouble();
            if (i == 0) {
                result = operand;
            }
            else {
                result = getStandardOperationResult(result, operand, operator);
            }
        }
        return result;
    }
    
    public static double getStandardOperationResult(double operand1, double operand2, String operator) {
        if (operator.equals("+")) {
            return operand1 + operand2;
        }
        else if (operator.equals("-")) {
            return operand1 - operand2;
        }
        else if (operator.equals("*")) {
            return operand1 * operand2;
        }
        else if (operator.equals("/")) {
            return operand1 / operand2;
        }
        else {
            return 0.0; // Without a default double return value Java will raise an error
        }
    }

    public static double performScientificOperation(Scanner scanner, int numOfOperands, String operator) {
        double result = 0.0;
        if (validateTrigFunction(operator)) {
            printScientificOperandPromptBasedOnOperator(operator);
            double operand = scanner.nextDouble();
            result = getScientificOperationResult(operand, operator);
        }
        else {
            result = performStandardOperation(scanner, numOfOperands, operator);
        }
        return result;
    }

    public static void printScientificOperandPromptBasedOnOperator(String operator) {
        if (operator.toLowerCase().equals("sin")) {
            System.out.println("Enter a number in radians to find the sine");
        }
        else if (operator.toLowerCase().equals("cos")) {
            System.out.println("Enter a number in radians to find the cosine");
        }
        else if (operator.toLowerCase().equals("tan")) {
            System.out.println("Enter a number in radians to find the tangent");
        }
    }

    public static double getScientificOperationResult(double operand, String operator) {
        if (operator.toLowerCase().equals("sin")) {
            return Math.sin(operand);
        }
        else if (operator.toLowerCase().equals("cos")) {
            return Math.cos(operand);
        }
        else if (operator.toLowerCase().equals("tan")) {
            return Math.tan(operand);
        }
        else {
            return 0.0;
        }
    }

    public static boolean promptUserToDoAnotherOperation(Scanner scanner) {
        String choice;

        while(true) {
            System.out.println("Do you want to start over? (Y/N)");
            choice = scanner.next().toLowerCase();
            if (validateChoiceToDoAnotherOperation(choice)) {
                break;
            }
            System.out.print("Invalid choice. ");
        }
        return choice.equals("y");
    }

    public static boolean validateChoiceToDoAnotherOperation(String choice) {
        return choice.equals("y") || choice.equals("n");
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        boolean isCalculatorActive = true;

        while(isCalculatorActive) {
            final String calculatorType = promptUserForCalculatorType(userInput);
            final String operator;
            final int numOfOperands;
            final double operationResult;

            if (calculatorType.equals("standard")) {
                operator = promptUserForStandardOperator(userInput);
                numOfOperands = promptUserForNumOfOperands(userInput, operator);
                operationResult = performStandardOperation(userInput, numOfOperands, operator);
                System.out.println("Result: " + operationResult);
            }
            else if (calculatorType.equals("scientific")) {
                operator = promptUserForScientificOperator(userInput);
                if (validateTrigFunction(operator)) {
                    numOfOperands = 1;
                }
                else {
                    numOfOperands = promptUserForNumOfOperands(userInput, operator);
                }
                operationResult = performScientificOperation(userInput, numOfOperands, operator);
                System.out.println("Result: " + operationResult);
            }
            isCalculatorActive = promptUserToDoAnotherOperation(userInput);
        }
        System.out.println("Goodbye");
        userInput.close();
    }
}