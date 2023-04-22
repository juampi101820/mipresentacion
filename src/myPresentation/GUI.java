package myPresentation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    //atributos
    private JButton myPhoto, myHobby, myExpectations;
    private JPanel containerButtons, containerImage;
    private Listener listener;
    private ListenerMouse listenerMouse;
    private ListenerKeygen listenerKeygen;
    private Title title;
    private JLabel imageLabel;
    private JTextArea expectativesText;

    //metodos
    public GUI(){
        initGUI();

        this.setTitle("My Presentation");
        this.setSize(600, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        //Definir container y Layout del JFrame
        //Crear objetos Escucha y Control
        //Configurar JComponents
        title = new Title("A little more about me", Color.BLACK);
        myPhoto = new JButton("This is me");
        myHobby = new JButton("This is my passion");
        myExpectations = new JButton("I expect to get the best of you");
        containerButtons = new JPanel();
        containerImage = new JPanel();
        listener = new Listener();
        listenerMouse = new ListenerMouse();
        listenerKeygen = new ListenerKeygen();
        imageLabel = new JLabel();
        expectativesText = new JTextArea(10, 12);

        containerImage.setBorder(BorderFactory.createTitledBorder(null, "About me", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, new Font(Font.SANS_SERIF,Font.PLAIN,20), Color.BLACK));
        containerImage.add(imageLabel);

        containerButtons.add(myPhoto);
        containerButtons.add(myHobby);
        containerButtons.add(myExpectations);

        myPhoto.addActionListener(listener);
        myExpectations.addActionListener(listener);
        myHobby.addMouseListener(listenerMouse);

        this.addKeyListener(listenerKeygen);
        this.requestFocusInWindow();

        this.add(title, BorderLayout.NORTH);
        this.add(containerButtons, BorderLayout.SOUTH);
        this.add(containerImage, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI myGui = new GUI();
            }
        });
    }

    private class Listener implements ActionListener{
        private ImageIcon image;

        @Override
        public void actionPerformed(ActionEvent e) {
            //JOptionPane.showMessageDialog(null, "Press button");
            imageLabel.setIcon(null);
            containerImage.remove(expectativesText);
            if (e.getSource() == myPhoto) {
                this.image = new ImageIcon(getClass().getResource("/resources/Me.jpg"));
                imageLabel.setIcon(image);
            } else if (e.getSource() == myExpectations) {
                expectativesText.setText("Espero aprender mucho sobre el frontend y las cosas que se pueden realizar con los eventos," +
                        "\naunque en mi futuro me vea como un back, lo mejor es saber tambien de front \n" +
                        "Mi contacto es juan.pablo.marin@correounivalle.edu.co");
                expectativesText.setBackground(null);
                expectativesText.setForeground(Color.BLACK);
                containerImage.add(expectativesText);
            }
            validate();
            repaint();
            GUI.this.requestFocusInWindow();
        }
    }
    private class ListenerMouse extends MouseAdapter{
        private ImageIcon image;
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                imageLabel.setIcon(null);
                containerImage.remove(expectativesText);
                this.image = new ImageIcon(getClass().getResource("/resources/Hobby.jpg"));
                imageLabel.setIcon(image);
            }
            GUI.this.requestFocusInWindow();
        }
    }
    private class ListenerKeygen extends KeyAdapter{

        private ImageIcon image;
        // para ejecutar el texto de las expectativas.
        public void keyPressed(KeyEvent e) {
            imageLabel.setIcon(null);
            containerImage.remove(expectativesText);
            if (e.getKeyCode() == KeyEvent.VK_M) {
                expectativesText.setText("Espero aprender mucho sobre el frontend y las cosas que se pueden realizar con los eventos," +
                        "\naunque en mi futuro me vea como un back, lo mejor es saber tambien de front \n" +
                        "Mi contacto es juan.pablo.marin@correounivalle.edu.co");
                expectativesText.setBackground(null);
                expectativesText.setForeground(Color.BLACK);
                containerImage.add(expectativesText);
            }
        }
    }
}
