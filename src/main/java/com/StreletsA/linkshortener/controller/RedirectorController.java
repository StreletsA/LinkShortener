package com.StreletsA.linkshortener.controller;

import com.StreletsA.linkshortener.exception.LinkNotFoundException;
import com.StreletsA.linkshortener.service.LinkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/l/")
public class RedirectorController {

    private final LinkInfoService linkInfoService;
    private final String host;


    public RedirectorController(@Value("${shortener.baselink}") String host,
                                @Autowired LinkInfoService linkInfoService) {
        this.linkInfoService = linkInfoService;
        this.host = host;
    }

    @GetMapping("/{postfix}")
    public ModelAndView redirectToNormalLink(@PathVariable String postfix) throws LinkNotFoundException {
    	linkInfoService.visitLink(postfix);
        return new ModelAndView("redirect:" +
                linkInfoService.getLinkInfoByShortLink("/l/" + postfix).getOriginalLink());
    }

}
