package seu.vczz.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    static long millisecondsofOneDay = 1000*60*60*24;

    public static java.sql.Date util2sql(Date date){
        return (java.sql.Date) date;
    }

    public static Date today(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());//背后是调了setMIlliSeconds(date.getTime()),应该就是设置了日期，没有具体时间
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();

    }


    public static Date monthBegin() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DATE, 1);//设置代表日期为1号

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }


    public static Date monthEnd() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        c.set(Calendar.DATE, 1);//先设成本月1号
        //c.add(Calendar.MONDAY, 1);
        //这句去掉了还不行妈的，看来是转到了下个月的一号，但是是为什么，草 原来是MONTH，而恰好MONTH和MONDAY都是2
        //c.add(Calendar.MONDAY, 1);
        c.add(Calendar.MONTH, 1);
        return c.getTime();
    }


    public static int thisMonthTotalDay(){

        long lastDayMilliSeconds = monthEnd().getTime();
        long firstDayMilliSeconds = monthBegin().getTime();

        return (int) ((lastDayMilliSeconds - firstDayMilliSeconds) / millisecondsofOneDay) + 1;
    }

    public static int thisMonthLeftDay() {
        long lastDayMilliSeconds = monthEnd().getTime();
        long toDayMilliSeconds = today().getTime();
        return (int) ((lastDayMilliSeconds - toDayMilliSeconds) / millisecondsofOneDay) + 1;
    }

    public static void main(String[] args) {
        //System.out.println(new Date());
        //System.out.println(DateUtil.today());
        System.out.println(DateUtil.monthBegin());
        System.out.println(DateUtil.monthEnd());
        System.out.println(thisMonthLeftDay());
        System.out.println(thisMonthTotalDay());

    }

}
