package org.firstinspires.ftc.teamcode;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.pedro.Constants;

import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous
public class ExampleAuto extends OpMode {
    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();
    private final Pose startPose = p.of(0, 0, 0);
    private final Pose scorePose = p.of(5, 8, 45);
    private final Pose park = p.of(0, 15, 90);
    @Override
    public void init() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();
    }
    @Override
    public void start() {
        //schedule(follow(follower, park()));
        schedule(autoRoutine());
    }
    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();
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

    /*
    private Path park() {
        return curve(startPose, controlPose, park).linear(startPose, park);
    }

    private final Pose controlPose = p.of(5, 5, 90);
*/
}