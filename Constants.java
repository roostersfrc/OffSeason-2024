// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */

/*
 * ec = encoder
 * mt = motor
 * fc = fim de curso
 * gy = giroscópio
 * cmd = comando
 * 
 * fórmula dos nomes:
 * sigla_subsystema_posição_identificação
 */
public final class Constants {
    public static class DriveTrainConstants {
        public static int ec_drivetrain_left_B = 5;
        public static int ec_drivetrain_left_A = 6;
        public static int ec_drivetrain_right_B = 7;
        public static int ec_drivetrain_right_A = 8;

        public static int mt_can_drivetrain_left_A = 1;
        public static int mt_can_drivetrain_left_B = 3;

        public static int mt_can_drivetrain_right_A = 2;
        public static int mt_can_drivetrain_right_B = 4;

        public static int mt_pwm_drivetrain_left_C = 9;

    }

    public static class ElevatorConstants {
        public static int fc_elevator_up = 0;
        public static int fc_elevator_down = 1;
        //public static int mt_pwm_elevator = 13;
    }
    /*
    public static class IntakeConstants {
        public static int mt_pwm_intake = 16;

    }

    public static class ShooterConstants {
        public static int mt_can_shooter_a = 18;
        public static int mt_can_shooter_b = 19;

    }*/

}
