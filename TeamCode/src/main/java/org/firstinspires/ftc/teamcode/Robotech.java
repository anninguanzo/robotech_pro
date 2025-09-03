package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.hardware.RtClaw;
import org.firstinspires.ftc.teamcode.hardware.RtDrive;
import org.firstinspires.ftc.teamcode.hardware.RtLed;
import org.firstinspires.ftc.teamcode.hardware.RtLift;
import org.firstinspires.ftc.teamcode.hardware.RtWrist;
import org.firstinspires.ftc.teamcode.sensors.RtColorSensor;
import org.firstinspires.ftc.teamcode.sensors.RtTouchSensor;
import org.firstinspires.ftc.teamcode.sensors.RtCamera;
import org.firstinspires.ftc.teamcode.utilities.RtLog;
import org.firstinspires.ftc.teamcode.utilities.RtSound;
import org.firstinspires.ftc.teamcode.utilities.RtTime;
import org.firstinspires.ftc.teamcode.utilities.RtTypes;

public class Robotech {

    // class attributes

    //==========================================================
    // private attributes - caller doesn't need these
    //==========================================================
    private HardwareMap m_hardwareMap;
    private Telemetry m_telemetry;

    private IMU m_imu;
    private Servo m_ledServo;

    // sensors
    private TouchSensor m_touchSensor;
    private RevColorSensorV3 m_revColorSensorV3;
    private org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName m_cameraName;

    // drive train motors
    private DcMotor m_dtLeftBackDcMotor;
    private DcMotor m_dtRightBackDcMotor;
    private DcMotor m_dtRightFrontDcMotor;
    private DcMotor m_dtLeftFrontDcMotor;

    // lift motors
    private DcMotor m_leftLiftDcMotor;
    private DcMotor m_rightLiftDcMotor;

    private DcMotor m_top1DcMotor; //what are these? difference between 1 and 2?
    private DcMotor m_top2DcMotor;

    // servos
    private Servo m_leftClawServo;
    private Servo m_rightClawServo;
    private Servo m_leftWristServo;
    private Servo m_rightWristServo;

    //==========================================================
    // public attributes - caller uses these
    //==========================================================

    //utilities
    public RtLog rtLog; //logs msgs to driver hub
    public RtSound rtSound; //plays sounds
    public RtTime rtTime; //adds delays

    //sensors
    public RtTouchSensor rtTouchSensor;
    public RtColorSensor rtColorSensor;
    public RtCamera rtCamera;

    //hardware
    public RtDrive rtDriveTrain;
    public RtWrist rtWrist;
    public RtLift rtLift;
    public RtClaw rtClaw;
    public RtLed rtLedLight;
    public Robotech(HardwareMap parHardwareMap, Telemetry parTelemetry)
    {
        m_hardwareMap = parHardwareMap;
        m_telemetry = parTelemetry;

        getRobotechHwMapConfig();
        configureRobotechDriveTrain();
        createRobotech();
    }

    private void getRobotechHwMapConfig()
    {
        //the device names MUST match config on driver hub!

        m_imu                 = m_hardwareMap.get(IMU.class,                 "imu");
        m_ledServo            = m_hardwareMap.tryGet(Servo.class,            "servoLedP0");
        m_touchSensor         = m_hardwareMap.tryGet(TouchSensor.class,      "digTouchP01");
        m_revColorSensorV3    = m_hardwareMap.tryGet(RevColorSensorV3.class, "i2cColorP1");
        m_cameraName          = m_hardwareMap.tryGet(WebcamName.class,       "robotech-cam");

        //all the device names below need to be improved and should include the control/expansion hub port!!!
        m_dtLeftBackDcMotor   = m_hardwareMap.tryGet(DcMotor.class,          "LBMotor");
        m_dtRightBackDcMotor  = m_hardwareMap.tryGet(DcMotor.class,          "RBMotor");
        m_dtRightFrontDcMotor = m_hardwareMap.tryGet(DcMotor.class,          "RFMotor");
        m_dtLeftFrontDcMotor  = m_hardwareMap.tryGet(DcMotor.class,          "LFMotor");

        m_leftLiftDcMotor     = m_hardwareMap.tryGet(DcMotor.class,          "lift1");
        m_rightLiftDcMotor    = m_hardwareMap.tryGet(DcMotor.class,          "lift2");

        m_top1DcMotor         = m_hardwareMap.tryGet(DcMotor.class,          "top1");
        m_top2DcMotor         = m_hardwareMap.tryGet(DcMotor.class,          "top2");

        m_leftClawServo       = m_hardwareMap.tryGet(Servo.class,            "leftClaw");
        m_rightClawServo      = m_hardwareMap.tryGet(Servo.class,            "rightClaw");

        m_leftWristServo      = m_hardwareMap.tryGet(Servo.class,            "leftWrist");
        m_rightWristServo     = m_hardwareMap.tryGet(Servo.class,            "rightWrist");
    }

    private void configureRobotechDriveTrain()
    {
        if ( m_dtLeftBackDcMotor   != null && m_dtRightBackDcMotor != null &&
             m_dtRightFrontDcMotor != null && m_dtLeftFrontDcMotor != null )
        {
            m_dtLeftFrontDcMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            m_dtLeftBackDcMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }
    private void createRobotech()
    {
        //utilities
        rtLog         = new RtLog(m_telemetry);
        rtSound       = new RtSound(m_hardwareMap);
        rtTime        = new RtTime();

        //sensors
        rtTouchSensor = new RtTouchSensor(m_touchSensor, m_telemetry);
        rtColorSensor = new RtColorSensor(m_revColorSensorV3, m_telemetry);
        rtCamera      = new RtCamera(m_cameraName, m_telemetry);

        //hardware
        rtDriveTrain  = new RtDrive(m_dtLeftBackDcMotor, m_dtRightBackDcMotor,
                                    m_dtRightFrontDcMotor, m_dtLeftFrontDcMotor, m_telemetry);
        rtWrist       = new RtWrist(m_leftWristServo, m_rightWristServo, m_telemetry);
        rtLift        = new RtLift(m_leftLiftDcMotor, m_rightLiftDcMotor,
                                    m_top1DcMotor, m_top2DcMotor, m_telemetry );
        rtClaw        = new RtClaw(m_leftClawServo, m_rightClawServo, m_telemetry);
        rtLedLight    = new RtLed(m_ledServo, m_telemetry);

        //good-to-go
        rtLog.print("Robotech Hardware Initialized");
        rtSound.play("gold");
    }

    public void searchUntilTouch()
    {
        //keep moving forward until touch sensor activates
        while(!rtTouchSensor.isTouched()) {
            rtDriveTrain.moveForward(RtTypes.RT_SLOW_SPEED); // implement movement pattern for search
        }

        //set light to green indicating touched
        rtLedLight.setColor(RtTypes.rtColor.GREEN);
    }

    public void searchUntilColor(RtTypes.rtColor parColor)
    {
        //move right until the sample color is detected
        while(rtColorSensor.getColor() != parColor) {
            rtDriveTrain.moveRight(RtTypes.RT_SLOW_SPEED); // implement movement pattern for search
        }

        //set light to red indicating detected
        rtLedLight.setColor(parColor);
    }
}
