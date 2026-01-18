package com.snakeandladder;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardDisplayTest {

    @Test
    public void display_showsEntitiesAndPlayers() {
        Board board = new Board(3);
        // place a snake and a ladder
        Snake s = new Snake("S1", 9, 3);
        Ladder l = new Ladder("L1", 2, 7);
        board.setEntities(java.util.Arrays.asList(s, l));

        Player p = new HumanPlayer("Alice", "Blue");
        p.setPosition(5);
        List<Player> players = new ArrayList<>();
        players.add(p);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(baos));
        try {
            board.display(players);
        } finally {
            System.setOut(oldOut);
        }

        String out = baos.toString();
        assertTrue(out.contains("S1"), "should display the snake label S1");
        assertTrue(out.contains("L1"), "should display the ladder label L1");
        // player initial A should be present at some cell
        assertTrue(out.contains("(A)"), "should display player initial");
    }
}
