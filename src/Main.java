/*
Here is clear code for Project of ConsoleMemoryGame.
Every code update will be after tests in external project.
Started August 7th 2022.
Finished - not yet.

made by:
GitHub @B4JD1K
Steam @Bajdik
*/

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    /**     used methods you will find below code     **/

    /**     * reading from keyboard     **/
    static Scanner cin = new Scanner(System.in);


    /**     * exec "Words.txt" file to game     **/
    final static File fileWithWords = new File("Words.txt");


    /**     * array for cards executed from file     **/
    public static String[] originalCard = new String[64];


    /**     * after first view after shuffle cards will be covered with these coords     **/
    public static String[] hiddenBoard = {
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


    /**     * this array is created for reassigning after wrong guess     **/
    public static String[] hiddenAgain = {
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


    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        /**     how many pairs to guess you have at specific level      **/
        final int easyLevel = 4;
        final int hardLevel = 8;
        final int extremeLevel = 16;
        final int goodLuck = 32;

        /**     which level is selected by player       **/
        int choosenLevel = easyLevel;


        int pairs = choosenLevel * 2;
        int guessLeft = 0;

        Scanner scan = new Scanner(fileWithWords);
        ArrayList<String> data = new ArrayList<>();
        while (scan.hasNextLine()) {
            data.add(scan.nextLine());
        }
        String[] simpleArray = data.toArray(new String[]{});

        String[] pairCard = new String[64];

        System.out.println("\n\nWygenerowane slowa to: \n");

        for (int i = 0; i < choosenLevel; i++) {
            originalCard[i] = simpleArray[wordGenerator()];
            pairCard[i] = originalCard[i];
        }

        for (int i = choosenLevel * 2 - 1; i >= choosenLevel; i--) {
            int temp1 = 0;
            temp1 = shuffledCards(choosenLevel);
            System.out.println("wylosowany temp = " + temp1);

            originalCard[i] = pairCard[temp1];
            ArrayList<Integer> shuffler = new ArrayList<>(i);
            boolean czyPrzerwac = shuffler.contains(temp1);
            if (czyPrzerwac) {
                System.out.println("wylosowany nowy" + (temp1 = shuffledCards(choosenLevel)));
                shuffler.add(temp1);
                System.out.println("dodano do listy " + shuffler.add(temp1));
            }
        }
        printBoard(pairs);

        System.out.println();

        for (int i = 0; i < 2 * choosenLevel; i++) {
            if (i % 4 == 0) {
                System.out.print("\n  |  ");
            }
            System.out.print(hiddenBoard[i] + "  |  ");
        }

        switch (choosenLevel) {
            case easyLevel -> guessLeft = 10;
            case hardLevel -> guessLeft = 15;
            case extremeLevel -> guessLeft = 25;
            case goodLuck -> guessLeft = 50;
        }

        do {
            System.out.println("Pozostalo zyc: " + guessLeft);
            System.out.println("Podaj pierwsza karte jaka chcesz odkryc: ");
            String firstGuess = cin.next();
            firstGuess = firstGuess.toUpperCase();

            if (stringContainsItemFromList(firstGuess, hiddenBoard)) {
                String savedGuess = firstGuess; // a1

                hiddenBoard[findIndex(hiddenBoard, firstGuess)] =
                        originalCard[findIndex(hiddenBoard, firstGuess)];

                firstGuess = originalCard[findIndex(hiddenAgain, savedGuess)];

                System.out.println("hiddenCards");
                printHiddenBoard(pairs);
            }

            System.out.println("Podaj druga karte jaka chcesz odkryc: ");
            String secondGuess = cin.next();
            secondGuess = secondGuess.toUpperCase();

            if (stringContainsItemFromList(secondGuess, hiddenBoard)) {
                String savedGuess = secondGuess;
                hiddenBoard[findIndex(hiddenBoard, secondGuess)] =
                        originalCard[findIndex(hiddenBoard, secondGuess)];

                secondGuess = originalCard[findIndex(hiddenAgain, savedGuess)];

                System.out.println("hiddenCards");
                printHiddenBoard(pairs);
            }

            if (secondGuess.equals(firstGuess)) {
                System.out.println("Karty pasuja do siebie");
            } else {
                System.out.println("karty nie pasuja czyli maja przyjac spowrotem wartosc ukryta");

                hiddenBoard[findIndex(hiddenBoard, firstGuess)] = hiddenAgain[findIndex(hiddenBoard, firstGuess)];
                hiddenBoard[findIndex(hiddenBoard, secondGuess)] = hiddenAgain[findIndex(hiddenBoard, secondGuess)];
                guessLeft--;

            }
            System.out.println("Plansza gry wyglada nastepujaco\n");
            printHiddenBoard(pairs);

        } while (guessLeft > 0);
    }

    /**     * printing gameboard to check words to guess     **/
    public static void printBoard(int printing) {
        for (int i = 0; i < printing; i++) {
            if (i % 4 == 0) System.out.println();
            System.out.print(" | " + originalCard[i] + " | ");
        }
        System.out.println();
    }   // end of printBoard()


    /**     * printing gameboard with coordinates     **/
    public static void printHiddenBoard(int printing) {
        for (int i = 0; i < printing; i++) {
            if (i % 4 == 0) System.out.println();
            System.out.print(" | " + hiddenBoard[i] + " | ");
        }
        System.out.println();
    }   // end of printHiddenBoard()


    /**     * checking is guessed coord at game board     **/
    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).anyMatch(inputStr::contains);
    }   // end of stringContainsItemFromList()


    /**     * finding array index of guessed coords     **/
    public static int findIndex(String[] array, String typed) {
        if (array == null) {
            return -1;
        }
        int len = array.length;
        int i = 0;

        while (i < len) {
            if (array[i].equals(typed)) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }   // end of findIndex()


    /**     * shuffling cards at game board     **/
    private static int shuffledCards(int zakres) {
        return (int) (Math.random() * (zakres - 1));
    }   // end of shuffledCards()


    /**     * counting lines in file "Words.txt"     **/
    private static int iloscLiniiPliku() {
        long lines = 0;
        try {
            lines = Files.lines(fileWithWords.toPath()).count();
        } catch (IOException ignore) {
        }
        return Math.toIntExact(lines);
    }   // end of iloscLiniiPliku()


    /**     * generating words for game from random line number - bound = lines count from method above     **/
    private static int wordGenerator() {
        Random liczba = new Random();
        return liczba.nextInt(iloscLiniiPliku());
    }   // end of wordGenerator()

}