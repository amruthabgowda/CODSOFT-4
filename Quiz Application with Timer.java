package codsoft;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    // Constructor to initialize question, options, and correct answer
    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Method to display the question and its options
    public void displayQuestion() {
        System.out.println(question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    // Method to check if the selected answer is correct
    public boolean isCorrectAnswer(int selectedAnswer) {
        return selectedAnswer == correctAnswer;
    }
}

public class quizapplicationwithtimer {
    private static Question[] questions;
    private static int score = 0;
    private static int timeLimit = 10;  // Time limit for each question (in seconds)
    private static boolean timeUp = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize quiz questions and answers
        questions = new Question[]{
                new Question("What is the capital of France?", new String[]{"Berlin", "Paris", "Rome", "Madrid"}, 2),
                new Question("Who invented the telephone?", new String[]{"Thomas Edison", "Alexander Graham Bell", "Nikola Tesla", "Albert Einstein"}, 2),
                new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Venus", "Jupiter"}, 2),
        };

        // Iterate through each question in the quiz
        for (int i = 0; i < questions.length; i++) {
            System.out.println("\n--- Question " + (i + 1) + " ---");
            questions[i].displayQuestion();

            // Timer for each question
            Timer timer = new Timer();
            timeUp = false;

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    timeUp = true;
                    System.out.println("\nTime's up! Moving to the next question.");
                }
            };

            // Schedule the timer task to run after timeLimit seconds
            timer.schedule(task, timeLimit * 1000);

            int selectedOption = 0;
            while (!timeUp) {
                try {
                    System.out.print("Enter your choice (1-4): ");
                    selectedOption = scanner.nextInt();
                    if (selectedOption < 1 || selectedOption > 4) {
                        System.out.println("Invalid choice! Please select a valid option.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number between 1 and 4.");
                    scanner.next(); // Clear the invalid input
                }
            }

            // Cancel the timer as the user has answered the question
            timer.cancel();

            // Check if the user's answer is correct
            if (questions[i].isCorrectAnswer(selectedOption)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer was option " + questions[i].correctAnswer + ".");
            }
        }

        // Display the final results
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Total Score: " + score + "/" + questions.length);
        for (int i = 0; i < questions.length; i++) {
            System.out.print("Question " + (i + 1) + ": ");
            if (questions[i].isCorrectAnswer(score)) {
                System.out.println("Correct");
            } else {
                System.out.println("Incorrect (Correct answer was option " + questions[i].correctAnswer + ")");
            }
        }

        scanner.close();
    }
}


