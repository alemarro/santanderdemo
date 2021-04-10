package com.santanderdemo.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @After
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void shouldAnswerWithTrue() {
        Subscriber client = new Subscriber();
        // Correct case
        client.onMessage("106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001");
        assertEquals(
                "{ id='106', name=' EUR/USD', bid='1.0989001', ask='1.2012001', timestamp='Mon Jun 01 12:01:01 BST 2020'}",
                out.toString());
    }

    @Test
    public void shouldAnswerWithFalse() {
        Subscriber client = new Subscriber();
        // Wrong case
        client.onMessage("107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002");
        assertNotEquals(
                "{ id='200', name=' EUR/JPY', bid='100', ask='120.019905', timestamp='Mon Jun 01 12:01:02 BST 2020'}",
                out.toString());
    }

    @Test
    public void shouldAnswerWithFailed() {
        Subscriber client = new Subscriber();
        // Wrong case
        client.onMessage("1b, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002");
        assertNotEquals(
                "",
                out.toString());
    }
}
