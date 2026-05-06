package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Class to create a new profile
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class NewProfileDialog extends JDialog {
    GamePanel gamePanel = GamePanel.getInstance();
    public static JTextField nicknameField;
    public static String selectedImagePath;
    public static String nickname;

    /**
     * Class constructor 
     * @param parent		in our case it's null
     * @param title			the title of the JDialogue
     * @param modal			modal specifies whether dialog blocks user input to other top-level
     *    					windows when shown. If {@code true}, the modality type property is set to
     *     					{@code DEFAULT_MODALITY_TYPE} otherwise the dialog is modeless
     */
    public NewProfileDialog(Frame parent, String title, boolean modal) {
        super(parent, title, modal);

        JLabel nameLabel = new JLabel("Nickname:");

        nicknameField = new JTextField(20);

        JLabel imageLabel = new JLabel("Profile Image:");

        JTextField imagePathField = new JTextField(20);
        imagePathField.setEditable(false);

        JButton browseButton = new JButton("Browse...");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("C:/Users/Alixel/Desktop/Uni/Dispense/Primo Anno/Secondo Semestre/Metodologie di Programmazione/"
                        + "Faralli 22-23/MdP2023Lab/JBomberMan/resources/profile"));
                fileChooser.setFileFilter(new FileNameExtensionFilter("Immagini", "jpg", "jpeg", "png", "gif"));
                int returnValue = fileChooser.showOpenDialog(NewProfileDialog.this); 

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedImagePath = selectedFile.getAbsolutePath();
                    imagePathField.setText(selectedImagePath);
                    setVisible(false); 
                    
                    gamePanel.getUi().setSelectedImagePath(selectedImagePath);
                }
            }
        });

        JButton saveButton = new JButton("Save and Continue");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nickname = nicknameField.getText();

                gamePanel.getUi().titleScreenState = 2;
                
                dispose();
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(nameLabel);
        panel.add(nicknameField);
        panel.add(imageLabel);
        panel.add(imagePathField);
        panel.add(browseButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 200);

        setLocationRelativeTo(parent);
    }
}


