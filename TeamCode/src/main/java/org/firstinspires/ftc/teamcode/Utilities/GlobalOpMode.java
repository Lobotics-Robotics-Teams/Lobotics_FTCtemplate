package org.firstinspires.ftc.teamcode.Utilities;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.Commands;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.teamcode.Subsystems.AutoSubsystem;
import org.firstinspires.ftc.teamcode.pedro.Constants;

public abstract class GlobalOpMode extends OpMode {
    public Follower follower;
    boolean isAuto;
    public final AllianceSelector currentAlliance;

    public AutoSubsystem autoSubsystem;
    public GlobalOpMode(AllianceSelector allianceSelector, boolean isAuto) {
        this.currentAlliance = allianceSelector;
        this.isAuto = isAuto;
        register(autoSubsystem = new AutoSubsystem(follower, currentAlliance));
    }

    public void schedule(Command... commands){
        CommandScheduler.getInstance().schedule(commands);
    }

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
        register(autoSubsystem = new AutoSubsystem(follower, currentAlliance));
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
        CommandScheduler.getInstance().run();
        run();
        follower.update();
    }

    public abstract void initialize();
}
