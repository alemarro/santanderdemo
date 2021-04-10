package com.santanderdemo.app;

import java.util.Date;

class Feed {
    private int id;
    private String name;
    private float bid;
    private float ask;
    private Date timestamp;

    public Feed() {}

    public Feed(int id, String name, float bid, float ask, Date timestamp) {
        this.id = id;
        this.name = name;
        this.bid = bid;
        this.ask = ask;
        this.timestamp = timestamp;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public float getBid() {
        return this.bid;
    }

    public float getAsk() {
        return this.ask;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    /**
     * Returns adjusted price based on commission. 
     * This functions should be inside a ClientController, but for simplicity its here.
     * @param type the type of data. When type is False adjust Bid. Adjust Ask otherwise
     * @return the adjusted price
     */
    public Float addCommission(boolean type) {
        return type ? this.ask*1.001f : this.bid*0.999f;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", bid='" + this.addCommission(false) + "'" +
            ", ask='" + this.addCommission(true) + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }

}