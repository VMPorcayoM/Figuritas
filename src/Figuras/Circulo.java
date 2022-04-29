package Figuras;

import figuritas.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Circulo extends Figura {

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

    public Circulo(int posX, int posY, int posicionX, int posicionY, Color color, Lienzo lienzo, String ruta, int estado, int trama, Color color1, Color color2) {
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
        this.setBounds(this.posX, this.posY, this.posicionX * 2, this.posicionY * 2);
    }

    public void paint(Graphics g) {
        if (estado == 1) {
            g.setColor(color);
            g.fillOval(0, 0, posicionX*2, posicionY*2);

        } else if (estado == 2) {

            Graphics2D g2d = (Graphics2D) g;

            g.setColor(Color.white);
            try {
                img = ImageIO.read(new File(ruta));
            } catch (IOException ex) {
                System.out.println(ex);
            }
            int u = 0, o = 0, b = 0, grosor1 = 0, grosor2 = posicionX;
            if (posicionX >= 100) {
                for (int i = 0; i < posicionX * 2; i++) {
                    b = b + 1;
                    i = i + 200;
                }
            } else if (posicionX <= 100) {
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

            for (int k = posicionX; k < posicionX * 2; k++) {
                g.drawOval(grosor1, grosor1, grosor2 * 2, grosor2 * 2);
                grosor1 = grosor1 - 1;
                grosor2 = grosor2 + 1;

            }

            for (int k = posicionX; k < posicionX * 2; k++) {
                g2d.drawOval(grosor1, grosor1, grosor2 * 2, grosor2 * 2);
                grosor1 = grosor1 - 1;
                grosor2 = grosor2 + 1;

            }
        }
    }
}
