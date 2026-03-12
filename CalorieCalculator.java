import java.util.Scanner;

public class CalorieCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Enter your gender (male/female): ");
        String gender = scanner.next();

        System.out.print("Enter your height in cm: ");
        double height = scanner.nextDouble();

        System.out.print("Enter your weight in kg: ");
        double weight = scanner.nextDouble();

        // Display numbered activity level options
        System.out.println("Select your activity level (enter number 1-5):");
        System.out.println("1. Sedentary (Little or no exercise)");
        System.out.println("2. Lightly Active (1-3 days/week)");
        System.out.println("3. Moderately Active (3-5 days/week)");
        System.out.println("4. Very Active (6-7 days/week)");
        System.out.println("5. Super Active (Athlete)");

        int activityChoice = scanner.nextInt();

        double activityMultiplier;
        switch (activityChoice) {
            case 1: activityMultiplier = 1.2; break;
            case 2: activityMultiplier = 1.375; break;
            case 3: activityMultiplier = 1.55; break;
            case 4: activityMultiplier = 1.725; break;
            case 5: activityMultiplier = 1.9; break;
            default:
                System.out.println("Invalid choice, defaulting to Sedentary.");
                activityMultiplier = 1.2;
        }

        // BMR Calculation
        double bmr;
        if (gender.equalsIgnoreCase("male")) {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }

        // Calories
        int maintenanceCalories = (int)Math.round(bmr * activityMultiplier);
        int cuttingCalories = (int)Math.round(maintenanceCalories * 0.8);
        int bulkingCalories = (int)Math.round(maintenanceCalories * 1.2);

        // Macronutrients
        int protein = (int)Math.round((maintenanceCalories * 0.30) / 4);
        int carbs = (int)Math.round((maintenanceCalories * 0.40) / 4);
        int fats = (int)Math.round((maintenanceCalories * 0.30) / 9);

        // Display results
        System.out.println("\n=== Your Maintenance Calorie & Macro Results ===");
        System.out.println("BMR: " + bmr + " kcal/day");
        System.out.println("Maintenance Calories: " + maintenanceCalories + " kcal/day");
        System.out.println("Cutting Calories (-20%): " + cuttingCalories + " kcal/day");
        System.out.println("Bulking Calories (+20%): " + bulkingCalories + " kcal/day");
        System.out.println("\nMacronutrients (approx.):");
        System.out.println("Protein: " + protein + " g");
        System.out.println("Carbs: " + carbs + " g");
        System.out.println("Fats: " + fats + " g");

        scanner.close();
    }
} 