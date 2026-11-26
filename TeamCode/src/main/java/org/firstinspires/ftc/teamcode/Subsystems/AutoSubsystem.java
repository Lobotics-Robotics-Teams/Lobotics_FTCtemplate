package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Utilities.AllianceSelector;


public class AutoSubsystem extends SubsystemBase {
    private final Follower follower;
    private DrivePowers powers;

    public static Pose currentPose = new Pose(0,0,0);
    public AutoSubsystem(Follower follower) {
        this.follower = follower;
    }

    /*
    NOTA: Si el robot no sigue el FieldCentric y se comporta raro, invertir el heading().
     */
    public void driveFollower(Gamepad gamepad) {
        powers = ManualDrive.fieldCentric(
                -gamepad.left_stick_y,
                -gamepad.left_stick_x,
                -gamepad.right_stick_y,
                follower.pose().heading()
        );

        follower.manual(powers);
    }

    @Override
    public void periodic() {
        //set periodic
    }

}
