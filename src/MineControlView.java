/*Autor: Federico Perez
Ing. Software
2016
UNC-FCEFyN
 */


import javax.swing.JFrame;
import javax.swing.JToggleButton;
import java.awt.Color;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


/*Clase que implementa la viste del reproductor de notas musicales*/


public class MineControlView {


    private static MineControlView instancia = null; //Le hago un singleton a la view para que no se creen mas de 1

    /*Diferentes elementos que utiliza la vista*/
    private JFrame frame;
    private JLabel label;
    private JLabel label_2;

    private JToggleButton toggleButton;
    private JToggleButton toggleButton_1;
    private JProgressBar progressBar;

    /*Lista donde guardo los botones del teclado para poder hacer un update*/
    private List<JToggleButton> listaBotones = new ArrayList<JToggleButton>();

    private MineControlView() {

        listaBotones = new ArrayList();

        /*Inicializo las vistas*/
        initialize();
        frame.setVisible(true);
    }


    /*Funciones del singleton*/
    private synchronized static void createInstance(){

        if(instancia == null){
            instancia = new MineControlView();
        }
    }

    /* Metodo que devuelve la instancia creada de la vista*/

    public static MineControlView get_instance(){
        if(instancia == null){
            createInstance();
        }
        return instancia;
    }

    /*Crea todos los elementos de la ventana y los inicializa*/
    private void initialize() {
        /*El marco de la ventana*/
        frame = new JFrame("Teclado");
        frame.setBounds(200, 200, 850, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        /*Todos los toggle buttonm*/
        toggleButton = new JToggleButton("CH4");
        /*Y por debajo todos los listener y los handler*/
        toggleButton.addActionListener(new ActionListener() {       //Cada listener actua cuando se presiona la tecla correspondiente
            public void actionPerformed(ActionEvent arg0) {
                levantarBotones(0);
                //
                //
                //
            }
        });
        toggleButton.setBackground(Color.WHITE);
        toggleButton.setBounds(5, 0, 133, 60);
        frame.getContentPane().add(toggleButton);
        listaBotones.add(toggleButton);

        toggleButton_1 = new JToggleButton("CO");
        toggleButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //
                //
                //
                levantarBotones(1);
            }
        });
        toggleButton_1.setForeground(Color.WHITE);
        toggleButton_1.setBackground(Color.BLACK);
        toggleButton_1.setBounds(138, 0, 133, 60);
        frame.getContentPane().add(toggleButton_1);
        listaBotones.add(toggleButton_1);



        /*Labels*/
        JLabel lblNotaActual = new JLabel("Agua");
        lblNotaActual.setFont(new Font("Dialog", Font.BOLD, 20));
        lblNotaActual.setBounds(319, 168, 155, 23);
        frame.getContentPane().add(lblNotaActual);

        label = new JLabel("Bomba encendida:");
        label.setFont(new Font("Dialog", Font.BOLD, 20));
        label.setBounds(495, 168, 155, 23);
        frame.getContentPane().add(label);

        JLabel label_1 = new JLabel("Carritos viajando:");
        label_1.setFont(new Font("Dialog", Font.BOLD, 20));
        label_1.setBounds(319, 219, 155, 23);
        frame.getContentPane().add(label_1);

        label_2 = new JLabel("No arrancar por gas.");
        label_2.setFont(new Font("Dialog", Font.BOLD, 20));
        label_2.setBounds(495, 219, 155, 23);
        frame.getContentPane().add(label_2);

        /*Botones de start y stop*/
        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
                //
            }
        });
        btnStart.setBounds(684, 166, 117, 25);
        frame.getContentPane().add(btnStart);

        JButton btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
                //
            }
        });
        btnStop.setBounds(684, 197, 117, 25);
        frame.getContentPane().add(btnStop);

        progressBar = new JProgressBar();
        progressBar.setBackground(Color.YELLOW);
        progressBar.setBounds(70, 199, 148, 14);
        frame.getContentPane().add(progressBar);

        JLabel lblFrecuenciaGrafica = new JLabel("Frecuencia Grafica");
        lblFrecuenciaGrafica.setBounds(70, 174, 162, 14);
        frame.getContentPane().add(lblFrecuenciaGrafica);

    }

    /*Setea en el label de nota la nota actual*/

    public void updateNotaActual(){
        //
    }

    public void updateFrecuenciaActual(){
        //
    }

    public void updateFrecuenciaGrafica(){
        //
    }

    /*Levanta todos los toggle button menos el que se pase como parametro*/
    private void levantarBotones(int botonExcluido){
        for(int i = 0; i<listaBotones.size(); i++){
            if(!(botonExcluido == i)){
                listaBotones.get(i).setSelected(false);
            }

        }
    }


}
