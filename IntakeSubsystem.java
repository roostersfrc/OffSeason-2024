
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//importando as bibliotecas
package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  // declarando as variaveis
  private CANSparkMax mt_pwm_intake;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {

    mt_pwm_intake = new CANSparkMax(16, CANSparkLowLevel.MotorType.kBrushless);
    mt_pwm_intake.restoreFactoryDefaults();
  }

  @Override
  public void periodic() {

  }

  public void set(double speed) {

    mt_pwm_intake.set(speed);
  }

}
