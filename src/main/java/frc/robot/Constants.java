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

    // Open-loop power levels for early testing.
    public static final double kLowPower = 0.25;
    public static final double kMidPower = 0.45;
    public static final double kHighPower = 0.65;

    private Shooter() {}
  }
}
