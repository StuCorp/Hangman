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
        System.out.println(String.format("%s, give me a word!", player2.getName()));
        word = UserInput.getUserWord();
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
            totalGuesses = "";
            clearScreen();
            guessLoop();
            checkForDeath();
            switchPlayers();
            keepGoing();
        }
    }

    private void clearScreen() {
        for (int manyTime = 0; manyTime < 100; manyTime++) {
            for (int times = 0; times < 50; times++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }

    public void keepGoing() {
        keepPlaying = UserInput.getUserBoolean();
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

//    private char getGuess() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter a guess");
//
//        return sc.nextLine().charAt(0);
//    }

    public void guessLoop() {
        while (player1.getLives() > 0 && hiddenWord.contains('*')) {
            hit = false;
            System.out.println(String.format("%s, enter a guess", player1.getName()));
            char guess = UserInput.getUserChar();
            totalGuesses += ", " + guess;
            checkGuess(guess);
            checkForHit();
            System.out.println(String.format("%d lives left", player1.getLives()));
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

