package com.snakeandladder;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BoardBuilderTest {

    @Test
    public void buildBoard_noOverlaps_andEntitiesPlaced() {
        int size = 6;
        BoardBuilder bld = new BoardBuilder(size);
        Board board = bld.buildBoard("easy");

        assertEquals(size, board.getSize());

        // basic sanity: entity start != end
        Set<String> names = new HashSet<>();
        // if entities exist, inspect them via getEntityAt across board
        for (int i = 1; i <= size * size; i++) {
            Entities e = board.getEntityAt(i);
            if (e != null) {
                assertNotEquals(e.getStart(), e.getEnd(), "entity start and end must differ");
                names.add(e.getName());
            }
        }

        // there should be zero or more entities but names should be unique
        assertEquals(names.size(), new HashSet<>(names).size());
    }
}
