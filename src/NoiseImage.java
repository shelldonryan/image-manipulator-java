import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class NoiseImage {
    public static void main(String[] args) throws IOException {
        File directory = new File("src/assets/noise_image/clear");
        File[] files = directory.listFiles();

        assert files != null;
        for (int i = 0; i < files.length; i++) {
            String path = files[i].getAbsolutePath();
            Random random = new Random();

            BufferedImage image = ImageIO.read(new File(path));

            int width = image.getWidth();
            int height = image.getHeight();
            double noiseLevel = 0.1;
            int neighbor = 1;

            for(int x = neighbor; x < width - neighbor; x++) {
                for (int y = neighbor; y < height - neighbor; y++) {
                    double randomNumber = random.nextDouble(0, 1);
                    if(randomNumber < noiseLevel) {
                        boolean whatColor = random.nextInt(0, 100) >= 50;
                        Color color;

                        if (whatColor) {
                            color = Color.black;
                        } else {
                            color = Color.white;
                        }

                        image.setRGB(x, y, color.getRGB());
                    }
                }
            }

            ImageIO.write(image, "png", new File(String.format("src/assets/noise_image/exercise1/noise_image%d.png", i + 1)));
        }
    }
}
