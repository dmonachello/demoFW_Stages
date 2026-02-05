// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HopperSubsystem extends SubsystemBase {
  private final SparkMax motor =
      new SparkMax(Constants.Hopper.kMotorId, MotorType.kBrushless);

  public HopperSubsystem() {
    motor.setInverted(Constants.Hopper.kInverted);
  }

  public void feed() {
    motor.set(Constants.Hopper.kFeedPower);
  }

  public void stop() {
    motor.stopMotor();
  }
}
