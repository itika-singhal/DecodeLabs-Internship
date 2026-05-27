package decodeLabs;
import java.util.*;
public class task1 {

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            Random random = new Random();

            int totalScore = 0;
            char playAgain;

            System.out.println("===== WELCOME TO NUMBER GUESSING GAME =====");

            do {

                int targetNumber = random.nextInt(100) + 1;
                int guess = 0;
                int attempts = 0;
                int maxAttempts = 7;
                boolean guessedCorrectly = false;

                System.out.println("\nI have generated a number between 1 and 100.");
                System.out.println("You have " + maxAttempts + " attempts to guess it.");

                while (attempts < maxAttempts) {

                    System.out.print("\nEnter your guess: ");

                    try {

                        guess = sc.nextInt();
                        attempts++;

                        if (guess == targetNumber) {
                            System.out.println(" Correct! You guessed the number.");
                            guessedCorrectly = true;

                            int roundScore = (maxAttempts - attempts + 1) * 10;
                            totalScore += roundScore;

                            System.out.println("Attempts used: " + attempts);
                            System.out.println("Round Score: " + roundScore);
                            break;
                        }

                        else if (guess > targetNumber) {
                            System.out.println(" Too High!");
                        }

                        else {
                            System.out.println(" Too Low!");
                        }

                        System.out.println("Attempts Left: " + (maxAttempts - attempts));

                    }

                    catch (InputMismatchException e) {
                        System.out.println(" Invalid Input! Please enter an integer only.");
                        sc.nextLine(); // Flush buffer
                    }
                }

                if (!guessedCorrectly) {
                    System.out.println("\n Game Over!");
                    System.out.println("The correct number was: " + targetNumber);
                }

                System.out.println("\nTotal Score: " + totalScore);

                System.out.print("\nDo you want to play again? (Y/N): ");
                playAgain = sc.next().charAt(0);

            } while (playAgain == 'Y' || playAgain == 'y');

            System.out.println("\n===== THANK YOU FOR PLAYING =====");

            sc.close();
        }
    }

