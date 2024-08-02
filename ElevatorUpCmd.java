// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorUpCmd extends Command {

  private ElevatorSubsystem elevator;
  private double speed;

  public ElevatorUpCmd(ElevatorSubsystem elevator, double speed) {

    this.elevator = elevator;
    this.speed = speed;

    addRequirements(this.elevator);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.elevator.reset_encoder_value();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    this.elevator.set(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.elevator.set(0);
    this.elevator.reset_encoder_value();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !this.elevator.get_fc_UP() || Math.abs(this.elevator.get_encoder_value()) >= 80;
  }
}
