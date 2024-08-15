import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class invertImageLeftToRight {
    public static void main(String[] args) throws IOException {
        String pathImage = "D:\\Shelldon-Personal\\College\\4º Período\\Image-processing-and-computer-vision\\image-manipulation\\src\\image.png";
        BufferedImage image = ImageIO.read(new File(pathImage));

        int width = image.getWidth();
        int height = image.getHeight();

        for (int column = 0; column < width / 2; column++) {
            for (int line = 0; line < height; line++) {
                int leftImagePixel = image.getRGB(column, line);
                int rightImagePixel = image.getRGB(width - column - 1, line);

                image.setRGB(column, line, rightImagePixel);
                image.setRGB(width - column - 1, line, leftImagePixel);

            }
        }

        ImageIO.write(image, "png", new File("src\\assets\\modify_image_invert_horizontal.png"));

    }
}

