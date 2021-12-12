package com.StreletsA.linkshortener.exception;

public class LinkNotFoundException extends Exception{

    public LinkNotFoundException(){
        super("Link not found");
    }

}
