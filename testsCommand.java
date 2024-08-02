// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.Map;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.Command;

public class testsCommand extends Command {
  private double seconds;
  private Timer timer = new Timer();
  private SimpleWidget widget;
  private GenericEntry entry;

  public testsCommand(double seconds, SimpleWidget widget) {
    this.seconds = seconds;
    this.widget = widget;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.timer.start();
    
    this.widget.withProperties(Map.of("Min", 0, "Max", this.seconds));
    this.entry = this.widget.getEntry();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.entry.setDouble(this.timer.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return this.timer.hasElapsed(seconds);
  }
}
