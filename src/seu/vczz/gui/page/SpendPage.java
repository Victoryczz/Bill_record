package seu.vczz.gui.page;

/**
 * 花费页面
 */
public class SpendPage {
    //本月支出

    public String monthSpend;
    //今日支出
    public String todaySpend;
    //平均日消费
    public String avgSpendPerDay;
    //月剩余克指出
    public String monthAvailable;
    //日均可用
    public String dayAvgAvailable;
    //本月剩余天数
    public String monthLeftDay;
    //已使用比例
    public int usagePercentage;
    //是否超支
    public boolean isOverSpend;

    public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthAvailable, int dayAvgAvailable,
                     int monthLeftDay, int usagePercentage){
        this.monthSpend = "¥"+monthSpend;
        this.todaySpend = "¥"+todaySpend;
        this.avgSpendPerDay = "¥"+avgSpendPerDay;

        if (monthAvailable < 0)
            isOverSpend = true;

        if (!isOverSpend){
            this.dayAvgAvailable = "¥" + dayAvgAvailable;
            this.monthAvailable = "¥" + monthAvailable;
        }else {
            this.monthAvailable = "超支¥" + (0-monthAvailable);
            this.dayAvgAvailable = "¥0";
        }

        this.monthLeftDay = monthLeftDay + "天";
        this.usagePercentage = usagePercentage;


    }

}
