package com.example.grahambruce.hangman;

import java.util.ArrayList;

/**
 * Created by stuartbryce on 2017-06-29.
 */

public interface Viewer {


    public void welcome();

    public void enterName(Player player);

    public void enterWord(Player wordMaster);

    public void enterGuess(Player guesser);

    public void status(Player guesser, ArrayList<Character> hiddenWord, String totalGuesses);

    public void printHiddenWord(ArrayList<Character> hiddenWord);

    public void lifeLost(Player guesser);


    public void gotAHit(Player guesser);

    public void guessesMade(String totalGuesses);

    public void livesLeft(Player guesser);

    public void winOutcome(ArrayList<Character> hiddenWord);

    public void deathOutcome();

    public void keepPlaying();

}

