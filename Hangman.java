package section_thirteen;

import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String word = randomWord();
        char[] board = new char[word.length()];
        char[] missedGuesses = new char[6];
        int misses = 0;

        for(int i = 0; i < board.length; i++){
            board[i] = '_';
        }

        while (misses < 6) {
            System.out.print(gallows[misses]);
            
            System.out.print("Word: ");
            printPlaceboard(board);
            System.out.print("\n");

            System.out.print("Misses: ");
            printMissedGuesses(missedGuesses);
            System.out.print("\n\n");

            System.out.print("Guess: ");
            char geuss = scan.nextLine().charAt(0);
            System.out.print("\n");

            if(checkGuess(word, geuss)){
                updateBoard(word, board, geuss);
            }else{
                missedGuesses[misses] = geuss;
                misses++;
            }

            if(Arrays.equals(board, word.toCharArray())){
                System.out.print(gallows[misses]);
                System.out.print("\nWord: ");
                printPlaceboard(missedGuesses);
                System.out.print("\nGood work!");
                break;
            }
        }

        if(misses == 6){
            System.out.println(gallows[6]);
            System.out.println("\nRIP");
            System.out.println("\nThe word was: '" + word + "'");
        }
        

        scan.close();
    }

// Selects a random word from the array
    public static String randomWord(){

        int randomIndex = (int) (Math.random() * words.length);
        String randWord = words[randomIndex];
        return randWord;
    }

    public static boolean checkGuess(String word, char geuss){
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == geuss){
                return true;
            }
        }
        return false;
    }

    public static void updateBoard(String word, char[] board, char geuss){
        for(int i = 0; i < word.length(); i++){
            board[i] = geuss;
        }
    }

    public static void printPlaceboard(char[] placeholders){
        for(int i = 0; i < placeholders.length; i++){
            System.out.print(" " + placeholders[i]);
        }
        System.out.print("\n");
    }

    public static void printMissedGuesses(char[] misses){
        for(int i = 0; i < misses.length; i++){
            System.out.print(misses[i]);
        }
    }
}
