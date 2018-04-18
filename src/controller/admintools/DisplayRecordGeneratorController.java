/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admintools;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import view.admintools.AdminTools;

/**
 *
 * @author John Ferdinand Antonio
 */
public class DisplayRecordGeneratorController implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 5) {
            try {
                AudioInputStream stream;
                DataLine.Info info;
                AudioFormat format;
                Clip clip;
                
                File file = new File(".\\src\\assets\\admintoolsenabled.wav");
                stream = AudioSystem.getAudioInputStream(file);
                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
                clip.start();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                ex.printStackTrace();
            }
//             showFrame(UIManager.getSystemLookAndFeelClassName());
//            showFrame(UIManager.getCrossPlatformLookAndFeelClassName());
            showFrame();
        }
    }
    
    private void showFrame() {
        AdminTools rrg = new AdminTools();
        rrg.setResizable(false);
        rrg.setTitle("Admin Tools");
        rrg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rrg.setPreferredSize(new Dimension(750, 250));
        rrg.pack();
        rrg.setLocationRelativeTo(null);
        rrg.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    
    
}
