/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeditorpanetxt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

/**
 *
 * @author Irina
 */
public final class TextEditor extends JFrame {
    
    JEditorPane editorPane;
    JScrollPane scrollPane;
    
    public TextEditor(){
        
        setTitle("Mhcrnl Java Text Editor 2018");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        editorPane = new JEditorPane();
        scrollPane = new JScrollPane(editorPane);
        add(scrollPane);
        
        this.setJMenuBar(createMenu());
        
        setVisible(true);
    }
    
    public JMenuBar createMenu(){
        
        JMenuBar menuBar = new JMenuBar();
        JMenu file, help;
        JMenuItem newFile, open, save, close;
        
        file = new JMenu("File");
        menuBar.add(file);
        
        open = new JMenuItem("Open");
        open.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser jfc = new JFileChooser();
                if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    BufferedReader br = null;
                    try {
                        File file = jfc.getSelectedFile();
                        String path = file.getAbsolutePath();
                        String line, text = "";
                        br = new BufferedReader(new FileReader(path));
                        while((line = br.readLine()) != null){
                            text += line+"\n";
                        } 
                        editorPane.setText(text);
                    } catch (FileNotFoundException ex) {
                    Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (IOException ex) {
                    Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            br.close();
                        }catch (IOException ex) {
                        Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                } 
                //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        file.add(open);
        
        close = new JMenuItem("Close");
        close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
               //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        file.add(close);
        
        return menuBar;
    }

//    @Override;
//    public void actionPerformed(ActionEvent ae) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
