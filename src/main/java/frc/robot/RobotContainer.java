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

    new JoystickButton(trellis, 1).onTrue(new SetShooterRpm(shooter, Constants.Shooter.kLowRpm));
    new JoystickButton(trellis, 2).onTrue(new SetShooterRpm(shooter, Constants.Shooter.kMidRpm));
    new JoystickButton(trellis, 3).onTrue(new SetShooterRpm(shooter, Constants.Shooter.kHighRpm));
    new JoystickButton(trellis, 24).onTrue(new StopShooter(shooter));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
