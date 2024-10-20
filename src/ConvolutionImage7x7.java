import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConvolutionImage7x7 {
    public static void main(String[] args) throws IOException {
        String path = "D:\\College-ComputerScience\\4-Periodo\\Image-processing-and-computer-vision\\image-manipulation-" +
                "scripts\\src\\assets\\convulation_image\\fake.png";

        BufferedImage image = ImageIO.read(new File(path));

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, image.getType());

        // Exemplo de kernel 7x7
        int[][] filter = {
                {  0,  0,  0, -1,  0,  0,  0 },
                {  0,  0, -1, -2, -1,  0,  0 },
                {  0, -1, -2, -4, -2, -1,  0 },
                { -1, -2, -4, 30, -4, -2, -1 },
                {  0, -1, -2, -4, -2, -1,  0 },
                {  0,  0, -1, -2, -1,  0,  0 },
                {  0,  0,  0, -1,  0,  0,  0 }
        };

        int offset = 3; // O kernel 7x7 terá um offset de 3

        for (int row = offset; row < width - offset; row++) {
            for (int column = offset; column < height - offset; column++) {

                // Definir variáveis para cada pixel da kernel 7x7
                Color p1 = new Color(image.getRGB(row - 3, column - 3));
                Color p2 = new Color(image.getRGB(row - 3, column - 2));
                Color p3 = new Color(image.getRGB(row - 3, column - 1));
                Color p4 = new Color(image.getRGB(row - 3, column));
                Color p5 = new Color(image.getRGB(row - 3, column + 1));
                Color p6 = new Color(image.getRGB(row - 3, column + 2));
                Color p7 = new Color(image.getRGB(row - 3, column + 3));

                Color p8 = new Color(image.getRGB(row - 2, column - 3));
                Color p9 = new Color(image.getRGB(row - 2, column - 2));
                Color p10 = new Color(image.getRGB(row - 2, column - 1));
                Color p11 = new Color(image.getRGB(row - 2, column));
                Color p12 = new Color(image.getRGB(row - 2, column + 1));
                Color p13 = new Color(image.getRGB(row - 2, column + 2));
                Color p14 = new Color(image.getRGB(row - 2, column + 3));

                Color p15 = new Color(image.getRGB(row - 1, column - 3));
                Color p16 = new Color(image.getRGB(row - 1, column - 2));
                Color p17 = new Color(image.getRGB(row - 1, column - 1));
                Color p18 = new Color(image.getRGB(row - 1, column));
                Color p19 = new Color(image.getRGB(row - 1, column + 1));
                Color p20 = new Color(image.getRGB(row - 1, column + 2));
                Color p21 = new Color(image.getRGB(row - 1, column + 3));

                Color p22 = new Color(image.getRGB(row, column - 3));
                Color p23 = new Color(image.getRGB(row, column - 2));
                Color p24 = new Color(image.getRGB(row, column - 1));
                Color p25 = new Color(image.getRGB(row, column));
                Color p26 = new Color(image.getRGB(row, column + 1));
                Color p27 = new Color(image.getRGB(row, column + 2));
                Color p28 = new Color(image.getRGB(row, column + 3));

                Color p29 = new Color(image.getRGB(row + 1, column - 3));
                Color p30 = new Color(image.getRGB(row + 1, column - 2));
                Color p31 = new Color(image.getRGB(row + 1, column - 1));
                Color p32 = new Color(image.getRGB(row + 1, column));
                Color p33 = new Color(image.getRGB(row + 1, column + 1));
                Color p34 = new Color(image.getRGB(row + 1, column + 2));
                Color p35 = new Color(image.getRGB(row + 1, column + 3));

                Color p36 = new Color(image.getRGB(row + 2, column - 3));
                Color p37 = new Color(image.getRGB(row + 2, column - 2));
                Color p38 = new Color(image.getRGB(row + 2, column - 1));
                Color p39 = new Color(image.getRGB(row + 2, column));
                Color p40 = new Color(image.getRGB(row + 2, column + 1));
                Color p41 = new Color(image.getRGB(row + 2, column + 2));
                Color p42 = new Color(image.getRGB(row + 2, column + 3));

                Color p43 = new Color(image.getRGB(row + 3, column - 3));
                Color p44 = new Color(image.getRGB(row + 3, column - 2));
                Color p45 = new Color(image.getRGB(row + 3, column - 1));
                Color p46 = new Color(image.getRGB(row + 3, column));
                Color p47 = new Color(image.getRGB(row + 3, column + 1));
                Color p48 = new Color(image.getRGB(row + 3, column + 2));
                Color p49 = new Color(image.getRGB(row + 3, column + 3));

                // Aplicar os pesos do filtro manualmente
                int valorR = filter[0][0] * p1.getRed() + filter[0][1] * p2.getRed() + filter[0][2] * p3.getRed() +
                        filter[0][3] * p4.getRed() + filter[0][4] * p5.getRed() + filter[0][5] * p6.getRed() +
                        filter[0][6] * p7.getRed() + filter[1][0] * p8.getRed() + filter[1][1] * p9.getRed() +
                        filter[1][2] * p10.getRed() + filter[1][3] * p11.getRed() + filter[1][4] * p12.getRed() +
                        filter[1][5] * p13.getRed() + filter[1][6] * p14.getRed() + filter[2][0] * p15.getRed() +
                        filter[2][1] * p16.getRed() + filter[2][2] * p17.getRed() + filter[2][3] * p18.getRed() +
                        filter[2][4] * p19.getRed() + filter[2][5] * p20.getRed() + filter[2][6] * p21.getRed() +
                        filter[3][0] * p22.getRed() + filter[3][1] * p23.getRed() + filter[3][2] * p24.getRed() +
                        filter[3][3] * p25.getRed() + filter[3][4] * p26.getRed() + filter[3][5] * p27.getRed() +
                        filter[3][6] * p28.getRed() + filter[4][0] * p29.getRed() + filter[4][1] * p30.getRed() +
                        filter[4][2] * p31.getRed() + filter[4][3] * p32.getRed() + filter[4][4] * p33.getRed() +
                        filter[4][5] * p34.getRed() + filter[4][6] * p35.getRed() + filter[5][0] * p36.getRed() +
                        filter[5][1] * p37.getRed() + filter[5][2] * p38.getRed() + filter[5][3] * p39.getRed() +
                        filter[5][4] * p40.getRed() + filter[5][5] * p41.getRed() + filter[5][6] * p42.getRed() +
                        filter[6][0] * p43.getRed() + filter[6][1] * p44.getRed() + filter[6][2] * p45.getRed() +
                        filter[6][3] * p46.getRed() + filter[6][4] * p47.getRed() + filter[6][5] * p48.getRed() +
                        filter[6][6] * p49.getRed();

                int valorG = filter[0][0] * p1.getGreen() + filter[0][1] * p2.getGreen() + filter[0][2] * p3.getGreen() +
                        filter[0][3] * p4.getGreen() + filter[0][4] * p5.getGreen() + filter[0][5] * p6.getGreen() +
                        filter[0][6] * p7.getGreen() + filter[1][0] * p8.getGreen() + filter[1][1] * p9.getGreen() +
                        filter[1][2] * p10.getGreen() + filter[1][3] * p11.getGreen() + filter[1][4] * p12.getGreen() +
                        filter[1][5] * p13.getGreen() + filter[1][6] * p14.getGreen() + filter[2][0] * p15.getGreen() +
                        filter[2][1] * p16.getGreen() + filter[2][2] * p17.getGreen() + filter[2][3] * p18.getGreen() +
                        filter[2][4] * p19.getGreen() + filter[2][5] * p20.getGreen() + filter[2][6] * p21.getGreen() +
                        filter[3][0] * p22.getGreen() + filter[3][1] * p23.getGreen() + filter[3][2] * p24.getGreen() +
                        filter[3][3] * p25.getGreen() + filter[3][4] * p26.getGreen() + filter[3][5] * p27.getGreen() +
                        filter[3][6] * p28.getGreen() + filter[4][0] * p29.getGreen() + filter[4][1] * p30.getGreen() +
                        filter[4][2] * p31.getGreen() + filter[4][3] * p32.getGreen() + filter[4][4] * p33.getGreen() +
                        filter[4][5] * p34.getGreen() + filter[4][6] * p35.getGreen() + filter[5][0] * p36.getGreen() +
                        filter[5][1] * p37.getGreen() + filter[5][2] * p38.getGreen() + filter[5][3] * p39.getGreen() +
                        filter[5][4] * p40.getGreen() + filter[5][5] * p41.getGreen() + filter[5][6] * p42.getGreen() +
                        filter[6][0] * p43.getGreen() + filter[6][1] * p44.getGreen() + filter[6][2] * p45.getGreen() +
                        filter[6][3] * p46.getGreen() + filter[6][4] * p47.getGreen() + filter[6][5] * p48.getGreen() +
                        filter[6][6] * p49.getGreen();

                int valorB = filter[0][0] * p1.getBlue() + filter[0][1] * p2.getBlue() + filter[0][2] * p3.getBlue() +
                        filter[0][3] * p4.getBlue() + filter[0][4] * p5.getBlue() + filter[0][5] * p6.getBlue() +
                        filter[0][6] * p7.getBlue() + filter[1][0] * p8.getBlue() + filter[1][1] * p9.getBlue() +
                        filter[1][2] * p10.getBlue() + filter[1][3] * p11.getBlue() + filter[1][4] * p12.getBlue() +
                        filter[1][5] * p13.getBlue() + filter[1][6] * p14.getBlue() + filter[2][0] * p15.getBlue() +
                        filter[2][1] * p16.getBlue() + filter[2][2] * p17.getBlue() + filter[2][3] * p18.getBlue() +
                        filter[2][4] * p19.getBlue() + filter[2][5] * p20.getBlue() + filter[2][6] * p21.getBlue() +
                        filter[3][0] * p22.getBlue() + filter[3][1] * p23.getBlue() + filter[3][2] * p24.getBlue() +
                        filter[3][3] * p25.getBlue() + filter[3][4] * p26.getBlue() + filter[3][5] * p27.getBlue() +
                        filter[3][6] * p28.getBlue() + filter[4][0] * p29.getBlue() + filter[4][1] * p30.getBlue() +
                        filter[4][2] * p31.getBlue() + filter[4][3] * p32.getBlue() + filter[4][4] * p33.getBlue() +
                        filter[4][5] * p34.getBlue() + filter[4][6] * p35.getBlue() + filter[5][0] * p36.getBlue() +
                        filter[5][1] * p37.getBlue() + filter[5][2] * p38.getBlue() + filter[5][3] * p39.getBlue() +
                        filter[5][4] * p40.getBlue() + filter[5][5] * p41.getBlue() + filter[5][6] * p42.getBlue() +
                        filter[6][0] * p43.getBlue() + filter[6][1] * p44.getBlue() + filter[6][2] * p45.getBlue() +
                        filter[6][3] * p46.getBlue() + filter[6][4] * p47.getBlue() + filter[6][5] * p48.getBlue() +
                        filter[6][6] * p49.getBlue();

                valorR = Math.min(Math.max(valorR, 0), 255); // Limitar entre 0 e 255
                valorG = Math.min(Math.max(valorG, 0), 255); // Limitar entre 0 e 255
                valorB = Math.min(Math.max(valorB, 0), 255); // Limitar entre 0 e 255

                Color newColor = new Color(valorR, valorG, valorB);
                newImage.setRGB(row, column, newColor.getRGB());
            }
        }

        File output = new File("src/assets/convulation_image/convolutionImage7x7.png");
        ImageIO.write(newImage, "png", output);
    }
}
