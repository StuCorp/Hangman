package com.example.grahambruce.hangman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static android.R.attr.name;
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



    public void run() {
        viewer.welcome();
        setNames(players);
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

    public void guessLoop() {
        while (guesser.getLives() > 0 && hiddenWord.contains('*')) {
            hit = false;
            viewer.enterGuess(guesser);
            char guess = UserInput.getUserChar();
            totalGuesses += ", " + guess;
            checkGuess(guess);
            checkForHit();
            viewer.status(guesser, hiddenWord, totalGuesses);
        }
    }


    public void setNames(ArrayList<Player> players) {
        int playerNum = 1;
        for (Player player : players){
            viewer.enterName(player, playerNum);
            String name = UserInput.getUserWord();
            player.setName(name);
            playerNum++;
        }
    }

    public void askPlayerforWord() {
        viewer.enterWord(wordMaster);
        word = UserInput.getUserWord();
        this.hiddenWord = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            hiddenWord.add('*');
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
        viewer.keepPlaying();
        keepPlaying = UserInput.getUserBoolean();
    }


    public void switchPlayers() {
        Collections.rotate(players, 1);
        this.guesser = players.get(0);
        this.wordMaster = players.get(1);
        this.guesser.setLives(6);
    }

    private void checkForDeath() {
        if (guesser.getLives() == 0) {
            viewer.deathOutcome();
        } else {
            viewer.winOutcome(hiddenWord);

        }
    }


    private void checkForHit() {
        viewer.newLine();
        if (hit == false) {
            guesser.loseLife();
            viewer.lifeLost(guesser);
        }
        else {
            viewer.gotAHit(guesser);
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

