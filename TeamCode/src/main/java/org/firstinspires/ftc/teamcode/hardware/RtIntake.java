package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class RtIntake {
    private Telemetry m_telemetry;
    private DcMotor m_intakeMotor;


    public RtIntake(DcMotor parIntakeMotor, Telemetry parTelemetry) {
        m_intakeMotor   = parIntakeMotor;
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

    public void intake (boolean parRetrieve, boolean parExpel, boolean parStop )
    {
        if (parRetrieve) {
            retrieveArtifact();
        }
        else if (parExpel)
        {
            expelArtifact();
        }
        else if(parStop)
        {
            stop();
        }
    }

    private boolean hwExists() {
        boolean exists = true;

        if (m_intakeMotor == null)
        {
            exists = false;
            m_telemetry.addLine("RtIntake HW NOT CONNECTED");
            //m_telemetry.update();
        }
        return exists;
    }
}
