/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants {
        public static final int kLeftMotor1_id = 36;
        public static final int kLeftMotor2_id = 38;
        public static final int kRightMotor1_id = 33;
        public static final int kRightMotor2_id = 37;

        public static final double kDriveGearRatio = 10.71;
        public static final double kWheelDiameterMeters = 0.1524;
        public static final double kTrackwidthMeters = 0.6713;

        public static final int kSparkMaxBuiltinCPR = 42;
        //Convert the Rotations reported by built-in neo encoder to Meters as needed by the 
        // WPILIB's Ramsette controller
        public static final double kNeoPositionConversionFactor = (1/kDriveGearRatio) * Math.PI * kWheelDiameterMeters;
        // Convert RPM reported by built-in neo encoder to Meters/sec as needed by the 
        // WPILIB's Ramsette controller
        public static final double kNeoVelocityConversionFactor = (1/60) * (1/kDriveGearRatio) * Math.PI * kWheelDiameterMeters;

        public static final DifferentialDriveKinematics kDriveKinematics =
            new DifferentialDriveKinematics(kTrackwidthMeters);

    
        // public static final int kNeoEncoderPulsesPerRev = kSparkMaxBuiltinCPR * 4;
        // public static final double kNeoEncoderMetersPerPulse =
        //     // Assumes the encoders are directly mounted on the wheel shafts
        //     (kWheelDiameterMeters * Math.PI) / (double) (kNeoEncoderPulsesPerRev * kDriveGearRatio);
    
        public static final boolean kGyroReversed = true;
    
        // kS, kV, kA values from frc-characterization tool
        public static final double ksVolts = 0.15;
        public static final double kvVoltSecondsPerMeter = 2.71;
        public static final double kaVoltSecondsSquaredPerMeter = 0.367;
    
        // Example value only - as above, this must be tuned for your drive!
        public static final double kPDriveVel = 1.34; // value from characterization tool is 13.4!
        public static final double kD = 0.0;
    }
    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kOtherControllerPort = 1;
    }

    public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;

        // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;
    }

    public static final class AutoPathsConstants {
        public static final int kPos3Path1_numSegments = 4;
        public static final String[] kPos3Path1 = 
            new String[] {"paths/Auto_pos3_path1_segment1.wpilib.json", "paths/Auto_pos3_path1_segment2.wpilib.json",
                "paths/Auto_pos3_path1_segment3.wpilib.json", "paths/Auto_pos3_path1_segment4.wpilib.json"};
    }

    public static final class IntakeConstants {
        public static final int kIntakeMotor_id = 21;
        public static final InvertType kMotorInverted = InvertType.None;
        public static final int[] kEncoderPorts = new int[]{4, 5};
        public static final boolean kEncoderReversed = true;
        public static final double kEncoderPulsesPerRev = 8192; // Rev Throughbore encoder

        public static final int[] kDSolenoidPorts = new int[]{0,1};

        public static final double kIntakeGearRatio = 4.0; // Assuming a 57Sport 4:1 gearbox
        public static final double kPullInPower = 0.6;  // Power to be applied for pulling in the PowerCell
        public static final double kPushOutPower = -0.5;  // Power to be applied for pushing out the PowerCell
    }

    public static final class IndexerConstants {
        public static final int kIndexerMotor_id = 11;
        public static final InvertType kMotorInverted = InvertType.None;
        public static final int[] kEncoderPorts = new int[]{6, 7};
        public static final boolean kEncoderReversed = false;
        public static final double kEncoderPulsesPerRev = 8192; // Rev Throughbore encoder
        public static final double kIndexerGearRatio = 24.0; // Assuming Johnson Electric Motor
        public static final double kPullInPower = 0.6;  // Power to be applied for pulling in the PowerCell in Indexer
        public static final double kPushOutPower = -0.5;  // Power to be applied for pushing out the PowerCell in Indexer
    }

    public static final class LauncherConstants {
        public static final int kLauncherMotorLeft_id = 31;
        public static final int kLauncherMotorRight_id = 39;
        public static final double kClosedLoopRampRate = 1.0;  // 1 second from 0 to full throttle
        public static final IdleMode kIdleMode = IdleMode.kCoast;
        // public static final double kLauncherPower = 0.8;

        // Values from FRC Characterization Tool (Spark Max controller; units: Rotations per second)
        public static final double kS = 0.0609;
        public static final double kV = 0.126;
        public static final double kA = 0.0177;
        public static final double char_kP_RPS = 0.14; // kP in units of Revolutions per second; output is voltage
        public static final double char_kD = 0.0;
        public static final double kMaxVoltage = 12.0;
        public static final double kP_RPM = char_kP_RPS / (kMaxVoltage * 60);

        // PID coefficients, tryied to manually tune them without characterization tool
        // public static final double  kP = 6e-5; 
        public static final double  kI = 0;
        public static final double  kD = 0; 
        public static final double  kIz = 0; 
        // public static final double  kFF = 0.00015; // zero-zero-zero-one-five
        public static final double  kMaxOutput = 1; 
        public static final double  kMinOutput = -1;
        public static final double  kMaxRPM = 5700;
    }
}
