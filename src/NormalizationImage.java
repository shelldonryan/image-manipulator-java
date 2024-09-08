import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class NormalizationImage {
    public static void main(String[] args) throws IOException {
        String pathImage1 = "D:\\College-ComputerScience\\4º Período\\Image-processing-and-computer-vision\\image-manipulation-scripts\\src\\assets\\normalization_image\\cat_gray_image.jpg";
        String pathImage2 = "D:\\College-ComputerScience\\4º Período\\Image-processing-and-computer-vision\\image-manipulation-scripts\\src\\assets\\normalization_image\\street_gray_image.jpg";

        BufferedImage image1 = ImageIO.read(new File(pathImage1));
        BufferedImage image2 = ImageIO.read(new File(pathImage2));

        int width = image1.getWidth();
        int height = image1.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int[][] matrixArray = new int[height][width];

        int maxValue = 0;
        int minValue = 255;

        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                int rgbImage1 = image1.getRGB(column, row);
                int rgbImage2 = image2.getRGB(column, row);

                int graySum = grayValueRgb(new Color(rgbImage1)) + grayValueRgb(new Color(rgbImage2));

                if (graySum > maxValue) {
                    maxValue = graySum;
                }

                if (graySum < minValue) {
                    minValue = graySum;
                }

                matrixArray[row][column] = graySum;
            }
        }

        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                int pixel = matrixArray[row][column];

                int pixelNormalization = normalizationFunction(pixel, minValue, maxValue);

                matrixArray[row][column] = pixelNormalization;
                newImage.setRGB(column, row, new Color(pixelNormalization, pixelNormalization, pixelNormalization).getRGB());
            }
        }

        ImageIO.write(newImage, "png", new File("src\\assets\\normalization_image\\normalized_image.png"));

        for (int row = 0; row < matrixArray.length; row++) {
            for (int column = 0; column < matrixArray[row].length; column++) {
                System.out.print(matrixArray[row][column] + " ");
            }
            System.out.println();
        }
    }

    public static int grayValueRgb(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        return (r + g + b) / 3;
    }

    public static int normalizationFunction(int pixel, int minValue, int maxValue) {

        double normalizedPixel = ((double) (pixel - minValue) / (maxValue - minValue)) * 255;

        return (int) Math.round(normalizedPixel);
    }
}
