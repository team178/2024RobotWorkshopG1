package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
    private double speed;
    private CANSparkMax shooterLowerMotor;
    private CANSparkMax shooterUpperMotor;
    private CANSparkMax shooterIndexMotor;

    private boolean slow;
    private boolean fast;

    public Shooter() {
        speed = 0;
        shooterLowerMotor = new CANSparkMax(ShooterConstants.kShooterLowerMotorCanID, MotorType.kBrushless);
        shooterUpperMotor = new CANSparkMax(ShooterConstants.kShooterUpperMotorCanID, MotorType.kBrushless);
        shooterIndexMotor = new CANSparkMax(ShooterConstants.kShooterIndexMotorCanID, MotorType.kBrushless);
        
        shooterLowerMotor.restoreFactoryDefaults();
        shooterUpperMotor.restoreFactoryDefaults();
        shooterIndexMotor.restoreFactoryDefaults();

        shooterLowerMotor.burnFlash();
        shooterUpperMotor.burnFlash();
        shooterIndexMotor.burnFlash();

        slow = false;
        fast = false;
    }

    public void shoot() {
        if(!fast) {
            shooterLowerMotor.setVoltage(-3);
            shooterUpperMotor.setVoltage(3);
            shooterIndexMotor.setVoltage(-3);
        }
        slow = true;
    }

    public void fastShoot() {
        shooterLowerMotor.setVoltage(-6);
        shooterUpperMotor.setVoltage(6);
        shooterIndexMotor.setVoltage(-6);
        fast = true;
    }

    public void rest() {
        shooterLowerMotor.setVoltage(0);
        shooterUpperMotor.setVoltage(0);
        shooterIndexMotor.setVoltage(0);
    }

    public void slowRest() {
        slow = false;
        if(!fast) rest();
        
    }

    public void fastRest() {
        fast = false;
        if(!slow) rest();
    }

    public Command runShoot() {
        return runOnce(() -> shoot());
    }

    public Command runFastShoot() {
        return runOnce(() -> fastShoot());
    }

    public Command runRest() {
        return runOnce(() -> rest());
    }

    public Command runSlowRest() {
        return runOnce(() -> slowRest());
    }
    public Command runFastRest() {
        return runOnce(() -> fastRest());
    }
}
