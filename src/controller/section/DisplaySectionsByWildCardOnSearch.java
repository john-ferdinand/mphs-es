
package controller.section;

import component_model_loader.SectionJCompModelLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Jordan
 */
public class DisplaySectionsByWildCardOnSearch implements ActionListener{
    
    private final JTextField jtfSearchBox;
    private final JTable jtblSectionMasterList;
    
    private final SectionJCompModelLoader sectionJCompModelLoader;
    
    public DisplaySectionsByWildCardOnSearch(JTextField jtfSearchBox, JTable jtblSectionMasterList) {
        this.jtfSearchBox = jtfSearchBox;
        this.jtblSectionMasterList = jtblSectionMasterList;
        sectionJCompModelLoader = new SectionJCompModelLoader();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jtblSectionMasterList.setModel(sectionJCompModelLoader.getSectionsByWildCard(jtfSearchBox, jtblSectionMasterList));
    }
    
}
