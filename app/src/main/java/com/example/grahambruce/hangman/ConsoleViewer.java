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

    public void enterName(Player player, int playerNum) {
        System.out.println(String.format("Enter your name, %s %d", player.getClass().getSimpleName(), playerNum));
    }

    public void enterWord(Player wordMaster) {
        System.out.println(String.format("Ladies' choice. Please enter a guess word, Wordmaster %s", wordMaster.getName()));
    }


    public void enterGuess(Player guesser) {
        System.out.println(String.format("Please guess a letter, %s", guesser.getName()));

    }


    public void status(Player guesser, ArrayList<Character> hiddenWord, String totalGuesses){
        livesLeft(guesser);
        printHiddenWord(hiddenWord);
        guessesMade(totalGuesses);

    }

    public void printHiddenWord(ArrayList<Character> hiddenWord) {
        String results = "";
        for (Character c : hiddenWord) {
            results += c;
        }
        System.out.println(results);
    }

    public void lifeLost(Player guesser) {
        System.out.println(String.format("Oh no! life lost, %s!", guesser.getName()));
    }


    public void gotAHit(Player guesser) {
        System.out.println(String.format("Blammo, %s! Another direct hit!", guesser.getName()));
    }

    public void guessesMade(String totalGuesses) {
        System.out.println("Guesses: " + totalGuesses);
    }

    public void livesLeft(Player guesser) {
        System.out.println(String.format("Lives left: %d", guesser.getLives()));
    }

    public void winOutcome(ArrayList<Character> hiddenWord) {

        System.out.println("You win!");
        System.out.println(String.format("The word was: %s", hiddenWord));

    }

    public void deathOutcome() {
        System.out.println("Oh no! You dead!");
    }

    public void keepPlaying(){
        System.out.println("Play again? 'yes' or 'no'?");
    }

    public void newLine(){
        System.out.println();
    }
}
