package org.firstinspires.ftc.teamcode.Utilities;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.Commands;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.teamcode.Subsystems.AutoSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MotorSubsystem;
import org.firstinspires.ftc.teamcode.pedro.Constants;

public abstract class GlobalOpMode extends OpMode {
    public Follower follower;
    boolean isAuto;
    public final AllianceSelector currentAlliance;

    public AutoSubsystem autoSubsystem;
    public MotorSubsystem motorSubsystem;
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

        //Aqui mandamos a llamar el metodo registrer() y les mandamos los subsistemas a registrar
        //Es donde vamos a editar cuando agreguemos mas subsistemas
        register(autoSubsystem = new AutoSubsystem(follower, currentAlliance),
                motorSubsystem = new MotorSubsystem(hardwareMap, telemetry));
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
