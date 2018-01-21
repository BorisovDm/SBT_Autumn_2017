package ru.sbt.mipt.oop.alarmSystem;

import ru.sbt.mipt.oop.sensors.SensorEvent;

public class AlarmSystemWaitForPassword implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public AlarmSystemWaitForPassword(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn() {}

    @Override
    public void turnOff() {}

    @Override
    public void onSensorEvent(SensorEvent event) {}

    @Override
    public void enterPassword(String password) {
        if(alarmSystem.checkPassword(password)) {
            alarmSystem.setState(new AlarmSystemOff(alarmSystem));
        }
        else {
            alarmSystem.setState(new AlarmSystemAlarm(alarmSystem));
        }
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.WAIT_FOR_PASSWORD;
    }
}
