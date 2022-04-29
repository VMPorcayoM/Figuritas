package figuritas;

import Figuras.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;

public class Figura extends JLabel implements MouseMotionListener, MouseListener {

    private int posicionX, posicionY, pos, posX, posY,  estado, trama;
    private Color color, color1, color2;
    private String ruta;
    public static Figura fig;
    private Lienzo lienzo;

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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getTrama() {
        return trama;
    }

    public void setTrama(int trama) {
        this.trama = trama;
    }

    public Lienzo getLienzo() {
        return lienzo;
    }

    public void setLienzo(Lienzo lienzo) {
        this.lienzo = lienzo;
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
    

    public Figura(int posX, int posY, int posicionX, int posicionY, Color color, Lienzo lienzo, String ruta, int estado, int trama, Color color1, Color color2) {
        this.posX = posX;
        this.posY = posY;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.color = color;
        this.lienzo = lienzo;
        this.ruta = ruta;
        this.estado = estado;
        this.trama = trama;
                this.color1 = color1;
        this.color2 = color2;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.setLocation(e.getX() + this.getX() - this.getWidth() / 2, e.getY() + this.getY() - this.getHeight() / 2);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        fig = (Figura) e.getSource();
        pos = lienzo.listaFiguras.indexOf(fig);
        lienzo.getEditar().setEnabled(true);
        lienzo.getEliminar().setEnabled(true);
        if (fig instanceof Cuadrado) {
            lienzo.getAreaCreacion().removeAll();
            lienzo.destruirTodoALV();
            lienzo.cuadrado();
            lienzo.color();
            lienzo.getLado().setText(("" + posicionX));
            lienzo.getColor1().setBackground(fig.color);
            lienzo.getAreaCreacion().repaint();
        } else if (fig instanceof Circulo) {
            lienzo.getAreaCreacion().removeAll();
            lienzo.destruirTodoALV();
            lienzo.circulo();
            lienzo.color();
            lienzo.getRadio().setText(("" + posicionX));
            lienzo.getColor1().setBackground(fig.color);
            lienzo.getAreaCreacion().repaint();
        } else if (fig instanceof Rectangulo) {
            lienzo.getAreaCreacion().removeAll();
            lienzo.destruirTodoALV();
            lienzo.RectanguloYTriangulo();
            lienzo.color();
            lienzo.getAltura().setText(("" + posicionY));
            lienzo.getBase().setText(("" + posicionX));
            lienzo.getColor1().setBackground(fig.color);
            lienzo.getAreaCreacion().repaint();
        } else if (fig instanceof Triangulo) {
            lienzo.getAreaCreacion().removeAll();
            lienzo.destruirTodoALV();
            lienzo.RectanguloYTriangulo();
            lienzo.color();
            lienzo.getAltura().setText(("" + posicionY));
            lienzo.getBase().setText(("" + posicionX));
            lienzo.getColor1().setBackground(fig.color);
            lienzo.getAreaCreacion().repaint();
        }
    }

    public static void eliminar() {
        Graphics ge = fig.getGraphics();
        ge.setColor(Color.WHITE);
        if (fig instanceof Cuadrado) {
            ge.fillRect(fig.getX(), fig.getY(), fig.getPosicionX(), fig.getPosicionY());
        } else if (fig instanceof Circulo) {
            ge.fillOval(fig.getX(), fig.getY(), fig.getPosicionX() * 2, fig.getPosicionY() * 2);
        } else if (fig instanceof Rectangulo) {
            ge.fillRect(fig.getX(), fig.getY(), fig.getPosicionX(), fig.getPosicionY());
        } else if (fig instanceof Triangulo) {
            ge.fillRect(fig.getX(), fig.getY(), fig.getPosicionX(), fig.getPosicionY());
        }
        fig.lienzo.getAreaCreacion().add(fig);
    }

    public static void editar(int estado, String ruta, Color color1, Color color2, int trama) {
        System.out.println(fig.ruta);
        fig.color = fig.lienzo.getColor1().getBackground();
        fig.setColor(fig.color);
        fig.setEstado(estado);
        fig.setRuta(ruta);
        fig.setTrama(trama);
        fig.setColor1(color1);
        fig.setColor2(color2);
        if (fig instanceof Cuadrado) {
            fig.posicionX = Integer.parseInt(fig.lienzo.getLado().getText());
            fig.setPosicionX(fig.posicionX);
            fig.posicionY = Integer.parseInt(fig.lienzo.getLado().getText());
            fig.setPosicionY(fig.posicionY);
            fig.setSize(fig.posicionX, fig.posicionY);
            
        } else if (fig instanceof Circulo) {
            fig.posicionX = Integer.parseInt(fig.lienzo.getRadio().getText());
            fig.setPosicionX(fig.posicionX);
            fig.posicionY = Integer.parseInt(fig.lienzo.getRadio().getText());
            fig.setPosicionY(fig.posicionY);
            fig.setSize(fig.posicionX*2, fig.posicionY*2);
        } else if (fig instanceof Rectangulo || fig instanceof Triangulo) {
            fig.posicionX = Integer.parseInt(fig.lienzo.getBase().getText());
            fig.setPosicionX(fig.posicionX);
            fig.posicionY = Integer.parseInt(fig.lienzo.getAltura().getText());
            fig.setPosicionY(fig.posicionY);
            fig.setSize(fig.posicionX, fig.posicionY);
        }
        fig.lienzo.getAreaLienzo().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
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

    
}