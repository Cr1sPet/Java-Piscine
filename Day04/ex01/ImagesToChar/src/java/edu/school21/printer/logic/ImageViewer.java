package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import edu.school21.printer.app.Program;

import java.io.File;
import java.io.IOException;

public class ImageViewer {

    public ImageViewer() {

    }

    public static char [][] seeBMPImage(char white, char black) throws IOException {
        BufferedImage image = ImageIO.read(ImageIO.class.getResource("/resources/image.bmp"));

        char[][] array2D = new char[image.getWidth()][image.getHeight()];

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                int color = image.getRGB(xPixel, yPixel);
                if (color == Color.BLACK.getRGB()) {
                    array2D[xPixel][yPixel] = black;
                } else {
                    array2D[xPixel][yPixel] = white;
                }
            }
        }
        return array2D;
    }

}
