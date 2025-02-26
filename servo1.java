package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "servo1")
public class servo1 extends LinearOpMode {
    Servo Ser;
    public void runOpMode(){
        Ser=hardwareMap.servo.get("servo");
        Ser.setDirection(Servo.Direction.REVERSE);
        waitForStart();
        Ser.setPosition(0.5);
        while(opModeIsActive()) {
            Ser.setPosition(gamepad2.right_trigger);
        }
    }
}
