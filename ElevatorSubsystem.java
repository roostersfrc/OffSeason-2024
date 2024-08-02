// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//importando as bibliotecas
package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {

  // private WPI_VictorSPX mt_pwm_elevator;
  private DigitalInput fc_elevator_up;
  private DigitalInput fc_elevator_down;

  private CANSparkMax mt_pwm_elevator;
  private RelativeEncoder encoder;

  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {
    // definindo as variaveis no construtor da classe
    // mt_pwm_elevator = new
    // WPI_VictorSPX(Constants.ElevatorConstants.mt_pwm_elevator);
    fc_elevator_up = new DigitalInput(Constants.ElevatorConstants.fc_elevator_up);
    fc_elevator_down = new DigitalInput(Constants.ElevatorConstants.fc_elevator_down);

    
    mt_pwm_elevator = new CANSparkMax(17, CANSparkLowLevel.MotorType.kBrushless);
    mt_pwm_elevator.restoreFactoryDefaults();
    
    this.encoder = this.mt_pwm_elevator.getEncoder();

  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("upFC", get_fc_UP());
    SmartDashboard.putBoolean("downFC", get_fc_DOWN());
    SmartDashboard.putNumber("Enconder Elevator", this.encoder.getPosition());
    // This method will be called once per scheduler run
  }

  public boolean get_fc_UP() {
    // fazendo o elevador subir
    return this.fc_elevator_up.get();
  }

  public boolean get_fc_DOWN() {
    // fazer o elevador descer
    return this.fc_elevator_down.get();
  }

  public double get_encoder_value(){
    return this.encoder.getPosition();
  }

  public void reset_encoder_value(){
     this.encoder.setPosition(0);
  }

  public void set(double speed) {
    // setando a variavel speed no elevador
    mt_pwm_elevator.set(speed);
  }

}
