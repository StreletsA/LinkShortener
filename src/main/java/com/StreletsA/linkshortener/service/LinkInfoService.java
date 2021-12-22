package com.StreletsA.linkshortener.service;

import com.StreletsA.linkshortener.entity.LinkInfo;
import com.StreletsA.linkshortener.repository.LinkInfoRepository;

import org.hibernate.query.criteria.internal.predicate.InPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    
    public LinkInfo getLinkInfoByPostfix(String postfix) {
        return getLinkInfoByShortLink("/l/" + postfix);
    }

    public LinkInfo addLinkInfo(String originalLink){

        LinkInfo linkInfo = new LinkInfo();
        linkInfo.setShortLink(makeShortLink(nextLinkNumber));
        linkInfo.setOriginalLink(originalLink);
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
    
    public LinkInfo getLinkInfoByOriginallLink(String original) {
    	return linkInfoRepository.findByOriginalLink(original);
    }
    
    public long getRankOfLink(LinkInfo linkInfo) {
    	List<LinkInfo> allLinkInfos = getAllLinks();
    	Collections.sort(allLinkInfos, Comparator.comparing(li -> li.getVisits()));
    	return allLinkInfos.indexOf(linkInfo) + 1;
    }
    
    public void visitLink(String postfix) {
    	LinkInfo visited = linkInfoRepository.findById("/l/" + postfix).get();
    	visited.visit();
    	saveOrUpdate(visited);
    }

}
