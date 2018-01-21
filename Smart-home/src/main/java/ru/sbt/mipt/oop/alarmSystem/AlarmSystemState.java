package ru.sbt.mipt.oop.alarmSystem;

import ru.sbt.mipt.oop.sensors.SensorEvent;

public interface AlarmSystemState {
    void turnOn();
    void turnOff();
    void onSensorEvent(SensorEvent event);
    void enterPassword(String password);
    AlarmSystemStateEnum getState();
}
