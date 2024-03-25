package com.example.usingapi;

public class coinData {

    private String id, name, image,last_updated;
    private double current_price, market_cap_rank,price_change_percentage_24h, market_cap_change_perecentage_24h,high_24h, low_24h,totalVolume;
    private long market_cap,rank ;
    public coinData() {
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(double current_price) {
        this.current_price = current_price;
    }

    public long getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(long market_cap) {
        this.market_cap = market_cap;
    }

    public double getMarket_cap_rank() {
        return market_cap_rank;
    }

    public void setMarket_cap_rank(double market_cap_rank) {
        this.market_cap_rank = market_cap_rank;
    }

    public double getPrice_change_percentage_24h() {
        return price_change_percentage_24h;
    }

    public void setPrice_change_percentage_24h(double price_change_percentage_24h) {
        this.price_change_percentage_24h = price_change_percentage_24h;
    }

    public double getMarket_cap_change_perecentage_24h() {
        return market_cap_change_perecentage_24h;
    }

    public void setMarket_cap_change_perecentage_24h(double market_cap_change_perecentage_24h) {
        this.market_cap_change_perecentage_24h = market_cap_change_perecentage_24h;
    }

    public double getHigh_24h() {
        return high_24h;
    }

    public void setHigh_24h(double high_24h) {
        this.high_24h = high_24h;
    }

    public double getLow_24h() {
        return low_24h;
    }

    public void setLow_24h(double low_24h) {
        this.low_24h = low_24h;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(double totalVolume) {
        this.totalVolume = totalVolume;
    }
}
