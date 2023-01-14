/*
 * Initially from https://github.com/Mechanical-Advantage/SwerveDevelopment
 */

package frc.lib.team3061.gyro;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class GyroIO_NavX implements GyroIO {
  private final AHRS gyro;

  public GyroIO_NavX() {
    gyro = new AHRS(SPI.Port.kMXP, (byte) 200);
  }

  @Override
  public void updateInputs(GyroIOInputs inputs) {
    inputs.connected = gyro.isConnected();
    if (gyro.isMagnetometerCalibrated()) {
      // We will only get valid *fused* headings if the magnetometer is calibrated
      inputs.positionDeg = gyro.getFusedHeading();
    } else
      // // We have to invert the angle of the NavX so that rotating the robot
      // counter-clockwise makes the angle increase.
      inputs.positionDeg = 360.0 - gyro.getYaw();
    inputs.velocityDegPerSec = gyro.getRate();
  }
}
