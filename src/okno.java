
package Edytor;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class okno extends JFrame implements ActionListener{
    JEditorPane editorPane;
    JButton open, save;
    JScrollPane editorScroll;

    public okno() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 500);
        Container contentPane = getContentPane();
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setTitle("Edytor");
        editorPane = new JEditorPane();
        //editorPane.setPreferredSize(new Dimension(300,400));
        editorScroll = new JScrollPane(editorPane);
        add(editorScroll);
        open = new JButton("Open");
        save = new JButton("Save");
        add(open);
        add(save);
        open.addActionListener(this);
        save.addActionListener(this);

        layout.putConstraint(SpringLayout.NORTH, editorScroll, 10,     SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, editorScroll, 7,     SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, editorScroll, -7,     SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, editorScroll, -50,     SpringLayout.SOUTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, open, 13,    SpringLayout.SOUTH, editorScroll);
        layout.putConstraint(SpringLayout.WEST, open, 5,    SpringLayout.WEST, editorScroll);
        layout.putConstraint(SpringLayout.NORTH, save, 0,    SpringLayout.NORTH, open);
        layout.putConstraint(SpringLayout.EAST, save, -5,     SpringLayout.EAST, editorScroll);


    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() ==open )
        {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                BufferedReader br = null;
                try {
                    File file = fc.getSelectedFile();
                    String path = file.getAbsolutePath();
                    String line, tekst="";
                    br = new BufferedReader(new FileReader(path));
                    while ((line = br.readLine()) != null) {
                        tekst += line+"\n";
                    }

                    editorPane.setText(tekst);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(okno.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(okno.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(okno.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }


        }
        if(e.getSource() ==save )
        {
            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                BufferedWriter wr = null;
                try {
                    File file = fc.getSelectedFile();
                    String path = file.getAbsolutePath();
                    wr = new BufferedWriter(new FileWriter(path));
                    wr.write(editorPane.getText());
                } catch (IOException ex) {
                    Logger.getLogger(okno.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        wr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(okno.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
    }




}