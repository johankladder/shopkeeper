package org.shopkeeper.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PriceGeneratorTest {

    @Test
    public void testPrices() {
        assertEquals(new Double(12.45), PriceGenerator.getPriceFromString("12.45"));
        assertEquals(new Double(13.38), PriceGenerator.getPriceFromString("13,38"));
        assertEquals(new Double(1.5), PriceGenerator.getPriceFromString("1.5   "));
        assertNull(PriceGenerator.getPriceFromString("12.5 p"));
    }

}