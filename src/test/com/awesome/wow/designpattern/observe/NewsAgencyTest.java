package com.awesome.wow.designpattern.observe;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsAgencyTest {

    @Test
    public void setNews() {
        NewsAgency newsAgency = new NewsAgency();
        NewsChannel newsChannel = new NewsChannel();
        newsAgency.addObserver(newsChannel);

        newsAgency.setNews("kk");
        assertEquals("kk", newsChannel.getNews());
    }
}