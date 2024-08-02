//* Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//importando as bibliotecas
/*
package frc.robot.subsystems;


import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;


//crinado as classes e extendendo o SubsystemBase(classe pai e classe filho)
//public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */
  // privando o motor e defnindo a variavel
  /*
  private PWMVictorSPX mt_shooter_a, mt_shooter_b;

  private GenericEntry entry;

  // definindo a variavel e adicoinando os atributos
  public ShooterSubsystem() {

    entry = Shuffleboard.getTab("info")
                              .add("Shooter Force", 1)
                              .withWidget(BuiltInWidgets.kNumberSlider)
                              .getEntry();

    mt_shooter_a = new PWMVictorSPX(0);
    mt_shooter_b = new PWMVictorSPX(5);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // setando o valor speed
  public void set(double speed) {
    this.mt_shooter_b.set(speed);
    this.mt_shooter_a.set(speed);
  }

  public double getEntryValue(){
    return this.entry.getDouble(.3);
  }

}*/



// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//importando as bibliotecas
package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  // declarando as variaveis
  private CANSparkMax mt_pwm_shooter_a, mt_pwm_shooter_b, mt_pwm_shooter_c;;

  /** Creates a new IntakeSubsystem. */
  public ShooterSubsystem() {

    mt_pwm_shooter_a = new CANSparkMax(19, CANSparkLowLevel.MotorType.kBrushless);
    mt_pwm_shooter_a.restoreFactoryDefaults();

    mt_pwm_shooter_b = new CANSparkMax(18, CANSparkLowLevel.MotorType.kBrushless);
    mt_pwm_shooter_b.restoreFactoryDefaults();

    mt_pwm_shooter_c = new CANSparkMax(20, CANSparkLowLevel.MotorType.kBrushless);
    mt_pwm_shooter_c.restoreFactoryDefaults();

  }

  @Override
  public void periodic() {

  }

  public void set(double speed1, double speed2, double speed3) {

    mt_pwm_shooter_a.set(speed3);
    mt_pwm_shooter_b.set(-speed1);
    mt_pwm_shooter_c.set(-speed2);
  }

}

