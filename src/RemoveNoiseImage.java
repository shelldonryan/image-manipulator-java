import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class RemoveNoiseImage {
    public static void main(String[] args) throws IOException {
        File directory = new File("src/assets/noise_image/dirty");
        File[] files = directory.listFiles();

        assert files != null;
        for (int i = 0; i < files.length; i++) {
            String path = files[i].getAbsolutePath();

            int kernelSize = 9;

            BufferedImage image = ImageIO.read(new File(path));
//            BufferedImage filteredImage = medianFilter(image, kernelSize);
            BufferedImage filteredImage = meanFilter(image, kernelSize);
            BufferedImage filteredImage2 = gaussianFilter(image, kernelSize);
            ImageIO.write(filteredImage, "png", new File(String.format("src/assets/noise_image/exercise2/meanFilter/kernel%dx%d/remove_noise_image%d.png", kernelSize, kernelSize, i + 1)));
            ImageIO.write(filteredImage2, "png", new File(String.format("src/assets/noise_image/exercise2/gaussianFilter/kernel%dx%d/remove_noise_image%d.png", kernelSize, kernelSize, i + 1)));


        }
    }

    public static BufferedImage medianFilter(BufferedImage image, int kernelSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        int offset = (kernelSize - 1) / 2;

        BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

        for(int x = offset; x < width - offset; x++) {
            for (int y = offset; y < height - offset; y++) {
                int[] window = new int[kernelSize * kernelSize];
                int index = 0;

                for (int m = - offset; m <= offset; m++) {
                    for (int n = - offset; n <= offset; n++) {
                        window[index++] = image.getRGB(x + m, y + n);
                    }
                }

                int[] intensities = new int[window.length];
                for (int k = 0; k < window.length; k++) {
                    intensities[k] = (window[k] >> 16 & 0xff) + (window[k] >> 8 & 0xff) + (window[k] & 0xff);
                }
                Arrays.sort(intensities);
                int median = intensities[intensities.length / 2];

                int newRGB = (median << 16) | (median << 8) | median;
                filteredImage.setRGB(x, y, newRGB);
            }
        }

        return filteredImage;
    }

    public static BufferedImage meanFilter(BufferedImage image, int kernelSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        int offset = (kernelSize - 1) / 2;

        BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

        for (int x = offset; x < width - offset; x++) {
            for (int y = offset; y < height - offset; y++) {
                int sumRed = 0, sumGreen = 0, sumBlue = 0;

                for(int kx = - offset; kx <= offset; kx++) {
                    for (int ky = -offset; ky <= offset; ky++) {
                        Color pixelColor = new Color(image.getRGB(x + kx, y + ky));
                        sumRed += pixelColor.getRed();
                        sumGreen += pixelColor.getGreen();
                        sumBlue += pixelColor.getBlue();
                    }
                }

                int newRed = Math.min(255, sumRed / (kernelSize * kernelSize));
                int newGreen = Math.min(255, sumGreen / (kernelSize * kernelSize));
                int newBlue = Math.min(255, sumBlue / (kernelSize * kernelSize));

                Color newColor = new Color(newRed, newGreen, newBlue);
                filteredImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return filteredImage;
    }

    public static BufferedImage gaussianFilter(BufferedImage image, int kernelSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        int offset = (kernelSize - 1) / 2;

        double[][] gaussianKernel = new double[][]{};

        if (kernelSize == 3) {
            gaussianKernel = new double[][]{
                    {0.0625, 0.125, 0.0625},
                    {0.125, 0.25, 0.125},
                    {0.0625, 0.125, 0.0625}
            };
        } else if (kernelSize == 5) {
            gaussianKernel = new double[][]{
                    {0.0625, 0.09375, 0.125, 0.09375, 0.0625},
                    {0.09375, 0.125, 0.15625, 0.125, 0.09375},
                    {0.125, 0.25, 0.5, 0.25, 0.125},
                    {0.09375, 0.125, 0.15625, 0.125, 0.09375},
                    {0.0625, 0.09375, 0.125, 0.09375, 0.0625},

            };
        } else if (kernelSize == 7) {
            gaussianKernel = new double[][]{
                    {  0,  0,  1,  2,  1,  0,  0 },
                    {  0,  3,  13, 22, 13,  3,  0 },
                    {  1, 13,  59, 97, 59, 13,  1 },
                    {  2, 22,  97, 159, 97, 22,  2 },
                    {  1, 13,  59, 97, 59, 13,  1 },
                    {  0,  3,  13, 22, 13,  3,  0 },
                    {  0,  0,  1,  2,  1,  0,  0 }
            };
        } else if (kernelSize == 9) {
            gaussianKernel = new double[][]{
                    {  0,  0,  1,  2,  3,  2,  1,  0,  0 },
                    {  0,  2,  4,  8, 10,  8,  4,  2,  0 },
                    {  1,  4,  9, 15, 19, 15,  9,  4,  1 },
                    {  2,  8, 15, 25, 31, 25, 15,  8,  2 },
                    {  3, 10, 19, 31, 39, 31, 19, 10,  3 },
                    {  2,  8, 15, 25, 31, 25, 15,  8,  2 },
                    {  1,  4,  9, 15, 19, 15,  9,  4,  1 },
                    {  0,  2,  4,  8, 10,  8,  4,  2,  0 },
                    {  0,  0,  1,  2,  3,  2,  1,  0,  0 }
            };
        }

        BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

        for (int x = offset; x < width - offset; x++) {
            for (int y = offset; y < height - offset; y++) {
                int sumRed = 0, sumGreen = 0, sumBlue = 0;
                double kernelWeigth = 0;


                for (int kx = 0; kx < 3; kx++) {
                    for (int ky = 0; ky < 3; ky++) {
                        kernelWeigth += gaussianKernel[kx][ky];
                        Color pixelColor = new Color(image.getRGB(x + kx - offset, y + ky - offset));
                        sumRed +=(int) (pixelColor.getRed() * gaussianKernel[ky][kx]);
                        sumGreen +=(int) (pixelColor.getGreen() * gaussianKernel[ky][kx]);
                        sumBlue +=(int) (pixelColor.getBlue() * gaussianKernel[ky][kx]);
                    }
                }

                int newRed =(int) Math.min(255, sumRed / kernelWeigth);
                int newGreen = (int) Math.min(255, sumGreen / kernelWeigth);
                int newBlue =(int) Math.min(255, sumBlue / kernelWeigth);

                Color newColor = new Color(newRed, newGreen, newBlue);
                filteredImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return filteredImage;
    }
}
