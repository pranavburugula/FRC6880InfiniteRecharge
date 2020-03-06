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

  /**
   * Creates a new Limelight.
   */
  public Limelight() {

  }

  public void displayLimelightValues() {
    tx_getter.setDouble(tx.getDouble(0.0));
    ty_getter.setDouble(ty.getDouble(0.0));
    ta_getter.setDouble(ta.getDouble(0.0));
  }

  /**
   * ToDo: 
   */
  public void calculateDistanceToPowerport() {

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
  }
}
