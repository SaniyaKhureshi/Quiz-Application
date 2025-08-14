package com.gqt.javabasics.Project;
	import java.util.Scanner;

	class Launch1 {
	    int Money = 0;
	    boolean used5050 = false, usedAudiencePoll = false;
	    Scanner sc;

	    // Constructor to pass Scanner from main
	    public Launch1(Scanner sc) {
	        this.sc = sc;
	    }

	    // ANSI color codes for console styling
	    public static final String RESET = "\u001B[0m";
	    public static final String RED = "\u001B[31m";
	    public static final String GREEN = "\u001B[32m";
	    public static final String YELLOW = "\u001B[33m";
	    public static final String CYAN = "\u001B[36m";

	    // Display rules
	    public void rules() {
	        System.out.println(CYAN + "********** QUIZ RULES **********" + RESET);
	        System.out.println("1. For each correct answer, reward increases as per question.");
	        System.out.println("2. You have lifelines, each can be used only once.");
	        System.out.println("3. Available lifelines: 50:50, Audience Poll.");
	        System.out.println("4. You can skip a question.");
	        System.out.println("5. You can quit anytime and keep your earned money.");
	        System.out.println(CYAN + "*******************************" + RESET);
	    }

	    // Generic method to handle a question
	    public void askQuestion(String question, String[] options, int correctOption, int prize) {
	        System.out.println(YELLOW + "\n" + question + RESET);
	        for (String option : options) {
	            System.out.println(option);
	        }

	        boolean answered = false;
	        while (!answered) {
	            System.out.println(CYAN + "\nOptions:" + RESET);
	            System.out.println("1. Answer");
	            System.out.println("2. Use 50:50 Lifeline");
	            System.out.println("3. Use Audience Poll Lifeline");
	            System.out.println("4. Skip");
	            System.out.println("5. Quit");
	            System.out.print("Enter your choice: ");
	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1: // Answer
	                    System.out.print("Enter your answer (1-4): ");
	                    int userAnswer = sc.nextInt();
	                    if (userAnswer == correctOption) {
	                        System.out.println(GREEN + "Correct! You win " + prize + " Rupees." + RESET);
	                        Money += prize;
	                    } else {
	                        System.out.println(RED + "Wrong answer." + RESET);
	                    }
	                    answered = true;
	                    break;

	                case 2: // 50:50 lifeline
	                    if (!used5050) {
	                        used5050 = true;
	                        System.out.println("50:50 Lifeline used. Remaining options:");
	                        for (int i = 0; i < options.length; i++) {
	                            if (i + 1 == correctOption || i + 1 == ((correctOption % options.length) + 1))
	                                System.out.println(options[i]);
	                        }
	                        System.out.print("Enter your choice: ");
	                        userAnswer = sc.nextInt();
	                        if (userAnswer == correctOption) {
	                            System.out.println(GREEN + "Correct! You win " + prize + " Rupees." + RESET);
	                            Money += prize;
	                        } else {
	                            System.out.println(RED + "Wrong answer." + RESET);
	                        }
	                        answered = true;
	                    } else {
	                        System.out.println(RED + "You already used 50:50 lifeline!" + RESET);
	                    }
	                    break;

	                case 3: // Audience poll lifeline
	                    if (!usedAudiencePoll) {
	                        usedAudiencePoll = true;
	                        System.out.println("Audience Poll Results:");
	                        for (int i = 0; i < options.length; i++) {
	                            int percent = (i + 1 == correctOption) ? 70 : 10;
	                            System.out.println((i + 1) + ". " + options[i].substring(3) + " - " + percent + "%");
	                        }
	                        System.out.print("Enter your choice: ");
	                        userAnswer = sc.nextInt();
	                        if (userAnswer == correctOption) {
	                            System.out.println(GREEN + "Correct! You win " + prize + " Rupees." + RESET);
	                            Money += prize;
	                        } else {
	                            System.out.println(RED + "Wrong answer." + RESET);
	                        }
	                        answered = true;
	                    } else {
	                        System.out.println(RED + "You already used Audience Poll lifeline!" + RESET);
	                    }
	                    break;

	                case 4: // Skip
	                    System.out.println(YELLOW + "Skipping question..." + RESET);
	                    answered = true;
	                    break;

	                case 5: // Quit
	                    System.out.println(YELLOW + "You chose to quit. Final score: " + Money + " Rupees." + RESET);
	                    System.exit(0);
	                    break;

	                default:
	                    System.out.println(RED + "Invalid choice. Try again!" + RESET);
	            }
	        }
	    }

	    // Launch all quiz questions
	    public void startQuiz() {
	        askQuestion("Question 1: What is the chemical symbol for water?",
	                new String[]{"1. MnO2", "2. O2", "3. H2O", "4. O3"},
	                3, 1000);

	        askQuestion("Question 2: What is the largest planet in our solar system?",
	                new String[]{"1. Mars", "2. Jupiter", "3. Mercury", "4. Earth"},
	                2, 2000);

	        askQuestion("Question 3: USB stands for?",
	                new String[]{"1. Unique Serial Bus", "2. Universal Serial Bus", "3. Unary Serial Bus", "4. Universal Secondary Bus"},
	                2, 4000);

	        askQuestion("Question 4: Who is considered the 'father of the computer'?",
	                new String[]{"1. Charles Babbage", "2. Dennis Ritchie", "3. Bill Gates", "4. James Gosling"},
	                1, 6000);

	        askQuestion("Question 5: What is known as temporary memory or volatile memory?",
	                new String[]{"1. ROM", "2. HDD", "3. USB", "4. RAM"},
	                4, 8000);

	        System.out.println(GREEN + "\nQuiz finished! Your total earnings: " + Money + " Rupees." + RESET);
	    }
	}

	public class Project3 {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        System.out.print("Enter your username: ");
	        String name = sc.next();

	        Launch1 quiz = new Launch1(sc);
	        quiz.rules();

	        System.out.print("Are you okay with the rules? (yes/no): ");
	        String agree = sc.next();
	        if (agree.equalsIgnoreCase("yes")) {
	            System.out.println("\nStarting the quiz for " + name + "...");
	            quiz.startQuiz();
	        } else {
	            System.out.println("Quitting the game. Bye!");
	        }
	    }
	}



