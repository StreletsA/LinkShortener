package com.StreletsA.linkshortener.service;

import com.StreletsA.linkshortener.entity.LinkInfo;
import com.StreletsA.linkshortener.repository.LinkInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinkInfoService {

    final LinkInfoRepository linkInfoRepository;
    
    private int nextLinkNumber;

    public LinkInfoService(@Autowired LinkInfoRepository linkInfoRepository){
        nextLinkNumber = 1;

        this.linkInfoRepository = linkInfoRepository;
    }

    public LinkInfo getLinkInfoByShortLink(String shortLink) {
        return linkInfoRepository.findById(shortLink).get();
    }

    public LinkInfo addLinkInfo(String normalLink){

        LinkInfo linkInfo = new LinkInfo();
        linkInfo.setShortLink(makeShortLink(nextLinkNumber));
        linkInfo.setNormalLink(normalLink);
        linkInfo.setVisits(0);

        nextLinkNumber++;

        saveOrUpdate(linkInfo);

        return linkInfo;
    }

    public List<LinkInfo> getAllLinks(){
    	List<LinkInfo> links = new ArrayList<>();
    	
    	linkInfoRepository.findAll().forEach(linkInfo -> links.add(linkInfo));
    	
    	return links;
    }

    public void saveOrUpdate(LinkInfo linkInfo) {
        linkInfoRepository.save(linkInfo);
    }

    public void delete(String shortLink) {
        linkInfoRepository.deleteById(shortLink);
    }

    public String makeShortLink(int linkNumber){

        String shortLink = "/l/";

        while(linkNumber > 0){

            int tmp = linkNumber;

            if(linkNumber > 25){
                linkNumber -= 25;
                continue;
            }

            shortLink = shortLink.concat(String.valueOf((char)(65 + tmp)));

            break;
        }

        return shortLink;

    }

}
