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

        double power = 1.0;
        m_intakeMotor.setPower(power);
    }

    public void expelArtifact(){

        double power = -1.0;
        m_intakeMotor.setPower(power);
    }

    public void stop(){
        if ( hwExists()) {
            m_intakeMotor.setPower(0);
        }
    }

    private boolean hwExists() {
        boolean exists = true;

        if (m_intakeMotor == null)
        {
            exists = false;
            m_telemetry.addLine("RtIntake HW NOT CONNECTED");
            m_telemetry.update();
        }
        return exists;
    }
}
