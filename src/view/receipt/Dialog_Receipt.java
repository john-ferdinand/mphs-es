package view.receipt;

import controller.global.Controller_Print_JButton;
import daoimpl.PenaltyDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.StudentDaoImpl;
import utility.component.ImageUtil;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.print.PageFormat;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import model.officialreceipt.OfficialReceipt;
import model.particulars.Particular;
import model.penalty.Penalty;
import model.student.Student;
import utility.initializer.Initializer;

/**
 *
 * @author Jordan
 */
public class Dialog_Receipt extends javax.swing.JDialog implements Initializer{

    private final Student student;
    private final OfficialReceipt officialReceipt;
    private SchoolYearDaoImpl schoolYearDaoImpl;
    private StudentDaoImpl studentDaoImpl;
    private PenaltyDaoImpl penaltyDaoImpl;
    private Image schoolLogo;

    public Dialog_Receipt(Student student, OfficialReceipt officialReceipt) {
        super(null, ModalityType.APPLICATION_MODAL);
        initComponents();
        schoolLogo = new ImageUtil().getResourceAsImage("assets/logo.png", 200, 200);
        
        this.student = student;
        this.officialReceipt = officialReceipt;
        
        initDaoImpl();
        initControllers();
        initModels();
        initJCompModelLoaders();
        initViewComponents();
        
    }

    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
    }

    @Override
    public void initRenderers() {
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
//        jlblCashReceived.setVisible(false);
//        jlblCashReceivedText.setVisible(false);
        
        String lastName = student.getRegistration().getLastName();
        String firstName = student.getRegistration().getFirstName();
        String middleName = student.getRegistration().getMiddleName();
        
        jlblGradeLevelText.setText(student.getGradeLevelNo() == 0 ? ("Kindergarten") : (student.getGradeLevelNo() + ""));
        jlblSchoolYearText.setText("" + officialReceipt.getSchoolYear().getYearFrom() + " - " + officialReceipt.getSchoolYear().getYearTo());
        jlblStudentNoText.setText(""+student.getStudentNo());
        jlblStudentNameText.setText(lastName+", "+firstName+" "+middleName);
        String formattedDateOfPayment = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(officialReceipt.getPayment().getDateOfPayment());
        jlblDateOfPayment.setText(formattedDateOfPayment);
        
        jlblCashReceivedText.setText(""+officialReceipt.getPayment().getAmountReceived());
        jlblAmountChargedText.setText(""+officialReceipt.getPayment().getAmountCharged());
        jlblOrNoAttached.setText("Or No. "+officialReceipt.getPayment().getOrNoAttached());
        
        DefaultTableModel tableModel = (DefaultTableModel) jtblParticulars.getModel();
        tableModel.setRowCount(0);
        for(Particular p : officialReceipt.getPayment().getParticulars()){
            tableModel.addRow(new Object[]{p.getName(),p.getAmountPaid()});
        }
        for(Penalty penalty: penaltyDaoImpl.getPenaltyBy(officialReceipt.getPayment().getOrNoAttached())){
            Object[] rowData = {penalty.getPenaltyName(),penalty.getPenaltyAmount()};
            tableModel.addRow(rowData);
        }
        
        jtblParticulars.setModel(tableModel);
        
        jlblCashierName.setText(officialReceipt.getPayment().getCashier().getLastName()+", "+officialReceipt.getPayment().getCashier().getFirstName());
    }

    @Override
    public void initControllers() {
        jtblParticulars.getModel().addTableModelListener((TableModelEvent e) -> {
            if (jtblParticulars.getRowCount() > 0) {
                BigDecimal totalPaymentDue = new BigDecimal(BigInteger.ZERO).setScale(2, RoundingMode.HALF_UP);
                for(int i = 0; i<jtblParticulars.getRowCount(); i++){
                    BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(jtblParticulars.getValueAt(i, 1).toString().trim()));
                    totalPaymentDue = totalPaymentDue.add(amount);
                }
                jlblTotalPaymentDueText.setText("" + totalPaymentDue);
                BigDecimal change = officialReceipt.getPayment().getAmountReceived().subtract(totalPaymentDue);
                if(change.compareTo(BigDecimal.ZERO) == 0 || change.compareTo(BigDecimal.ZERO) == 1){
                    jlblChangeText.setText(""+officialReceipt.getPayment().getAmountReceived().subtract(totalPaymentDue));
                }else{
                    jlblChangeText.setText("0.00");
                }
                
            }
        });
        
        jmiPrintReceipt.addActionListener(new Controller_Print_JButton(jpnlReceiptContainer,PageFormat.LANDSCAPE));
    }

    @Override
    public void initDaoImpl() {
        studentDaoImpl = new StudentDaoImpl();
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        penaltyDaoImpl = new PenaltyDaoImpl();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jpnlReceiptContainer = new javax.swing.JPanel();
        jpnlHeader = new javax.swing.JPanel();
        jpnlLogo = new javax.swing.JPanel(){
            public void paintComponent(Graphics g){
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.8f));
            g2d.drawImage(schoolLogo, 0, 0, getWidth(), getHeight(), null);
            jpnlReceiptContainer.repaint();
        }
    };
    jPanel10 = new javax.swing.JPanel();
    jLabel5 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jpnlRegistrationNo = new javax.swing.JPanel();
    jlblOrNoAttached = new javax.swing.JLabel();
    jPanel2 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jpnlContainer = new javax.swing.JPanel();
    jPanel4 = new javax.swing.JPanel();
    jlblCashReceived = new javax.swing.JLabel();
    jlblAmountCharged = new javax.swing.JLabel();
    jlblChange = new javax.swing.JLabel();
    jlblCashReceivedText = new javax.swing.JLabel();
    jlblAmountChargedText = new javax.swing.JLabel();
    jlblChangeText = new javax.swing.JLabel();
    jpnlPaymentInformation = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jtblParticulars = new javax.swing.JTable();
    jLabel12 = new javax.swing.JLabel();
    jlblTotalPaymentDueText = new javax.swing.JLabel();
    jpnlStudentInformation = new javax.swing.JPanel();
    jlblStudentNoText = new javax.swing.JLabel();
    jlblName = new javax.swing.JLabel();
    jlblStudentNameText = new javax.swing.JLabel();
    jlblSchoolYear = new javax.swing.JLabel();
    jlblSchoolYearText = new javax.swing.JLabel();
    jlblDate = new javax.swing.JLabel();
    jlblDateOfPayment = new javax.swing.JLabel();
    jlblStudentNo = new javax.swing.JLabel();
    jlblGradeLevel = new javax.swing.JLabel();
    jlblGradeLevelText = new javax.swing.JLabel();
    jPanel5 = new javax.swing.JPanel();
    jlblReceivedByCashierName = new javax.swing.JLabel();
    jlblCashierName = new javax.swing.JLabel();
    jPanel3 = new javax.swing.JPanel();
    jLabel2 = new javax.swing.JLabel();
    jMenuBar1 = new javax.swing.JMenuBar();
    jMenu1 = new javax.swing.JMenu();
    jmiPrintReceipt = new javax.swing.JMenuItem();
    jMenuItem2 = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Official Receipt");
    setMaximumSize(new java.awt.Dimension(420, 650));
    setMinimumSize(new java.awt.Dimension(420, 500));
    setPreferredSize(new java.awt.Dimension(420, 500));
    getContentPane().setLayout(new java.awt.GridBagLayout());

    jpnlReceiptContainer.setBackground(new java.awt.Color(255, 255, 255));
    jpnlReceiptContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jpnlReceiptContainer.setMinimumSize(new java.awt.Dimension(500, 550));
    jpnlReceiptContainer.setPreferredSize(new java.awt.Dimension(500, 550));
    jpnlReceiptContainer.setLayout(new java.awt.GridBagLayout());

    jpnlHeader.setBackground(new java.awt.Color(255, 255, 255));
    jpnlHeader.setForeground(new java.awt.Color(51, 51, 51));
    jpnlHeader.setLayout(new java.awt.GridBagLayout());

    jpnlLogo.setBackground(new java.awt.Color(255, 255, 255));
    jpnlLogo.setMinimumSize(new java.awt.Dimension(70, 70));
    jpnlLogo.setPreferredSize(new java.awt.Dimension(70, 70));
    jpnlLogo.setLayout(new java.awt.GridBagLayout());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(3, 20, 3, 3);
    jpnlHeader.add(jpnlLogo, gridBagConstraints);

    jPanel10.setBackground(new java.awt.Color(255, 255, 255));
    jPanel10.setMinimumSize(new java.awt.Dimension(250, 57));
    jPanel10.setPreferredSize(new java.awt.Dimension(290, 18));
    jPanel10.setLayout(new java.awt.GridBagLayout());

    jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(0, 114, 188));
    jLabel5.setText("Mother of Perpetual Help School");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jPanel10.add(jLabel5, gridBagConstraints);

    jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jLabel7.setForeground(new java.awt.Color(51, 51, 51));
    jLabel7.setText("Iris Street Dahlia, West Fairview Quezon City");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
    jPanel10.add(jLabel7, gridBagConstraints);

    jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jLabel8.setForeground(new java.awt.Color(51, 51, 51));
    jLabel8.setText("1118 Metro Manila, Philippines");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.weighty = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
    jPanel10.add(jLabel8, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    jpnlHeader.add(jPanel10, gridBagConstraints);

    jpnlRegistrationNo.setBackground(new java.awt.Color(255, 255, 255));
    jpnlRegistrationNo.setLayout(new java.awt.GridBagLayout());

    jlblOrNoAttached.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jlblOrNoAttached.setForeground(new java.awt.Color(102, 51, 0));
    jlblOrNoAttached.setText("No.");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(10, 2, 2, 10);
    jpnlRegistrationNo.add(jlblOrNoAttached, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
    gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
    jpnlHeader.add(jpnlRegistrationNo, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 0.5;
    jpnlReceiptContainer.add(jpnlHeader, gridBagConstraints);

    jPanel2.setBackground(new java.awt.Color(255, 255, 255));
    jPanel2.setLayout(new java.awt.GridBagLayout());

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(0, 0, 0));
    jLabel1.setText("OFFICIAL RECEIPT");
    jPanel2.add(jLabel1, new java.awt.GridBagConstraints());

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlReceiptContainer.add(jPanel2, gridBagConstraints);

    jpnlContainer.setBackground(new java.awt.Color(255, 255, 255));
    jpnlContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
    jpnlContainer.setForeground(new java.awt.Color(0, 0, 0));
    jpnlContainer.setPreferredSize(new java.awt.Dimension(350, 496));
    jpnlContainer.setLayout(new java.awt.GridBagLayout());

    jPanel4.setBackground(new java.awt.Color(255, 255, 255));
    jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jPanel4.setLayout(new java.awt.GridBagLayout());

    jlblCashReceived.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblCashReceived.setForeground(new java.awt.Color(0, 0, 0));
    jlblCashReceived.setText("Cash Received");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 3);
    jPanel4.add(jlblCashReceived, gridBagConstraints);

    jlblAmountCharged.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblAmountCharged.setForeground(new java.awt.Color(0, 0, 0));
    jlblAmountCharged.setText("Amount Charged");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 3);
    jPanel4.add(jlblAmountCharged, gridBagConstraints);

    jlblChange.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblChange.setForeground(new java.awt.Color(0, 0, 0));
    jlblChange.setText("Change");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 3);
    jPanel4.add(jlblChange, gridBagConstraints);

    jlblCashReceivedText.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
    jlblCashReceivedText.setForeground(new java.awt.Color(0, 0, 0));
    jlblCashReceivedText.setText("CashReceivedText");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 10);
    jPanel4.add(jlblCashReceivedText, gridBagConstraints);

    jlblAmountChargedText.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
    jlblAmountChargedText.setForeground(new java.awt.Color(0, 0, 0));
    jlblAmountChargedText.setText("AmountChargedText");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 10);
    jPanel4.add(jlblAmountChargedText, gridBagConstraints);

    jlblChangeText.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
    jlblChangeText.setForeground(new java.awt.Color(0, 0, 0));
    jlblChangeText.setText("ChangeText");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 10);
    jPanel4.add(jlblChangeText, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlContainer.add(jPanel4, gridBagConstraints);

    jpnlPaymentInformation.setBackground(new java.awt.Color(255, 255, 255));
    jpnlPaymentInformation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jpnlPaymentInformation.setLayout(new java.awt.GridBagLayout());

    jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
    jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));
    jScrollPane1.setPreferredSize(new java.awt.Dimension(453, 400));

    jtblParticulars.setAutoCreateRowSorter(true);
    jtblParticulars.setBackground(new java.awt.Color(255, 255, 255));
    jtblParticulars.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
    jtblParticulars.setForeground(new java.awt.Color(0, 0, 0));
    jtblParticulars.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Particular", "Amount Charged"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jtblParticulars.setEnabled(false);
    jtblParticulars.setGridColor(new java.awt.Color(255, 255, 255));
    jtblParticulars.setShowHorizontalLines(false);
    jtblParticulars.setShowVerticalLines(false);
    jtblParticulars.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(jtblParticulars);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.weighty = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlPaymentInformation.add(jScrollPane1, gridBagConstraints);

    jLabel12.setBackground(new java.awt.Color(255, 255, 255));
    jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jLabel12.setForeground(new java.awt.Color(0, 0, 0));
    jLabel12.setText("Total Payment Due :");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 3);
    jpnlPaymentInformation.add(jLabel12, gridBagConstraints);

    jlblTotalPaymentDueText.setBackground(new java.awt.Color(255, 255, 255));
    jlblTotalPaymentDueText.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
    jlblTotalPaymentDueText.setForeground(new java.awt.Color(0, 0, 0));
    jlblTotalPaymentDueText.setText("TotalPaymentDueText");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 10);
    jpnlPaymentInformation.add(jlblTotalPaymentDueText, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weighty = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlContainer.add(jpnlPaymentInformation, gridBagConstraints);

    jpnlStudentInformation.setBackground(new java.awt.Color(255, 255, 255));
    jpnlStudentInformation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jpnlStudentInformation.setLayout(new java.awt.GridBagLayout());

    jlblStudentNoText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jlblStudentNoText.setForeground(new java.awt.Color(0, 0, 0));
    jlblStudentNoText.setText("Student No Text");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlStudentInformation.add(jlblStudentNoText, gridBagConstraints);

    jlblName.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblName.setForeground(new java.awt.Color(0, 0, 0));
    jlblName.setText("Name :");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 3);
    jpnlStudentInformation.add(jlblName, gridBagConstraints);

    jlblStudentNameText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jlblStudentNameText.setForeground(new java.awt.Color(0, 0, 0));
    jlblStudentNameText.setText("Name Text");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlStudentInformation.add(jlblStudentNameText, gridBagConstraints);

    jlblSchoolYear.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblSchoolYear.setForeground(new java.awt.Color(0, 0, 0));
    jlblSchoolYear.setText("School Year");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlStudentInformation.add(jlblSchoolYear, gridBagConstraints);

    jlblSchoolYearText.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblSchoolYearText.setForeground(new java.awt.Color(0, 0, 0));
    jlblSchoolYearText.setText("SchoolYearText");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlStudentInformation.add(jlblSchoolYearText, gridBagConstraints);

    jlblDate.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblDate.setForeground(new java.awt.Color(0, 0, 0));
    jlblDate.setText("Date");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 3);
    jpnlStudentInformation.add(jlblDate, gridBagConstraints);

    jlblDateOfPayment.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblDateOfPayment.setForeground(new java.awt.Color(0, 0, 0));
    jlblDateOfPayment.setText("DateOfPaymentText");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlStudentInformation.add(jlblDateOfPayment, gridBagConstraints);

    jlblStudentNo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblStudentNo.setForeground(new java.awt.Color(0, 0, 0));
    jlblStudentNo.setText("Student No :");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 3);
    jpnlStudentInformation.add(jlblStudentNo, gridBagConstraints);

    jlblGradeLevel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblGradeLevel.setForeground(new java.awt.Color(0, 0, 0));
    jlblGradeLevel.setText("Grade Level :");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlStudentInformation.add(jlblGradeLevel, gridBagConstraints);

    jlblGradeLevelText.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblGradeLevelText.setForeground(new java.awt.Color(0, 0, 0));
    jlblGradeLevelText.setText("jLabel3");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlStudentInformation.add(jlblGradeLevelText, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlContainer.add(jpnlStudentInformation, gridBagConstraints);

    jPanel5.setBackground(new java.awt.Color(255, 255, 255));
    jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jPanel5.setForeground(new java.awt.Color(0, 0, 0));
    jPanel5.setLayout(new java.awt.GridBagLayout());

    jlblReceivedByCashierName.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblReceivedByCashierName.setForeground(new java.awt.Color(0, 0, 0));
    jlblReceivedByCashierName.setText("Processed By :");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jPanel5.add(jlblReceivedByCashierName, gridBagConstraints);

    jlblCashierName.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jlblCashierName.setForeground(new java.awt.Color(0, 0, 0));
    jlblCashierName.setText("CashierName");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jPanel5.add(jlblCashierName, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlContainer.add(jPanel5, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.weighty = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jpnlReceiptContainer.add(jpnlContainer, gridBagConstraints);

    jPanel3.setBackground(new java.awt.Color(255, 255, 255));

    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(0, 114, 188));
    jLabel2.setText("Kindly count your change, check your name and amount paid before leaving the counter");
    jPanel3.add(jLabel2);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlReceiptContainer.add(jPanel3, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.weighty = 0.5;
    getContentPane().add(jpnlReceiptContainer, gridBagConstraints);

    jMenu1.setText("File");

    jmiPrintReceipt.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
    jmiPrintReceipt.setText("Print");
    jMenu1.add(jmiPrintReceipt);

    jMenuItem2.setText("Exit");
    jMenu1.add(jMenuItem2);

    jMenuBar1.add(jMenu1);

    setJMenuBar(jMenuBar1);

    pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlblAmountCharged;
    private javax.swing.JLabel jlblAmountChargedText;
    private javax.swing.JLabel jlblCashReceived;
    private javax.swing.JLabel jlblCashReceivedText;
    private javax.swing.JLabel jlblCashierName;
    private javax.swing.JLabel jlblChange;
    private javax.swing.JLabel jlblChangeText;
    private javax.swing.JLabel jlblDate;
    private javax.swing.JLabel jlblDateOfPayment;
    private javax.swing.JLabel jlblGradeLevel;
    private javax.swing.JLabel jlblGradeLevelText;
    private javax.swing.JLabel jlblName;
    private javax.swing.JLabel jlblOrNoAttached;
    private javax.swing.JLabel jlblReceivedByCashierName;
    private javax.swing.JLabel jlblSchoolYear;
    private javax.swing.JLabel jlblSchoolYearText;
    private javax.swing.JLabel jlblStudentNameText;
    private javax.swing.JLabel jlblStudentNo;
    private javax.swing.JLabel jlblStudentNoText;
    private javax.swing.JLabel jlblTotalPaymentDueText;
    private javax.swing.JMenuItem jmiPrintReceipt;
    private javax.swing.JPanel jpnlContainer;
    private javax.swing.JPanel jpnlHeader;
    private javax.swing.JPanel jpnlLogo;
    private javax.swing.JPanel jpnlPaymentInformation;
    private javax.swing.JPanel jpnlReceiptContainer;
    private javax.swing.JPanel jpnlRegistrationNo;
    private javax.swing.JPanel jpnlStudentInformation;
    private javax.swing.JTable jtblParticulars;
    // End of variables declaration//GEN-END:variables
}
