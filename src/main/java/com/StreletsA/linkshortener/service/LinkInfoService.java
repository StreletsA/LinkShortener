package com.StreletsA.linkshortener.service;

import com.StreletsA.linkshortener.data.LinkInformation;
import com.StreletsA.linkshortener.entity.LinkInfo;
import com.StreletsA.linkshortener.exception.LinkNotFoundException;
import com.StreletsA.linkshortener.repository.LinkInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinkInfoService {

    final LinkInfoRepository linkInfoRepository;

    private String host;
    private int nextLinkNumber;

    public LinkInfoService(@Value("${shortener.baselink}") String host, @Autowired LinkInfoRepository linkInfoRepository){

        this.host = host;
        nextLinkNumber = 1;

        this.linkInfoRepository = linkInfoRepository;
    }

    public List getLinkInfo() {
        List linkInfoList = new ArrayList();
        linkInfoRepository.findAll().forEach(person -> linkInfoList.add(person));
        return linkInfoList;
    }

    public LinkInfo getLinkInfoByShortLink(String shortLink) {
        return (LinkInfo) linkInfoRepository.findById(shortLink).get();
    }

    public LinkInfo addLinkInfo(String normalLink){

        LinkInfo linkInfo = new LinkInfo();
        linkInfo.setShortLink(makeShortLink(nextLinkNumber));
        linkInfo.setNormalLink(validateLink(normalLink));
        linkInfo.setVisits(0);

        nextLinkNumber++;

        saveOrUpdate(linkInfo);

        return linkInfo;
    }

    public void getAllLinks(){

    }

    public void saveOrUpdate(LinkInfo linkInfo) {
        linkInfoRepository.save(linkInfo);
    }

    public void delete(String shortLink) {
        linkInfoRepository.deleteById(shortLink);
    }

    public String makeShortLink(int linkNumber){

        String shortLink = "";

        while(linkNumber > 0){

            int tmp = linkNumber;

            if(linkNumber > 25){
                linkNumber -= 25;
                continue;
            }

            shortLink = shortLink.concat(String.valueOf((char)(65 + tmp)));

            break;
        }

        return host + shortLink;

    }

    private String validateLink(String link){

        if (!link.startsWith("http")){
            link = "http://" + link;
        }

        return link;

    }

}
