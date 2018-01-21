package ru.sbt.mipt.oop.alarmSystem;

import ru.sbt.mipt.oop.sensors.SensorEvent;

public class AlarmSystemOn implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public AlarmSystemOn(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn() {}

    @Override
    public void turnOff() {}

    @Override
    public void onSensorEvent(SensorEvent event) {
        alarmSystem.setState(new AlarmSystemWaitForPassword(alarmSystem));
    }

    @Override
    public void enterPassword(String password) {}

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.ON;
    }
}
