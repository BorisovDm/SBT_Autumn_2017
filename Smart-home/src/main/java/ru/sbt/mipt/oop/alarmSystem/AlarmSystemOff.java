package ru.sbt.mipt.oop.alarmSystem;

import ru.sbt.mipt.oop.sensors.SensorEvent;

public class AlarmSystemOff implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public AlarmSystemOff(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn() {
        alarmSystem.setState(new AlarmSystemOn(alarmSystem));
    }

    @Override
    public void turnOff() {}

    @Override
    public void onSensorEvent(SensorEvent event) {}

    @Override
    public void enterPassword(String password) {}

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.OFF;
    }
}
