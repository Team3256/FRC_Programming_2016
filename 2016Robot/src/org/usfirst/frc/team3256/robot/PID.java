package org.usfirst.frc.team3256.robot;

public class PID {
	double P;
	double I;
	double D;
	double kP;
	double kI;
	double kD;	
	double error = 0;
    double sumError = 0;
    double changeError = 0;
    double prevError = 0;
    double PID;
    double PIDOutput;
    double pi = 3.1415926535897932384626;
    
    public PID(double kP, double kI, double kD){
    	this.kP = kP;
    	this.kI = kI;
    	this.kD = kD;
    }
    
    public double getError(double current, double setpoint){
    	return setpoint - current;
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
        //System.out.println("Error" + error);
        if (PID > 1)
        	PID = 1;
        return PID;
    }

}

