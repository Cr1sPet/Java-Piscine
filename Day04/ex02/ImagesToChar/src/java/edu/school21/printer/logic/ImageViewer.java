package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageViewer {

    public ImageViewer() {

    }

    public static char [][] seeBMPImage(String white, String black) throws IOException {
        BufferedImage image = ImageIO.read(ImageIO.class.getResource("/resources/image.bmp"));

        ColoredPrinter whitePrinter = new ColoredPrinter.
                Builder(1, false).
                background(Ansi.BColor.valueOf(white))
                .build();
        ColoredPrinter blackPrinter = new ColoredPrinter.
                Builder(1, false).
                background(Ansi.BColor.valueOf(black))
                .build();


        char[][] array2D = new char[image.getWidth()][image.getHeight()];

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                int color = image.getRGB(yPixel, xPixel);
                if (color == Color.BLACK.getRGB()) {
                    whitePrinter.print(" ");
                } else {
                    blackPrinter.print(" ");
                }
            }
            System.out.println();
        }
        return array2D;
    }

}
