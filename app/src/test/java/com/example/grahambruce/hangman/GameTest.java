package com.example.grahambruce.hangman;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by grahambruce on 28/06/2017.
 */

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;
    private ArrayList<Player> players;

    @Before
    public void before(){
        player1 = new Player("Stu", 6);
        player2 = new Player("Graham", 6);
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        game = new Game(players);
    }

//    @Test
//    public void testGetWord(){
//        game.askPlayerforWord();
//        assertEquals("Godzilla", game.getWord());
//        assertEquals(8, game.getHiddenWord().length());
////        game.hiddenWord.insert(5, 'c');
////        assertEquals(1, game.getHiddenWord().length());
//
//    }
//
//    @Test
//    public void testCheckGuess(){
//        game.askPlayerforWord();
//        game.checkGuess('l');
//        assertEquals(8, game.hiddenWord.length());
//    }

    @Test
    public void testPlayerSwitch(){
        assertEquals("Stu", game.player1.getName());
        game.switchPlayers();
        assertEquals("Graham", game.player1.getName());

    }

}
