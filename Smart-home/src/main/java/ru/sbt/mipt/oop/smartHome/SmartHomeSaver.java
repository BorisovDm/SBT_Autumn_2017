package ru.sbt.mipt.oop.smartHome;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.smartHome.homeElements.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SmartHomeSaver {
    public static void save(SmartHome smartHome) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        Path path = Paths.get("smart-home-1.js");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
            System.out.println("\nSmartHome final state was successfully saved");
        } catch (Exception e){
            System.out.println("\nSmartHome final state was not saved");
        }
    }
}
