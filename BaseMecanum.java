package org.firstinspires.ftc.teamcode;
import static androidx.core.math.MathUtils.clamp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(name = "Mecanum")
public class BaseMecanum extends LinearOpMode {
    DcMotor motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight, centerGrab, liftMotor;
    Servo SerGrab;
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() {
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        centerGrab = hardwareMap.dcMotor.get("motorCenter");
        SerGrab = hardwareMap.servo.get("ServoGrab");
        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        centerGrab.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        centerGrab.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double ly, rx, lx, speed, grabTrigR, grabTrigL, denominator, frontLeftPower, backLeftPower, frontRightPower, backRightPower;

        waitForStart();
        // раздвигаем после старта
        timer.reset();
        centerGrab.setPower(-0.6);
        sleep(650);
        centerGrab.setPower(0);
        timer.reset();
        int startPosition = centerGrab.getCurrentPosition();
        double dist = 2000;
        int startPositionLift = liftMotor.getCurrentPosition();
            telemetry.addData("encod", startPositionLift);
            telemetry.update();
        if (isStopRequested()) return;
        //Mecanum Base
        while (opModeIsActive()) {
            speed = gamepad1.right_trigger * 4;
            if (gamepad1.left_stick_y != 0) {
                ly = -gamepad1.left_stick_y;
            } else if (gamepad2.left_stick_y != 0) {
                ly = -gamepad2.left_stick_y;
            } else {
                ly = 0;
            }
            if (gamepad1.left_stick_x != 0) {
                lx = gamepad1.left_stick_x;
            } else if (gamepad2.left_stick_x != 0) {
                lx = gamepad2.left_stick_x;
            } else {
                lx = 0;
            }
            if (gamepad1.right_stick_x != 0) {
                rx = gamepad1.right_stick_x;
            } else if (gamepad2.right_stick_x != 0) {
                rx = gamepad2.right_stick_x;
            } else {
                rx = 0;
            }
            // ограничение газом левый триггер
            ly = clamp(ly, -0.2, 0.2);
            rx = clamp(rx, -0.2, 0.2);
            lx = clamp(lx, -0.2, 0.2);
            speed = clamp(speed, 1, 5); // плавность изменения скорости
            denominator = Math.max(Math.abs(ly) + Math.abs(rx) + Math.abs(lx), 1);
            frontLeftPower = (ly + rx + lx) / denominator;
            backLeftPower = (ly - rx + lx) / denominator;
            frontRightPower = (ly - rx - lx) / denominator;
            backRightPower = (ly + rx - lx) / denominator;
            motorFrontLeft.setPower(frontLeftPower * speed);
            motorBackLeft.setPower(backLeftPower * speed);
            motorFrontRight.setPower(frontRightPower * speed);
            motorBackRight.setPower(backRightPower * speed);
            // центровая клешня:
            if (gamepad2.dpad_up && (startPosition - centerGrab.getCurrentPosition() < dist)) {
                centerGrab.setPower(-0.6);
                telemetry.addData("encod", startPosition - centerGrab.getCurrentPosition());
                telemetry.update();
            } else if (gamepad2.dpad_down && startPosition - centerGrab.getCurrentPosition() > 90) {
                centerGrab.setPower(0.6);
                telemetry.addData("encod", startPosition - centerGrab.getCurrentPosition());
                telemetry.update();
            } else {
                centerGrab.setPower(0);
            }
            // trigerom хваталка двумя тригерами
            grabTrigR = clamp(gamepad2.right_trigger, 0, 0.7);
            grabTrigL = clamp(gamepad2.left_trigger, 0.7, 1);
            if (grabTrigR < 0.7) {
                SerGrab.setPosition(grabTrigR);
            } else if ((grabTrigR == 0.7)) {
                if(grabTrigL>0.7) {
                    while (startPosition - centerGrab.getCurrentPosition() > 90 && opModeIsActive()) {
                        centerGrab.setPower(0.7);
                    }
                }
                centerGrab.setPower(0);
                SerGrab.setPosition(grabTrigL);
            }
            // ножничный подъемник
            if (gamepad2.a && (startPositionLift - liftMotor.getCurrentPosition() > 10)) {
                liftMotor.setPower(1);
            } else if (gamepad2.y && (startPositionLift - liftMotor.getCurrentPosition() < 1050)) {
                liftMotor.setPower(-1);
            }
            else if (gamepad1.x) {
                liftMotor.setPower(0.3);
            } else if (gamepad1.b) {
                liftMotor.setPower(-0.3);}
            else {
                liftMotor.setPower(0);
            }
        }
    }
}