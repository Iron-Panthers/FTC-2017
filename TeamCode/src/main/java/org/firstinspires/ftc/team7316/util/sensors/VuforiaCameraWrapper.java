package org.firstinspires.ftc.team7316.util.sensors;

import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.CryptoLocations;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.teamcode.R;

/**
 * Created by jerry on 9/27/17.
 * In order to use vuforia in the opmodes,
 * make the opmode init with startTracking() and add the UpdateVuforia command to scheduler
 */

public class VuforiaCameraWrapper {

    private final VuforiaLocalizer.CameraDirection CAMERA_DIRECTION= VuforiaLocalizer.CameraDirection.BACK;

    private VuforiaLocalizer.Parameters params;
    private VuforiaLocalizer vuforia;
    private VuforiaTrackables relicTrackables;
    private VuforiaTrackable relicTemplate;

    public double tX, tY, tZ;
    public double rX, rY, rZ;

    public double itX, itY, itZ;    //initial translations of cipher
    public double irX, irY, irZ;    //initial angle of cipher

    public RelicRecoveryVuMark vuMark;
    public RelicRecoveryVuMark currentVuMark;

    public VuforiaCameraWrapper() {
        params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
//        params = new VuforiaLocalizer.Parameters();
        params.vuforiaLicenseKey = Constants.vuforiaLicenseKey;
        params.cameraDirection = CAMERA_DIRECTION;
        params.useExtendedTracking = false;

        vuMark = RelicRecoveryVuMark.UNKNOWN;
    }

    /**
     * put in the opmode init to activate vuforia tracking
     */
    public void startTracking() {
        vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).setPhoneInformation(new OpenGLMatrix(), CAMERA_DIRECTION);

        relicTrackables.activate();
    }

    public void setCipherLocation() {
        itX = tX;
        itY = tY;
        itZ = tZ;

        irX = rX;
        irY = rY;
        irZ = rZ;
    }

    /**
     * Measurements right now.
     * tY is the normal horizontal displacement of the pictograph.
     * tZ is the normal displacement to the pictograph.
     * rY is the rotation of the robot relative to the pictograph.
     * Every other measurement isn't currently used.
     */
    public void update() {
        currentVuMark = RelicRecoveryVuMark.from(relicTemplate);

        if (currentVuMark != RelicRecoveryVuMark.UNKNOWN) {
            vuMark = currentVuMark;
                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
            Hardware.log("VuMark visible", currentVuMark);

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
//            Hardware.log("Pose", format(pose));

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
            if (pose != null) {
                VectorF trans = pose.getTranslation();
                Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                // Extract the X, Y, and Z components of the offset of the target relative to the robot
                tX = trans.get(0);
                tY = trans.get(1);
                tZ = trans.get(2);
//                Hardware.log("tx", tX);
                Hardware.log("tY", tY);
                Hardware.log("tZ", tZ);

                // Extract the rotational components of the target relative to the robot
                rX = rot.firstAngle;
                rY = rot.secondAngle;
                rZ = rot.thirdAngle;
//                Hardware.log("rx", rX);
                Hardware.log("ry", rY);
//                Hardware.log("rz", rZ);

                setCipherLocation();
            }
        }
        else {
            Hardware.log("VuMark", "not visible");
        }
    }

    private String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
