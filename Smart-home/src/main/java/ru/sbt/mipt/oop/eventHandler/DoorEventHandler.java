package ru.sbt.mipt.oop.eventHandler;

import ru.sbt.mipt.oop.sensors.CommandType;
import ru.sbt.mipt.oop.sensors.SensorCommand;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.smartHome.homeElements.Door;
import ru.sbt.mipt.oop.smartHome.homeElements.Light;
import ru.sbt.mipt.oop.smartHome.homeElements.Room;
import ru.sbt.mipt.oop.smartHome.homeElements.SmartHome;

import java.util.Iterator;

import static ru.sbt.mipt.oop.Application.sendCommand;
import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (!isDoorEvent(event)) {
            return;
        }
        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator doorsIterator = room.getDoorsIterator();
            while (doorsIterator.hasNext()) {
                Door door = (Door) doorsIterator.next();
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");

                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) {
                            turnOffAllLights(smartHome);
                        }
                    }
                }
            }
        }
    }

    private void turnOffAllLights(SmartHome smartHome) {
        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            Iterator lightsIterator = room.getLightsIterator();
            while (lightsIterator.hasNext()) {
                Light light = (Light) lightsIterator.next();
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
