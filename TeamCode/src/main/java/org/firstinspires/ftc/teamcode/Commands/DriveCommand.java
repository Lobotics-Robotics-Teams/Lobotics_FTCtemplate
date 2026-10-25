package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.AutoSubsystem;

public class DriveCommand extends CommandBase {

    AutoSubsystem autoSubsystem;
    Gamepad gamepad;
    public DriveCommand(AutoSubsystem autoSubsystem, Gamepad gamepad) {
        this.autoSubsystem = autoSubsystem;
        this.gamepad = gamepad;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        autoSubsystem.driveFollower(gamepad.left_stick_y, gamepad.left_stick_x, gamepad.right_stick_y);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}
