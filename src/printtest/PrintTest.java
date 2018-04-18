/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printtest;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JTable;

/**
 *
 * @author John Ferdinand Antonio
 */
public class PrintTest implements ActionListener{

    private final JTable jTable;
    String header;
    
    public PrintTest(JTable jTable, String headerMessage){
        this.jTable = jTable;
        this.header = headerMessage;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        print();
    }
    
    void print(){
        try {
            MessageFormat h = new MessageFormat(this.header);
            MessageFormat f = new MessageFormat("Page - {0}");
            PrintRequestAttributeSet printReqAttr = new HashPrintRequestAttributeSet();
            printReqAttr.add(OrientationRequested.LANDSCAPE);
            boolean complete = jTable.print(JTable.PrintMode.FIT_WIDTH, h, f, true, printReqAttr, false);
            if(complete){
                System.out.println("Print complete.");
            }else{
                System.out.println("Printing cancelled");
            }
        } catch (PrinterException ex) {
            ex.printStackTrace();
            Logger.getLogger(PrintTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
