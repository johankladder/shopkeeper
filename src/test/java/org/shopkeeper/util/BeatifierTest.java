package org.shopkeeper.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by johankladder on 1/10/16.
 */
public class BeatifierTest {

    @Test
    public void testBeatifyString() throws Exception {
        assertEquals("Test", Beatifier.beautifyString("test"));
        assertEquals("Test", Beatifier.beautifyString("test"));
        assertEquals("Test", Beatifier.beautifyString("   test   "));
        assertEquals("Test", Beatifier.beautifyString("   test"));
        assertEquals("Test", Beatifier.beautifyString("test   "));
        assertNull(Beatifier.beautifyString(null));
        assertNull(Beatifier.beautifyString(""));

    }
}