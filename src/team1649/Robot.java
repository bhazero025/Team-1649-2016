package team1649;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends SampleRobot
{
	//Test2
	//Declare all global variables
    RobotDrive robotDrive;
    Joystick stick; //Controller
    VictorSP leftMotor;
    VictorSP rightMotor;
    VictorSP arm;
    Compressor pneumatics; 
    Solenoid solenoid;


    // PWM channels
    final int pwmRight = 1;
    final int pwmLeft = 0;
    final int solenoidChannel = 1;
    final int armChannel = 2;
    
    // The channel on the driver station that the joystick is connected to
    final int joystickChannel	= 0;
    
    public Robot() 
    {
        robotDrive = new RobotDrive(pwmLeft ,pwmRight);
        robotDrive.setExpiration(0.1);
        
        pneumatics = new Compressor(); // Default is 0.
        
        solenoid = new Solenoid(solenoidChannel);
        
        arm = new VictorSP(armChannel);
    
        stick = new Joystick(joystickChannel);        
    }
    
    public void test()
    {
    	
    }
    
    public void a_turnLeft(int times)
    {
    	for (int i = 0; i < times; i++)
    	{
        	robotDrive.drive(1.0, 0.5);
            Timer.delay(0.1);
    	}
    }
    
    public void autonomous()
    {
    	a_turnLeft(10);
        
        
        robotDrive.drive(0.0, 0.0);	// stop robot
    }	
        
    public void operatorControl() 
    {
        robotDrive.setSafetyEnabled(true);
           
        while (isOperatorControl() && isEnabled())
        {
    	    robotDrive.tankDrive(leftSpeed(), rightSpeed());
    	    
        	if (stick.getRawButton(3))
        	{ 	
        		solenoid.set(true);
        	}
        	
        	if (stick.getRawButton(4))
        	{
        		solenoid.set(false);
        	}
        	
        	if (stick.getRawButton(5))
        	{
        		arm.set(0.7);
        	}
        	else
        	{
        		arm.set(0);
        	}
        	
        	if (stick.getRawButton(6))
        	{
        		arm.set(-1);
        	}
       
	        
	        SmartDashboard.putNumber("leftSpeed(): ", leftSpeed());
	        SmartDashboard.putNumber("rightSpeed(): ", rightSpeed());
	        SmartDashboard.putBoolean("rightMotor isAlive", rightMotor.isAlive());
	        SmartDashboard.putBoolean("leftMotor isAlive", leftMotor.isAlive());
	        SmartDashboard.putBoolean("Solenoid", solenoid.get());
	        SmartDashboard.putNumber("armSpeed():", arm.get());
	        
	        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
          
        }    
    }
    
    private double leftSpeed()
    {	
    	double speed = stick.getRawAxis(1) * - 1;
    	
    	if ((speed <= 0.25) & (speed >=- 0.25))
    	{
    		return 0.0;
    	}
    	else
    	{
        	return speed;
    	}
    }
    
    private double leftSpeed(int axis)
    {	
    	double speed = stick.getRawAxis(axis) * - 1;
    	
    	if ((speed <= 0.25) & (speed >=- 0.25))
    	{
    		return 0.0;
    	}
    	else
    	{
        	return speed;
    	}
    }
    
    private double rightSpeed()
    {
    	double speed = stick.getRawAxis(5) * - 1;
    	
    	if ((speed <= 0.25) & (speed >=- 0.25))
    	{
    		return 0.0;
    	}
    	else
    	{
        	return speed;
    	}
    }

    private double rightSpeed(int axis)
    {
    	double speed = stick.getRawAxis(axis) * - 1;
    	
    	if ((speed <= 0.25) & (speed >=- 0.25))
    	{
    		return 0.0;
    	}
    	else
    	{
        	return speed;
    	}
    }
}

