/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveBaseSubsystem extends SubsystemBase {
  private SpeedControllerGroup leftMotors;
  private SpeedControllerGroup rightMotors;
  private DifferentialDrive drivesys;

  /**
   * Creates a new DriveBaseSubsystem.
   */
  public DriveBaseSubsystem() {
    rightMotors = new SpeedControllerGroup(new VictorSP(0), new VictorSP(1));
    leftMotors = new SpeedControllerGroup(new VictorSP(2), new VictorSP(3));
    drivesys = new DifferentialDrive(leftMotors, rightMotors);
  }

  public void arcadeDrive(double speed, double rotation) {
    drivesys.arcadeDrive(speed, rotation);
  }
  public void arcadeDrive(){}
}
