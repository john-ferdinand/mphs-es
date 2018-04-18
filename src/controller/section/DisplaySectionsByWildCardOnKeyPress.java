
package controller.section;

import component_model_loader.SectionJCompModelLoader;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Jordan
 */
public class DisplaySectionsByWildCardOnKeyPress implements KeyListener{
    
    private final JTextField jtfSearchBox;
    private final JTable jtblSectionMasterList;
    
    private final SectionJCompModelLoader sectionJCompModelLoader;
    
    public DisplaySectionsByWildCardOnKeyPress(JTextField jtfSearchBox, JTable jtblSectionMasterList) {
        this.jtfSearchBox = jtfSearchBox;
        this.jtblSectionMasterList = jtblSectionMasterList;
        sectionJCompModelLoader = new SectionJCompModelLoader();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ENTER){
            jtblSectionMasterList.setModel(sectionJCompModelLoader.getSectionsByWildCard(jtfSearchBox, jtblSectionMasterList));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    
    
}
