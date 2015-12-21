package org.shopkeeper.database.parsers;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by root on 21-12-15.
 */
public class ResultParserTest {

    @Test
    public void testResultParser() {
        assertTrue(ResultParser.queryWithResult("SELECT * FROM TABLE"));
        assertTrue(ResultParser.queryWithResult("select * From Table"));
        assertTrue(ResultParser.queryWithResult("SelEct * fRom TaBALle"));
        assertTrue(ResultParser.queryWithResult("SelEct COUNT(row) fRom TaBALle"));
        assertFalse(ResultParser.queryWithResult("Insert INTO Table"));
        assertFalse(ResultParser.queryWithResult("DELETE from Table"));
        assertFalse(ResultParser.queryWithResult("DELETE from Table"));
        assertFalse(ResultParser.queryWithResult("UPDATE TABLE FROM TABLE WHERE ID (SELECT * FROM BANAAN)"));
    }

}