//importando as bibliotecas
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



//criando a classe e extendendo o subsystemBase(classe pai e classe filho)
public class DriveTrainSubsystem extends SubsystemBase {

  /**
   * Variavel usada para controlar o modificador de
   * velocidade.
   */
  public double speedModifier = 1.0;


  // declarando os motores
  private WPI_VictorSPX mt_drivetrain_left_a;
  private WPI_TalonSRX mt_drivetrain_left_b;

  private WPI_VictorSPX mt_drivetrain_right_a;
  private WPI_TalonSRX mt_drivetrain_right_b;

  // declarando o diferrentialDrive
  private  DifferentialDrive differentialDrive;

  private GenericEntry speedEntry = Shuffleboard.getTab("info").add("SpeedMod", speedModifier).getEntry();
  

  public DriveTrainSubsystem() {

    mt_drivetrain_left_a = new WPI_VictorSPX(DriveTrainConstants.mt_can_drivetrain_left_A);
    mt_drivetrain_left_b = new WPI_TalonSRX(DriveTrainConstants.mt_can_drivetrain_left_B);

    mt_drivetrain_right_a = new WPI_VictorSPX(DriveTrainConstants.mt_can_drivetrain_right_A);
    mt_drivetrain_right_b = new WPI_TalonSRX(DriveTrainConstants.mt_can_drivetrain_right_B);

    // invertendo os motores da direita
    mt_drivetrain_right_a.setInverted(true);
    mt_drivetrain_right_b.setInverted(true);

    mt_drivetrain_left_a.setInverted(false);
    mt_drivetrain_left_b.setInverted(false);

    mt_drivetrain_left_b.follow(mt_drivetrain_left_a);
    mt_drivetrain_right_b.follow(mt_drivetrain_right_a);


    differentialDrive = new DifferentialDrive(mt_drivetrain_left_a, mt_drivetrain_right_a);

  }

  @Override
  public void periodic() {
    this.speedEntry.setDouble(speedModifier);
    SmartDashboard.putNumber("Robot Speed", this.speedModifier);
  }

  /**
   * Usado para pilotar o robo.
   * 
   * @param foward é multiplicado pelo speedModifier no topo da classe.
   * @param turn   é modificado pelo piloto para deixar numa configuração
   *               aceitavel
   */
  public void drive(double foward, double turn) {
    differentialDrive.arcadeDrive(foward * speedModifier, turn * -.8);
  }

  /**
   * Usado para parar os motores.
   */
  public void stop() {

    differentialDrive.stopMotor();

  }
}
