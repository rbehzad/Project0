package com.example.omarket.backend.application;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication {
    public static ExecutorService executorService = Executors.newFixedThreadPool(4);
}
