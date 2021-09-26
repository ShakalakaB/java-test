package com.awesome.wow.designpattern.observe;

import lombok.Getter;
import lombok.Setter;

import java.util.Observable;

@Getter
public class NewsAgency extends Observable {
    private String news;

    public void setNews(String news) {
        this.news = news;
        setChanged();
        notifyObservers(news);
    }
}
