// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private final SparkMax leader =
      new SparkMax(Constants.Shooter.kLeaderId, MotorType.kBrushless);
  private final SparkMax follower =
      new SparkMax(Constants.Shooter.kFollowerId, MotorType.kBrushless);

  public ShooterSubsystem() {
    follower.follow(leader, Constants.Shooter.kFollowerInverted);
  }

  public void setPower(double power) {
    leader.set(power);
  }

  public void stop() {
    leader.stopMotor();
  }
}
