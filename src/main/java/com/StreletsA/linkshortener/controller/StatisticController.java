package com.StreletsA.linkshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StreletsA.linkshortener.entity.LinkInfo;
import com.StreletsA.linkshortener.response.AllLinkResponse;
import com.StreletsA.linkshortener.response.StatsResponse;
import com.StreletsA.linkshortener.service.LinkInfoService;

@RestController
@RequestMapping("/stats")
public class StatisticController {

	private final LinkInfoService linkInfoService;

    public StatisticController(@Autowired LinkInfoService linkInfoService){
        this.linkInfoService = linkInfoService;
    }
    
    @GetMapping("/{postfix}")
    public StatsResponse showAllLinks(@PathVariable String postfix) {
    	return new StatsResponse(linkInfoService, linkInfoService.getLinkInfoByPostfix(postfix));
    }
	
}
