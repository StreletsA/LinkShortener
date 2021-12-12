package com.StreletsA.linkshortener.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LinkInfo {

    @Id
    private String shortLink;
    private String normalLink;
    private int visits;

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public void setNormalLink(String normalLink) {
        this.normalLink = normalLink;
    }

    public void setVisits(int visits) {
        this.visits = visits;
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
}
