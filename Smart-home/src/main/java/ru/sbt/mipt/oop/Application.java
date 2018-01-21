package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.mipt.oop.eventHandler.DoorEventHandler;
import ru.sbt.mipt.oop.eventHandler.LightEventHandler;
import ru.sbt.mipt.oop.sensors.SensorCommand;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventGenerator;
import ru.sbt.mipt.oop.sensors.SensorEventObserver;
import ru.sbt.mipt.oop.smartHome.homeElements.SmartHome;
import ru.sbt.mipt.oop.smartHome.SmartHomeSaver;

import java.io.IOException;

public class Application {
    public static void main(String... args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        SmartHome smartHome = context.getBean(SmartHome.class);
        SensorEventObserver sensorEventObserver = context.getBean(SensorEventObserver.class);

        // начинаем цикл обработки событий
        SensorEventGenerator eventGenerator = context.getBean(SensorEventGenerator.class);
        SensorEvent event = eventGenerator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            sensorEventObserver.handle(smartHome, event);
            event = eventGenerator.getNextSensorEvent();
        }
        SmartHomeSaver.save(smartHome);
    }

    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
