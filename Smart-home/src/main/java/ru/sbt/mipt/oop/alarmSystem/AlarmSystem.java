package ru.sbt.mipt.oop.alarmSystem;

import ru.sbt.mipt.oop.sensors.SensorEvent;

public class AlarmSystem {
    private final String truePassword;
    private AlarmSystemState state;

    public AlarmSystem(String password) {
        truePassword = password;
        state = new AlarmSystemOff(this);
    }

    public void turnOn(){
        state.turnOn();
    }

    public void turnOff() {
        state.turnOff();
    }

    public void onSensorEvent(SensorEvent sensorEvent) {
        state.onSensorEvent(sensorEvent);
    }
    public void enterPassword(String password) {
        state.enterPassword(password);
    }

    public void setState(AlarmSystemState state) {
        this.state = state;
    }

    boolean checkPassword (String password) {
        if(password.equals(truePassword)) return true;
        return false;
    }

    public AlarmSystemStateEnum getState() {
        return state.getState();
    }
}
