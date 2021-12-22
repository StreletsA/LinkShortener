package com.StreletsA.linkshortener.response;

import com.StreletsA.linkshortener.entity.LinkInfo;
import com.StreletsA.linkshortener.service.LinkInfoService;

public class StatsResponse {

	private final String link;
	private final String original;
	private final long rank;
	private final long count;

    public StatsResponse(LinkInfoService linkInfoService, LinkInfo linkInfo) {
        this.link = linkInfo.getShortLink();
        this.original = linkInfo.getOriginalLink();
        this.count = linkInfo.getVisits();
        this.rank = linkInfoService.getRankOfLink(linkInfo);
    }

    public String getLink() {
        return link;
    }
    
    public String getOriginal() {
    	return original;
    }
    
    public long getRank() {
    	return rank;
    }
    
    public long getCount() {
    	return count;
    }
	
}

