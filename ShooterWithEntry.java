// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterWithEntry extends Command {

  private ShooterSubsystem shooter;
  private Double speed1, speed2, speed3;

  /** Creates a new ShooterWithEntry. */
  public ShooterWithEntry(ShooterSubsystem shooter, double speed1, double speed2, double speed3) {
    this.shooter  = shooter;
    this.speed1  = speed1;
    this.speed2  = speed2;
    this.speed3  = speed3;
    addRequirements(this.shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.shooter.set(this.speed1, this.speed2, this.speed3);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.shooter.set(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
