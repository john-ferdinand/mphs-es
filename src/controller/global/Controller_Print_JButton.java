package controller.global;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;

/**
 *
 * @author Jordan
 */
public class Controller_Print_JButton implements ActionListener {

    private final JPanel panelToPrint;
    private final int pageFormatOrientation;
    
    public Controller_Print_JButton(JPanel panelToPrint,int pageFormatOrientation) {
        this.panelToPrint = panelToPrint;
        this.pageFormatOrientation = pageFormatOrientation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        printContentsOf(panelToPrint);
    }

    public void printContentsOf(JPanel panel) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(" Print from Enrollment System - Jordan ");
        if (pj.printDialog()) {
            PageFormat pf = pj.defaultPage();
            Paper paper = pf.getPaper();
            double width = 8.5d * 72d;
            double height = 11d * 72d;
            double margin = 0.25d * 72d;
            paper.setSize(width, height);
            paper.setImageableArea(margin, margin, width - (margin * 2), height - (margin * 2));
//            pf.setOrientation(PageFormat.LANDSCAPE); //0
//            pf.setOrientation(PageFormat.PORTRAIT); //1
            pf.setOrientation(pageFormatOrientation);

            pf.setPaper(paper);
            Book pBook = new Book();
            pBook.append(new Page(), pf);
            pj.setPageable(pBook);
        }

//        pj.setPrintable();
        if (pj.printDialog() == false) {
            return;
        }

        try {
            pj.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    public class Page implements Printable {
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex >= 1) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
            double width = pageFormat.getImageableWidth();
            double height = pageFormat.getImageableHeight();
//            g2d.drawRect(0, 0, (int) pageFormat.getImageableWidth() - 1, (int) pageFormat.getImageableHeight() - 1);
            FontMetrics fm = g2d.getFontMetrics();
//            String text = "top";
//            g2d.drawString(text, 0, fm.getAscent());
//            text = "bottom";
//            double x = width - fm.stringWidth(text);
//            double y = (height - fm.getHeight()) + fm.getAscent();
//            g2d.drawString(text, (int) x, (int) y);

            panelToPrint.paint(g2d);
            
            return Printable.PAGE_EXISTS;
        }

        protected String dump(Paper paper) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(paper.getWidth()).append("x").append(paper.getHeight())
                    .append("/").append(paper.getImageableX()).append("x").
                    append(paper.getImageableY()).append(" - ").append(paper
                    .getImageableWidth()).append("x").append(paper.getImageableHeight());
            return sb.toString();
        }

        protected String dump(PageFormat pf) {
            Paper paper = pf.getPaper();
            return dump(paper);
        }

    }

}
