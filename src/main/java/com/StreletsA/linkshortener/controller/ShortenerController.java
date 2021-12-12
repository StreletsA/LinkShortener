package com.StreletsA.linkshortener.controller;

import com.StreletsA.linkshortener.data.LinkDao;
import com.StreletsA.linkshortener.response.LinkCreatorResponse;
import com.StreletsA.linkshortener.service.LinkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shortener")
public class ShortenerController {

    //private final LinkDao linkDao;
    private final LinkInfoService linkInfoService;

    public ShortenerController(@Autowired LinkInfoService linkInfoService){
        this.linkInfoService = linkInfoService;
    }

    @GetMapping("/shorten/{link}")
    public LinkCreatorResponse shortenLink(@PathVariable String link){
        return new LinkCreatorResponse(linkInfoService, link);
    }

    /*
    @GetMapping("/all")
    public String shortenLink(){
        return linkInfoService.getAllLinks();
    }
     */

}
