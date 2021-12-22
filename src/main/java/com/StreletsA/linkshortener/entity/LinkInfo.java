package com.StreletsA.linkshortener.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LinkInfo {

    @Id
    private String shortLink;
    private String originalLink;
    private int visits;

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public String getShortLink() {
        return shortLink;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public int getVisits() {
        return visits;
    }
    
    public void visit() {
    	visits++;
    }
}
