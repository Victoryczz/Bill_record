package seu.vczz.service;

import seu.vczz.dao.impl.RecordDAOImp;
import seu.vczz.dao.inter.RecordDAO;
import seu.vczz.entity.Record;
import seu.vczz.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 报表的service层
 */
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
        //本月的记录
        List<Record> monthRawData = recordDAO.getMonth();

        List<Record> result = new ArrayList<Record>();
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDays = DateUtil.thisMonthTotalDay();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < monthTotalDays; i++){
            Record record = new Record();
            //日期设置为本月开始
            calendar.setTime(monthBegin);
            //日期每次+1，也就是遍历该月，取每一天
            calendar.add(Calendar.DATE, i);
            //获得当天的日期
            Date eachDayofThisMonth = calendar.getTime();
            //获得每天的消费总额
            int daySpend = getDaySpend(eachDayofThisMonth, monthRawData);
            record.setSpend(daySpend);
            result.add(record);
            //此函数写得相当麻烦，每次取一天都要遍历一遍该月的记录，直接用getByDate接口即可
        }
        return result;
    }

}
