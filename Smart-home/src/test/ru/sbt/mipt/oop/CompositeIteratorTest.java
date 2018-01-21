package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.smartHome.HomeBuilder;
import ru.sbt.mipt.oop.smartHome.homeElements.Door;
import ru.sbt.mipt.oop.smartHome.homeElements.Light;
import ru.sbt.mipt.oop.smartHome.homeElements.Room;
import ru.sbt.mipt.oop.smartHome.homeElements.SmartHome;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class CompositeIteratorTest {
    @Test
    public void testForRooms() {
        SmartHome smartHome = HomeBuilder.create();
        Collection<Room> rooms = new HashSet<>();

        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            rooms.add(room);
        }

        smartHome.executeAction(object -> {
            if (object instanceof Room) {
                rooms.remove(object);
            }
        });
        assertEquals(0, rooms.size());
    }

    @Test
    public void testForLights() {
        SmartHome smartHome = HomeBuilder.create();
        Collection<Light> lights = new HashSet<>();

        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator lightsIterator = room.getLightsIterator();
            while (lightsIterator.hasNext()) {
                Light light = (Light) lightsIterator.next();
                lights.add(light);
            }
        }

        smartHome.executeAction(object -> {
            if (object instanceof Light) {
                lights.remove(object);
            }
        });
        assertEquals(0, lights.size());
    }

    @Test
    public void testForDoors() {
        SmartHome smartHome = HomeBuilder.create();
        Collection<Door> doors = new HashSet<>();

        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator doorsIterator = room.getDoorsIterator();
            while (doorsIterator.hasNext()) {
                Door door = (Door) doorsIterator.next();
                doors.add(door);
            }
        }

        smartHome.executeAction(object -> {
            if (object instanceof Door) {
                doors.remove(object);
            }
        });
        assertEquals(0, doors.size());
    }
}
