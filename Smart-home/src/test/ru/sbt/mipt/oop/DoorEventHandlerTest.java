package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.eventHandler.DoorEventHandler;
import ru.sbt.mipt.oop.eventHandler.EventHandler;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.smartHome.*;
import ru.sbt.mipt.oop.smartHome.homeElements.Door;
import ru.sbt.mipt.oop.smartHome.homeElements.Light;
import ru.sbt.mipt.oop.smartHome.homeElements.Room;
import ru.sbt.mipt.oop.smartHome.homeElements.SmartHome;

import java.util.*;
import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_OPEN;

public class DoorEventHandlerTest {
    @Test
    public void testCloseAllDoors() {
        SmartHome smartHome = HomeBuilder.create();
        EventHandler closeAllDoorsHandler = new DoorEventHandler();
        List<SensorEvent> doorEvents = new ArrayList<>();

        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator doorsIterator = room.getDoorsIterator();
            while (doorsIterator.hasNext()) {
                Door door = (Door) doorsIterator.next();
                doorEvents.add(new SensorEvent(DOOR_CLOSED, door.getId()));
            }
        }

        for (SensorEvent event: doorEvents) {
            closeAllDoorsHandler.handle(smartHome, event);
        }

        roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator doorsIterator = room.getDoorsIterator();
            while (doorsIterator.hasNext()) {
                Door door = (Door) doorsIterator.next();
                assertEquals(false, door.isOpen());
            }
        }
    }

    @Test
    public void testOpenAllDoors() {
        SmartHome smartHome = HomeBuilder.create();
        EventHandler openAllDoorsHandler = new DoorEventHandler();
        List<SensorEvent> doorEvents = new ArrayList<>();

        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator doorsIterator = room.getDoorsIterator();
            while (doorsIterator.hasNext()) {
                Door door = (Door) doorsIterator.next();
                doorEvents.add(new SensorEvent(DOOR_OPEN, door.getId()));
            }
        }

        for (SensorEvent event: doorEvents) {
            openAllDoorsHandler.handle(smartHome, event);
        }

        roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator doorsIterator = room.getDoorsIterator();
            while (doorsIterator.hasNext()) {
                Door door = (Door) doorsIterator.next();
                assertEquals(true, door.isOpen());
            }
        }
    }

    @Test
    public void testCloseOneDoor() {
        SmartHome smartHome = HomeBuilder.create();
        Map<String, Boolean> doorInitialState = new HashMap();
        boolean isOpenDoorChosen = false;
        String doorIdToClose = null;

        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator doorsIterator = room.getDoorsIterator();
            while (doorsIterator.hasNext()) {
                Door door = (Door) doorsIterator.next();
                doorInitialState.put(door.getId(), door.isOpen());
                if (!isOpenDoorChosen && door.isOpen()) {
                    doorIdToClose = door.getId();
                    isOpenDoorChosen = true;
                }
            }
        }
        if (!isOpenDoorChosen) return;

        new DoorEventHandler().handle(smartHome, new SensorEvent(DOOR_CLOSED, doorIdToClose));
        roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator doorsIterator = room.getDoorsIterator();
            while (doorsIterator.hasNext()) {
                Door door = (Door) doorsIterator.next();
                if (door.getId().equals(doorIdToClose)) {
                    assertEquals(false, door.isOpen());
                }
                else {
                    assertEquals(doorInitialState.get(door.getId()), door.isOpen());
                }
            }
        }
    }

    @Test
    public void testOpenOneDoor() {
        SmartHome smartHome = HomeBuilder.create();
        Map<String, Boolean> doorInitialState = new HashMap();
        boolean isCloseDoorChosen = false;
        String doorIdToOpen = null;

        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator doorsIterator = room.getDoorsIterator();
            while (doorsIterator.hasNext()) {
                Door door = (Door) doorsIterator.next();
                doorInitialState.put(door.getId(), door.isOpen());
                if (!isCloseDoorChosen && !door.isOpen()) {
                    doorIdToOpen = door.getId();
                    isCloseDoorChosen = true;
                }
            }
        }
        if (!isCloseDoorChosen) return;

        new DoorEventHandler().handle(smartHome, new SensorEvent(DOOR_OPEN, doorIdToOpen));
        roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator doorsIterator = room.getDoorsIterator();
            while (doorsIterator.hasNext()) {
                Door door = (Door) doorsIterator.next();
                if (door.getId().equals(doorIdToOpen)) {
                    assertEquals(true, door.isOpen());
                }
                else {
                    assertEquals(doorInitialState.get(door.getId()), door.isOpen());
                }
            }
        }
    }

    @Test
    public void testCloseHall() {
        SmartHome smartHome = HomeBuilder.create();
        boolean isHallExists = false;
        String hallDoorId = null;

        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            if (room.getName().equals("hall")) {
                isHallExists = true;
                Iterator doorsIterator = room.getDoorsIterator();
                while (doorsIterator.hasNext()) {
                    Door door = (Door) doorsIterator.next();
                    hallDoorId = door.getId();
                    break;
                }
                break;
            }
        }
        if (!isHallExists) return;

        new DoorEventHandler().handle(smartHome, new SensorEvent(DOOR_CLOSED, hallDoorId));
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
}