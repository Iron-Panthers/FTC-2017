package org.firstinspires.ftc.team7316.util.hardware;

import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.teamcode.R;

/**
 * Created by jerry on 9/27/17.
 */

public class VuforiaCameraWrapper implements Loopable {

    VuforiaLocalizer.Parameters params;
    VuforiaLocalizer vuforia;
    VuforiaTrackables glyphKeys;

    @Override
    public void init() {
        params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.vuforiaLicenseKey = Constants.vuforiaLicenseKey;
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        glyphKeys = vuforia.loadTrackablesFromAsset("RelicVuMark");
        glyphKeys.get(0).setName("RelicVuMark");

        glyphKeys.activate();
    }

    @Override
    public void loop() {
        for(VuforiaTrackable keys : glyphKeys)
        {
            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) keys.getListener()).getPose();

            if(pose != null)
            {
                VectorF translation = pose.getTranslation();

                Hardware.log(keys.getName() + "-Translation", translation);
            }
        }

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
