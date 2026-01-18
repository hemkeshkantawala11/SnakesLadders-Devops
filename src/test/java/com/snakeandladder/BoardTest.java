package com.snakeandladder;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void entityMapping_andGetEntityAt() {
        Board b = new Board(5);
        Snake s = new Snake("S1", 14, 4);
        Ladder l = new Ladder("L1", 3, 12);
        b.setEntities(Arrays.asList(s, l));

        assertEquals("S1", b.getEntityAt(14).getName());
        assertEquals("S1", b.getEntityAt(4).getName());
        assertEquals("L1", b.getEntityAt(3).getName());
        assertEquals("L1", b.getEntityAt(12).getName());
        assertNull(b.getEntityAt(2));
    }
}
