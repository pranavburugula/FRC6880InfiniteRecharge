/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.XboxController.Axis;
import frc.robot.Constants.LimelightConstants;
import frc.robot.Constants.OIConstants;
//import frc.robot.commands.Auto_pos3_path1;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.VictorSP_NavX_DriveTrain;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final VictorSP_NavX_DriveTrain m_robotDrive = new VictorSP_NavX_DriveTrain("");
  // private final Intake m_intake = new Intake();
  // private final Indexer m_indexer = new Indexer();
  // private final Launcher m_launcher = new Launcher();
  private final Limelight m_limelight = new Limelight();

  // private final AutoDriveToTrench m_autoCommand = new AutoDriveToTrench(m_robotDrive);
  // The driver's controller
  XboxController m_gamepad1 = new XboxController(OIConstants.kDriverControllerPort);
  XboxController m_gamepad2 = new XboxController(OIConstants.kOtherControllerPort);



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_robotDrive.setDefaultCommand(
      new RunCommand(() -> m_robotDrive
        .arcadeDrive(m_gamepad1.getY(GenericHID.Hand.kLeft),
        m_gamepad1.getX(GenericHID.Hand.kRight)), m_robotDrive));

    // ToDo: Need to change the button binding for launcher
    // m_launcher.setDefaultCommand(
    //   new RunCommand(() -> m_launcher
    //     .launch(m_gamepad2.getY(GenericHID.Hand.kLeft)), m_launcher));
    
    // ToDo: Add more trigger / gamepad-stick bindings

    // new InstantCommand(() -> m_robotDrive
    //     .arcadeDrive(m_gamepad1.getY(GenericHID.Hand.kLeft), m_gamepad1.getX(GenericHID.Hand.kRight)), m_robotDrive);

    
    // ToDo:  Add CameraServer to display the front side camera (not Limelight!) video stream.

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Assume that we will use 2 xbox controllers for controlling the robot
    new JoystickButton(m_gamepad1, Button.kBumperRight.value)
        .whenPressed(() -> m_robotDrive.setMaxOutput(0.5))
        .whenReleased(() -> m_robotDrive.setMaxOutput(1));

    // Bind Limelight-based shooter aiming to Gamepad 1 button a
    new JoystickButton(m_gamepad1, XboxController.Button.kA.value)
        .whileHeld(new RunCommand(() -> m_robotDrive.arcadeDrive(0.0, m_limelight.calculateAimingCorrection(1.0))));

    // Bind Limelight-based seek target to Gamepad 1 button y
    new JoystickButton(m_gamepad1, XboxController.Button.kY.value)
        .whileHeld(new RunCommand(() -> m_robotDrive.arcadeDrive(m_limelight.calculateDistanceCorrection(LimelightConstants.seekTargetDistance), 
                                                  m_limelight.calculateAimingCorrection(1.0))));

    // new Trigger(() -> m_gamepad1.getY(GenericHID.Hand.kLeft) != 0.0 || m_gamepad1.getX(GenericHID.Hand.kRight) != 0.0)
    //     .whenActive(() -> m_robotDrive.arcadeDrive(m_gamepad1.getY(GenericHID.Hand.kLeft), m_gamepad1.getX(GenericHID.Hand.kRight)));
    
    // Add another binding to toggle coast and brake modes for the drive train
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // ToDo:  Instantiate AutonomousOptions class, create 3 commands that invoke the methods
    //        in that class.
    // ToDo: Use SendableChooser to select one of the 3 autonomous options,
    //       and return the command corresponding to the selected option.
    return (null);
  }
}
