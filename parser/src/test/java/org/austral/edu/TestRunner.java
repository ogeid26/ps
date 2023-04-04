package org.austral.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRunner {

    @Test
    public void testParser() {
        assertEquals(48, new Parser().testing());
    }
}
