
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCmd extends Command {

  private ShooterSubsystem shooter1;
  private double speed1, speed2, speed3;

  public ShooterCmd(ShooterSubsystem shooter, double speed1, double speed2, double speed3) {

    this.shooter1 = shooter;
    this.speed1 = speed1;
    this.speed2 = speed2;
    this.speed3 = speed3;

    addRequirements(this.shooter1);

  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    this.shooter1.set(this.speed1, this.speed2, this.speed3);
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter1.set(0, 0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
