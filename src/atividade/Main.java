package atividade;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedImage imagem = ImageIO.read(new File("src/imagem.jpg"));

        int novaLargura = 800;
        int novaAltura = 600;

        BufferedImage novaImagem = new BufferedImage(novaLargura, novaAltura, BufferedImage.TYPE_INT_ARGB);

        redimensionarImagemLargAlt(imagem, novaImagem);
        redimensionarImagemPorProporcao(imagem, 200);



    }

    private static void redimensionarImagemLargAlt(BufferedImage original, BufferedImage nova) throws IOException {
        int larguraOriginal = original.getWidth();
        int alturaOriginal = original.getHeight();

        for (int y = 0; y < nova.getHeight(); y++) {
            for (int x = 0; x < nova.getWidth(); x++) {

                int srcX = x * larguraOriginal / nova.getWidth();
                int srcY = y * alturaOriginal / nova.getHeight();

                int pixel = original.getRGB(srcX, srcY);

                nova.setRGB(x, y, pixel);
            }
        }
        ImageIO.write(nova, "png", new File("src/redimensionadaAltLarg.png"));
    }

    private static void redimensionarImagemPorProporcao(BufferedImage original, int fatorEscala) throws IOException {
        int larguraOriginal = original.getWidth();
        int alturaOriginal = original.getHeight();

        int novaLargura = larguraOriginal * fatorEscala / 100;
        int novaAltura = alturaOriginal * fatorEscala / 100;

        BufferedImage nova = new BufferedImage(novaLargura, novaAltura, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < nova.getHeight(); y++) {
            for (int x = 0; x < nova.getWidth(); x++) {
                int srcX = x * larguraOriginal / novaLargura;
                int srcY = y * alturaOriginal / novaAltura;
                int pixel = original.getRGB(srcX, srcY);
                nova.setRGB(x, y, pixel);
            }
        }

        ImageIO.write(nova, "png", new File("src/redimensionadaEscala.png"));

    }

}
