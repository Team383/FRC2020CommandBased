/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SpinnerSubsystem extends SubsystemBase {
  /**
   * Creates a new SpinnerSubsystem.
   */

  public enum Cores {
    /**
     * Acessar o valor de cada elemento com o metodo ordinal() Ex.: int blue =
     * Cores.B.ordinal(); blue vai ter valor 0, pois B está na posicao 0.
     */
    B, G, R, Y;
  }

  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private WPI_TalonSRX spinner = new WPI_TalonSRX(Constants.Spinner.kSpinner);
  String colorString, gameData, colorLetras;
  int gameDataNum, colorNum;
  boolean vai, vaiCor, start;
  double startTime, time;
  Timer timer = new Timer();

  public SpinnerSubsystem() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Color detectedColor = m_colorSensor.getColor();

    /**
     * Run the color match algorithm on our detected color
     */

    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    gameData = DriverStation.getInstance().getGameSpecificMessage();

    if (gameData.equals("B")) {
      gameDataNum = Cores.B.ordinal();
    } else if (gameData.equals("G")) {
      gameDataNum = Cores.G.ordinal();
    } else if (gameData.equals("R")) {
      gameDataNum = Cores.R.ordinal();
    } else if (gameData.equals("Y")) {
      gameDataNum = Cores.Y.ordinal();
    }

    if (match.color == kBlueTarget) {
      colorString = "Blue";
      colorLetras = "B";
      colorNum = Cores.B.ordinal();
    } else if (match.color == kRedTarget) {
      colorString = "Red";
      colorLetras = "R";
      colorNum = Cores.R.ordinal();

    } else if (match.color == kGreenTarget) {
      colorString = "Green";
      colorLetras = "G";
      colorNum = Cores.G.ordinal();

    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
      colorLetras = "Y";
      colorNum = Cores.Y.ordinal();

    } else {
      colorString = "Unknown";
      colorLetras = "Unknown";
    }

    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the sensor.
     */
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
  }

  // go to the selected color
  public void positionControll() {
    if (vaiCor) {
      if (gameDataNum != ((colorNum + 2) % 4)) {
        spinner.set(0.25);
      } else {
        spinner.set(0);
        vaiCor = false;
      }
    } else {
      spinner.set(0);
    }
  }

  // rotate the control panel
  public void rotationControl() {
    
      if (start) {
        timer.start();
        start = false;
      }
      vai = true;

    

    if (vai) {

      if (timer.get() < 3) {
        // colorCount++;
        spinner.set(.75);

      } else {
        vai = false;
        timer.stop();
        spinner.set(0);
      }
    }

   
      if (!gameData.equals(colorLetras)) {
        spinner.set(0.4);
      } else {
        spinner.set(0);
      }
    } 
  
}
