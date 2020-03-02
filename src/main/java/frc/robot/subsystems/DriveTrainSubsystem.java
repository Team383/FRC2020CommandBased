/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrainSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveTrainSubsystem.
   */
  private WPI_TalonSRX FL = new WPI_TalonSRX(1);
  private WPI_VictorSPX FR = new WPI_VictorSPX(2);
  private WPI_TalonSRX RL = new WPI_TalonSRX(3);
  private WPI_VictorSPX RR = new WPI_VictorSPX(4);

  private DifferentialDrive driveTrain = new DifferentialDrive(FL, FR);

  private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  
  public DriveTrainSubsystem() {
    RL.follow(FL);
    RR.follow(FR);

    gyro.reset();
    gyro.calibrate();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
