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

        for (int x = 0; x < width / 2; x++) {
            for (int y = 0; y < height; y++) {
                int leftImagePixel = image.getRGB(x, y);
                int rightImagePixel = image.getRGB(width - x - 1, y);

                image.setRGB(x, y, rightImagePixel);
                image.setRGB(width - x - 1, y, leftImagePixel);

            }
        }

        ImageIO.write(image, "png", new File("src\\assets\\modify_image_invert_horizontal.png"));

    }
}

