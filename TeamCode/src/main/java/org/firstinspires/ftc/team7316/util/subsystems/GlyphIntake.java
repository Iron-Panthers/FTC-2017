package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.hardware.ServoWrapper;

/**
 * Created by jerry on 10/11/17.
 */

public class GlyphIntake implements Loopable {

    private ServoWrapper servo;


    public GlyphIntake(ServoWrapper servo)
    {
        this.servo = servo;
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
