package Figuras;

import figuritas.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Triangulo extends Figura {

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

    public Triangulo(int posX, int posY, int posicionX, int posicionY, Color color, Lienzo lienzo, String ruta, int estado, int trama, Color color1, Color color2) {
        super(posX, posY, posicionX, posicionY, color, lienzo, ruta, estado, trama, color1, color2);
        this.posX = posX;
        this.posY = posY;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.color = color;
        this.estado = estado;
        this.trama = trama;
        this.color1 = color1;
        this.color2 = color2;
        this.setBounds(this.posX, this.posY, this.posicionX, this.posicionY);
    }

    public void paint(Graphics g) {
        g.setColor(color);
        int[] vx2 = {posicionX / 2, 0, posicionX};
        int[] vy2 = {0, posicionY, posicionY};
        g.fillPolygon(vx2, vy2, 3);

    }
}
