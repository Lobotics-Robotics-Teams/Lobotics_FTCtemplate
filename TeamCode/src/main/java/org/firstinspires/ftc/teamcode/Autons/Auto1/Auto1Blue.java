package org.firstinspires.ftc.teamcode.Autons.Auto1;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Utilities.AllianceSelector;

@Autonomous
public class Auto1Blue extends Auto1 {
    public Auto1Blue(){
        super(AllianceSelector.BLUE);
    }
}
