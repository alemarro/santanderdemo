package com.santanderdemo.app;

import java.text.SimpleDateFormat;

/**
 * SubscriberInterface
 * The interface defined by the provider.
 */
interface ISubscriber {
    public void onMessage(String msg);
    public Feed findLatest();
}

/**
 * Subscriber as Spring service
 * \@Service
 */
public class Subscriber implements ISubscriber {
    Feed feed = null;

    @Override
    public void onMessage(String msg) {
        Feed feed = handleFeed(msg);
        deliverUpdatedFeed(feed);
    }

    private Feed handleFeed(String msg) {
        // For simplicity, I am considering that the message is line by line instead of
        // the whole csv displayed in the doc
        // In case the whole csv is to be considered, then use CSVReader or just split
        // by ...
        String[] feedInfo = msg.split(",");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS");

        Feed feedObject = null;
        try {
            feedObject = new Feed(
                    // id : Integer
                    Integer.parseInt(feedInfo[0]),
                    // Name : Instrument Name EUR/USD
                    feedInfo[1],
                    // Bid : Bid value
                    Float.parseFloat(feedInfo[2]),
                    // Ask : Asked value
                    Float.parseFloat(feedInfo[3]),
                    // Date : Format dd-MM-yyyy HH:mm:ss:SSS
                    format.parse(feedInfo[4]));
        } catch (Exception e) {
            System.out.println("Failed to parse");
            return null;
        }

        return feedObject;
    }

    private boolean deliverUpdatedFeed(Feed feed) {
        if (feed == null) {
            System.out.println("");
            return false;
        }
        System.out.println(feed.toString());
        this.feed = feed;
        return true;
    }

    /**
     * Provide the latest feed to FeedController (RestController)
     */
    @Override
    public Feed findLatest() {
        return feed;
    }
}
