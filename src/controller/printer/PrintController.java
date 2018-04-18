package controller.printer;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 *
 * @author John Ferdinand Antonio
 */
public class PrintController implements ActionListener {

    private final Component component;

    public PrintController(Component component) {
        this.component = component;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        printComponent();
    }

    public void printComponent() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(" Print Component ");
        pj.setPrintable(new Printable() {
            @Override
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                component.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        if (pj.printDialog() == false) {
            return;
        }

        try {
            pj.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

}
