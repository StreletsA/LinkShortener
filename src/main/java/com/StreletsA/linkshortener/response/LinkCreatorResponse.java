package com.StreletsA.linkshortener.response;

import com.StreletsA.linkshortener.data.LinkDao;
import com.StreletsA.linkshortener.entity.LinkInfo;
import com.StreletsA.linkshortener.service.LinkInfoService;

public class LinkCreatorResponse {

    private final String shortLink;

    public LinkCreatorResponse(LinkInfoService linkInfoService, String link) {
        this.shortLink = linkInfoService.addLinkInfo(link).getShortLink();
    }

    public String getShortLink() {
        return shortLink;
    }

}
