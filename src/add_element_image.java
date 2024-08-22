import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class add_element_image {
    public static void main(String[] args) throws IOException {
        String pathImagePeople = "D:\\College-ComputerScience\\4º Período\\Image-processing-and-computer-vision\\image-manipulation\\src\\assets\\chorma_key_image\\chorma_key_image_1.png";
        String pathImageFund = "D:\\College-ComputerScience\\4º Período\\Image-processing-and-computer-vision\\image-manipulation\\src\\assets\\chorma_key_image\\fund_image.png";
        BufferedImage imagePeople = ImageIO.read(new File(pathImagePeople));
        BufferedImage imageFund = ImageIO.read(new File(pathImageFund));

        int width = imagePeople.getWidth();
        int height = imageFund.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        int limiar = 128;

        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                int rgbImagePeople = imagePeople.getRGB(column, row);
                int rgbImageFund = imageFund.getRGB(column, row);

                Color colorImagePeople = new Color(rgbImagePeople);
                Color colorImageFund = new Color(rgbImageFund);

                int red = colorImagePeople.getRed();
                int green = colorImagePeople.getGreen();
                int blue = colorImagePeople.getBlue();

                if (red < limiar && green > limiar && blue < limiar) {
                    newImage.setRGB(column, row, rgbImageFund);
                } else {
                    newImage.setRGB(column, row, rgbImagePeople);
                }
            }
        }

        ImageIO.write(newImage, "png", new File("src\\assets\\chorma_key_image\\add_element_to_image.png"));
    }
}
