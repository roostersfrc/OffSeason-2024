// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//importando as bibliotecas
package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;

/** Add your docs here. */
// criando a classes e extendendo p Command(classe pai e classe filho)
public class ArcadeDriveCmd extends Command {

  // privando e definindo as variaveis
  private DriveTrainSubsystem driveSubsystem;
  private XboxController controller;
  private double turn, forward;
  private SlewRateLimiter filter;

  // atribuindo as bibliotecas ao ArcadeDriveCmd
  public ArcadeDriveCmd(DriveTrainSubsystem drive, XboxController joy) {
    // Use addRequirements() here to declare subsystem dependencies.
    // definindo o driveSusystem e o controller
    driveSubsystem = drive;
    controller = joy;
    filter = new SlewRateLimiter(2.0);
    addRequirements(this.driveSubsystem);
  }

  // Called when the command is initially scheduled.
  // quando for inicializado os encoders serão zerados
  @Override
  public void initialize() {
    /*
     * this.driveSubsystem.resetEncoders();
     */ }

  // Called every time the scheduler runs while the command is scheduled.
  // definindo os eixos dos controles (virar e andar)
  @Override
  public void execute() {
    forward = controller.getLeftY();
    turn = controller.getRightX();
    driveSubsystem.drive(filter.calculate(forward), turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Lógica de encerramento, se necessário.
  }

  // Returns true when the command should end.

  @Override
  public boolean isFinished() {
    return false;
  }
}
