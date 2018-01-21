package ru.sbt.mipt.oop.alarmSystem;

import ru.sbt.mipt.oop.sensors.SensorEvent;

public class AlarmSystemAlarm implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public AlarmSystemAlarm(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn() {}

    @Override
    public void turnOff() {
        alarmSystem.setState(new AlarmSystemWaitForPassword(alarmSystem));
    }

    @Override
    public void onSensorEvent(SensorEvent event) {}

    @Override
    public void enterPassword(String password) {}

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.ALARM;
    }
}
