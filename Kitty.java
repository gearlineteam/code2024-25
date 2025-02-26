package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "kittY")
 public class Kitty extends LinearOpMode {
 DcMotor lf, lb, rf, rb;

    public void runOpMode() {

     lf = hardwareMap.get(DcMotor.class, "motorFrontLeft");
     lb = hardwareMap.get(DcMotor.class, "motorBackLeft");
     rb = hardwareMap.get(DcMotor.class, "motorBackRight");
     rf = hardwareMap.get(DcMotor.class, "motorFrontRight");
     lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     lf.setDirection(DcMotorSimple.Direction.REVERSE); //crutsit nazad
     lb.setDirection(DcMotorSimple.Direction.REVERSE);
     rf.setDirection(DcMotorSimple.Direction.FORWARD); //crutit vpered
     rb.setDirection(DcMotorSimple.Direction.FORWARD);

     waitForStart();
     GoRight(1000);
     sleep(2000);
     GoLeft(1000);
     sleep(2000);
     forward(0.5);
     sleep(2000);
     forward(-0.5);
     sleep(2000);


//     lf.setPower(0.2); //ezda vpered
//     rf.setPower(0.2);
//     lb.setPower(0.2);
//     rb.setPower(0.2);
//     sleep(2000);
//     rf.setPower(0);
//     lf.setPower(0);
//     rb.setPower(0);
//     lb.setPower(0);
//     sleep(3000);
//     lf.setPower(0.2); //ezda bokom
//     rf.setPower(-0.2);
//     lb.setPower(-0.2);
//     rb.setPower(0.2);
//     sleep(2000);
//     rf.setPower(0);
//     lf.setPower(0);
//     rb.setPower(0);
//     lb.setPower(0);
//     sleep(3000);
//     lf.setPower(0.2); //ezda naiskosok
//     rf.setPower(0);
//     lb.setPower(0);
//     rb.setPower(0.2);
//     sleep(2000);
//     rf.setPower(0);
//     lf.setPower(0);
//     rb.setPower(0);
//     lb.setPower(0);
//     sleep(3000);
//     rf.setPower(0); //razvorot na 1 kolese
//     lf.setPower(0.2);
//     rb.setPower(0);
//     lb.setPower(0.2);
//     sleep(2000);
//     rf.setPower(0);
//     lf.setPower(0);
//     rb.setPower(0);
//     lb.setPower(0);
//     sleep(3000);
//     lf.setPower(0.2); //razvorot
//     rf.setPower(-0.2);
//     lb.setPower(0.2);
//     rb.setPower(-0.2);
//     sleep(2000);
//     rf.setPower(0);
//     lf.setPower(0);
//     rb.setPower(0);
//     lb.setPower(0);
//     sleep(3000);
//     rf.setPower(0.2); //...
//     lf.setPower(-0.2);
//     rb.setPower(0);
//     lb.setPower(0);
//     sleep(2000);
//     rf.setPower(0);
//     lf.setPower(0);
//     rb.setPower(0);
//     lb.setPower(0);
    }

 public void forward(double speed){
  lf.setPower(speed); //ezda vpered i nazad
  rf.setPower(speed);
  lb.setPower(speed);
  rb.setPower(speed);
 }
 public void GoRight(long time){
  lf.setPower(0.2); //razvorot
  rf.setPower(-0.2);
  lb.setPower(0.2);
  rb.setPower(-0.2);
  sleep(time);
 }

 public void GoLeft(long time) {
  lf.setPower(-0.2); //razvorot
  rf.setPower(0.2);
  lb.setPower(-0.2);
  rb.setPower(0.2);
  sleep(time);
 }
}
