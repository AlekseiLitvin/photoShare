package by.gsu.epamlab.controllers.images.filers;

import by.gsu.epamlab.constants.ConstantsError;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BWFilter {
    public static byte[] convertToBlackAndWhite(byte[] image) {
        try(InputStream inputStream = new ByteArrayInputStream(image);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream() ) {

            BufferedImage originalImage = ImageIO.read(inputStream);
            BufferedImage blackAndWhiteImg = new BufferedImage(originalImage.getWidth(),originalImage.getHeight(),
                    BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D graphics = blackAndWhiteImg.createGraphics();
            graphics.drawImage(originalImage, 0, 0, null);
            ImageIO.write(blackAndWhiteImg, "png", outputStream);

            return outputStream.toByteArray();
        } catch (IOException e) {
            System.err.println(ConstantsError.STREAM_ERROR + e.getMessage());
            return new byte[0];
        }
    }
}
