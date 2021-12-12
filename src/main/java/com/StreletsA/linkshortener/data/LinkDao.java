package com.StreletsA.linkshortener.data;

import com.StreletsA.linkshortener.exception.LinkNotFoundException;

public interface LinkDao {

    String addShortLink(String link);

    String getNormalLink(String shortLink) throws LinkNotFoundException;

    String makeShortLink(int linkNumber);

    String getStringLinks();

}
