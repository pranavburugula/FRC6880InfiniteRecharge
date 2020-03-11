/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry tx = table.getEntry("tx");
  private NetworkTableEntry ty = table.getEntry("ty");
  private NetworkTableEntry ta = table.getEntry("ta");
  private NetworkTableEntry tv = table.getEntry("tv");

  private ShuffleboardTab sf_tab = Shuffleboard.getTab("Limelight");
  private NetworkTableEntry tx_getter = sf_tab.add("tx Value", 0.0).getEntry();
  private NetworkTableEntry ty_getter = sf_tab.add("ty Value", 0.0).getEntry();
  private NetworkTableEntry ta_getter = sf_tab.add("ta Value", 0.0).getEntry();

  private double kP_orientation, kP_distance;
  private double initSpeed;
  private double mountingAngle, mountingHeight;
  private double targetHeight;

  /**
   * Creates a new Limelight.
   */
  public Limelight() {
    kP_orientation = 0.1;
    kP_distance = 0.1;
    initSpeed = 0.05;
    mountingAngle = 0.0;
    mountingHeight = 0.0;
    targetHeight = 1.0;
  }

  public Limelight(double kP_orient, double kP_dist, double speed, double mountA, double mountH, double targetH) {
    kP_orientation = kP_orient;
    kP_distance = kP_dist;
    initSpeed = speed;    // Min speed required to move drivetrain
    mountingAngle = mountA;
    mountingHeight = mountH;
    targetHeight = targetH;
  }

  public void displayLimelightValues() {
    tx_getter.setDouble(tx.getDouble(0.0));
    ty_getter.setDouble(ty.getDouble(0.0));
    ta_getter.setDouble(ta.getDouble(0.0));
  }

  /**
   * Calculates radial distance to vision target using the following formula:
   * 
   * tan(vertical angle offset + mounting angle) = (targetHeight - mountingHeight) / distance
   * 
   * @param mountH Height of Limelight from ground
   * @param mountA Angle of Limelight with respect to ground
   * @param targetH Height of target from ground
   * @return Radial distance to target
   */
  public double calculateDistanceToTarget(double mountH, double mountA, double targetH) {
    double phi = ty.getDouble(0.0);
    double distance = (targetH - mountH) / (Math.tan(phi + mountA));

    return distance;
  }
  public double calculateDistanceToTarget() {
    return calculateDistanceToTarget(mountingHeight, mountingAngle, targetHeight);
  }

  public double calculateDistanceCorrection(double targetDist, double targetAngle) {
    double deltaDist = calculateDistanceToTarget() - targetDist;
    double aimAdjust = calculateAimingCorrection(targetAngle);
    return aimAdjust + kP_distance * deltaDist;
  }

  /**
   * Calculates correction to apply to robot to aim towards a target
   * @param targetOffset Desired angle (degrees) between robot and target
   * @return
   */
  public double calculateAimingCorrection(double targetOffset) {
    double theta = tx.getDouble(0.0);
    double correction = 0.0;
    if (tv.getDouble(0.0) > 0.0) {
      if (theta > targetOffset) {
        correction = kP_orientation * theta - initSpeed;    // Robot oriented too far right, spin left
      } else if (theta < targetOffset) {
        correction = kP_orientation * theta + initSpeed;    // Robot oriented too far left, spin right
      }
    } else {
      System.out.println("frc6880: Limelight: No targets visible");
    }

    return correction;
  }

  /**
   * ToDo:  Implement if possible
   */
  public void seekPowerport() {
    // This method is expected to generate a trajectory 
    // and use that trajectory with a RamsetterCommand to move
    // the robot to in front of the power port
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //read values periodically
    displayLimelightValues();
  }
}
