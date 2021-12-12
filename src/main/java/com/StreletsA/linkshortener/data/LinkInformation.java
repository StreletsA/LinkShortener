package com.StreletsA.linkshortener.data;

public class LinkInformation {

    private final String shortLink;
    private final String normalLink;
    private int visits;

    public LinkInformation(String shortLink, String normalLink, int visits){

        this.shortLink = shortLink;
        this.normalLink = normalLink;
        this.visits = visits;

    }

    public LinkInformation(String shortLink, String normalLink){

        this.shortLink = shortLink;
        this.normalLink = normalLink;
        this.visits = 0;

    }

    public String getShortLink() {
        return shortLink;
    }

    public String getNormalLink() {
        return normalLink;
    }

    public int getVisits() {
        return visits;
    }

    public void addVisit(){
        visits++;
    }

    public void addVisits(int count){
        visits += count;
    }
}
