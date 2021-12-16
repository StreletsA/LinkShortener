package com.StreletsA.linkshortener.response;

import java.util.ArrayList;
import java.util.List;

import com.StreletsA.linkshortener.entity.LinkInfo;
import com.StreletsA.linkshortener.service.LinkInfoService;

public class AllLinkResponse {

    private final List<LinkInfo> links;

    public AllLinkResponse(LinkInfoService linkInfoService) {
        this.links = linkInfoService.getAllLinks();
    }

	public List<LinkInfo> getLinks() {
        return links;
    }

}

