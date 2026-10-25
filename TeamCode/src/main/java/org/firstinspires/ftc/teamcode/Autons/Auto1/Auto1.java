package org.firstinspires.ftc.teamcode.Autons.Auto1;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.Utilities.AllianceSelector;
import org.firstinspires.ftc.teamcode.Utilities.GlobalOpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import static org.firstinspires.ftc.teamcode.Subsystems.AutoSubsystem.currentPose;

public class Auto1 extends GlobalOpMode {

    PoseFactory p(){
        return AllianceSelector.BLUE.equals(currentAlliance) ? PoseFactory.degrees().mirrorX(70.75) : PoseFactory.degrees();
    }
    private Pose startPose;
    private Pose scorePose;
    private Pose park;

    public Auto1(AllianceSelector allianceSelector) {
        super(allianceSelector, true);
    }

    @Override
    public void initialize() {
        startPose = p().of(0, 0, 0);
        scorePose = p().of(5, 8, 45);
        park = p().of(0, 15, 90);

        follower.setPose(startPose);
    }

    @Override
    public void start(){
        Scheduler.schedule(autoRoutine());
    }

    @Override
    public void run() {
        currentPose = follower.pose();
    }

    private Path startToScore() {
        return  line(startPose, scorePose).linear(startPose,scorePose);
    }
    private Path park() {
        return  line(scorePose, park).linear(scorePose,park).with(Constants.foresightConfig.maxPathSpeed.at(0.8));
    }
    private Command autoRoutine() {
        return sequential(
                follow(follower, startToScore()),
                // Add mechanism commands here.
                follow(follower, park())
        );
    }
}
