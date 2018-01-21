package ru.sbt.mipt.oop.eventHandler;

import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.smartHome.homeElements.SmartHome;

public interface EventHandler {
    void handle(SmartHome smartHome, SensorEvent event);
}
