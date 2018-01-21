package ru.sbt.mipt.oop.smartHome;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.smartHome.homeElements.SmartHome;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeReader {
    public static SmartHome read() throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        return gson.fromJson(json, SmartHome.class);
    }
}