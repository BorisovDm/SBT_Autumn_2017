package ru.sbt.mipt.oop.smartHome.homeElements;

import ru.sbt.mipt.oop.smartHome.Action;
import ru.sbt.mipt.oop.smartHome.Actionable;

import java.util.Collection;
import java.util.Iterator;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Iterator getLightsIterator() {
        return lights.iterator();
    }

    public Iterator getDoorsIterator() {
        return doors.iterator();
    }

    public String getName() {
        return name;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);

        Iterator lightsIterator = getLightsIterator();
        while (lightsIterator.hasNext()) {
            Light light = (Light) lightsIterator.next();
            light.executeAction(action);
        }

        Iterator doorsIterator = getDoorsIterator();
        while (doorsIterator.hasNext()) {
            Door door = (Door) doorsIterator.next();
            door.executeAction(action);
        }
    }
}
