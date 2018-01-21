package ru.sbt.mipt.oop.smartHome.homeElements;

import ru.sbt.mipt.oop.smartHome.Action;
import ru.sbt.mipt.oop.smartHome.Actionable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SmartHome implements Actionable {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Iterator getRoomsIterator() {
        return rooms.iterator();
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        Iterator roomsIterator = getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            room.executeAction(action);
        }
    }
}
