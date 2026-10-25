package org.firstinspires.ftc.teamcode.TeleOps;

import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Utilities.AllianceSelector;
import org.firstinspires.ftc.teamcode.Utilities.GlobalOpMode;
import static org.firstinspires.ftc.teamcode.Subsystems.AutoSubsystem.currentPose;

import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

public class TeleOp extends GlobalOpMode {
    private AllianceSelector alliance;
    private GamepadEx gamepad;
    public TeleOp(AllianceSelector alliance) {
        super(alliance, false);
    }

    @Override
    public void initialize() {
        gamepad = new GamepadEx(gamepad1);

        follower.setPose(currentPose);
        CommandScheduler.getInstance().setDefaultCommand(autoSubsystem, new DriveCommand(autoSubsystem, gamepad1));
    }

    @Override
    public void run() {
        currentPose = follower.pose();
    }
}
