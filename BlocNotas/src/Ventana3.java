import javax.annotation.processing.FilerException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.jar.JarFile;

public class Ventana3 extends JFrame {

    private JLabel nombre;
    JTextArea txtContenidoFichero; //aqui vamos a mostrar el contenido

    JButton btnGuardar, btnAbrir;//boton para guardar lo que haya en el texto

    JTextField txtNombre; //para escribir el nombre del archivo

    public Ventana3() {



        nombre = new JLabel("Juan Medrano");
        nombre.setBounds(20, 30, 200, 30);
        add(nombre);

        this.setBounds(50, 50, 600, 400); //dnde esta colocada

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//La aplicacion se cierra si el usuario
        //ciera la ventana

        getContentPane().setLayout(new BorderLayout()); //asignar formato a la ventana

        //JTextArea para que ocupe el centro
        txtContenidoFichero = new JTextArea();
        add(txtContenidoFichero, BorderLayout.CENTER);

        //panel parte superior de la ventana
        JPanel jp = new JPanel(new FlowLayout());
        add(jp, BorderLayout.NORTH);

        txtNombre = new JTextField("fichero.txt", 30);
        jp.add(txtNombre, BorderLayout.SOUTH);

        btnAbrir = new JButton("Abrir");

        jp.add(btnAbrir);
        btnAbrir.addActionListener(new CallBackAbrir());

        btnGuardar = new JButton("Guardar");

        jp.add(btnGuardar);
        btnGuardar.addActionListener(new CallBackGuardar());


    }

    private class CallBackGuardar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            escribirArchivo();

        }
    }

    private class CallBackAbrir implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            leerArchivo();

        }
    }


    public void leerArchivo() {

            JFileChooser fileChooser = new JFileChooser();
            //creo un filechooser y lo configuro para que solo lea ficheros
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            //muestro el directorio actual que es del proyecto
            fileChooser.setCurrentDirectory(new File("."));

            //si el usuario termina el ficlechooseer pulsando aeptar, continua

            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(this)) {
                File archivo = fileChooser.getSelectedFile();

                try {
                    BufferedReader br = new BufferedReader(new FileReader(archivo));
                    String linea = br.readLine();
                    String contenido = "";
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

           /* //abrimos  el fichero de lectura
            BufferedReader br = new BufferedReader(new FileReader(txtNombre.getText()));

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
        }*/
    }

    public void escribirArchivo() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File("."));
        if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(this)) ;
        File archivo = fileChooser.getSelectedFile();


        try {
            //abre el fichero para escribir y guarda el contenido de JTesxtArea
            BufferedWriter bw = new BufferedWriter(new FileWriter(txtNombre.getText()));
            bw.write(txtContenidoFichero.getText());
            bw.close();
        } catch (IOException ioe) {
            System.out.println("No se ha podido escribir en el fichero");
        }
    }
}

