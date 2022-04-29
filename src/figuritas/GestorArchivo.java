package figuritas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Issa
 */
public class GestorArchivo extends JFileChooser implements Serializable {

    private JFileChooser file;
    private boolean guardar = false;
    private String rutaRecuperada;
    private File archivo;
    
    
            
    private ArrayList<Figura> listaFiguras;

    public GestorArchivo(ArrayList<Figura> listaFiguras) {
        this.listaFiguras = listaFiguras;
    }
    
    public GestorArchivo(ArrayList<Figura> listaFiguras, File archivo) {
        this.listaFiguras = listaFiguras;
        this.archivo = archivo;
    }

    public void guardarArchivo(boolean estado, Lienzo lienzo, String rutaRecuperada) {
        if(estado == false){
        int selec = this.showSaveDialog(this);
        if (selec == 0) {
            String ruta = this.getSelectedFile() + ".dat";
            this.rutaRecuperada = ruta;
            Archivo archivo = new Archivo(ruta);
            archivo.guardarArchivo(listaFiguras, lienzo);
            JOptionPane.showMessageDialog(null, "El archivo se a guardado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            lienzo.setEstado(true);
        } else if (selec == this.CANCEL_OPTION){
                lienzo.destruirTodoALV();
                lienzo.getAreaCreacion().removeAll();
                lienzo.getEditar().setEnabled(false);
                lienzo.getEliminar().setEnabled(false);
                lienzo.getAreaCreacion().repaint();
        } 
        }else{
            System.out.println(rutaRecuperada);
            Archivo archivo = new Archivo(rutaRecuperada, this.archivo);
            this.rutaRecuperada = rutaRecuperada;
            archivo.guardarArchivo(listaFiguras, lienzo);
            
            JOptionPane.showMessageDialog(null, "Orden Ejecutada", "Información", JOptionPane.INFORMATION_MESSAGE);   
        }   
}
                

    public ArrayList abrirArchivo(Lienzo lienzo) throws ClassNotFoundException {
        ArrayList listaFigura = new ArrayList();
        try {
            file = new JFileChooser();
            int status = file.showOpenDialog(this);
            if(status == file.APPROVE_OPTION){
            lienzo.getAreaLienzo().removeAll();
            lienzo.getAreaLienzo().repaint();
            File abre = file.getSelectedFile();
            if (abre != null) {
                rutaRecuperada = abre.getAbsolutePath();
                Archivo archivo = new Archivo(rutaRecuperada);
                this.archivo = archivo.recuperarArchivo();
            listaFigura = archivo.abrir();
            }
            } else if (status == file.CANCEL_OPTION){
                lienzo.destruirTodoALV();
                lienzo.getAreaCreacion().removeAll();
                lienzo.getEditar().setEnabled(false);
                lienzo.getEliminar().setEnabled(false);
                lienzo.getAreaCreacion().repaint();
                this.rutaRecuperada = "";
                
                
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
        return listaFigura;
    }
    
    public String recuperarRutra(){
        return rutaRecuperada;
        
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    
}
