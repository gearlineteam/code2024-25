package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "4_motors")
public class DriveGo extends LinearOpMode {

    DcMotor lf, lb, rf, rb;

    @Override
    public void runOpMode() throws InterruptedException {

        lf = hardwareMap.get(DcMotor.class, "leftFront");
        lb = hardwareMap.get(DcMotor.class, "leftRear");
        rb = hardwareMap.get(DcMotor.class, "rightRear");
        rf = hardwareMap.get(DcMotor.class, "rightFront");

        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lf.setDirection(DcMotorSimple.Direction.FORWARD); // Mb nado pomenyat na rb i rf, protestit'
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setDirection(DcMotorSimple.Direction.FORWARD); // Mb nado pomenyat na rb i rf, protestit'
        rb.setDirection(DcMotorSimple.Direction.REVERSE);//Nado chtobi pri podache 1 na vse motori kb robot ehal vpered

        waitForStart();

        while (!isStopRequested()) {

            telemetry.addData("Looop","start");

//            konst0 = 0.7;
//            konst1 = 0.2;

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rotation = gamepad1.right_trigger - gamepad1.left_trigger;
            boolean rotationSlowRight = gamepad1.right_bumper;
            boolean rotationSlowLeft = gamepad1.left_bumper;
            boolean ySlowDown = gamepad1.dpad_down;
            boolean ySlowUp = gamepad1.dpad_up;
            boolean xslowRight = gamepad1.dpad_right;
            boolean xSlowLeft = gamepad1.dpad_left;
            double lfpower, lbpower, rfpower, rbpower;
            lfpower = y + x + rotation;
            lbpower = y - x + rotation;
            rfpower = y - x - rotation;
            rbpower = y + x - rotation;
            double[] powers = {Math.abs(lfpower), Math.abs(lbpower), Math.abs(rfpower), Math.abs(rbpower)};
            double max = 0;

            for (int i = 0; i < 3; i++) {
                if (powers[i] > max)
                    max = powers[i];
            }

            if(gamepad1.dpad_up) {
                telemetry.addData("gamepad","stick");;
                lf.setPower(1);
                lb.setPower(1);
                rf.setPower(1);
                rb.setPower(1);
            }
            lf.setPower(0);
            lb.setPower(0);
            rf.setPower(0);
            rb.setPower(0);

            }



        }


    }




