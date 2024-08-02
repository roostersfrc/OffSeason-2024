// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomo;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;

public class TurnPerSeconds extends Command {
   private DriveTrainSubsystem drive;
  private double seconds, force;
  private Timer timer;
  /** Creates a new TurnPerSeconds. */
  public TurnPerSeconds(DriveTrainSubsystem drive, double seconds, double force) {
    this.drive = drive;
    this.seconds = seconds;
    this.force = force;
    this.timer = new Timer();

    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.timer.start();
   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   
    this.drive.drive(0, force);
  }

  @Override
  public void end(boolean interrupted) {
    this.drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.timer.get() >= this.seconds;
  }
}
