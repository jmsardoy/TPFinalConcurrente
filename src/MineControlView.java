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


public class MineControlView implements Observer {


    private static MineControlView instancia = null; //Le hago un singleton a la view para que no se creen mas de 1

    /*Diferentes elementos que utiliza la vista*/
    private JFrame frame;
    private JLabel label_bomba;
    private JLabel label_alarma_CH4;
    private JLabel label_alarma_CO;

    private JToggleButton toggleButton_CH4;
    private JToggleButton toggleButton_CO;
    private JToggleButton toggleButton_H2O;

    private VariablesExternas variables;

    private int bomba_on = 18;
    private int bomba_off = 16;
    private int bomba_no_arrancar_por_gas = 14;
    private int bomba_parada_por_gas = 15;
    private int CH4_alarma = 5;
    private int CH4_alarma_TO = 4;
    private int CO_alarma = 37;
    private int CO_alarma_TO = 35;


    /*Lista donde guardo los botones del teclado para poder hacer un update*/
    //private List<JToggleButton> listaBotones = new ArrayList<JToggleButton>();

    private MineControlView(VariablesExternas variables) {

        /*Inicializo las vistas*/
        this.variables = variables;
        initialize();
        frame.setVisible(true);
    }


    /*Funciones del singleton*/
    private synchronized static void createInstance(VariablesExternas variables){

        if(instancia == null){
            instancia = new MineControlView(variables);
        }
    }

    /* Metodo que devuelve la instancia creada de la vista*/

    public static MineControlView get_instance(VariablesExternas variables){
        if(instancia == null){
            createInstance(variables);
        }
        return instancia;
    }

    /*Crea todos los elementos de la ventana y los inicializa*/
    private void initialize() {
        /*El marco de la ventana*/
        frame = new JFrame("Mina");
        frame.setBounds(0, 0, 1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        /*Todos los toggle buttonm*/
        toggleButton_CH4 = new JToggleButton("CH4");
        /*Y por debajo todos los listener y los handler*/

        toggleButton_CH4.addActionListener(new ActionListener() {       //Cada listener actua cuando se presiona la tecla correspondiente
            public void actionPerformed(ActionEvent e) {
                variables.setCH4(toggleButton_CH4.isSelected());
            }
        });
        toggleButton_CH4.setBackground(Color.WHITE);
        toggleButton_CH4.setBounds(5, 0, 133, 60);
        frame.getContentPane().add(toggleButton_CH4);

        toggleButton_CO= new JToggleButton("CO");
        toggleButton_CO.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                variables.setCO(toggleButton_CO.isSelected());
            }
        });
        toggleButton_CO.setBackground(Color.WHITE);
        toggleButton_CO.setBounds(134, 0, 133, 60);
        frame.getContentPane().add(toggleButton_CO);

        toggleButton_H2O = new JToggleButton("H20");
        toggleButton_H2O.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                variables.setAgua(toggleButton_H2O.isSelected());
            }
        });
        toggleButton_H2O.setBackground(Color.WHITE);
        toggleButton_H2O.setBounds(267, 0, 133, 60);
        frame.getContentPane().add(toggleButton_H2O);




        /*Labels*/
        label_bomba = new JLabel("Bomba: ");
        label_bomba.setFont(new Font("Dialog", Font.BOLD, 20));
        label_bomba.setBounds(0, 168, 500, 23);
        frame.getContentPane().add(label_bomba);

        label_alarma_CH4 = new JLabel("Alarma CH4: ");
        label_alarma_CH4.setFont(new Font("Dialog", Font.BOLD, 20));
        label_alarma_CH4.setBounds(0, 219, 500, 23);
        frame.getContentPane().add(label_alarma_CH4);

        label_alarma_CO = new JLabel("Alarma CO: ");
        label_alarma_CO.setFont(new Font("Dialog", Font.BOLD, 20));
        label_alarma_CO.setBounds(0, 270, 500, 23);
        frame.getContentPane().add(label_alarma_CO);

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
    }

    @Override
    public void update(Matrix marcado) {

        //update de la bomba
        if(marcado.getVal(0,bomba_on) ==1){
            label_bomba.setText("Bomba: On");
        }
        else if(marcado.getVal(0,bomba_no_arrancar_por_gas) == 1){
            label_bomba.setText("Bomba: No Arrancar Por Gas");
        }
        else if(marcado.getVal(0,bomba_parada_por_gas) == 1){
            label_bomba.setText("Bomba: Parada Por Gas");
        }
        else{
            label_bomba.setText("Bomba: Off");

        }

        //update del sensor CH4
        if(marcado.getVal(0,CH4_alarma) == 1 || marcado.getVal(0,CH4_alarma_TO)==1){
            label_alarma_CH4.setText("CH4: On");
        }
        else{
            label_alarma_CH4.setText("CH4: Off");
        }

        //update del sensor CO
        if(marcado.getVal(0,CO_alarma) == 1 || marcado.getVal(0,CO_alarma_TO)==1){
            label_alarma_CO.setText("CO: On");
        }
        else{
            label_alarma_CO.setText("CO: Off");
        }
    }

}
