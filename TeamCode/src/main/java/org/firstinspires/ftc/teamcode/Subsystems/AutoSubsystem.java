package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Utilities.AllianceSelector;


public class AutoSubsystem extends SubsystemBase {
    private final Follower follower;
    private AllianceSelector allianceSelector;
    private DrivePowers powers;

    public static Pose currentPose = new Pose(0,0,0);
    public AutoSubsystem(Follower follower, AllianceSelector allianceSelector) {
        this.follower = follower;
        this.allianceSelector = allianceSelector;
    }

    public void driveFollower(float left_stick_y, float left_stick_x, float right_stick_y) {
        powers = ManualDrive.fieldCentric(
                -left_stick_y,
                -left_stick_x,
                -right_stick_y,
                follower.pose().heading()
        );

        follower.manual(powers);
    }

    @Override
    public void periodic() {
    }

}
