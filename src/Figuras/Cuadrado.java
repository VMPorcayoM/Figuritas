package Figuras;

import figuritas.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Cuadrado extends Figura {

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public Cuadrado(int posX, int posY, int posicionX, int posicionY, Color color, Lienzo lienzo, String ruta, int estado, int trama, Color color1, Color color2) {
        super(posX, posY, posicionX, posicionY, color, lienzo, ruta, estado, trama, color1, color2);
        this.posX = posX;
        this.posY = posY;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.color = color;
        this.ruta = ruta;
        this.estado = estado;
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
            int u = 0, o = 0, b = 0;
            if (posicionX >= 200) {
                for (int i = 0; i < posicionX; i++) {
                    b = b + 1;
                    i = i + 200;
                }
            } else if (posicionX <= 200) {
                b = 1;
            }

            for (int j = 0; j < b * b; j++) {
                g.drawImage(img, u, o, this);
                u += 200;
                if (u == (b * 200)) {
                    u = 0;
                    o += 200;
                }

            }
//        g.fillRect(0, 0, posicionX, posicionY);

        } else if (estado == 3) {
            if (trama == 1) {
                g.setColor(color1);
                g.fillRect(0, 0, posicionX, posicionY);
                g.setColor(color2);

                int b = 0;
                for (int i = 0; i < posicionX; i++) {
                    b = b + 1;
                    i = i + 20;
                }

                int a1 = 20, a2 = 40, a3 = 0, a4 = 0, b1 = 0, b2 = 0, b3 = 40, b4 = 20;
                for (int i = 0; i < b; i++) {
                    int[] vx2 = {a1, a2, a3, a4};
                    int[] vy2 = {b1, b2, b3, b4};
                    g.fillPolygon(vx2, vy2, 4);

                    a1 = a1 + 40;
                    a2 = a2 + 40;
                    b3 = b3 + 40;
                    b4 = b4 + 40;
                }
            } else if (trama == 2) {

                int b = 0;
                for (int i = 0; i < posicionX; i++) {
                    b = b + 1;
                    i = i + 10;
                }

                g.setColor(color1);

                int a1 = 0, a2 = 10, a3 = 10, a4 = 0, b1 = 0, b2 = 0, b3 = posicionX, b4 = posicionX;
                for (int i = 0; i < b; i++) {
                    int[] vx2 = {a1, a2, a3, a4};
                    int[] vy2 = {b1, b2, b3, b4};
                    g.fillPolygon(vx2, vy2, 4);

                    a1 = a1 + 20;
                    a2 = a2 + 20;
                    a3 = a3 + 20;
                    a4 = a4 + 20;

                }

                g.setColor(color2);

                a1 = 10;
                a2 = 20;
                a3 = 20;
                a4 = 10;
                b1 = 0;
                b2 = 0;
                b3 = posicionX;
                b4 = posicionX;

                for (int i = 0; i < b; i++) {
                    int[] vx2 = {a1, a2, a3, a4};
                    int[] vy2 = {b1, b2, b3, b4};
                    g.fillPolygon(vx2, vy2, 4);

                    a1 = a1 + 20;
                    a2 = a2 + 20;
                    a3 = a3 + 20;
                    a4 = a4 + 20;

                }
            } else if (trama == 3) {
                g.setColor(color1);
                g.fillRect(0, 0, posicionX, posicionY);
                g.setColor(color2);

                int b = 0;
                for (int i = 0; i < posicionX; i++) {
                    b = b + 1;
                    i = i + 40;
                }

                int n=b, a1 = 30, a2 = 55, a3 = 25, b1 = 30, b2 = 30, b3 = 60, c1 = 15, c2 = 15, c3 = 50, d1 = 50, d2 = 50, d3 = 50;
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
                        n = n+ b;
                    }
                }
            }
        }
    }
}
