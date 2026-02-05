// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.SetShooterPower;
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
    operator.a().onTrue(new SetShooterPower(shooter, Constants.Shooter.kLowPower));
    operator.y().onTrue(new SetShooterPower(shooter, Constants.Shooter.kMidPower));
    operator.b().onTrue(new SetShooterPower(shooter, Constants.Shooter.kHighPower));
    operator.x().onTrue(new StopShooter(shooter));

    new JoystickButton(trellis, 1)
        .onTrue(new SetShooterPower(shooter, Constants.Shooter.kLowPower));
    new JoystickButton(trellis, 2)
        .onTrue(new SetShooterPower(shooter, Constants.Shooter.kMidPower));
    new JoystickButton(trellis, 3)
        .onTrue(new SetShooterPower(shooter, Constants.Shooter.kHighPower));
    new JoystickButton(trellis, 24).onTrue(new StopShooter(shooter));
  }

  public Command getAutonomousCommand() {
    return edu.wpi.first.wpilibj2.command.Commands.print("No autonomous command configured");
  }
}
