package controller.section;

import component_model_loader.SectionJCompModelLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author Jordan
 */
public class DisplaySectionsByGradeLevelStateChange implements ItemListener{

    private final JComboBox jcmbGradeLevel;
    private final JTable jtblSectionMasterList;

    private SectionJCompModelLoader sectionJCompModelLoader;
    
    public DisplaySectionsByGradeLevelStateChange(JComboBox jcmbGradeLevel, JTable jtblSectionMasterList) {
        this.jcmbGradeLevel = jcmbGradeLevel;
        this.jtblSectionMasterList = jtblSectionMasterList;
        sectionJCompModelLoader = new SectionJCompModelLoader();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        jtblSectionMasterList.setModel(sectionJCompModelLoader.getSectionsByGradeLevelNo(jtblSectionMasterList,jcmbGradeLevel));
    }
}
