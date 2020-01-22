/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DriveBaseSubsystem;

public class DefaultDrive extends RunCommand {
  private DriveBaseSubsystem drivebase;
  private DoubleSupplier speed;
  private DoubleSupplier rotation;

  /**
   * Creates a new DefaultDrive.
   */
  public DefaultDrive(DriveBaseSubsystem driveSubsystem, DoubleSupplier speed, DoubleSupplier rotation) {
    // Use addRequirements() here to declare subsystem dependencies.
    super(driveSubsystem::arcadeDrive, driveSubsystem);
    drivebase = driveSubsystem;
    this.speed = speed;
    this.rotation = rotation;
    addRequirements(drivebase);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivebase.arcadeDrive(speed.getAsDouble(), rotation.getAsDouble());
  }
}
