package ru.sbt.mipt.oop.alarmSystem;

import org.junit.Test;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_OPEN;

public class AlarmSystemTest {
    private final String password = "123456";
    private final String wrong_password = "лол кек чебурек";
    private final SensorEvent sensorEvent = new SensorEvent(DOOR_OPEN, "2");

    @Test
    public void testInitialState() {
        AlarmSystem alarmSystem = new AlarmSystem(password);
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }

    @Test
    public void testAlarmSystemOffState() {
        AlarmSystem alarmSystem = new AlarmSystem(password);
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());

        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());

        alarmSystem.enterPassword(wrong_password);
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());

        alarmSystem.enterPassword(password);
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());

        alarmSystem.onSensorEvent(sensorEvent);
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());

        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void testAlarmSystemOnState() {
        AlarmSystem alarmSystem = new AlarmSystem(password);
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());

        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());

        alarmSystem.enterPassword(wrong_password);
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());

        alarmSystem.enterPassword(password);
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());

        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());

        alarmSystem.onSensorEvent(sensorEvent);
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    @Test
    public void testAlarmSystemWaitForPasswordState() {
        AlarmSystem alarmSystem = new AlarmSystem(password);
        alarmSystem.turnOn();
        alarmSystem.onSensorEvent(sensorEvent);
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem.onSensorEvent(sensorEvent);
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem.enterPassword(password);
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());

        alarmSystem.turnOn();
        alarmSystem.onSensorEvent(sensorEvent);

        alarmSystem.enterPassword(wrong_password);
        assertEquals(AlarmSystemStateEnum.ALARM, alarmSystem.getState());
    }

    @Test
    public void testAlarmSystemAlarmState() {
        AlarmSystem alarmSystem = new AlarmSystem(password);
        alarmSystem.turnOn();
        alarmSystem.onSensorEvent(sensorEvent);
        alarmSystem.enterPassword(wrong_password);
        assertEquals(AlarmSystemStateEnum.ALARM, alarmSystem.getState());

        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ALARM, alarmSystem.getState());

        alarmSystem.onSensorEvent(sensorEvent);
        assertEquals(AlarmSystemStateEnum.ALARM, alarmSystem.getState());

        alarmSystem.enterPassword(password);
        assertEquals(AlarmSystemStateEnum.ALARM, alarmSystem.getState());

        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }
}