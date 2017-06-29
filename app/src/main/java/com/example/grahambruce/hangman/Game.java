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
    Player guesser;
    Player wordMaster;
    boolean hit;
    String totalGuesses;
    Viewer viewer;

    public Game(ArrayList<Player> players, Viewer viewer) {
        this.guesser = players.get(0);
        this.wordMaster = players.get(1);
        this.players = players;
        this.keepPlaying = true;
        this.totalGuesses = "";
        this.viewer = viewer;
    }

    public void askPlayerforWord() {
        System.out.println(String.format("%s, give me a word!", wordMaster.getName()));
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
        this.guesser = players.get(0);
        this.wordMaster = players.get(1);
    }

    private void checkForDeath() {
        if (guesser.getLives() == 0) {
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
        while (guesser.getLives() > 0 && hiddenWord.contains('*')) {
            hit = false;
            System.out.println(String.format("%s, enter a guess", guesser.getName()));
            char guess = UserInput.getUserChar();
            totalGuesses += ", " + guess;
            checkGuess(guess);
            checkForHit();
            System.out.println(String.format("%d lives left", guesser.getLives()));
            System.out.println(hiddenWord);
            System.out.println("Guesses: " + totalGuesses);
        }
    }

    private void printHiddenWord() {
        System.out.println(hiddenWord);
    }

    private void checkForHit() {
        if (hit == false) {
            guesser.loseLife();
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

