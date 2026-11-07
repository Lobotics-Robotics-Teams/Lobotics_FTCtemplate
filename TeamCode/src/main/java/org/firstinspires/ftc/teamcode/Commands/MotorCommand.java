package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.AutoSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MotorSubsystem;
import org.firstinspires.ftc.teamcode.Utilities.Constants;

public class MotorCommand extends CommandBase {
    MotorSubsystem motorSubsystem;
    public MotorCommand(MotorSubsystem motorSubsystem) {
        this.motorSubsystem = motorSubsystem;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        motorSubsystem.setPower(Constants.ExampleConstants.motorPower);
    }

    @Override
    public void end(boolean interrupted){
        motorSubsystem.stopMotor();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
