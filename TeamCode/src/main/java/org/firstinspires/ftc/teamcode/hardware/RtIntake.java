package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class RtIntake {
    private Telemetry m_telemetry;
    private DcMotor m_intakeMotor;
    private CRServo m_midtakeServo;

    private Boolean m_intakeRetrieve = false;
    private Boolean m_intakeExpel = false;
    private Boolean m_midtakeOn = false;
    public RtIntake(DcMotor parIntakeMotor, CRServo parMidtakeServo, Telemetry parTelemetry) {
        m_intakeMotor   = parIntakeMotor;
        m_midtakeServo  = parMidtakeServo;
        m_telemetry     = parTelemetry;
    }

    public void retrieveArtifact(){
        m_telemetry.addLine("RtIntake retrieve");
        if ( hwExists()) {
            double power = 1.0;
            m_intakeMotor.setPower(power);
        }
    }

    public void expelArtifact(){
        m_telemetry.addLine("RtIntake expel");
        if ( hwExists()) {
            double power = -1.0;
            m_intakeMotor.setPower(power);
        }
    }

    public void stop(){
        m_telemetry.addLine("RtIntake stop");
        if ( hwExists()) {
            m_intakeMotor.setPower(0);
        }
    }

    public void runMidtake(Boolean parOn){
        m_telemetry.addLine("RtIntake run midtake");
        if (hwExists()) {
            double power = 1.0;
            if(parOn == false)
            {
                power = 0;
            }
            m_midtakeServo.setPower(power);
        }
    }

    public void intake (boolean parRetrieveToggle, boolean parExpelToggle, boolean parMidtakeToggle )
    {
        //apply toggles
        if (parRetrieveToggle) {
            m_intakeRetrieve = !m_intakeRetrieve;
        }
        if (parExpelToggle) {
            m_intakeExpel = !m_intakeExpel;
        }
        if (parMidtakeToggle) {
            m_midtakeOn = !m_midtakeOn;
        }

        //take action
        if (parRetrieveToggle == true || parExpelToggle == true) {
            if (m_intakeRetrieve == false && m_intakeExpel == false) {
                stop();
            }
            if (m_intakeRetrieve) {
                retrieveArtifact();
            } else if (m_intakeExpel) {
                expelArtifact();
            }
        }
        if (parMidtakeToggle == true)
        {
            runMidtake(m_midtakeOn);
        }
    }

    private boolean hwExists() {
        boolean exists = true;

        if (m_intakeMotor == null || m_midtakeServo == null)
        {
            exists = false;
            m_telemetry.addLine("RtIntake HW NOT CONNECTED");
            //m_telemetry.update();
        }
        return exists;
    }
}
