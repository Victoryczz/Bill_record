package seu.vczz.service;

import seu.vczz.dao.impl.RecordDAOImp;
import seu.vczz.dao.inter.RecordDAO;
import seu.vczz.entity.Record;
import seu.vczz.gui.page.SpendPage;
import seu.vczz.util.DateUtil;

import java.util.List;

/**
 * 应该是预览消费窗口使用的service层
 */
public class SpendService {
    /**
     * 获得报表的page页面
     * @return
     */
    public SpendPage getSpendPage(){
        RecordDAO recordDAO = new RecordDAOImp();
        //月记录
        List<Record> thisMonthRecords = recordDAO.getMonth();
        //日记录
        List<Record> todayRecord = recordDAO.getToday();
        //本月总天数
        int thisMonthDays = DateUtil.thisMonthTotalDay();

        int monthSpend = 0;
        int todaySpend = 0;
        int avgSpendPerDay = 0;
        int monthAvailable = 0;
        int dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;
        //月预算
        int monthBudget = new ConfigService().getBudget();

        for (Record record : thisMonthRecords){
            monthSpend += record.getSpend();
        }
        for (Record record : todayRecord){
            todaySpend += record.getSpend();
        }
        //日平均，但是貌似不对，直接算的是已消费除以总天数而不是已消费天数
        //avgSpendPerDay = monthSpend/thisMonthDays;
        avgSpendPerDay = monthSpend/(thisMonthDays-DateUtil.thisMonthLeftDay()+1);
        //月剩余
        monthAvailable = monthBudget-monthSpend;
        //月剩余天数
        monthLeftDay = DateUtil.thisMonthLeftDay();
        //平均日剩余可用
        dayAvgAvailable = monthAvailable/monthLeftDay;
        //已用比例
        usagePercentage = monthSpend*100 / monthBudget;

        return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, monthAvailable, dayAvgAvailable, monthLeftDay,usagePercentage);
    }




}
