import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Array;

public class TransformColorInImageToBlack {
    public static void main(String[] args) throws IOException {
        String pathImage = "D:\\Shelldon-Personal\\College\\4º Período\\Image-processing-and-computer-vision\\image-manipulation\\src\\image.png";
        BufferedImage image = ImageIO.read(new File(pathImage));

        int width = image.getWidth();
        int height = image.getHeight();

        for (int column = 0; column < width; column++) {
            for (int line = 0; line < height; line ++) {
                int rgb = image.getRGB(column, line);

                Color color = new Color(rgb);

                int r = color.getRed();
                int b = color.getBlue();
                int g = color.getGreen();

                if (r >= 100 && g <= 75 && b <= 75) {
                    image.setRGB(column, line, Color.BLACK.getRGB());
                }
            }
        }

        ImageIO.write(image, "png", new File("src\\assets\\modify_color_image.png"));

    }
}