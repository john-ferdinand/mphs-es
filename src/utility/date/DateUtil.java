/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author Jordan
 */
public class DateUtil {
    
    public Date setTimeToMidnightOf(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public Date toUtilDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String[] datePieces = date.trim().split("-");
        String year = datePieces[0];
        String month = datePieces[1];
        String day = datePieces[2];
        StringBuilder sb = new StringBuilder();
        sb.append(year).append("-");
        sb.append(month).append("-");
        sb.append(day);
        Date d = df.parse(sb.toString());
        return d;
    }
    
    public LocalDate toLocalDate(Date utilDate){
        LocalDate localDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }
    
    public java.sql.Date toSqlDate(Date utilDate) {
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    public Calendar toCalendarDate(Date date) {
        Calendar calDate = Calendar.getInstance();
        calDate.setTime(date);
        return calDate;
    }
}
