package controller.enrollment;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Old_DisplayTablePopupMenu implements MouseListener{
    private final JTable jtblStudentsList;
    private JPopupMenu jPopupMenu;
    private JMenuItem jmiCopyStudentId;
    private JMenuItem jmiCopyRegistrationId;
    private JMenuItem jmiCopyStudentLastName;
    
    public Old_DisplayTablePopupMenu(JTable jtblStudentsList){
        initComponents();
        this.jtblStudentsList = jtblStudentsList;
    }

    private void initComponents(){
        jPopupMenu = new JPopupMenu();
        jmiCopyStudentId = new JMenuItem("Copy Student Id");
        jmiCopyRegistrationId = new JMenuItem("Copy Registration Id");
        jmiCopyStudentLastName = new JMenuItem("Copy Last Name");
        
        jPopupMenu.add(jmiCopyStudentId);
        jPopupMenu.add(jmiCopyRegistrationId);
        jPopupMenu.add(jmiCopyStudentLastName);
    }
    
    private void initializeControllers(MouseEvent mouseEvent){
        jmiCopyStudentId.addActionListener(new Old_ClickPopupMenuItem(jtblStudentsList, jmiCopyStudentId, jmiCopyRegistrationId, jmiCopyStudentLastName,mouseEvent));
        jmiCopyRegistrationId.addActionListener(new Old_ClickPopupMenuItem(jtblStudentsList, jmiCopyStudentId, jmiCopyRegistrationId, jmiCopyStudentLastName,mouseEvent));
        jmiCopyStudentLastName.addActionListener(new Old_ClickPopupMenuItem(jtblStudentsList, jmiCopyStudentId, jmiCopyRegistrationId, jmiCopyStudentLastName,mouseEvent));
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int rowSelected = jtblStudentsList.getSelectedRow();
        int studentId = Integer.parseInt(jtblStudentsList.getValueAt(rowSelected, 0).toString());
        if (e.getClickCount() == 2) {
//            JdlgStudentInfo jdlgStudentInfo = new JdlgStudentInfo(studentId);
//            jdlgStudentInfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            jdlgStudentInfo.setTitle("Student Information");
//            jdlgStudentInfo.setPreferredSize(new Dimension(1080,700));
//            jdlgStudentInfo.setLocationRelativeTo(null);
//            jdlgStudentInfo.pack();
//            jdlgStudentInfo.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        jtblStudentsList.setComponentPopupMenu(jPopupMenu);
        // selects the row at which point the mouse is clicked
        initializeControllers(mouseEvent);
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
