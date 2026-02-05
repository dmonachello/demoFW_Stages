// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.SetShooterRpm;
import frc.robot.commands.StopShooter;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.util.ShooterRpmMap;

public class RobotContainer {
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final CommandXboxController operator =
      new CommandXboxController(Constants.OI.kOperatorPort);
  private final GenericHID trellis = new GenericHID(Constants.OI.kTrellisPort);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    operator.a().onTrue(new SetShooterRpm(shooter, Constants.Shooter.kLowRpm));
    operator.y().onTrue(new SetShooterRpm(shooter, Constants.Shooter.kMidRpm));
    operator.b().onTrue(new SetShooterRpm(shooter, Constants.Shooter.kHighRpm));
    operator.x().onTrue(new StopShooter(shooter));
    operator.leftBumper()
        .onTrue(Commands.runOnce(shooter::requestAutoShoot, shooter));
    operator.rightBumper()
        .onTrue(Commands.runOnce(shooter::shootTheBall, shooter));
    operator
        .povUp()
        .whileTrue(
            Commands.run(
                () ->
                    shooter.setTargetRpm(
                        ShooterRpmMap.rpmFromDistanceTable(getVisionDistanceMeters())),
                shooter));

    new JoystickButton(trellis, 1).onTrue(new SetShooterRpm(shooter, Constants.Shooter.kLowRpm));
    new JoystickButton(trellis, 2).onTrue(new SetShooterRpm(shooter, Constants.Shooter.kMidRpm));
    new JoystickButton(trellis, 3).onTrue(new SetShooterRpm(shooter, Constants.Shooter.kHighRpm));
    new JoystickButton(trellis, 4)
        .onTrue(Commands.runOnce(shooter::shootTheBall, shooter));
    new JoystickButton(trellis, 5)
        .onTrue(Commands.runOnce(shooter::requestAutoShoot, shooter));
    new JoystickButton(trellis, 6)
        .whileTrue(
            Commands.run(
                () ->
                    shooter.setTargetRpm(
                        ShooterRpmMap.rpmFromDistanceTable(getVisionDistanceMeters())),
                shooter));
    new JoystickButton(trellis, 24).onTrue(new StopShooter(shooter));
  }

  private double getVisionDistanceMeters() {
    // TODO: Replace the fake distance with PhotonVision when available.
    /*
    PhotonCamera camera = new PhotonCamera(Constants.Vision.kCameraName);
    PhotonPipelineResult result = camera.getLatestResult();
    if (!result.hasTargets()) {
      return Double.NaN;
    }
    PhotonTrackedTarget target = result.getBestTarget();
    return PhotonUtils.calculateDistanceToTargetMeters(
        Constants.Vision.kCameraHeightMeters,
        Constants.Vision.kTargetHeightMeters,
        Constants.Vision.kCameraPitchRadians,
        Units.degreesToRadians(target.getPitch()));
    */
    return Constants.Vision.kFakeDistanceMeters;
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
