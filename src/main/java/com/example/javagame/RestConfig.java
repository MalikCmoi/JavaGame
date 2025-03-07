package com.example.javagame;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
public class RestConfig extends Application {
    // Rien de particulier à mettre si vous utilisez le "scan" automatique
}