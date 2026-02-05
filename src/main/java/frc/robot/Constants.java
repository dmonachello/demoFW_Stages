// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  private Constants() {}

  public static final class OI {
    public static final int kOperatorPort = 0;
    public static final int kTrellisPort = 1;

    private OI() {}
  }

  public static final class Shooter {
    public static final int kLeaderId = 25;
    public static final int kFollowerId = 40;
    public static final boolean kFollowerInverted = true;

    // RPM setpoints for the demo.
    public static final double kLowRpm = 2000.0;
    public static final double kMidRpm = 3250.0;
    public static final double kHighRpm = 4500.0;
    public static final double kMaxRpm = 5700.0;

    // Basic velocity PID + feedforward gains for a NEO on a Spark Max.
    public static final double kP = 0.00018;
    public static final double kI = 0.0;
    public static final double kD = 0.00003;
    public static final double kFF = 0.961 / kMaxRpm;
    public static final double kIZone = 0.0;
    public static final double kAllowedClosedLoopError = 75.0;
    public static final int kSmartCurrentLimit = 40;
    public static final double kOpenLoopRampRate = 0.1;
    public static final double kClosedLoopRampRate = 0.0;

    private Shooter() {}
  }
}
