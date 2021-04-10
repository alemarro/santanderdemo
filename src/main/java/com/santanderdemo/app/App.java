package com.santanderdemo.app;


public class App 
{
    public static void main( String[] args )
    {
        Subscriber client = new Subscriber();
        // Correct test
        client.onMessage("106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001");
        // Wrong test
        client.onMessage("107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002");
        // Exception
        client.onMessage("1b, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002");

        // Appropriate tests can be seen in AppTest.java
    }
}
