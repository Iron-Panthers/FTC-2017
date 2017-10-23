package org.firstinspires.ftc.team7316.test;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;

/**
 * Created by Maxim on 10/23/2017.
 */

public class SchedulerTesting {

    static TestSubsystem subA = new TestSubsystem();
    static TestSubsystem subB = new TestSubsystem();
    static TestSubsystem subC = new TestSubsystem();

    static void basicTest() {
        System.out.println("Basic Single-Command");
        TestIterateCommand first = new TestIterateCommand("first", 5, subA);
        Scheduler.instance.add(first);

        performTest(10);
    }

    static void sequentialTest() {
        System.out.println("Sequential");
        TestIterateCommand first = new TestIterateCommand("first", 5, subA);
        TestIterateCommand second = new TestIterateCommand("second", 5, subA);

        SequentialCommand seq = new SequentialCommand(first, second);

        Scheduler.instance.add(seq);
        performTest(15);
    }

    static void simpleOverwriteTest() {
        System.out.println("Simple Overwrite");
        TestIterateCommand first = new TestIterateCommand("first", 5, subA);
        TestIterateCommand wait = new TestIterateCommand("wait", 3);
        TestIterateCommand second = new TestIterateCommand("second", 10, subA);


        performTest(15);
    }

    static void performTest(int iterations) {
        for (int i=0; i < iterations; i++) {
            Scheduler.instance.loop();
            System.out.printf("%s \t%s\t%s\t%s\t%s\n", i, subA.currentCmd, subB.currentCmd, subC.currentCmd, Scheduler.instance.getCommands());
        }
        Scheduler.instance.clear();
    }

    public static void main(String[] args) {
        //basicTest();
        sequentialTest();
        //simpleOverwriteTest();
    }

}
