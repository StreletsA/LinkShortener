package com.StreletsA.linkshortener.response;

import com.StreletsA.linkshortener.service.LinkInfoService;

public class LinkResponse {

    private final String link;

    public LinkResponse(LinkInfoService linkInfoService, String link) {
        this.link = linkInfoService.addLinkInfo(link).getShortLink();
    }

    public String getLink() {
        return link;
    }

}
