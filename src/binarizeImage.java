import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class binarizeImage {
    public static void main(String[] args) throws IOException {
        String pathImage = "D:\\College-ComputerScience\\4-Periodo\\Image-processing-and-computer-vision\\image-manipulation-scripts\\src\\assets\\image.png";
        BufferedImage image = ImageIO.read(new File(pathImage));

        int width = image.getWidth();
        int height = image.getHeight();

        int limiar = 128;

        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                int rgb = image.getRGB(column, row);

                Color color = new Color(rgb);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int gray = (red + green + blue) / 3;

                if (gray > limiar) {
                    image.setRGB(column, row, new Color(0).getRGB());
                } else {
                    image.setRGB(column, row, new Color(255, 255, 255).getRGB());
                }
            }
        }

        ImageIO.write(image, "png", new File("src\\assets\\binarize_image.png"));
    }
}
