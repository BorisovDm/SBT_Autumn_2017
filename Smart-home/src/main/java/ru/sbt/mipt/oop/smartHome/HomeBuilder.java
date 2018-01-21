package ru.sbt.mipt.oop.smartHome;

import ru.sbt.mipt.oop.smartHome.homeElements.Door;
import ru.sbt.mipt.oop.smartHome.homeElements.Light;
import ru.sbt.mipt.oop.smartHome.homeElements.Room;
import ru.sbt.mipt.oop.smartHome.homeElements.SmartHome;

import java.io.IOException;
import java.util.Arrays;

public class HomeBuilder {
    public static void main(String[] args) throws IOException {
        SmartHome smartHome = create();
        SmartHomeSaver.save(smartHome);
    }

    public static SmartHome create() {
        Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1"), new Door(true, "10")),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door(false, "2"), new Door(false, "20")),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door(true, "3"), new Door(true, "30")),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Arrays.asList(new Door(false, "4")),
                "hall");
        return new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
    }
}
