package org.firstinspires.ftc.teamcode.Utilities;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.Commands;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.teamcode.Subsystems.AutoSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MotorSubsystem;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import java.nio.file.Path;

public abstract class GlobalOpMode extends OpMode {

    /*
    gamepad1, gamepad2, telemetry, hardwareMap, entre otros, son heredados de OpMode.
    Cualquier otra clase que no herede OpMode o GlobalOpMode no tendrá las funciones ni
    los objetos de ftc heredados del OpMode, para subsistemas se les tendrá que pasar como
    parametro.
     */
    public Follower follower;
    boolean isAuto;
    public final AllianceSelector currentAlliance;
    public AutoSubsystem autoSubsystem;
    public MotorSubsystem motorSubsystem;
    private static Pose initPose() {
        PoseFactory p = PoseFactory.degrees();
        return p.of(org.firstinspires.ftc.teamcode.Utilities.Constants.PoseConstants.initialPose.getX(),
                org.firstinspires.ftc.teamcode.Utilities.Constants.PoseConstants.initialPose.getY(),
                org.firstinspires.ftc.teamcode.Utilities.Constants.PoseConstants.initialPose.getHeading());
    }
    public GlobalOpMode(AllianceSelector allianceSelector, boolean isAuto) {
        this.currentAlliance = allianceSelector;
        this.isAuto = isAuto;
    }

    public void schedule(Command... commands){
        CommandScheduler.getInstance().schedule(commands);
    }

    //Aqui registramos todos nuestros subsistemas en el CommandScheduler para que se manden a llamar el metodo periodic() a la vez.
    public void register(Subsystem... subsystems) {
        CommandScheduler.getInstance().registerSubsystem(subsystems);
    }

    public void reset() {
        CommandScheduler.getInstance().reset();
    }

    public void run() {}

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        follower.setPose(initPose());

        /*
        Aqui mandamos a llamar el metodo registrer() y les mandamos los subsistemas a registrar
        Es donde vamos a editar cuando agreguemos mas subsistemas
        */
        register(autoSubsystem = new AutoSubsystem(follower),
                motorSubsystem = new MotorSubsystem(hardwareMap));
        follower.update();
        initialize();
    }

    @Override
    public void init_loop() {
        if (isAuto) {
            CommandScheduler.getInstance().run();
        }
    }

    @Override
    public void loop() {
        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();

        CommandScheduler.getInstance().run();
        run();
        follower.update();
    }

    public abstract void initialize();
}
