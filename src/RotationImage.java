import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RotationImage {
    public static void main(String[] args) throws IOException {
        String path = "D:\\College-ComputerScience\\4-Periodo\\Image-processing-and-computer-vision\\image-manipulation-scripts\\src\\assets\\image.png";
        BufferedImage image = ImageIO.read(new File(path));

        BufferedImage rotatedImage = RotationImage.rotationImage(image, 45);

//        RotationImage.rotateImage2(image, 180);
    }

    public static BufferedImage rotationImage(BufferedImage image, int angle) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();
        double angleRadians =  Math.toRadians(angle);

        int newWidth = (int) (Math.abs(width * Math.cos(angleRadians)) + Math.abs(height * Math.sin(angleRadians)));
        int newHeight = (int) (Math.abs(width * Math.sin(angleRadians)) + Math.abs(height * Math.cos(angleRadians)));

        int cx = width / 2;
        int cy = height / 2;
        int ncx = newWidth / 2;
        int ncy = newHeight / 2;

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int originalX = (int) (((x - cx) * Math.cos(angleRadians)) - ((y - cy) * Math.sin(angleRadians))) + ncx;
                int originalY = (int) (((x - cx) * Math.sin(angleRadians)) + ((y - cy) * Math.cos(angleRadians))) + ncy;

                if (originalX >= 0 && originalX < newWidth && originalY >= 0 && originalY < newHeight) {
                    int pixel = image.getRGB(x, y);
                    newImage.setRGB(originalX, originalY, pixel);
                }
            }
        }

        ImageIO.write(newImage, "png", new File("src/assets/rotation_image.png"));
        return newImage;
    }

    public static void rotateImage2(BufferedImage image, double angleDegrees) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();
        double angleRadians = Math.toRadians(angleDegrees);

        int cx = width / 2;
        int cy = height / 2;


        BufferedImage rotatedImage = new BufferedImage(width, height, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int originalX = (int) ((x - cx) * Math.cos(angleRadians) + (y - cy) * Math.sin(angleRadians)) + cx;
                int originalY = (int) (-(x - cx) * Math.sin(angleRadians) + (y - cy) * Math.cos(angleRadians)) + cy;

                if (originalX >= 0 && originalX < width && originalY >= 0 && originalY < height) {
                    int pixel = image.getRGB(originalX, originalY);
                    rotatedImage.setRGB(x, y, pixel);
                } else {
                    rotatedImage.setRGB(x, y, 0);
                }
            }
        }

        ImageIO.write(rotatedImage, "png", new File("src/assets/rotation_image2.png"));
    }
}
