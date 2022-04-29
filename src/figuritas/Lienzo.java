package figuritas;

import Atxy2k.CustomTextField.RestrictedTextField;
import Figuras.*;
import static figuritas.Figura.fig;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Lienzo extends JFrame implements ActionListener, MouseListener {

    private JPanel areaLienzo, areaCreacion;
    private JButton boton, dibujar, eliminar, color1, editar, textura, trama, color2, color3;
    private JLabel titulo1;
    private int tipo, cont = 0;
    private JTextField radio, lado, base, altura;
    private JLabel tRadio, tLado, tBase, tAltura, tColor;
    private Color color, colorT1, colorT2;
    private boolean estado;

    private String rutaRecuperada;
    private File archivo;

    //para los botones
    public ArrayList<JButton> listaBotones = new ArrayList();
    private ImageIcon imagen;

    //para las figuras
    public ArrayList<Figura> listaFiguras = new ArrayList();

    //para las texturas
    public ArrayList<JButton> listaTexturas = new ArrayList();
    private JButton aceptar, boton1;
    private JScrollPane pane;
    private JPanel jp;
    private ImageIcon imagen2;
    private JLabel titulo;
    private int posicion, estadoEleccion = 1;
    private String ruta = "";
    private boolean indicadorDeExistencia;

    //para las tramas
    public ArrayList<JButton> listaTramas = new ArrayList();
    private int estadoTrama = 0;

    //Fuente de letra
    private Font fuente = new Font("3Ds", Font.BOLD, 30);
    private Font fuente1 = new Font("3Ds", Font.BOLD, 12);

    private JMenuBar mb;
    private JMenu menu1, menu2;
    private JMenuItem abrir, guardar, guardarcomo, mi4, png, jpg;
    private Figura figura, fig;

    public Lienzo() {
        this.setTitle("Figuritas");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.BLACK);
        int x = 50;
        int y = 70;

        menu();

        //Botones
        for (int i = 0; i < 4; i++) {
            boton = new JButton();
            this.add(boton);
            imagen = new ImageIcon("..\\Figuritas\\src\\Imagenes\\" + i + ".jpg");
            boton.setIcon(imagen);
            listaBotones.add(boton);
            boton.addActionListener(this);
        }

        for (int i = 0; i < 4; i++) {
            listaBotones.get(i).setBounds(x, y, 100, 100);
            x += 150;
            this.add(listaBotones.get(i));
            if (x == (4 * 100) + 550) {
                x = 550;
                y += 230;
            }
        }

        eliminar = new JButton("Eliminar");
        eliminar.setBounds(1750, 135, 150, 50);
        eliminar.setFont(fuente);
        eliminar.setEnabled(false);
        eliminar.addActionListener(this);
        this.add(eliminar);

        editar = new JButton("Editar");
        editar.setBounds(1750, 75, 150, 50);
        editar.setFont(fuente);
        editar.setEnabled(false);
        editar.addActionListener(this);
        this.add(editar);

        dibujar = new JButton("Dibujar");
        dibujar.setBounds(1750, 15, 150, 50);
        dibujar.setFont(fuente);
        dibujar.setEnabled(false);
        dibujar.addActionListener(this);
        this.add(dibujar);

        areaLienzo = new JPanel(null);
        areaLienzo.setBackground(Color.WHITE);
        areaLienzo.setBounds(10, 200, 1900, 780);
        areaLienzo.addMouseListener(this);
        this.add(areaLienzo);

        areaCreacion = new JPanel(null);
        areaCreacion.setBackground(Color.WHITE);
        areaCreacion.setBounds(1200, 5, 500, 185);
        this.add(areaCreacion);

        //Labels
        titulo1 = new JLabel("Figuras");
        titulo1.setBounds(50, -20, 200, 100);
        titulo1.setFont(fuente);
        titulo1.setForeground(Color.WHITE);
        this.add(titulo1);

        jp = new JPanel(null);
        pane = new JScrollPane(jp);
        jp.setBounds(690, 5, 500, 185);
        jp.setBackground(Color.WHITE);
        this.add(jp);
    }

    public void menu() {
        setLayout(null);
        mb = new JMenuBar();
        setJMenuBar(mb);
        menu1 = new JMenu("Archivo");
        mb.add(menu1);
        menu2 = new JMenu("Exportar");
        mb.add(menu2);
        ImageIcon abrir1 = new ImageIcon("..\\Figuritas\\src\\Imagenes\\abrir.png");
        abrir = new JMenuItem("Abrir", abrir1);
        abrir.addActionListener(this);
        menu1.add(abrir);
        ImageIcon guardar1 = new ImageIcon("..\\Figuritas\\src\\Imagenes\\guardar.png");
        guardar = new JMenuItem("Guardar", guardar1);
        guardar.addActionListener(this);
        menu1.add(guardar);
        ImageIcon guardarC = new ImageIcon("..\\Figuritas\\src\\Imagenes\\guardarcomo.png");
        guardarcomo = new JMenuItem("Guardar Como", guardarC);
        guardarcomo.addActionListener(this);
        menu1.add(guardarcomo);
        ImageIcon pngi = new ImageIcon("..\\Figuritas\\src\\Imagenes\\png.png");
        png = new JMenuItem("PNG", pngi);
        png.addActionListener(this);
        menu2.add(png);
        ImageIcon jpg1 = new ImageIcon("..\\Figuritas\\src\\Imagenes\\jpg.png");
        jpg = new JMenuItem("JPG", jpg1);
        menu2.add(jpg);
        jpg.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(listaBotones.get(0))) {
            jp.removeAll();
            quitarMenuTextura();
            areaCreacion.removeAll();
            destruirTodoALV();
            cuadrado();
            color();
            textura();
            trama();
            color = color1.getBackground();
            tipo = 1;
            botones();
        } else if (e.getSource().equals(listaBotones.get(1))) {
            jp.removeAll();
            quitarMenuTextura();
            areaCreacion.removeAll();
            destruirTodoALV();
            circulo();
            color();
            textura();
            trama();
            color = color1.getBackground();
            tipo = 2;
            botones();
        } else if (e.getSource().equals(listaBotones.get(2))) {
            jp.removeAll();
            quitarMenuTextura();
            areaCreacion.removeAll();
            destruirTodoALV();
            RectanguloYTriangulo();
            color();
            textura();
            trama();
            color = color1.getBackground();
            tipo = 3;
            botones();
        } else if (e.getSource().equals(listaBotones.get(3))) {
            jp.removeAll();
            quitarMenuTextura();
            areaCreacion.removeAll();
            destruirTodoALV();
            RectanguloYTriangulo();
            color();
            textura();
            trama();
            color = color1.getBackground();
            tipo = 4;
            botones();
        }
        if (e.getSource().equals(dibujar)) {
            try {

                if (tipo == 1) {
                    Cuadrado cuadrado = new Cuadrado(100, 100, Integer.parseInt(lado.getText()), Integer.parseInt(lado.getText()), color, this, ruta, estadoEleccion, estadoTrama, colorT1, colorT2);
                    cuadrado.addMouseListener(this);
                    lado.setText("");
                    listaFiguras.add(cuadrado);
                    areaLienzo.add(cuadrado);
                } else if (tipo == 2) {
                    Circulo circulo = new Circulo(100, 100, Integer.parseInt(radio.getText()), Integer.parseInt(radio.getText()), color, this, ruta, estadoEleccion, estadoTrama, colorT1, colorT2);
                    circulo.addMouseListener(this);
                    radio.setText("");
                    listaFiguras.add(circulo);
                    areaLienzo.add(circulo);
                } else if (tipo == 3) {
                    Rectangulo rectangulo = new Rectangulo(100, 100, Integer.parseInt(base.getText()), Integer.parseInt(altura.getText()), color, this, ruta, estadoEleccion, estadoTrama, colorT1, colorT2);
                    rectangulo.addMouseListener(this);
                    base.setText("");
                    altura.setText("");
                    listaFiguras.add(rectangulo);
                    areaLienzo.add(rectangulo);
                } else if (tipo == 4) {
                    Triangulo triangulo = new Triangulo(100, 100, Integer.parseInt(base.getText()), Integer.parseInt(altura.getText()), color, this, ruta, estadoEleccion, estadoTrama, colorT1, colorT2);
                    triangulo.addMouseListener(this);
                    base.setText("");
                    altura.setText("");
                    listaFiguras.add(triangulo);
                    areaLienzo.add(triangulo);
                }
                areaLienzo.repaint();
                dibujar.setEnabled(false);
            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos parametros", "Error al especificar parametros", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource().equals(color1)) {
            estadoEleccion = 1;
            Color paleta = JColorChooser.showDialog(null, "Seleccion de colores", Color.WHITE);
            color = paleta;
            color1.setBackground(color);
        } else if (e.getSource().equals(eliminar)) {
            Figura.eliminar();
            areaLienzo.repaint();
            eliminar.setEnabled(false);
            listaFiguras.remove(Figura.fig);
            destruirTodoALV();
            areaCreacion.removeAll();
            areaCreacion.repaint();
            editar.setEnabled(false);
            if (listaFiguras.size() < 1) {
            }
        } else if (e.getSource().equals(editar)) {
            Figura.editar(estadoEleccion, ruta, colorT1, colorT2, estadoTrama);

        } else if (e.getSource().equals(png)) {
            BufferedImage imagen = new BufferedImage(areaLienzo.getWidth(), areaLienzo.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = imagen.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.white);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
            areaLienzo.paintAll(g2);
            try {
                ImageIO.write(imagen, "png", new File("..\\Figuritas\\src\\Temporales\\figuritas " + cont + ".png"));
                cont++;
            } catch (IOException ex) {
                Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource().equals(jpg)) {
            BufferedImage imagen = new BufferedImage(areaLienzo.getWidth(), areaLienzo.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = imagen.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.white);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
            areaLienzo.paintAll(g2);
            try {
                ImageIO.write(imagen, "jpg", new File("..\\Figuritas\\src\\Temporales\\figuritas " + cont + ".jpg"));
                cont++;
            } catch (IOException ex) {
                Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource().equals(abrir)) {
            try {
                abrirArchivo();
                for (int i = 0; i < listaFiguras.size(); i++) {
                    listaFiguras.get(i).addMouseListener(this);
                    areaLienzo.add(listaFiguras.get(i));
                }
                areaLienzo.repaint();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource().equals(guardar)) {
            guardarArchivo();
        } else if (e.getSource().equals(guardarcomo)) {
            estado = false;
            rutaRecuperada = "";
            guardarArchivo();
        } else if (e.getSource().equals(textura)) {
            menuTextura("Textura");
            estadoEleccion = 2;
        } else if (e.getSource().equals(trama)) {
            menuTrama("Trama");
            estadoEleccion = 3;
        }

        if (posicion == 1) {
            for (int i = 0; i < 14; i++) {
                if (e.getSource().equals(listaTexturas.get(i))) {
                    ruta = "..\\Figuritas\\src\\Imagenes\\texturas\\" + i + ".jpg";
                }
            }
            if (e.getSource().equals(aceptar)) {
                ImageIcon imagen = new ImageIcon(ruta);
                textura.setIcon(imagen);
                textura.setText("TEXTURA");
            }
        } else if (posicion == 2) {
            for (int i = 0; i < 3; i++) {
                if (e.getSource().equals(listaTramas.get(i))) {
                    estadoTrama = i + 1;
                }
            }

            if (e.getSource().equals(color2)) {
                Color paleta = JColorChooser.showDialog(null, "Seleccion de colores", Color.WHITE);
                colorT1 = paleta;
                color2.setBackground(colorT1);
            } else if (e.getSource().equals(color3)) {
                Color paleta = JColorChooser.showDialog(null, "Seleccion de colores", Color.WHITE);
                colorT2 = paleta;
                color3.setBackground(colorT2);
            }
        }
    }

    private void abrirArchivo() throws ClassNotFoundException {
        GestorArchivo gestor = new GestorArchivo(listaFiguras, archivo);
        listaFiguras = gestor.abrirArchivo(this);
        estado = true;
        rutaRecuperada = gestor.recuperarRutra();
        this.archivo = gestor.getArchivo();

    }

    private void guardarArchivo() {

        for (Figura e : listaFiguras) {
            e.removeMouseListener(this);
        }

        GestorArchivo gestor = new GestorArchivo(listaFiguras, archivo);
        gestor.guardarArchivo(estado, this, rutaRecuperada);

        rutaRecuperada = gestor.recuperarRutra();
        System.out.println(rutaRecuperada);

        for (Figura e : listaFiguras) {
            e.addMouseListener(this);
        }
    }

    public void soloNumeros(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char solo = e.getKeyChar();
                if (solo < '0' || solo > '9') {
                    e.consume();
                    RestrictedTextField restricted = new RestrictedTextField(a);
                    restricted.setLimit(10);
                }
            }
        });
    }

    public void botones() {
        dibujar.setEnabled(true);
        eliminar.setEnabled(false);
        editar.setEnabled(false);
    }

    public void destruirTodoALV() {
        tLado = null;
        lado = null;
        tRadio = null;
        radio = null;
        tAltura = null;
        altura = null;
        tBase = null;
        base = null;
        color1 = null;
        tColor = null;
    }

    public void circulo() {
        tRadio = new JLabel("Radio");
        tRadio.setBounds(10, 5, 100, 50);
        tRadio.setFont(fuente);
        tRadio.setVisible(true);
        tRadio.setForeground(Color.BLUE);
        areaCreacion.add(tRadio);

        radio = new JTextField("");
        radio.setBounds(110, 5, 100, 50);
        radio.setFont(fuente);
        radio.setVisible(true);
        soloNumeros(radio);
        areaCreacion.add(radio);

        areaCreacion.repaint();
    }

    public void cuadrado() {
        tLado = new JLabel("Lado");
        tLado.setBounds(10, 5, 100, 50);
        tLado.setFont(fuente);
        tLado.setForeground(Color.BLUE);
        tLado.setVisible(true);
        areaCreacion.add(tLado);

        lado = new JTextField("");
        lado.setBounds(110, 5, 100, 50);
        lado.setFont(fuente);
        lado.setVisible(true);
        soloNumeros(lado);
        areaCreacion.add(lado);

        areaCreacion.repaint();
    }

    public void RectanguloYTriangulo() {
        tBase = new JLabel("Base");
        tBase.setBounds(10, 60, 100, 50);
        tBase.setFont(fuente);
        tBase.setForeground(Color.BLUE);
        tBase.setVisible(true);
        areaCreacion.add(tBase);

        tAltura = new JLabel("Altura");
        tAltura.setBounds(10, 5, 100, 50);
        tAltura.setFont(fuente);
        tAltura.setForeground(Color.BLUE);
        tAltura.setVisible(true);
        areaCreacion.add(tAltura);

        altura = new JTextField("");
        altura.setBounds(110, 5, 100, 50);
        altura.setFont(fuente);
        altura.setVisible(true);
        soloNumeros(altura);
        areaCreacion.add(altura);

        base = new JTextField("");
        base.setBounds(110, 60, 100, 50);
        base.setFont(fuente);
        base.setVisible(true);
        soloNumeros(base);
        areaCreacion.add(base);

        areaCreacion.repaint();

    }

    public void color() {
        tColor = new JLabel("Color");
        tColor.setBounds(10, 125, 100, 50);
        tColor.setFont(fuente);
        tColor.setForeground(Color.BLUE);
        tColor.setVisible(true);
        areaCreacion.add(tColor);

        color1 = new JButton();
        color1.setBounds(150, 125, 50, 50);
        color1.setOpaque(true);
        color1.setBackground(Color.BLACK);
        color1.setVisible(true);
        color1.addActionListener(this);
        areaCreacion.add(color1);
    }

    public void textura() {
        textura = new JButton("TEXTURA");
        textura.setBounds(250, 125, 100, 50);
        textura.setOpaque(true);
        textura.setFont(fuente1);
        textura.setForeground(Color.BLACK);
        textura.setVisible(true);
        textura.addActionListener(this);
        areaCreacion.add(textura);

    }

    public void trama() {
        trama = new JButton("TRAMA");
        trama.setBounds(375, 125, 100, 50);
        trama.setOpaque(true);
        trama.setFont(fuente1);
        trama.setForeground(Color.BLACK);
        trama.setVisible(true);
        trama.addActionListener(this);
        areaCreacion.add(trama);

    }

    public void destruirTextura() {
        textura = null;
        aceptar = null;
        boton1 = null;
        titulo = null;
        jp.removeAll();
        jp.repaint();
        ruta = "";
    }

    public void destruirTrama() {
        textura = null;
        boton1 = null;
        titulo = null;
        color2 = null;
        color3 = null;
        jp.removeAll();
        jp.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(areaLienzo)) {
            destruirTodoALV();
            areaCreacion.removeAll();
            editar.setEnabled(false);
            eliminar.setEnabled(false);
            dibujar.setEnabled(false);
            areaCreacion.repaint();
            destruirTextura();
            destruirTrama();
        } else if (e.getSource() instanceof Figura) {
            destruirTextura();
            fig = (Figura) e.getSource();
            figura = (Figura) e.getSource();
            this.getEditar().setEnabled(true);
            this.getEliminar().setEnabled(true);
            if (figura instanceof Cuadrado) {
                this.getAreaCreacion().removeAll();
                this.destruirTodoALV();
                this.cuadrado();
                this.color();
                this.textura();
                this.trama();
                this.getLado().setText(("" + figura.getPosicionX()));
                this.getColor1().setBackground(figura.getColor());
                this.getAreaCreacion().repaint();
                this.repaint();
            } else if (figura instanceof Circulo) {
                this.getAreaCreacion().removeAll();
                this.destruirTodoALV();
                this.circulo();
                this.color();
                this.textura();
                this.trama();
                this.getRadio().setText(("" + figura.getPosicionX()));
                this.getColor1().setBackground(figura.getColor());
                this.getAreaCreacion().repaint();

            } else if (figura instanceof Rectangulo) {
                this.getAreaCreacion().removeAll();
                this.destruirTodoALV();
                this.RectanguloYTriangulo();
                this.color();
                this.textura();
                this.trama();
                this.getAltura().setText(("" + figura.getPosicionY()));
                this.getBase().setText(("" + figura.getPosicionX()));
                this.getColor1().setBackground(figura.getColor());
                this.getAreaCreacion().repaint();
            } else if (figura instanceof Triangulo) {
                this.getAreaCreacion().removeAll();
                this.destruirTodoALV();
                this.RectanguloYTriangulo();
                this.color();
                this.textura();
                this.trama();
                this.getAltura().setText(("" + figura.getPosicionY()));
                this.getBase().setText(("" + figura.getPosicionX()));
                this.getColor1().setBackground(figura.getColor());
                this.getAreaCreacion().repaint();
            }
        }
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

    public JPanel getAreaLienzo() {
        return areaLienzo;
    }

    public void setAreaLienzo(JPanel areaLienzo) {
        this.areaLienzo = areaLienzo;
    }

    public JPanel getAreaCreacion() {
        return areaCreacion;
    }

    public void setAreaCreacion(JPanel areaCreacion) {
        this.areaCreacion = areaCreacion;
    }

    public JButton getBoton() {
        return boton;
    }

    public void setBoton(JButton boton) {
        this.boton = boton;
    }

    public JButton getDibujar() {
        return dibujar;
    }

    public void setDibujar(JButton dibujar) {
        this.dibujar = dibujar;
    }

    public JButton getEliminar() {
        return eliminar;
    }

    public void setEliminar(JButton eliminar) {
        this.eliminar = eliminar;
    }

    public JLabel getTitulo1() {
        return titulo1;
    }

    public void setTitulo1(JLabel titulo1) {
        this.titulo1 = titulo1;
    }

    public JButton getColor1() {
        return color1;
    }

    public void setColor1(JButton color1) {
        this.color1 = color1;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public JTextField getRadio() {
        return radio;
    }

    public void setRadio(JTextField radio) {
        this.radio = radio;
    }

    public JTextField getLado() {
        return lado;
    }

    public void setLado(JTextField lado) {
        this.lado = lado;
    }

    public JTextField getBase() {
        return base;
    }

    public void setBase(JTextField base) {
        this.base = base;
    }

    public JTextField getAltura() {
        return altura;
    }

    public void setAltura(JTextField altura) {
        this.altura = altura;
    }

    public JLabel gettRadio() {
        return tRadio;
    }

    public void settRadio(JLabel tRadio) {
        this.tRadio = tRadio;
    }

    public JLabel gettLado() {
        return tLado;
    }

    public void settLado(JLabel tLado) {
        this.tLado = tLado;
    }

    public JLabel gettBase() {
        return tBase;
    }

    public void settBase(JLabel tBase) {
        this.tBase = tBase;
    }

    public JLabel gettAltura() {
        return tAltura;
    }

    public void settAltura(JLabel tAltura) {
        this.tAltura = tAltura;
    }

    public JLabel gettColor() {
        return tColor;
    }

    public void settColor(JLabel tColor) {
        this.tColor = tColor;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<JButton> getListaBotones() {
        return listaBotones;
    }

    public void setListaBotones(ArrayList<JButton> listaBotones) {
        this.listaBotones = listaBotones;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public ArrayList<Figura> getListaFiguras() {
        return listaFiguras;
    }

    public void setListaFiguras(ArrayList<Figura> listaFiguras) {
        this.listaFiguras = listaFiguras;
    }

    public Font getFuente() {
        return fuente;
    }

    public void setFuente(Font fuente) {
        this.fuente = fuente;
    }

    public JButton getEditar() {
        return editar;
    }

    public void setEditar(JButton editar) {
        this.editar = editar;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void quitarMenuTextura() {
        if (indicadorDeExistencia) {
            aceptar = null;
            titulo = null;
            posicion = 0;
            jp.repaint();
        }
    }

    private void menuTextura(String t) {
        indicadorDeExistencia = true;
        int x1 = 10;
        int y1 = 60;
//        pane.setBounds(690, 5, 500, 185);

        //Botones
        for (int i = 0; i < 14; i++) {
            boton1 = new JButton();
            this.add(boton1);
            imagen2 = new ImageIcon("..\\Figuritas\\src\\Imagenes\\texturas\\iconos\\" + i + ".jpg");
            boton1.setIcon(imagen2);
            listaTexturas.add(boton1);
            boton1.addActionListener(this);
        }

        for (int i = 0; i < 14; i++) {
            listaTexturas.get(i).setBounds(x1, y1, 50, 50);
            x1 += 60;
            jp.add(listaTexturas.get(i));
            if (x1 == (8 * 50) + 90) {
                x1 = 10;
                y1 += 60;
            }
        }

        aceptar = new JButton("Aceptar");
        aceptar.setBounds(350, 0, 150, 50);
        aceptar.setFont(fuente);
        aceptar.setEnabled(true);
        aceptar.addActionListener(this);
        jp.add(aceptar);

        titulo = new JLabel(t);
        titulo.setBounds(0, 0, 500, 50);
        titulo.setFont(fuente);
        titulo.setOpaque(true);
        titulo.setBackground(Color.BLACK);
        titulo.setForeground(Color.white);
        titulo.setVisible(true);
        jp.add(titulo);

        posicion = 1;

        jp.repaint();

    }

    public void quitarMenuTrama() {
        if (indicadorDeExistencia) {
            titulo = null;
            color2 = null;
            color3 = null;
            posicion = 0;
            jp.repaint();
        }
    }

    private void menuTrama(String t) {
        indicadorDeExistencia = true;
        int x1 = 10;
        int y1 = 60;
//        pane.setBounds(690, 5, 500, 185);

        //Botones
        for (int i = 0; i < 3; i++) {
            boton1 = new JButton();
            this.add(boton1);
            imagen2 = new ImageIcon("..\\Figuritas\\src\\Imagenes\\trama\\" + i + ".jpg");
            boton1.setIcon(imagen2);
            listaTramas.add(boton1);
            boton1.addActionListener(this);
        }

        for (int i = 0; i < 3; i++) {
            listaTramas.get(i).setBounds(x1, y1, 50, 50);
            x1 += 60;
            jp.add(listaTramas.get(i));
            if (x1 == (8 * 50) + 90) {
                x1 = 10;
                y1 += 60;
            }
        }

        color2 = new JButton();
        color2.setBounds(50, 140, 100, 30);
        color2.setOpaque(true);
        color2.setBackground(Color.BLACK);
        colorT1 = Color.BLACK;
        color2.setVisible(true);
        color2.addActionListener(this);
        jp.add(color2);

        color3 = new JButton();
        color3.setBounds(350, 140, 100, 30);
        color3.setOpaque(true);
        color3.setBackground(Color.BLACK);
        colorT2 = Color.BLACK;
        color3.setVisible(true);
        color3.addActionListener(this);
        jp.add(color3);

        titulo = new JLabel(t);
        titulo.setBounds(0, 0, 500, 50);
        titulo.setFont(fuente);
        titulo.setOpaque(true);
        titulo.setBackground(Color.BLACK);
        titulo.setForeground(Color.white);
        titulo.setVisible(true);
        jp.add(titulo);

        posicion = 2;

        jp.repaint();

    }

}
