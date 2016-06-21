package org.usfirst.frc.team3256.robot;

public class PIDController {
	double P;
	double I;
	double D;
	double kP;
	double kI;
	double kD;	
	double error;
    double sumError;
    double changeError;
    double prevError;
    double PID;
    
    public PIDController(double kP, double kI, double kD){
    	this.kP = kP;
    	this.kI = kI;
    	this.kD = kD;
    }
    
    public void resetPID(){
    	error=0;
    	sumError=0;
    	changeError=0;
    	prevError=0;
    }
    
    public double getError(double current, double setpoint){
    	return Math.abs(setpoint - current);
    }
    
    public double calculatePID(double current, double setpoint) {
        error = setpoint - current;
        sumError = sumError + error;
        changeError = (error-prevError);
        P = kP * error;
        I = sumError * kI;
        D = kD * changeError;
        PID = P + I + D;
        prevError = error;
        if (PID > 1)
        	PID = 1;
        return PID;
    }

}