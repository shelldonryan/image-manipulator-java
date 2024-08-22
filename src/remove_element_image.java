import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class remove_element_image {
    public static void main(String[] args) throws IOException {
        String pathImage = "D:\\College-ComputerScience\\4º Período\\Image-processing-and-computer-vision\\image-manipulation\\src\\assets\\chorma_key_image\\chorma_key_image_1.png";
        BufferedImage image = ImageIO.read(new File(pathImage));

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        int limiar = 128;

        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                int rgb = image.getRGB(column, row);

                Color color = new Color(rgb);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                if (!(red < limiar && green > limiar && blue < limiar)) {
                    newImage.setRGB(column, row, rgb);
                }
            }
        }

        ImageIO.write(newImage, "png", new File("src\\assets\\chorma_key_image\\remove_green_from_image.png"));
    }
}
