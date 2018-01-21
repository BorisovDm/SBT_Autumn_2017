package ru.sbt.mipt.oop.eventHandler;

import ru.sbt.mipt.oop.smartHome.homeElements.Light;
import ru.sbt.mipt.oop.smartHome.homeElements.Room;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.smartHome.homeElements.SmartHome;

import java.util.Iterator;

import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) {
            return;
        }
        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator lightsIterator = room.getLightsIterator();
            while (lightsIterator.hasNext()) {
                Light light = (Light) lightsIterator.next();
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setOn(true);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    } else {
                        light.setOn(false);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
