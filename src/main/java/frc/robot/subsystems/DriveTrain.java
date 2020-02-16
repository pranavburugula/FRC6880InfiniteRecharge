/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.function.Supplier;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends SubsystemBase {
  CANSparkMax m_leftMaster, m_leftSlave, m_rightMaster, m_rightSlave;
  CANEncoder  neo_leftEncoder, neo_rightEncoder;
  // private final Encoder m_leftEncoder;
  // private final Encoder m_rightEncoder;


  Supplier<Double> leftEncoderPosition;
  Supplier<Double> leftEncoderRate;
  Supplier<Double> rightEncoderPosition;
  Supplier<Double> rightEncoderRate;
  Supplier<Double> gyroAngleRadians;
  DifferentialDrive m_dDrive;

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    m_leftMaster = new CANSparkMax(DriveConstants.kLeftMotor1_id, MotorType.kBrushless);
    m_leftMaster.setInverted(false);
    m_leftMaster.setIdleMode(IdleMode.kBrake);
    neo_leftEncoder = m_leftMaster.getEncoder();

    m_leftSlave = new CANSparkMax(DriveConstants.kLeftMotor2_id, MotorType.kBrushless);
    m_leftSlave.setIdleMode(IdleMode.kBrake);
    m_leftSlave.follow(m_leftMaster);

    m_rightMaster = new CANSparkMax(DriveConstants.kRightMotor1_id, MotorType.kBrushless);
    m_rightMaster.setInverted(false);
    m_rightMaster.setIdleMode(IdleMode.kBrake);
    neo_rightEncoder = m_rightMaster.getEncoder();

    m_rightSlave = new CANSparkMax(DriveConstants.kRightMotor2_id, MotorType.kBrushless);
    m_rightSlave.setIdleMode(IdleMode.kBrake);
    m_rightSlave.follow(m_rightMaster);

    m_dDrive = new DifferentialDrive(m_leftMaster, m_rightMaster);
    m_dDrive.setDeadband(0);

    resetEncoders();


    // Let's name the sensors on the LiveWindow
    addChild("Drive", m_dDrive);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Update the odometry in the periodic block
    //m_odometry.update(Rotation2d.fromDegrees(getHeading()), m_leftEncoder.getDistance(),
    //                  m_rightEncoder.getDistance());
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_dDrive.arcadeDrive(fwd, rot);
  }

  public void arcadeDrive() {}

  /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts  the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    // ToDo:  Verify whether setVolatge() is applied to the follower motor controller.
    m_leftMaster.setVoltage(leftVolts);
    m_rightMaster.setVoltage(-rightVolts);
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (neo_leftEncoder.getPosition() + neo_rightEncoder.getPosition()) / 2.0;
    // return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public CANEncoder getLeftEncoder() {
    return neo_leftEncoder;
    //    return m_leftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public CANEncoder getRightEncoder() {
    return neo_rightEncoder;
    // return m_rightEncoder;
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_dDrive.setMaxOutput(maxOutput);
  }



  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    neo_leftEncoder.setPosition(0.0);
    neo_rightEncoder.setPosition(0.0);
    // m_leftEncoder.reset();
    // m_rightEncoder.reset();
  }


}
