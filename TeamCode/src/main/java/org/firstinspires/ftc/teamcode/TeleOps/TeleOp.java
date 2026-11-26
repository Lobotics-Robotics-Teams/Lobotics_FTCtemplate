package org.firstinspires.ftc.teamcode.TeleOps;

import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.MotorCommand;
import org.firstinspires.ftc.teamcode.Utilities.AllianceSelector;
import org.firstinspires.ftc.teamcode.Utilities.GlobalOpMode;
import static org.firstinspires.ftc.teamcode.Subsystems.AutoSubsystem.currentPose;

import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

public class TeleOp extends GlobalOpMode {
    private GamepadEx gamepad;
    public TeleOp(AllianceSelector alliance) {
        super(alliance, false);
    }

    @Override
    public void initialize() {
        //Se declaran todos los comportamientos con el CommandScheduler y los botones
        gamepad = new GamepadEx(gamepad1);
        Button runMotor = new GamepadButton(gamepad, GamepadKeys.Button.A);

        follower.setPose(currentPose);
        CommandScheduler.getInstance().setDefaultCommand(autoSubsystem, new DriveCommand(autoSubsystem, gamepad1));


        runMotor.whenHeld(new MotorCommand(motorSubsystem));
    }

    /*
    Se importa y se usa currentPose para que el robot sin importar que se cambien de teleoperados o se pase de
    autonomo a teleop se pierda la posicion con odometria
     */

    @Override
    public void run() {
        currentPose = follower.pose();
    }
}
