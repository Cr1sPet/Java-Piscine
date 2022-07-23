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

    public static char [][] seeBMPImage(String BMPFileName, char white, char black) throws IOException {
//        BMPFileName = "it.bmp";
        BufferedImage image = ImageIO.read(new File(BMPFileName));

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
