/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TurretSubsystem extends SubsystemBase {
  /**
   * Creates a new TurretSubsystem.
   */
  //------------ Shooter -------------
  private WPI_TalonSRX master = new WPI_TalonSRX(Constants.Turret.kMaster);
  private WPI_VictorSPX slave = new WPI_VictorSPX(Constants.Turret.kSlave);

  // ------- Limit switchs
  DigitalInput left = new DigitalInput(Constants.Turret.kTurretLeft);
  DigitalInput right = new DigitalInput(Constants.Turret.kTurretRight);

  // -------------------------------------
  WPI_TalonSRX feeder = new WPI_TalonSRX(Constants.Turret.kFeeder);

  // -------------------------------------
  WPI_TalonSRX yaw =  new WPI_TalonSRX(10);
  WPI_TalonSRX pitch = new WPI_TalonSRX(11);

  public TurretSubsystem() {
    slave.follow(master);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move
}
