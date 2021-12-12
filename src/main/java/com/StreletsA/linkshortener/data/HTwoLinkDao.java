package com.StreletsA.linkshortener.data;

import com.StreletsA.linkshortener.exception.LinkNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HTwoLinkDao implements LinkDao {

    private int nextLinkNumber;
    private List<LinkInformation> links;
    private String host;

    public HTwoLinkDao(@Value("${shortener.baselink}") String host){

        this.host = host;
        nextLinkNumber = 1;
        links = new ArrayList<>();

    }

    @Override
    public String addShortLink(String link){
        String shortLink = makeShortLink(nextLinkNumber);
        links.add(new LinkInformation(shortLink, validateLink(link)));
        nextLinkNumber++;
        return shortLink;
    }

    @Override
    public String getNormalLink(String postfix) throws LinkNotFoundException {

        for (LinkInformation linkInformation : links){

            if(linkInformation.getShortLink().equals(host + postfix)){
                linkInformation.addVisit();
                return linkInformation.getNormalLink();
            }

        }

        throw new LinkNotFoundException();

    }

    @Override
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

    @Override
    public String getStringLinks() {
        StringBuilder answer = new StringBuilder();

        for (LinkInformation linkInformation : links){

            answer.append(linkInformation.getShortLink()).
                    append(" : ").
                    append(linkInformation.getNormalLink()).
                    append(" : ").
                    append(linkInformation.getVisits()).
                    append("\n");

        }

        return answer.toString();
    }

    private String validateLink(String link){

        if (!link.startsWith("http")){
            link = "http://" + link;
        }

        return link;

    }
}
