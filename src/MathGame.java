import java.util.Scanner;


public class MathGame {


    private Player player1;
    private Player player2;
    private Player player3;
    private Player currentPlayer;
    private Player winner;
    private boolean gameOver;
    private Scanner scanner;


    private int player1_score;
    private int player2_score;
    private int player3_score;
    public String streak;
    private String yesNo;


    // create MathGame object
    public MathGame(Player player1, Player player2, Player player3,Scanner scanner) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.scanner = scanner;
        currentPlayer = null; // will get assigned at start of game
        winner = null; // will get assigned when a Player wins
        gameOver = false;
    }




// ------------ PUBLIC METHODS (to be used by client classes) ------------




    // returns winning Player; will be null if neither Player has won yet
    public Player getWinner() {
        return winner;
    }




    // plays a round of the math game
    public void playRound() {
        chooseStartingPlayer(); // this helper method (shown below) sets currentPlayer to either player1 or player2
        System.out.print("Would you like harder questions (Only Division and Multiplication) Y/N?");
        String yn = scanner.next();
        if (yn.equals("Y")) {
            yesNo = "y";
        }
        int incorrect = 0;
        while (!gameOver) {
            printGameState(); // this helper method (shown below) prints the state of the Game
            System.out.println("Current player: " + currentPlayer.getName());
            boolean correct = askQuestion(); // this helper method (shown below) asks a question and returns T or F
            if (correct) {
                if (incorrect == 1) {
                    incorrect--;
                }
                System.out.println("Correct!");
                currentPlayer.incrementScore(); // this increments the currentPlayer's score
            } else {
                incorrect++;
            }
            if (incorrect == 1) {
                System.out.println("INCORRECT!");
            }
            if (incorrect == 2) {
                System.out.println("INCORRECT!");
                gameOver = true;
                determineWinner();


            }
            swapPlayers();
        }
    }




    // prints the current scores of the two players
    private void printGameState() {
        System.out.println("--------------------------------------");
        System.out.println("Current Scores:");
        System.out.println(player1.getName() + ": " + player1.getScore());
        System.out.println(player2.getName() + ": " + player2.getScore());
        System.out.println(player3.getName() + ": " + player3.getScore());
        System.out.println("--------------------------------------");
    }




    // resets the game back to its starting state
    public void resetGame() {
        player1.reset(); // this method resets the player
        player2.reset();
        player3.reset();
        gameOver = false;
        currentPlayer = null;
        winner = null;
    }




// ------------ PRIVATE HELPER METHODS (internal use only) ------------




    // randomly chooses one of the Player objects to be the currentPlayer
    private void chooseStartingPlayer() {
        int randNum = (int) (Math.random() * 3) + 1;
        if (randNum == 1) {
            currentPlayer = player1;
        }
        if (randNum == 2) {
            currentPlayer = player2;
        }
        if (randNum == 3) {
            currentPlayer = player3;
        }
    }




    // asks a math question and returns true if the player answered correctly, false if not
    private boolean askQuestion() {
        int operation = (int) (Math.random() * 4) + 1;
        if (yesNo.equals("y"))  {
            operation = (int) (Math.random() * 2) + 1;
            if (operation == 1) {
                operation = 3;
            }
            if (operation == 2) {
                operation = 4;
            }
        }
        int num1 = (int) (Math.random() * 100) + 1;
        int num2;
        int correctAnswer;
        System.out.println("Type in your answer as an integer (/ is int division)");
        if (operation == 1) {
            num2 = (int) (Math.random() * 100) + 1;
            System.out.print(num1 + " + " + num2 + " = ");
            correctAnswer = num1 + num2;
        } else if (operation == 2) {
            num2 = (int) (Math.random() * 100) + 1;
            System.out.print(num1 + " - " + num2 + " = ");
            correctAnswer = num1 - num2;
        } else if (operation == 3) {
            num2 = (int) (Math.random() * 10) + 1;
            System.out.print(num1 + " * " + num2 + " = ");
            correctAnswer = num1 * num2;
        } else { // option == 4
            num2 = (int) (Math.random() * 10) + 1;
            System.out.print(num1 + " / " + num2 + " = ");
            correctAnswer = num1 / num2;
        }




        int playerAnswer = scanner.nextInt(); // get player's answer using Scanner
        scanner.nextLine(); // clear text buffer after numeric scanner input




        if (playerAnswer == correctAnswer) {
            return true;
        } else {
            return false;
        }
    }




    // swaps the currentPlayer to the other player
    private void swapPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else if (currentPlayer == player2) {
            currentPlayer = player3;
        } else {
            currentPlayer = player1;
        }
    }




    // sets the winner when the game ends based on the player that missed the question
    private void determineWinner() {
        if (currentPlayer == player1) {
            winner = player2;


        } else if (currentPlayer == player2) {
            winner = player3;




        } else {
            winner = player1;
        }
    }


    public String getStreak() {
        if (currentPlayer == player1) {
            player2_score++;
            player1_score = 0;
            player3_score = 0;
            return player2.getName() + " has won " + player2_score + " games in a row!";


        } else if (currentPlayer == player2) {
            player3_score++;
            player1_score = 0;
            player2_score = 0;
            return player3.getName() + " has won " + player3_score + " games in a row!";




        } else {
            player1_score++;
            player2_score = 0;
            player3_score = 0;
            return player1.getName() + " has won " + player1_score + " games in a row!";
        }
    }


}

