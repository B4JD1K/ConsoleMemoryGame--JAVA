/*
Here is clear code for Project of ConsoleMemoryGame.
Every code update will be after tests in external project

    made by:
        GitHub @B4JD1K
        Steam @Bajdik
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Main {

    /**
     * used methods you will find below code
     * **/

    /**     * reading from keyboard       **/
    static Scanner cin = new Scanner(System.in);

    /**     * exec "Words.txt" file to game       **/
    final static File fileWithWords = new File("Words.txt");

    /**     * array for cards executed from file       **/
    public static String[] originalCard = new String[64];
    public static String[] mixedCards = new String[64];

    /**     * after first view after shuffle cards will be covered with these coords       **/
    public static String[] hiddenBoard;

    static {
        hiddenBoard = new String[]{
                "A1", "A2", "A3", "A4",
                "B1", "B2", "B3", "B4",
                "C1", "C2", "C3", "C4",
                "D1", "D2", "D3", "D4",
                "E1", "E2", "E3", "E4",
                "F1", "F2", "F3", "F4",
                "G1", "G2", "G3", "G4",
                "H1", "H2", "H3", "H4",
                "I1", "I2", "I3", "I4",
                "J1", "J2", "J3", "J4",
                "K1", "K2", "K3", "K4",
                "L1", "L2", "L3", "L4",
                "M1", "M2", "M3", "M4",
                "N1", "N2", "N3", "N4",
                "O1", "O2", "O3", "O4",
                "P1", "P2", "P3", "P4",
        };
    }

    /**     * this array is created for reassigning after wrong guess       **/
    public static String[] hiddenAgain;

    static {
        hiddenAgain = new String[]{
                "A1", "A2", "A3", "A4",
                "B1", "B2", "B3", "B4",
                "C1", "C2", "C3", "C4",
                "D1", "D2", "D3", "D4",
                "E1", "E2", "E3", "E4",
                "F1", "F2", "F3", "F4",
                "G1", "G2", "G3", "G4",
                "H1", "H2", "H3", "H4",
                "I1", "I2", "I3", "I4",
                "J1", "J2", "J3", "J4",
                "K1", "K2", "K3", "K4",
                "L1", "L2", "L3", "L4",
                "M1", "M2", "M3", "M4",
                "N1", "N2", "N3", "N4",
                "O1", "O2", "O3", "O4",
                "P1", "P2", "P3", "P4",
        };
    }

    /**     * how many pairs to guess you have at specific level      **/
    public static int choosenLevel;
    public static final int easyLevel = 4;
    public static final int hardLevel = 8;
    public static final int extremeLevel = 16;
    public static final int goodLuck = 32;

    /**     * executing main functions by launching the game        **/
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        mainScreen();
        game();
    }


    /**     * main screen options       */
    public static void mainScreen() throws InterruptedException, FileNotFoundException {
        System.out.flush();
        System.out.println("\n\n\n\n\n\n\n\n\n\n");

        System.out.println(".____________________________.");
        System.out.println("|        HELLO WORLD!        |");
        System.out.println("|     MATCHING CARD GAME     |");
        System.out.println(".____________________________.");

        System.out.println("\n\tWhat do you want to do?\n");
        System.out.println("Select an option and press enter.");
        System.out.println("\t1. Let's play!");
        System.out.println("\t2. Check highscores");
        System.out.println("\t3. Read about");
        System.out.println("\t4. Rules");
        System.out.println("\t5. Quit game");
        System.out.println();

        char selectMenuOption = cin.next().charAt(0);
        switch (selectMenuOption) {
            case '1':
                System.out.println("\nSelect difficulty to play: ");
                System.out.println("\t1. Easy (4 pairs) ");
                System.out.println("\t2. Hard (8 pairs) ");
                System.out.println("\t3. Extreme (16 pairs) ");
                System.out.println("\t4. GoodLuck (32 pairs) ");
                System.out.println("\n\t0. Back to menu ");

                char selectDificulty = cin.next().charAt(0);
                switch (selectDificulty) {
                    case '1':
                        choosenLevel = easyLevel;
                        System.out.println("Easy level was selected. You have to match 4 pairs and you have 10 chances to do it.");
                        System.out.println("You have " + choosenLevel + " seconds to remember all pairs");
                        System.out.println("You will lose if you use all your chances.\n\n\t\tGood luck!");
                        game();
                        break;

                    case '2':
                        choosenLevel = hardLevel;
                        System.out.println("Easy level was selected. You have to match 8 pairs and you have 15 chances to do it.");
                        System.out.println("You have " + choosenLevel + " seconds to remember all pairs");
                        System.out.println("You will lose if you use all your chances.\n\n\t\tGood luck!");
                        game();
                        break;

                    case '3':
                        choosenLevel = extremeLevel;
                        System.out.println("Easy level was selected. You have to match 16 pairs and you have 30 chances to do it.");
                        System.out.println("You have " + choosenLevel + " seconds to remember all pairs");
                        System.out.println("\n\t\tBest of luck\n\t\t(b ᵔ▽ᵔ)b");
                        game();
                        break;

                    case '4':
                        choosenLevel = goodLuck;
                        System.out.println("'Good luck' level was selected. You have to match 32 pairs and you have 50 chances to do it.");
                        System.out.println("You have " + choosenLevel + " seconds to remember all pairs");
                        System.out.println("You will lose if you use all your chances.");
                        System.out.println("\n\t\tBest of luck\n\t\t(b ᵔ▽ᵔ)b");
                        sleep(2);
                        System.out.println("""
                                ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
                                ░░░░░░░░░░░░░░░░░░░░░░████████░░░░░░░░░
                                ░░███████░░░░░░░░░░███▒▒▒▒▒▒▒▒███░░░░░░
                                ░░█▒▒▒▒▒▒█░░░░░░░███▒▒▒▒▒▒▒▒▒▒▒▒███░░░░
                                ░░░█▒▒▒▒▒▒█░░░░██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██░░
                                ░░░░█▒▒▒▒▒█░░░██▒▒▒▒▄██▄▒▒▒▒▄██▄▒▒▒███░
                                ░░░░░█▒▒▒█░░░█▒▒▒▒▒▒████▒▒▒▒████▒▒▒▒▒██
                                ░░░█████████████▒▒▒▒▀██▀▒▒▒▒▀██▀▒▒▒▒▒██
                                ░░░█▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒██
                                ░██▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒██▒▒▒▒▒▒▒▒▒██▒▒▒▒██
                                ██▒▒▒███████████▒▒▒▒▒██▒▒▒▒▒▒▒██▒▒▒▒▒██
                                █▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒███████▒▒▒▒▒▒▒██
                                ██▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██░
                                ░█▒▒▒███████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██░░░
                                ░██▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█░░░░░
                                ░░████████████░░░████████████████░░░░░░
                                ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
                                ░░▄█████▄░▄███████▄░▄███████▄░██████▄░░
                                ░░██▒▒▒▒█░███▒▒▒███░███▒▒▒███░██▒▒▒██░░
                                ░░██▒▒▒▒▒░██▒▒▒▒▒██░██▒▒▒▒▒██░██▒▒▒██░░
                                ░░██▒▒▒▀█░███▒▒▒███░███▒▒▒███░██▒▒▒██░░
                                ░░▀█████▀░▀███████▀░▀███████▀░██████▀░░
                                ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
                                ░░░░██▒▒▒▒░██▒▒▒██░▄█████░██▒▒▒▒██▀░░░░
                                ░░░░██▒▒▒▒░██▒▒▒██░██▀▒▒▒░██▒▒▒██░░░░░░
                                ░░░░██▒▒▒▒░██▒▒▒██░██▒▒▒▒░█████▀░░░░░░░
                                ░░░░██▒▒▒▒░██▄▒▄██░██▄▒▒▒░██▒▒▒██░░░░░░
                                ░░░░▀█████░▀█████▀░▀█████░██▒▒▒▒██▄░░░░
                                ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░""");
                        sleep(2);
                        game();

                        break;

                    case '0':
                        mainScreen();
                        break;

                    default:
                        System.out.println("This option does not exist!");
                        mainScreen();

                }

            case '2':
                System.out.println("\t|\tWork hard, have fun, make history.");
                sleep(1);
                highScores();
                System.out.println("\n\t\tWork hard, have fun, make history. ~ Jeff Bezos, Amazon founder\n");
                Thread.sleep(2000);
                System.out.print("\tNow you're going back to main menu.");
                sleep(2);
                mainScreen();

                break;

            case '3':
                System.out.println();
                System.out.println("Game name:\tMemory Card Game");
                System.out.println("Author:\t\tBartlomiej Noworyta");
                System.out.println("Version:\t1.0");
                System.out.println();
                Thread.sleep(1000);
                System.out.print("\tNow you're going back to main menu.");
                sleep(2);
                mainScreen();

                break;

            case '4':
                System.out.println("\t- You have to match all cards.");
                System.out.println("\t- Carefully enter card coordinates that you want to be revealed.");
                System.out.println("\t- If you make mistake, you can crash the game.");
                System.out.println("\t- Enjoy!");
                Thread.sleep(2000);
                System.out.println("\n");
                System.out.println("\t\t- I'm so sorry for every bug you will find.");
                System.out.println("\t\t- I'm trying to fix all bugs.");
                System.out.println("\t\t- If you find any of these, please contact with me.");
                sleep(3);
                mainScreen();
                break;

            case '5':
                System.exit(0);
                break;

            default:
                System.out.println("This option does not exist!");
                mainScreen();
        }
    }   // end of mainScreen()

    public static void game() throws FileNotFoundException, InterruptedException {

        /**     looping game        **/
        boolean play = true;
        do{
            System.out.println();
            Thread.sleep(2000);

            /**     which level is selected by player and /switch/ define how many attempts player have to win game for any level     **/
            int guessLeft = 0;
            int guessMatch = 0;
            switch (choosenLevel) {
                case easyLevel -> guessLeft = 10;
                case hardLevel -> guessLeft = 15;
                case extremeLevel -> guessLeft = 25;
                case goodLuck -> guessLeft = 50;
            }
            int guessStart = guessLeft;

            /**     every card's own pair, so total cars amount is multiplied by 2      **/
            int cardsAmount = choosenLevel * 2;

            /**     coping words from text file to extra array      **/
            Scanner scan = new Scanner(fileWithWords);
            ArrayList<String> data = new ArrayList<>();
            while (scan.hasNextLine()) {
                data.add(scan.nextLine());
            }
            String[] arrayWithWords = data.toArray(new String[]{});

            /**     generating random word at every card        **/
            for (int i = 0; i < choosenLevel; i++) {
                originalCard[i] = arrayWithWords[wordGenerator()];
            }

            /**     creating pair for every card        **/
            for (int i = choosenLevel; i < cardsAmount; i++) {
                originalCard[i] = originalCard[i - choosenLevel];
            }

            /**     shuffling cards and printing them to board      **/
            shufflingCards(cardsAmount);
            printBoard(cardsAmount);

            System.out.println();

            sleep(choosenLevel);
            System.out.println("Now game board looks like:\n");
            System.out.println("Guesses left: " + guessLeft);

            String firstGuess = null;
            String secondGuess = null;
            String guessBufor = null;
            String previousGuess = null;

            first:
            do {
                next:
                do {
                    printHiddenBoard(cardsAmount);

                    /**     getting first guess from player     **/
                    System.out.println("Pick first card: ");

                    firstGuess = cin.next();
                    firstGuess = firstGuess.toUpperCase();
                    // .toUpperCase(); because 'tiles' have coordinates with capital letter
                    guessBufor = firstGuess;
                    previousGuess = firstGuess;
                    secondGuess = "";

                    second:
                    do {
                        /**     revealing first card        **/
                        if (secondGuess.equals(previousGuess)) {
                            System.out.println("Wrong coordinates. Pick first card again: ");
                            System.out.println("Guesses left: " + guessLeft);
                            continue first;

                        } else if (previousGuess.equals(secondGuess)) {
                            System.out.println("You picked this coords before. Pick another one: ");
                            System.out.println("Guesses left: " + guessLeft);
                            continue first;

                        } else if (stringContainsItemFromList(firstGuess, hiddenBoard)) {
                            hiddenBoard[findIndex(hiddenBoard, firstGuess)] =
                                    mixedCards[findIndex(hiddenBoard, firstGuess)];
                            firstGuess = mixedCards[findIndex(hiddenAgain, guessBufor)];

                            printHiddenBoard(cardsAmount);
                        }

                        do {
                            /**     getting second guess from player        **/
                            System.out.println("Pick second card: ");
                            secondGuess = cin.next();
                            secondGuess = secondGuess.toUpperCase();
                            guessBufor = secondGuess;
                            previousGuess = secondGuess;

                            /**     revealing second card       **/
                            if (firstGuess.equals(previousGuess)) {
                                System.out.println("Wrong coordinates. Pick second card again: ");
                                hiddenBoard[findIndex(hiddenBoard, firstGuess)] =
                                        hiddenAgain[findIndex(hiddenBoard, firstGuess)];
                                System.out.println("Guesses left: " + guessLeft);
                                continue second;

                            } else if (guessBufor.equals(firstGuess)) {
                                System.out.println("You picked this coords before. Pick another one: ");
                                System.out.println("Guesses left: " + guessLeft);
                                continue second;

                            } else if (stringContainsItemFromList(secondGuess, hiddenBoard)) {
                                hiddenBoard[findIndex(hiddenBoard, secondGuess)] =
                                        mixedCards[findIndex(hiddenBoard, secondGuess)];
                                secondGuess = mixedCards[findIndex(hiddenAgain, guessBufor)];

                                printHiddenBoard(cardsAmount);
                            }

                            /**     checking both cards - if correct  **/
                            if (secondGuess.equals(firstGuess)) {
                                System.out.println("Match!");
                                System.out.println("Guesses left: " + guessLeft);
                                guessMatch++;

                                /**     if incorrect guesses left are reduced by 1 and revealed cards will be hidden again **/
                            } else {
                                System.out.println("The cards do not match.");

                                hiddenBoard[findIndex(hiddenBoard, firstGuess)] = hiddenAgain[findIndex(hiddenBoard, firstGuess)];
                                hiddenBoard[findIndex(hiddenBoard, secondGuess)] = hiddenAgain[findIndex(hiddenBoard, secondGuess)];
                                guessLeft--;
                                System.out.println("Guesses left: " + guessLeft);
                                continue next;

                            }
                            sleep(1);
                            System.out.println("Now game board looks like:\n");

                            if (guessLeft == 0)
                                break first;
                            if (guessMatch == choosenLevel)
                                break first;

                            continue next;
                        } while (true);
                    } while (true);

                } while (true);
            } while (false);

            if (guessLeft == 0) {
                System.out.println("You lost the game.");
                System.out.println("You matched " + guessMatch + " pairs.");
            } else if (guessMatch == choosenLevel) {
                System.out.println("You won the game.");
                System.out.println("You matched all pairs in " + (guessStart - guessLeft) + " moves!");
            }

            System.out.println("\n\nDo you want to play again? (y/n)\n");
            String answer = cin.nextLine();
            answer = answer.toLowerCase();

            if (answer.equals("n") || answer.equals("no")){
                play = false;
                System.exit(0);
            }

            if (answer.equals("y") || answer.equals("yes"))
                play = true;
        } while(play);
    }

    /**     * printing gameboard to check words to guess        **/
    public static void printBoard(int printing) {
        for (int i = 0; i < printing; i++) {
            if (i % 4 == 0)
                System.out.println();
            System.out.print(" | " + mixedCards[i] + " | ");
        }
    }   // end of printBoard()

    /**     * creating game board with 'tiles'      **/
    public static void printHiddenBoard(int printing) {
        for (int i = 0; i < printing; i++) {
            if (i % 4 == 0)
                System.out.println();
            System.out.print(" | " + hiddenBoard[i] + " | ");
        }
        System.out.println();
    }   // end of printHiddenBoard()

    /**     * checking is guessed coord at game board       **/
    public static boolean stringContainsItemFromList(String typed, String[] elements) {
        return Arrays.stream(elements).anyMatch(typed::contains);
    }   // end of stringContainsItemFromList()

    /**     * finding array index of guessed coords       **/
    public static int findIndex(String[] array, String typed) {
        if (array == null)
            return -1;

        int arrLength = array.length;
        int i = 0;

        while (i < arrLength) {
            if (array[i].equals(typed))
                return i;
            else
                i++;
        }
        return -1;
    }   // end of findIndex()

    /**     * shuffling cards at game board by index       **/
    public static void shufflingCards(int printing) {
        Random shuffleCards = new Random();
        int newIndex = shuffleCards.nextInt();
        Set<Integer> nextIndex = new HashSet<>();

        /*      adding first random number to set       */
        nextIndex.add(newIndex);
        for (int i = 0; i < printing; i++) {

            /*      looking for a number that does not exist yet        */
            while (nextIndex.contains(newIndex)) {
                newIndex = shuffleCards.nextInt(printing);
            }
            /*      if found add this number to set     */
            nextIndex.add(newIndex);
            mixedCards[i] = originalCard[newIndex];
        }
    }   // end of shufflingCards()

    /**     * counting lines in file "Words.txt"        **/
    private static int fileLinesAmount() {
        long lines = 0;
        try {
            lines = Files.lines(fileWithWords.toPath()).count();
        } catch (IOException ignore) {
        }
        return Math.toIntExact(lines);
    }   // end of fileLinesAmount()

    /**     * generating words for game from random line number - bound = lines count from method above      **/
    private static int wordGenerator() {
        Random liczba = new Random();
        return liczba.nextInt(fileLinesAmount());
    }   // end of wordGenerator()

    /**     * function to sleep console and 'clear' by pushing old text higher       **/
    private static void sleep(int time) {
        time *= 1000;
        try {
            Thread.sleep(time);
        } catch (InterruptedException reallyIgnored) {
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
    }   // end of sleep()

    /**     * Work hard, have fun, make history. ~ Jeff Bezos, Amazon founder       **/
    public static void highScores() throws InterruptedException {
        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < 15; j++) {
                Thread.sleep(50);
                System.out.println(frames[j] + "               \n");
            }
        }
        for (int j = 0; j < 6; j++) {
            Thread.sleep(50);
            System.out.println(frames[j] + "               \n");
        }



    }

    public static String[] frames;
    static {
        frames = new String[]{
                "               \n" +
                        "      _._      \n" +
                        "     / O \\    \n" +
                        "     \\| |/    \n" +
                        "  O--+=-=+--O  \n"
                ,
                "               \n" +
                        "               \n" +
                        "     ,-O-,     \n" +
                        "  O--=---=--O  \n" +
                        "      2'2'     \n"
                ,
                "               \n" +
                        "     ,_O_,     \n" +
                        "  O--(---)--O  \n" +
                        "      >'>      \n" +
                        "      - -      \n"
                ,
                "               \n" +
                        "     ._O_.    \n" +
                        "  O--<-+->--O \n" +
                        "       X      \n" +
                        "      / \\    \n" +
                        "     -   -    \n"
                ,
                "               \n" +
                        "  O--=-O-=--O \n" +
                        "      '-'     \n" +
                        "       v      \n" +
                        "      / )     \n" +
                        "     ~   Z    "
                ,
                "               \n" +
                        "  O-,-----,-O \n" +
                        "     \\ O /    \n" +
                        "       X       \n" +
                        "      / \\     \n" +
                        "     =   =    "
                ,
                "               \n" +
                        "  O-,-----,-O \n" +
                        "     \\ O /    \n" +
                        "       X       \n" +
                        "      / \\     \n" +
                        "     =   =     \n"
                ,
                "               \n" +
                        "  O-,-----,-O \n" +
                        "     \\ O /    \n" +
                        "       X       \n" +
                        "      / \\     \n" +
                        "     =   =     \n"
                ,
                "               \n" +
                        "  O-,-----,-O \n" +
                        "     \\ O /    \n" +
                        "       X       \n" +
                        "      / \\     \n" +
                        "     =   =     \n"
                ,
                "               \n" +
                        "  O-,-----,-O \n" +
                        "     \\ O /    \n" +
                        "       X       \n" +
                        "      / \\     \n" +
                        "     =   =     \n"
                ,
                "               \n" +
                        "  O--=-O-=--O \n" +
                        "      '-'     \n" +
                        "       v      \n" +
                        "      / )     \n" +
                        "     ~   Z    \n"
                ,
                "               \n" +
                        "     ._O_.    \n" +
                        "  O--<-+->--O \n" +
                        "       X      \n" +
                        "      / \\    \n" +
                        "     -   -    \n"
                ,
                "               \n" +
                        "               \n" +
                        "     ,_O_,     \n" +
                        "  O--(---)--O  \n" +
                        "      >'>      \n" +
                        "      - -      \n"
                ,
                "               \n" +
                        "               \n" +
                        "     ,-O-,     \n" +
                        "  O--=---=--O  \n" +
                        "      2'2'     \n"
                ,
                "               \n" +
                        "      _._      \n" +
                        "     / O \\    \n" +
                        "     \\| |/    \n" +
                        "  O--+=-=+--O  \n"

        };
    }
}