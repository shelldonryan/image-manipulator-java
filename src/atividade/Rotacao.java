package atividade;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Rotacao {

    public static BufferedImage rotacionarImagem(BufferedImage imagem, double angulo) throws IOException {
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();
        double radianos = Math.toRadians(angulo);

        int novaLargura = (int) Math.abs(largura * Math.cos(radianos)) + (int) Math.abs(altura * Math.sin(radianos));
        int novaAltura = (int) Math.abs(largura * Math.sin(radianos)) + (int) Math.abs(altura * Math.cos(radianos));

        BufferedImage imagemRotacionada = new BufferedImage(novaLargura, novaAltura, imagem.getType());

        int centroX = largura / 2;
        int centroY = altura / 2;
        int novoCentroX = novaLargura / 2;
        int novoCentroY = novaAltura / 2;

        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {

                int novaX = (int) ((x - centroX) * Math.cos(radianos) - (y - centroY) * Math.sin(radianos)) + novoCentroX;
                int novaY = (int) ((x - centroX) * Math.sin(radianos) + (y - centroY) * Math.cos(radianos)) + novoCentroY;

                if (novaX >= 0 && novaX < novaLargura && novaY >= 0 && novaY < novaAltura) {
                    Color cor = new Color(imagem.getRGB(x, y));
                    imagemRotacionada.setRGB(novaX, novaY, cor.getRGB());
                }
            }
        }

        ImageIO.write(imagemRotacionada, "jpg", new File("src/imagem_rotacionada.jpg"));

        return imagemRotacionada;
    }

    public static void main(String[] args) throws IOException {

        BufferedImage imagem = ImageIO.read(new File("src/imagem.jpg"));

        BufferedImage imagemRotacionada = rotacionarImagem(imagem, 45);
    }
}
