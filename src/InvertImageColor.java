import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InvertImageColor {
    public static void main(String[] args) throws IOException {
        String pathImage = "D:\\College-ComputerScience\\4º Período\\Image-processing-and-computer-vision\\image-manipulation\\src\\assets\\image.png";
        BufferedImage image = ImageIO.read(new File(pathImage));

        int width = image.getWidth();
        int height = image.getHeight();

        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                int rgb = image.getRGB(column, row);

                Color color = new Color(rgb);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int redInvert = 255 - red;
                int greenInvert = 255 - green;
                int blueInvert = 255 - blue;

                image.setRGB(column, row, new Color(redInvert, greenInvert, blueInvert).getRGB());
            }
        }

        ImageIO.write(image, "png", new File("src\\assets\\invert_color_image.png"));
    }
}
