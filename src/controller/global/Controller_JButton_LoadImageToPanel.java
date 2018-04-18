
package controller.global;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 *
 * @author Jordan
 */
public class Controller_JButton_LoadImageToPanel implements ActionListener{

    private final JPanel jpnlImageContainer;
    private final JTextArea jtaPath;
    
    /**
     * 
     * @param jpnlImageContainer 
     * - is any JPanel component that doesn't contain any other JComponents
     * @param jtaPath
     * - is any JTextArea to "display" the path of the image file selected.
     */
    public Controller_JButton_LoadImageToPanel(JPanel jpnlImageContainer, JTextArea jtaPath) {
        this.jpnlImageContainer = jpnlImageContainer;
        this.jtaPath = jtaPath;

        jtaPath.setWrapStyleWord(true);
        jtaPath.setLineWrap(true);
        jtaPath.setOpaque(false);
        jtaPath.setEditable(false);
        jtaPath.setFocusable(false);
        jtaPath.setBackground(UIManager.getColor("Label.background"));
        jtaPath.setFont(UIManager.getFont("Label.font"));
        jtaPath.setBorder(UIManager.getBorder("Label.border"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel jlblImage;
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String imagePath = file.getAbsolutePath();
            jtaPath.setText(imagePath);
            ImageIcon raw = new ImageIcon(imagePath);
            Image renderedImage = raw.getImage().getScaledInstance(jpnlImageContainer.getWidth(), jpnlImageContainer.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon renderedImgIcon = new ImageIcon(renderedImage);
            jlblImage = new JLabel("", renderedImgIcon, JLabel.CENTER);
            jpnlImageContainer.removeAll();
            jpnlImageContainer.add(jlblImage, BorderLayout.CENTER);
            jpnlImageContainer.revalidate();
            jpnlImageContainer.repaint();
        }
    }
}
    
