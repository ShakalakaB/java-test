package com.awesome.wow.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SharedMapWithUserContext implements Runnable{
    public static Map<Integer, Context> userContextPerUserId = new ConcurrentHashMap<>();

    private final Integer userId;

    private final UserRepository userRepository = new UserRepository();

    public SharedMapWithUserContext(Integer userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        String userName = userRepository.getUserNameForUserId(userId);
        userContextPerUserId.put(userId, new Context(userName));
    }
}
