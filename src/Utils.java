import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void clearConsole() {
        // Print multiple newlines to simulate clearing the console
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    public static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    public static int getIntInput(int min, int max) {
        while (true) {
            try {
                System.out.print("Enter your choice (" + min + "-" + max + "): ");
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    public static void printLine(String s) {
        System.out.println("║ " + s);
    }
    
    public static void printHeader(String title) {
        int width = 50;
        String border = "═".repeat(width);
        String centeredTitle = centerText(title, width);
        
        System.out.println("╔" + border + "╗");
        System.out.println("║" + centeredTitle + "║");
        System.out.println("╚" + border + "╝");
    }
    
    public static void printSubHeader(String title) {
        int width = 40;
        String border = "─".repeat(width);
        String centeredTitle = centerText(title, width);
        
        System.out.println("┌" + border + "┐");
        System.out.println("│" + centeredTitle + "│");
        System.out.println("└" + border + "┘");
    }
    
    private static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }
    
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void printSpinner(int duration) {
        String[] spinner = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};
        int iterations = duration / 100;
        
        for (int i = 0; i < iterations; i++) {
            System.out.print("\r" + spinner[i % spinner.length] + " ");
            sleep(100);
        }
        System.out.println();
    }
} 