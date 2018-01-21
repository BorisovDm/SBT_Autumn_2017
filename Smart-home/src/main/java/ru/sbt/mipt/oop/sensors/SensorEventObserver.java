package ru.sbt.mipt.oop.sensors;

import ru.sbt.mipt.oop.smartHome.homeElements.SmartHome;
import ru.sbt.mipt.oop.eventHandler.EventHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SensorEventObserver {
    private Collection<EventHandler> handlers = new ArrayList<>();

    public SensorEventObserver(List<EventHandler> handlers) {
        this.handlers = handlers;
    }

    public void handle(SmartHome smartHome, SensorEvent event) {
        for (EventHandler handler: handlers) {
            handler.handle(smartHome, event);
        }
    }
}
