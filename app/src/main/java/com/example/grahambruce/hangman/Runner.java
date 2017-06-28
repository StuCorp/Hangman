package com.example.grahambruce.hangman;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by grahambruce on 28/06/2017.
 */

public class Runner {

    public static void main(String[] args) {

        Player player1 = new Player("Davie", 6);
        Player player2 = new Player("Mickb", 6);
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        Game game = new Game(players);
        game.run();
    }

}