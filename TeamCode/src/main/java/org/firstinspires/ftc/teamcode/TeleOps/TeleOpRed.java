package org.firstinspires.ftc.teamcode.TeleOps;

import org.firstinspires.ftc.teamcode.Utilities.AllianceSelector;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOpRed extends TeleOp{
    //Esta clase es la que se elije desde la DriverHub.
    /* Se ejecuta el codigo de teleoperado porque hereda la clase TeleOp,
    simplemente manda el valor RED con super() al metodo constructor de la clase que hereda, en este caso TeleOp */
    public TeleOpRed() {
        super(AllianceSelector.RED);
    }
}
