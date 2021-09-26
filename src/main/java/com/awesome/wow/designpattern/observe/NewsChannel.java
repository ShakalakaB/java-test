package com.awesome.wow.designpattern.observe;

import lombok.Getter;
import lombok.Setter;

import java.util.Observable;
import java.util.Observer;

@Getter
@Setter
public class NewsChannel implements Observer {
    private String news;
    @Override
    public void update(Observable o, Object arg) {
        news = (String) arg;
        System.out.println("updating news: " + news);
    }
}
