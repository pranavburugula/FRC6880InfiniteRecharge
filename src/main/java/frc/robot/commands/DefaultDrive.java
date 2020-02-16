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
import frc.robot.subsystems.DriveTrain;

public class DefaultDrive extends RunCommand {
  private DriveTrain m_drvTrain;
  private DoubleSupplier m_speed;
  private DoubleSupplier m_rotation;
  /**
   * Creates a new DefaultDrive.
   */
  public DefaultDrive(DriveTrain drvTrain, DoubleSupplier speed, DoubleSupplier rotation) {
    super(drvTrain::arcadeDrive, drvTrain);
    m_drvTrain = drvTrain;
    m_speed = speed;
    m_rotation = rotation;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drvTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drvTrain.arcadeDrive(-1 * m_speed.getAsDouble(), m_rotation.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
