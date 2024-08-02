package frc.robot;

import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.ShooterCmd;
//import frc.robot.commands.ShooterWithEntry;
import frc.robot.commands.Autonomo.DrivePerSecond;
import frc.robot.commands.Autonomo.IntakePerSecond;
import frc.robot.commands.Autonomo.ShooterPerSeconds;
import frc.robot.commands.Elevator.ElevatorDownCmd;
import frc.robot.commands.Elevator.ElevatorUpCmd;
import frc.robot.commands.Instants.reverseCMD;
import frc.robot.commands.Instants.speedCMD;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {

  //CRIANDO VARIAVES
  private DriveTrainSubsystem drive;
  private ShooterSubsystem shooter1;
  private IntakeSubsystem intake;
  private ElevatorSubsystem elevator;

  private XboxController player1;

  private Trigger ShooterTrigger, CollectorTrigger, ColletorHighTrigger, AmpTrigger;


  private Trigger changeSpeed1;
  private Trigger changeSpeed2;
  private Trigger changeSpeed3;
  private Trigger changeHeading;
  
  public SimpleWidget CommandTimingEntry = Shuffleboard.getTab("info")
      .add("ActualCommandTiming", 0)
      .withWidget(BuiltInWidgets.kDial);

  public SimpleWidget CommandActualEntry = Shuffleboard.getTab("info")
      .add("ActualCommandName", "none")
      .withWidget(BuiltInWidgets.kTextView);

  public RobotContainer() {
 
    //VARIAVEIS
    drive = new DriveTrainSubsystem();
    shooter1 = new ShooterSubsystem();
    intake = new IntakeSubsystem();
    elevator = new ElevatorSubsystem();

    player1 = new XboxController(0); 

    //DEFFINIÇÃO DOS BOTÕES
    ShooterTrigger = new JoystickButton(player1, XboxController.Button.kLeftBumper.value);
    CollectorTrigger = new JoystickButton(player1, XboxController.Button.kRightBumper.value);
    ColletorHighTrigger = new JoystickButton(player1, XboxController.Button.kStart.value);
    AmpTrigger = new JoystickButton(player1, XboxController.Button.kBack.value);


     changeSpeed1 = new JoystickButton(player1, XboxController.Button.kY.value);
     changeSpeed2 = new JoystickButton(player1, XboxController.Button.kB.value);
     changeSpeed3 = new JoystickButton(player1, XboxController.Button.kA.value);
     changeHeading = new JoystickButton(player1, XboxController.Button.kX.value);

     CameraServer.startAutomaticCapture();

    configureButtonBindings();
    setDefaultCommands();
  }

  private void configureButtonBindings() {


    changeSpeed1.onTrue(new speedCMD(drive, 1.0));
    changeSpeed2.onTrue(new speedCMD(drive, 0.75));
    changeSpeed3.onTrue(new speedCMD(drive, 0.5));
    changeHeading.onTrue(new reverseCMD(drive));


    ShooterTrigger.whileTrue(
       new ParallelCommandGroup(
            new IntakeCmd(intake, -1), 
            new ShooterCmd(shooter1, 1, 1, 0.8)
            )); 

    AmpTrigger.whileTrue(
       new ParallelCommandGroup(
        new IntakeCmd(intake, -0.3), 
        new ShooterCmd(shooter1, 0.05, 0.1, 1))
        //new IntakeCmd(intake, -1)
    );

    ColletorHighTrigger.whileTrue(
      new ParallelCommandGroup(
        new ShooterCmd(shooter1, -0.3, -0.3, -0.2),
        new IntakeCmd(intake, 0.3)));

    CollectorTrigger.whileTrue(
        new ParallelCommandGroup(
            new ElevatorUpCmd(elevator, -0.5),
            new IntakeCmd(intake, 0.6)))
          .onFalse(
            new ElevatorDownCmd(elevator, 0.4));

  }

  private void setDefaultCommands() {
    this.drive.setDefaultCommand(new ArcadeDriveCmd(drive, player1));
  }

  public Command getAutonomousCommand() {

    /* 
     return new SequentialCommandGroup(
      new testsCommand(10, CommandTimingEntry),
      new testsCommand(2, CommandTimingEntry));
     */

    return new SequentialCommandGroup( 
        new ParallelCommandGroup(
            new ShooterPerSeconds(shooter1, -1, 1.5),
            new IntakePerSecond(intake, 1.5, -1)),
        new ElevatorUpCmd(elevator, -0.7),
        new ParallelCommandGroup(
            new DrivePerSecond(drive, 1.8, 0.8),
            new IntakePerSecond(intake, 0.8, 0.8)),

        new ParallelCommandGroup(
            new DrivePerSecond(drive, 1.8, -0.58),
            new ElevatorDownCmd(elevator, 0.7)),

        new ParallelCommandGroup(
            new ShooterPerSeconds(shooter1, -1, 1),
            new IntakePerSecond(intake, 1, -1)),
        new DrivePerSecond(drive, 0.6, 0.7)

    );

  }
}
