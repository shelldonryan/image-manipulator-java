import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizingImage {
    public static void main(String[] args) throws IOException{
        String path = "D:\\College-ComputerScience\\4-Periodo\\Image-processing-and-computer-vision\\image-manipulation-scripts\\src\\assets\\image.png";

        BufferedImage image = ImageIO.read(new File(path));
        BufferedImage newImage = resizeImagePerRatio(image, 2);

        showImage(newImage);
    }

    public static void showImage(BufferedImage image) {
        JFrame frame = new JFrame("Imagem Redimensionada");
        ImageIcon icon = new ImageIcon("src/assets/resizing_image.png");
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(image.getWidth() + 50, image.getHeight() + 50);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static BufferedImage resizeImagePerRatio(BufferedImage image, double scaleFactor) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();

        int newWidth =(int) Math.round(width * scaleFactor);
        int newHeight =(int) Math.round(height * scaleFactor);

        float widthRatio = (float) width / newWidth;
        float heightRatio = (float) height / newHeight;

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < newImage.getHeight(); y++) {
            for (int x = 0; x < newImage.getWidth(); x++) {

                int findRow = (int) (x * widthRatio);
                int findColumn = (int) (y * heightRatio);

                int pixel = image.getRGB(findRow, findColumn);
                newImage.setRGB(x, y, pixel);
            }
        }

        ImageIO.write(newImage, "png", new File("src/assets/resizing_image.png"));
        return newImage;
    }
}
