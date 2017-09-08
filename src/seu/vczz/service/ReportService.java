package seu.vczz.service;

import seu.vczz.dao.impl.RecordDAOImp;
import seu.vczz.dao.inter.RecordDAO;
import seu.vczz.entity.Record;
import seu.vczz.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportService {
    /**
     * 某天的消费金额
     * @param date
     * @param monthRawData
     * @return
     */
    public int getDaySpend(Date date, List<Record> monthRawData){
        int daySpend = 0;
        for (Record record : monthRawData){
            if (record.getDate().equals(date)){
                daySpend += record.getSpend();
            }
        }
        return daySpend;
    }

    /**
     * 一个月的消费集合，分天来累计而不是按照记录累计
     * @return
     */
    public List<Record> listThisMonthRecords(){
        RecordDAO recordDAO = new RecordDAOImp();
        List<Record> monthRawData = recordDAO.getMonth();
        List<Record> result = new ArrayList<Record>();
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDays = DateUtil.thisMonthTotalDay();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < monthTotalDays; i++){
            Record record = new Record();
            calendar.setTime(monthBegin);
            calendar.add(Calendar.DATE, i);
            Date eachDayofThisMonth = calendar.getTime();
            int daySpend = getDaySpend(eachDayofThisMonth, monthRawData);
            record.setSpend(daySpend);
            result.add(record);
        }
        return result;
    }

}
