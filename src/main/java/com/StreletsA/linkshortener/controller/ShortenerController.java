package com.StreletsA.linkshortener.controller;

import com.StreletsA.linkshortener.core.OriginalLink;
import com.StreletsA.linkshortener.response.AllLinkResponse;
import com.StreletsA.linkshortener.response.LinkResponse;
import com.StreletsA.linkshortener.service.LinkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generate")
public class ShortenerController {

    private final LinkInfoService linkInfoService;

    public ShortenerController(@Autowired LinkInfoService linkInfoService){
        this.linkInfoService = linkInfoService;
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public LinkResponse shortenLink(@RequestBody OriginalLink original){
        return new LinkResponse(linkInfoService, original.getOriginal());
    }
    
    @GetMapping("/all")
    public AllLinkResponse showAllLinks() {
    	return new AllLinkResponse(linkInfoService);
    }

}
