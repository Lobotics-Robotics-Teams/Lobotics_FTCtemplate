package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utilities.Constants;

public class MotorSubsystem extends SubsystemBase {
    DcMotorEx motor;
    Telemetry telemetry;

    //El metodo constructor. Aqui recibe el HardwareMap y el Telemetry que nos otorga OpMode y las pasa a mi calse MotorSubsystem.
    //Cabe aclarar que este es un ejemplo de su uso, pero se puede manejar depende de las necesiadades.
    public MotorSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        motor = hardwareMap.get(DcMotorEx.class, Constants.ExampleConstants.motorName);
    }

    //Con la libreria de Solverslib, cuando registramos el subsistema con registrer() se manda a ejecutar el periodic del SubsystemBaase
    @Override
    public void periodic() {
        telemetry.addData("Motor velocity", motor.getVelocity());
        telemetry.update();
    }

    //Se crean las funciones de mi subsistema para acceder a ellas por medio de comandos

    public void setPower(double power) {
        motor.setPower(power);
    }

    public void stopMotor() {
        motor.setPower(0);
    }
}
