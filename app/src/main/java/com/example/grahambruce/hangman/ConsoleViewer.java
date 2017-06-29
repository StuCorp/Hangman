package com.example.grahambruce.hangman;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by stuartbryce on 2017-06-29.
 */

public class ConsoleViewer implements Viewer {

    public void welcome() {
        System.out.println("Welcome to HangPerson!");

    }

    public void enterName(Player player) {
        System.out.println(String.format("Enter your name, %s", player.getClass().getSimpleName()));
    }

    public void enterWord(Player wordMaster) {
        System.out.println(String.format("Please enter a guess word, Wordmaster %s", wordMaster.getName()));
    }

    public void enterGuess(Player guesser) {
        System.out.println(String.format("Please guess a letter, %s", guesser.getName()));

    }

    public void printHiddenWord(ArrayList<Character> hiddenWord) {
        String results = "";
        for (Character c : hiddenWord) {
            results += c;
        }
        System.out.println(results);
    }

    public void lifeLost(Player guesser) {
        System.out.print(String.format("Oh no! life lost, %s!", guesser.getName()));
    }


    public void gotAHit(Player guesser) {
        System.out.print(String.format("Blammo, %s! Another direct hit!", guesser.getName()));
    }

    public void guessesMade(String totalGuesses) {
        System.out.print("Guesses: " + totalGuesses);
    }

    public void livesLeft(Player guesser) {
        System.out.println(String.format("Lives left: %d", guesser.getLives()));
    }

    public void winOutcome() {
        System.out.println("You win!");
    }

    public void deathOutcome() {
        System.out.print("Oh no! You dead!");
    }

    public void keepPlaying(){
        System.out.print("Play again? 'yes' or 'no'?");
    }
}
