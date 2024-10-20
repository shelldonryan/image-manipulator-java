import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConvulationImage {
    public static void main(String[] args) throws IOException {
        String path = "D:\\College-ComputerScience\\4-Periodo\\Image-processing-and-computer-vision\\image-manipulation-" +
                "scripts\\src\\assets\\convulation_image\\fake.png";

        BufferedImage image = ImageIO.read(new File(path));

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, image.getType());

        Color px1, px2, px3, px4, px5, px6, px7, px8, px9;

        //filter
        int[] filter = {-2, -1, 0, -1, 1, 1, 0, 1, 2};

        for (int row = 1; row < width - 1; row++) {
            for (int column = 1; column < height - 1; column++) {
                px1 = new Color(image.getRGB(row-1, column-1));
                px2 = new Color(image.getRGB(row-1, column));
                px3 = new Color(image.getRGB(row-1, column+1));
                px4 = new Color(image.getRGB(row, column-1));
                px5 = new Color(image.getRGB(row, column));
                px6 = new Color(image.getRGB(row, column+1));
                px7 = new Color(image.getRGB(row+1, column-1));
                px8 = new Color(image.getRGB(row+1, column));
                px9 = new Color(image.getRGB(row+1, column+1));

                int valorR = (filter[0] * px1.getRed()) + (filter[1] * px2.getRed()) + (filter[2] * px3.getRed()) + (filter[3] * px4.getRed()) + (filter[4] * px5.getRed()) + (filter[5] * px6.getRed()) + (filter[6] * px7.getRed()) + (filter[7] * px8.getRed()) + (filter[8] * px9.getRed());
                int valorG = (filter[0] * px1.getGreen()) + (filter[1] * px2.getGreen()) + (filter[2] * px3.getGreen()) + (filter[3] * px4.getGreen()) + (filter[4] * px5.getGreen()) + (filter[5] * px6.getGreen()) + (filter[6] * px7.getGreen()) + (filter[7] * px8.getGreen()) + (filter[8] * px9.getGreen());
                int valorB = (filter[0] * px1.getBlue()) + (filter[1] * px2.getBlue()) + (filter[2] * px3.getBlue()) + (filter[3] * px4.getBlue()) + (filter[4] * px5.getBlue()) + (filter[5] * px6.getBlue()) + (filter[6] * px7.getBlue()) + (filter[7] * px8.getBlue()) + (filter[8] * px9.getBlue());

                if (valorR > 255) {
                    valorR = 255;
                } else if (valorR < 0) {
                    valorR = 0;
                }
                if (valorG > 255){
                    valorG = 255;
                } else if (valorG < 0) {
                    valorG = 0;
                }
                if (valorB > 255){
                    valorB = 255;
                } else if (valorB < 0) {
                    valorB = 0;
                }

                Color newColor = new Color(valorR, valorG, valorB);

                newImage.setRGB(row, column, newColor.getRGB());
            }
        }

        ImageIO.write(newImage, "png", new File("src/convulationImage.png"));
    }
}
