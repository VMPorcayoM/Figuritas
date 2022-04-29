package Figuras;

import figuritas.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Rectangulo extends Figura {

    private int posicionX, posicionY, posX, posY, estado, trama;
    private Color color, color1, color2;
    private String ruta;
    private BufferedImage img;

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getTrama() {
        return trama;
    }

    public void setTrama(int trama) {
        this.trama = trama;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Rectangulo(int posX, int posY, int posicionX, int posicionY, Color color, Lienzo lienzo, String ruta, int estado, int trama, Color color1, Color color2) {
        super(posX, posY, posicionX, posicionY, color, lienzo, ruta, estado, trama, color1, color2);
        this.posX = posX;
        this.posY = posY;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.color = color;
        this.estado = estado;
        this.ruta = ruta;
        this.trama = trama;
        this.color1 = color1;
        this.color2 = color2;
        this.setBounds(this.posX, this.posY, this.posicionX, this.posicionY);
    }

    public void paint(Graphics g) {
        if (estado == 1) {
            g.setColor(color);
            g.fillRect(0, 0, posicionX, posicionY);

        } else if (estado == 2) {
            try {
                //           g.setColor(color);
                img = ImageIO.read(new File(ruta));
            } catch (IOException ex) {
                System.out.println(ex);
            }
            int u = 0, o = 0, b = 0, a = 0;
            if (posicionX >= 200) {
                for (int i = 0; i < posicionX; i++) {
                    b = b + 1;
                    i = i + 200;
                }

            } else if (posicionX <= 200) {
                b = 1;
            }

            if (posicionY >= 200) {
                for (int i = 0; i < posicionY; i++) {
                    a = a + 1;
                    i = i + 200;
                }

            } else if (posicionY <= 200) {
                a = 1;
            }

            for (int j = 0; j < b * a; j++) {
                g.drawImage(img, u, o, this);
                u += 200;
                if (u == (b * 200)) {
                    u = 0;
                    o += 200;
                }

            }
//        g.fillRect(0, 0, posicionX, posicionY);

        } else if (trama == 3) {
            g.setColor(color1);
            g.fillRect(0, 0, posicionX, posicionY);
            g.setColor(color2);

            int b = 0;
            for (int i = 0; i < posicionX; i++) {
                b = b + 1;
                i = i + 40;
            }

            int n = b, a1 = 30, a2 = 55, a3 = 25, b1 = 30, b2 = 30, b3 = 60, c1 = 15, c2 = 15, c3 = 50, d1 = 50, d2 = 50, d3 = 50;
            for (int i = 0; i < b * b; i++) {

                g.fillOval(a1, b1, c1, d1);
                g.fillOval(a2, b2, c2, d2);
                g.fillOval(a3, b3, c3, d3);

                a1 = a1 + 100;
                a2 = a2 + 100;
                a3 = a3 + 100;
                if (i == n) {
                    b1 = b1 + 120;
                    b2 = b2 + 120;
                    b3 = b3 + 120;
                    a1 = 30;
                    a2 = 55;
                    a3 = 25;
                    n = n + b;
                }
            }
        }
    }
}
