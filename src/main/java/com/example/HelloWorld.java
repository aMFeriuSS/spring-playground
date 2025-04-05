package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        logger.info("Starting HelloWorld application");
        System.out.println("Hello, World!");
        logger.info("HelloWorld application completed");
    }
}
