import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class invertImageTopToBottom {
    public static void main(String[] args) throws IOException {
        String pathImage = "src\\image.png";
        BufferedImage image = ImageIO.read(new File(pathImage));

        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height / 2; y++) {

                int topImagePixel = image.getRGB(x, y);
                int bottomImagePixel = image.getRGB(x, height - y - 1);

                image.setRGB(x, y, bottomImagePixel);
                image.setRGB(x, height - y - 1, topImagePixel);
            }
        }

        ImageIO.write(image, "png", new File("src\\assets\\modify_image_invert_upright.png"));
    }
}
