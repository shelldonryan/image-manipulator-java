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

        for (int column = 0; column < width; column++) {
            for (int line = 0; line < height / 2; line++) {

                int topImagePixel = image.getRGB(column, line);
                int bottomImagePixel = image.getRGB(column, height - line - 1);

                image.setRGB(column, line, bottomImagePixel);
                image.setRGB(column, height - line - 1, topImagePixel);
            }
        }

        ImageIO.write(image, "png", new File("src\\assets\\modify_image_invert_upright.png"));
    }
}
