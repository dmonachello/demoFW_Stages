// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HopperSubsystem extends SubsystemBase {
  private final SparkMax motor =
      new SparkMax(Constants.Hopper.kMotorId, MotorType.kBrushless);

  public HopperSubsystem() {
    SparkMaxConfig config = new SparkMaxConfig();
    config.inverted(Constants.Hopper.kInverted);
    motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void feed() {
    motor.set(Constants.Hopper.kFeedPower);
  }

  public void stop() {
    motor.stopMotor();
  }
}
