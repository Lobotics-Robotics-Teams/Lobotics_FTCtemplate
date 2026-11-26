package org.firstinspires.ftc.teamcode.Utilities;

import com.seattlesolvers.solverslib.geometry.Pose2d;

public class Constants {

    /*En Constants se declaran aquellos valores que no se modificarán en el codigo, en este caso
    nombre de los motores, velocidad de motores o etcetera, haciendo que sea más comodo al momento de tunear los valores.
     */
    public static class ExampleConstants {
        public static final String motorName = "motor";
        public static final double motorPower = 1.0;
    }

    public static class PoseConstants {
        //Modificar dependiendo de la estrategia. Es la pose inicial del robot en coordenadas de la cancha.
        public static final Pose2d initialPose = new Pose2d(0,0,0);
    }
}
