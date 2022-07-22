import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Program {

    public static char [][] seeBMPImage(String BMPFileName) throws IOException {
        BufferedImage image = ImageIO.read(Program.class.getResource(BMPFileName));

        char[][] array2D = new char[image.getWidth()][image.getHeight()];

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                int color = image.getRGB(xPixel, yPixel);
                if (color == Color.BLACK.getRGB()) {
                    array2D[xPixel][yPixel] = 'o';
                } else {
                    array2D[xPixel][yPixel] = '.';
                }
            }
        }
        return array2D;
    }

    public static void main(String[] args) throws IOException {
        char [][] bmpInNumberArray = seeBMPImage("images.png");
        for (int i = 0; i < bmpInNumberArray.length; i++) {
            for(int j = 0; j < bmpInNumberArray[i].length; j++) {
                System.out.print(bmpInNumberArray[j][i]);
            }
            System.out.println();
        }
    }

}
