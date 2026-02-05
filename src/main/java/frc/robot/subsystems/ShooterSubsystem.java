// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private final SparkMax leader =
      new SparkMax(Constants.Shooter.kLeaderId, MotorType.kBrushless);
  private final SparkMax follower =
      new SparkMax(Constants.Shooter.kFollowerId, MotorType.kBrushless);
  private final RelativeEncoder leaderEncoder = leader.getEncoder();
  private final RelativeEncoder followerEncoder = follower.getEncoder();
  private final SparkClosedLoopController pid = leader.getClosedLoopController();

  private double targetRpm = 0.0;

  public ShooterSubsystem() {
    SparkMaxConfig baseConfig = new SparkMaxConfig();
    baseConfig
        .inverted(false)
        .smartCurrentLimit(Constants.Shooter.kSmartCurrentLimit)
        .openLoopRampRate(Constants.Shooter.kOpenLoopRampRate)
        .closedLoopRampRate(Constants.Shooter.kClosedLoopRampRate)
        .closedLoop
        .pid(Constants.Shooter.kP, Constants.Shooter.kI, Constants.Shooter.kD)
        .iZone(Constants.Shooter.kIZone)
        .allowedClosedLoopError(Constants.Shooter.kAllowedClosedLoopError, ClosedLoopSlot.kSlot0)
        .outputRange(-1.0, 1.0);
    baseConfig.closedLoop.feedForward.kV(Constants.Shooter.kFF, ClosedLoopSlot.kSlot0);

    SparkMaxConfig leaderConfig = new SparkMaxConfig();
    leaderConfig.apply(baseConfig);
    leader.configure(leaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    SparkMaxConfig followerConfig = new SparkMaxConfig();
    followerConfig.apply(baseConfig).follow(leader, Constants.Shooter.kFollowerInverted);
    follower.configure(followerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void setTargetRpm(double rpm) {
    targetRpm = MathUtil.clamp(rpm, 0.0, Constants.Shooter.kMaxRpm);
    pid.setSetpoint(targetRpm, ControlType.kVelocity);
  }

  public void stop() {
    targetRpm = 0.0;
    leader.stopMotor();
  }

  public double getTargetRpm() {
    return targetRpm;
  }

  public double getMeasuredRpm() {
    return leaderEncoder.getVelocity();
  }

  public double getFollowerMeasuredRpm() {
    return followerEncoder.getVelocity();
  }

  public boolean isAtTargetRpm() {
    return targetRpm > 0.0 && pid.isAtSetpoint();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Shooter Target RPM", targetRpm);
    SmartDashboard.putNumber("Shooter Leader RPM", getMeasuredRpm());
    SmartDashboard.putNumber("Shooter Follower RPM", getFollowerMeasuredRpm());
    SmartDashboard.putBoolean("Shooter At Target RPM", isAtTargetRpm());
  }
}
