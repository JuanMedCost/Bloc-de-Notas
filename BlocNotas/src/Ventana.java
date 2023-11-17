import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Ventana extends JFrame {
    JTextArea txtContenidoFichero; //aqui vamos a mostrar el contenido

    JButton btnGuardar, btnAbrir;//boton para guardar lo que haya en el texto


    public Ventana(){

        this.setBounds(50, 50, 600, 400); //dnde esta colocada

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//La aplicacion se cierra si el usuario
        //ciera la ventana

        getContentPane().setLayout(new BorderLayout()); //asignar formato a la ventana

        //JTextArea para que ocupe el centro
        txtContenidoFichero = new JTextArea();
        add(txtContenidoFichero, BorderLayout.CENTER);

        //panel parte superior de la ventana
        JPanel jp=new JPanel(new FlowLayout());
        add(jp, BorderLayout.NORTH);

        btnAbrir= new JButton("Abrir");

        jp.add(btnAbrir);
        btnAbrir.addActionListener(new CallBackAbrir());

        btnGuardar=new JButton("Guardar");

        jp.add(btnGuardar);
        btnGuardar.addActionListener(new CallBackGuardar());


    }
    private class CallBackGuardar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) { escribirArchivo();

        }
    }

    private class CallBackAbrir implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) { leerArchivo();

        }
    }

    public void leerArchivo(){
        try {
            //abrimos  el fichero de lectura
            BufferedReader br = new BufferedReader(new FileReader("fichero.txt"));

            //Leemos la primera linea
            String linea = br.readLine();


            //Creamos una variable en la que vamos a ir acumulando las lineas
            String contenido = "";


            //hacemos un bucle para leer todos las lineas del archivo

            while (linea != null) {
                //añadimos la linea leida y un salto de linea a la variable
                contenido += linea + "\n";
                linea = br.readLine();
            }
            br.close();

            txtContenidoFichero.setText(contenido);

        } catch (FileNotFoundException fnfe) {
            System.out.println("No se encuentra el fichero");

        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el archivo");
        }

    }

    public void escribirArchivo(){
        try {
            //abre el fichero para escribir y guarda el contenido de JTesxtArea
            BufferedWriter bw = new BufferedWriter(new FileWriter("fichero.txt"));
            bw.write(txtContenidoFichero.getText());
            bw.close();
        } catch (IOException ioe){
            System.out.println("No se ha podido escribir en el fichero");
        }
    }
}

