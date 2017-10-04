package org.firstinspires.ftc.team7316.modes.teleop;

import android.os.Debug;
import android.util.Log;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Axis;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.internal.PreferencesHelper;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.teamcode.R;

/**
 * Created by jerry on 10/4/17.
 */
@TeleOp(name="dontcrashplease")
public class VuforiaPleaseDontCrashMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.vuforiaLicenseKey = Constants.vuforiaLicenseKey;
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        VuforiaTrackables glyphKeys = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        glyphKeys.get(0).setName("Wheels");
        glyphKeys.get(1).setName("Tools");
        glyphKeys.get(2).setName("Lego");
        glyphKeys.get(3).setName("Gears");

        waitForStart();

        glyphKeys.activate();

        while(opModeIsActive())
        {
            for(VuforiaTrackable keys : glyphKeys)
            {

                Log.d("vuftest", "getting listner for key " + String.valueOf(keys));
                VuforiaTrackableDefaultListener listener = (VuforiaTrackableDefaultListener) keys.getListener();

                Log.d("vuftest", "getting pose for listener " + String.valueOf(listener));
                OpenGLMatrix pose = listener.getPose();

                Log.d("vuftest", "pose worked yay " + String.valueOf(pose));

                if(pose != null)
                {
                    Log.d("vuftest", "pose no null yay");
                    VectorF translation = pose.getTranslation();

                    Log.d("vuftest", "pose can translation yay");
                    Hardware.log(keys.getName() + "-Translation", translation);
                } else {
                    break;
                }
            }
        }
    }
}
