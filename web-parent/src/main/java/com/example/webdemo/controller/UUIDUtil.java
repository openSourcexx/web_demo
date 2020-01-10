package com.example.webdemo.controller;

import java.util.UUID;

/**
 * uuid
 */
public class UUIDUtil {
    public static String nextId() {
        return UUID.randomUUID()
            .toString()
            .trim()
            .replaceAll("-", "")
            .toUpperCase();
    }
}
