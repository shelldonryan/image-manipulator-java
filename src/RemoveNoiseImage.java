import javax.imageio.ImageIO;
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

            BufferedImage image = ImageIO.read(new File(path));

            int width = image.getWidth();
            int height = image.getHeight();
            int kernelsize = 3;
            int offset = (kernelsize - 1) / 2;

            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            for(int x = offset; x < width - offset; x++) {
                for (int y = offset; y < height - offset; y++) {
                    int[] window = new int[kernelsize * kernelsize];
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

            ImageIO.write(filteredImage, "png", new File(String.format("src/assets/noise_image/exercise2/kernel3x3/remove_noise_image%d.png", i + 1)));
        }
    }
}
