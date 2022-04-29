package figuritas;

import Figuras.*;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Archivo {

    private File archivo;
    private ObjectOutputStream objetoEscribir = null;
    private ObjectInputStream entrada = null;

    public Archivo(String ruta) {
        archivo = new File(ruta);
    }

    public Archivo(File archivo) {
        this.archivo = archivo;
    }
    
        public Archivo( String ruta, File archivo) {
        this.archivo = archivo;   
    }
    

    public Archivo() {
    }

    public void guardar(ArrayList<Figura> listaFiguras) {
        try {
            Figura fig = null;
            objetoEscribir = new ObjectOutputStream(new FileOutputStream(archivo, false));
            for (Figura f : listaFiguras) {
                objetoEscribir.writeObject(f);
            }
            objetoEscribir.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarArchivo(ArrayList<Figura> listaFiguras, Lienzo lienzo) {
        if (archivo.exists() == false) {
            guardar(listaFiguras);
        } else {
            int n = JOptionPane.showConfirmDialog(null,"¿Desea reemplazar el archivo?","advertencia",JOptionPane.YES_NO_OPTION);
        if(true){           
            archivo.delete();
            guardar(listaFiguras);
        }
        else {
            
        }
        }
    }

    public ArrayList<Figura> abrir() throws IOException, ClassNotFoundException {
        ArrayList listaFiguras = new ArrayList<>();
        try {
            Figura fig = null;
            entrada = new ObjectInputStream(new FileInputStream(archivo));
            while (true) {
                fig = (Figura) entrada.readObject();
                listaFiguras.add(fig);
            }
        } catch (FileNotFoundException ex) {
            entrada.close();
            return listaFiguras;
        } catch (IOException ex) {
            entrada.close();
            return listaFiguras;
        }
    }
    
    public File recuperarArchivo(){
        return archivo;
    }
}
