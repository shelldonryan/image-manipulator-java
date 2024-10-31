import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QuantizationImage {
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("src/assets/image_aliasing.jpg"));

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, image.getType());

        for (int x = 4; x < width - 4; x++) {
            for (int y = 4; y < height - 4; y++) {
                int red = 0, green = 0, blue = 0;

                for (int i = -4; i <= 4; i++) {
                    for (int j = -4; j <= 4; j++) {
                        int rgbImage = image.getRGB(x + i, y + j);

                        Color color = new Color(rgbImage);

                        red += color.getRed();
                        green += color.getGreen();
                        blue += color.getBlue();
                    }
                }

                red /= 81;
                green /= 81;
                blue /= 81;

                if (red < 0) {
                    red = 0;
                } else if (red > 255) {
                    red = 255;
                }

                if (green < 0) {
                    green = 0;
                } else if (green > 255) {
                    green = 255;
                }

                if (blue < 0) {
                    blue = 0;
                } else if (blue > 255) {
                    blue = 255;
                }

                Color color = new Color(red, green, blue);

                newImage.setRGB(x, y, color.getRGB());

            }
        }

        ImageIO.write(newImage, "jpg", new File("src/assets/image_antiAliasing.jpg"));
    }
}