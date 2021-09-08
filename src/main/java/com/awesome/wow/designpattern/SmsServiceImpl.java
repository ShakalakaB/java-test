package com.awesome.wow.designpattern;

public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        this.internalSend();
        return message;
    }

    public void internalSend() {
        System.out.println("internal message:");
    }
}
