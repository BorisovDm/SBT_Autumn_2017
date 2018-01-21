package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.eventHandler.EventHandler;
import ru.sbt.mipt.oop.eventHandler.LightEventHandler;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.smartHome.HomeBuilder;
import ru.sbt.mipt.oop.smartHome.homeElements.Light;
import ru.sbt.mipt.oop.smartHome.homeElements.Room;
import ru.sbt.mipt.oop.smartHome.homeElements.SmartHome;

import java.util.*;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.sensors.SensorEventType.*;

public class LightEventHandlerTest {
    @Test
    public void testSwitchOffAllLights() {
        SmartHome smartHome = HomeBuilder.create();
        EventHandler switchOffAllLightsHandler = new LightEventHandler();
        List<SensorEvent> lightEvents = new ArrayList<>();

        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator lightsIterator = room.getLightsIterator();
            while (lightsIterator.hasNext()) {
                Light light = (Light) lightsIterator.next();
                lightEvents.add(new SensorEvent(LIGHT_OFF, light.getId()));
            }
        }

        for (SensorEvent event: lightEvents) {
            switchOffAllLightsHandler.handle(smartHome, event);
        }

        roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator lightsIterator = room.getLightsIterator();
            while (lightsIterator.hasNext()) {
                Light light = (Light) lightsIterator.next();
                assertEquals(false, light.isOn());
            }
        }
    }

    @Test
    public void testSwitchOnAllLights() {
        SmartHome smartHome = HomeBuilder.create();
        EventHandler switchOnAllLightsHandler = new LightEventHandler();
        List<SensorEvent> lightEvents = new ArrayList<>();

        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator lightsIterator = room.getLightsIterator();
            while (lightsIterator.hasNext()) {
                Light light = (Light) lightsIterator.next();
                lightEvents.add(new SensorEvent(LIGHT_ON, light.getId()));
            }
        }

        for (SensorEvent event: lightEvents) {
            switchOnAllLightsHandler.handle(smartHome, event);
        }

        roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator lightsIterator = room.getLightsIterator();
            while (lightsIterator.hasNext()) {
                Light light = (Light) lightsIterator.next();
                assertEquals(true, light.isOn());
            }
        }
    }

    @Test
    public void testSwitchOnOneLight() {
        SmartHome smartHome = HomeBuilder.create();
        Map<String, Boolean> lightInitialState = new HashMap();
        boolean isLightOnChosen = false;
        String lightIdToSwitchOn = null;

        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator lightsIterator = room.getLightsIterator();
            while (lightsIterator.hasNext()) {
                Light light = (Light) lightsIterator.next();
                lightInitialState.put(light.getId(), light.isOn());
                if (!isLightOnChosen && !light.isOn()) {
                    lightIdToSwitchOn = light.getId();
                    isLightOnChosen = true;
                }
            }
        }
        if (!isLightOnChosen) return;

        new LightEventHandler().handle(smartHome, new SensorEvent(LIGHT_ON, lightIdToSwitchOn));
        roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator lightsIterator = room.getLightsIterator();
            while (lightsIterator.hasNext()) {
                Light light = (Light) lightsIterator.next();
                if (light.getId().equals(lightIdToSwitchOn)) {
                    assertEquals(true, light.isOn());
                }
                else {
                    assertEquals(lightInitialState.get(light.getId()), light.isOn());
                }
            }
        }
    }

    @Test
    public void testSwitchOffOneLight() {
        SmartHome smartHome = HomeBuilder.create();
        Map<String, Boolean> lightInitialState = new HashMap();
        boolean isLightOffChosen = false;
        String lightIdToSwitchOff = null;

        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator lightsIterator = room.getLightsIterator();
            while (lightsIterator.hasNext()) {
                Light light = (Light) lightsIterator.next();
                lightInitialState.put(light.getId(), light.isOn());
                if (!isLightOffChosen && light.isOn()) {
                    lightIdToSwitchOff = light.getId();
                    isLightOffChosen = true;
                }
            }
        }
        if (!isLightOffChosen) return;

        new LightEventHandler().handle(smartHome, new SensorEvent(LIGHT_OFF, lightIdToSwitchOff));
        roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator lightsIterator = room.getLightsIterator();
            while (lightsIterator.hasNext()) {
                Light light = (Light) lightsIterator.next();
                if (light.getId().equals(lightIdToSwitchOff)) {
                    assertEquals(false, light.isOn());
                }
                else {
                    assertEquals(lightInitialState.get(light.getId()), light.isOn());
                }
            }
        }
    }
}