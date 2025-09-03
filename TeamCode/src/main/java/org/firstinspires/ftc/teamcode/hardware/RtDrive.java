package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utilities.RtLog;




public class RtDrive {
    private Telemetry m_telemetry;
    private DcMotor m_dtLeftBackMotor;
    private DcMotor m_dtRightBackMotor;
    private DcMotor m_dtRightFrontMotor;
    private DcMotor m_dtLeftFrontMotor;

    public RtDrive(DcMotor parLbMotor, DcMotor parRbMotor, DcMotor parRfMotor, DcMotor parLfMotor, Telemetry parTelemetry) {
        m_dtLeftBackMotor   = parLbMotor;
        m_dtRightBackMotor  = parRbMotor;
        m_dtRightFrontMotor = parRfMotor;
        m_dtLeftFrontMotor  = parLfMotor;
        m_telemetry         = parTelemetry;
    }

    public void printDtPower()
    {
        if (hwExists()) {
            m_telemetry.addData("LFMotor Power Drive", "%.2f", m_dtLeftFrontMotor.getPower());
            m_telemetry.addData("RFMotor Power Drive", "%.2f", m_dtRightFrontMotor.getPower());
            m_telemetry.addData("LBMotor Power Drive", "%.2f", m_dtLeftBackMotor.getPower());
            m_telemetry.addData("RBMotor Power Drive", "%.2f", m_dtRightBackMotor.getPower());
            m_telemetry.update();
        }
    }
    public void drive(float parX, float parY, float parRotation, boolean parBoostSpeed){

        final double QUARTER_PI = Math.PI/4;
        final double NOMINAL_SPEED = 0.6;
        final double TURBO_SPEED = 0.9;
        final double MAX_POWER = 1.0;

        double theta = Math.atan2(parY, parX);
        double power = Math.hypot(parX, parY);
        double sin = Math.sin(theta - QUARTER_PI), cos = Math.cos(theta - QUARTER_PI);
        double max = Math.max(Math.abs(sin), Math.abs(cos));
        if (hwExists()) {
            m_dtLeftFrontMotor.setPower((power * cos / max + parRotation) * NOMINAL_SPEED);
            m_dtRightFrontMotor.setPower((power * sin / max - parRotation) * NOMINAL_SPEED);
            m_dtLeftBackMotor.setPower((power * sin / max + parRotation) * NOMINAL_SPEED);
            m_dtRightBackMotor.setPower((power * cos / max - parRotation) * NOMINAL_SPEED);

            if ((power + Math.abs(parRotation)) > MAX_POWER) {
                m_dtLeftFrontMotor.setPower(m_dtLeftFrontMotor.getPower() / (power + parRotation));
                m_dtRightFrontMotor.setPower(m_dtRightFrontMotor.getPower() / (power + parRotation));
                m_dtRightBackMotor.setPower(m_dtRightBackMotor.getPower() / (power + parRotation));
                m_dtLeftBackMotor.setPower(m_dtLeftBackMotor.getPower() / (power + parRotation));
            }

            if (parBoostSpeed) {
                m_dtLeftFrontMotor.setPower((power * cos / max + parRotation) * TURBO_SPEED);
                m_dtRightFrontMotor.setPower((power * sin / max - parRotation) * TURBO_SPEED);
                m_dtLeftBackMotor.setPower((power * sin / max + parRotation) * TURBO_SPEED);
                m_dtRightBackMotor.setPower((power * cos / max - parRotation) * TURBO_SPEED);
            }
        }
    }

    public void stop(){
        if ( hwExists()) {
            m_dtLeftFrontMotor.setPower(0);
            m_dtRightFrontMotor.setPower(0);
            m_dtLeftBackMotor.setPower(0);
            m_dtRightBackMotor.setPower(0);
        }
    }
    public void moveRight(float parSpeed){
        if (hwExists()) {
            drive(parSpeed,0,0,false);
        }
    }

    public void moveLeft(float parSpeed){
        if (hwExists()) {
            drive(-parSpeed,0,0,false);
        }
    }

    public void moveForward(float parSpeed){
        if (hwExists()) {
            drive(0,-parSpeed,0,false);
        }
    }

    public void moveBackward(float parSpeed){
        if (hwExists()) {
            drive(0,parSpeed,0,false);
        }
    }

    public void turnClockwise(long parRotationInDegrees){
        if (hwExists()) {
            drive(0,0,parRotationInDegrees,false);
        }
    }

    public void turnCounterClockwise(long parRotationInDegrees){
        if (hwExists()) {
            drive(0,0,-parRotationInDegrees,false);
        }
    }
    private boolean hwExists() {
        boolean exists = true;

        if (m_dtLeftBackMotor   == null || m_dtRightBackMotor == null ||
            m_dtRightFrontMotor == null || m_dtLeftFrontMotor == null )
        {
            exists = false;
            m_telemetry.addLine("RtDrive HW NOT CONNECTED");
            m_telemetry.update();
        }
        return exists;
    }
}
