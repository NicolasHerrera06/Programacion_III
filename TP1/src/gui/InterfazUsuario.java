package gui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import negocio.MatrizTableModel;
import negocio.Logica;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class InterfazUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private Logica miTablero;
    private JLabel lblPuntaje;
    private JLabel lblRestPuntaje;

    public InterfazUsuario() {
    	
    	miTablero = new Logica();
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 549, 504);
        setTitle("JUEGO 2048");
        setLocationRelativeTo(null); //ubica la pantalla en el medio
        iniciarComponentes();
        
        //Eventos de tecleado
        
        addKeyListener(new MyKeyListener());
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    private void iniciarComponentes() {
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        table = new JTable();
        table.setBounds(21, 129, 486, 275);
        // Configurar la altura de las filas
        table.setRowHeight(50);
        
        // Cambiar el color de la cuadrícula
        //--table.setGridColor(Color.BLACK);
        // Configurar el renderizador de celdas para centrar los valores
        // Configurar el renderizador de celdas para centrar los valores
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        });
        
        contentPane.add(table);
        
        JLabel lblTitulo = new JLabel("Juego 2048");
        lblTitulo.setBackground(Color.BLACK);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setOpaque(true);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(61, 11, 411, 30);
        contentPane.add(lblTitulo);
        
        lblPuntaje = new JLabel("Puntaje:");
        lblPuntaje.setBounds(318, 72, 66, 14);
        contentPane.add(lblPuntaje);
        
        lblRestPuntaje = new JLabel("");
        lblRestPuntaje.setBounds(402, 72, 46, 14);
        contentPane.add(lblRestPuntaje);
        
    }
    
    public void dibujarMatriz() { 

        try {
            // Asignar el modelo de tabla al campo 'table'
        	table.setModel(new MatrizTableModel(miTablero.obtenerTablero()));
        	
        	lblRestPuntaje.setText(miTablero.obtenerPuntaje()+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void mostar() {
    	miTablero.mostrar();
    	System.out.println("");
    	
    }

    private class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            
            if (keyCode == KeyEvent.VK_UP) {
                miTablero.moverArriba();

            }
            if (keyCode == KeyEvent.VK_DOWN) {
                miTablero.moverAbajo();

            }
            if (keyCode == KeyEvent.VK_LEFT) {
                miTablero.moverIzquierda();

            }
            if (keyCode == KeyEvent.VK_RIGHT) {
                miTablero.moverDerecha();
;
            }
            miTablero.mostrar();
            dibujarMatriz();
        }
    }
}
