package com.example.grahambruce.hangman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static android.R.string.yes;

/**
 * Created by grahambruce on 28/06/2017.
 */

public class Game {

    ArrayList<Player> players;
    String word;
    ArrayList<Character> hiddenWord;
    boolean keepPlaying;
    Player player1;
    Player player2;
    boolean hit;
    String totalGuesses;

    public Game(ArrayList<Player> players) {
        this.player1 = players.get(0);
        this.player2 = players.get(1);
        this.players = players;
        this.keepPlaying = true;
        this.totalGuesses = "";
    }

    public void askPlayerforWord() {
        word = "Godzilla";
        this.hiddenWord = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            hiddenWord.add('*');
        }
    }

    public ArrayList<Character> getHiddenWord() {
        return hiddenWord;
    }

    public String getWord() {
        return word;
    }

    public void run() {
        while (keepPlaying) {
            askPlayerforWord();
            guessLoop();
            checkForDeath();
            switchPlayers();
            keepGoing();
        }
    }

    public void keepGoing() {
        keepPlaying = userInputKeepingGoing();
    }

    public boolean userInputKeepingGoing() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Keep going? yes or no");
        if (sc.nextLine().equals("yes")) {
            System.out.println("yes");
            return true;

        } else {
            System.out.println("no");

            return false;
        }
    }

    public void switchPlayers() {
        Collections.rotate(players, 1);
        this.player1 = players.get(0);
        this.player2 = players.get(1);
    }

    private void checkForDeath() {
        if (player1.getLives() == 0) {
            System.out.println("You are dead, mate!");
        } else {
            System.out.println("You win!");
            System.out.println("The word was:");
            printHiddenWord();
        }
    }

    private char getGuess() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a guess");

        return sc.nextLine().charAt(0);
    }

    public void guessLoop() {
        while (player1.getLives() > 0 && hiddenWord.contains('*')) {
            hit = false;
            char guess = getGuess();
            totalGuesses += ", " + guess;
            checkGuess(guess);
            checkForHit();
            System.out.println(hiddenWord);
            System.out.println("Guesses: " + totalGuesses);
        }
    }

    private void printHiddenWord() {
        System.out.println(hiddenWord);
    }

    private void checkForHit() {
        if (hit == false) {
            player1.loseLife();
        }
    }

    public void checkGuess(char guess) {
        for (int index = 0; index < word.length(); index++) {
            if (guess == word.charAt(index)) {
                this.hiddenWord.set(index, guess);
                hit = true;
            }
        }
    }
}

