// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import frc.robot.Constants;

public final class ShooterRpmMap {
  // TODO: Replace with calibrated distance-to-RPM points.
  private static final double[] kDistanceMeters = {1.5, 2.5, 3.5, 4.5};
  private static final double[] kRpm = {2500.0, 3200.0, 3900.0, 4500.0};

  // TODO: Replace with a tuned linear fit for your shooter.
  private static final double kLinearSlopeRpmPerMeter = 700.0;
  private static final double kLinearInterceptRpm = 1450.0;

  private ShooterRpmMap() {}

  public static double rpmFromDistanceTable(double distanceMeters) {
    if (!Double.isFinite(distanceMeters)) {
      return 0.0;
    }

    if (distanceMeters <= kDistanceMeters[0]) {
      return kRpm[0];
    }
    if (distanceMeters >= kDistanceMeters[kDistanceMeters.length - 1]) {
      return kRpm[kRpm.length - 1];
    }

    for (int i = 0; i < kDistanceMeters.length - 1; i++) {
      double x0 = kDistanceMeters[i];
      double x1 = kDistanceMeters[i + 1];
      if (distanceMeters >= x0 && distanceMeters <= x1) {
        double y0 = kRpm[i];
        double y1 = kRpm[i + 1];
        double t = (distanceMeters - x0) / (x1 - x0);
        return y0 + t * (y1 - y0);
      }
    }

    return 0.0;
  }

  public static double rpmFromDistanceLinear(double distanceMeters) {
    if (!Double.isFinite(distanceMeters)) {
      return 0.0;
    }

    double rpm = kLinearSlopeRpmPerMeter * distanceMeters + kLinearInterceptRpm;
    return clampRpm(rpm);
  }

  public static double rpmFromDistance(double distanceMeters, boolean useTable) {
    double rpm = useTable ? rpmFromDistanceTable(distanceMeters) : rpmFromDistanceLinear(distanceMeters);
    return clampRpm(rpm);
  }

  private static double clampRpm(double rpm) {
    if (rpm < 0.0) {
      return 0.0;
    }
    if (rpm > Constants.Shooter.kMaxRpm) {
      return Constants.Shooter.kMaxRpm;
    }
    return rpm;
  }
}
