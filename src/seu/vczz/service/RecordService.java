package seu.vczz.service;

import seu.vczz.dao.impl.RecordDAOImp;
import seu.vczz.dao.inter.RecordDAO;
import seu.vczz.entity.Category;
import seu.vczz.entity.Record;

import java.util.Date;

public class RecordService {
    RecordDAO recordDAO = new RecordDAOImp();

    /**
     * 增加一个条目，以消费额为基础，要选择条目，评语和时间，构成新的record，然后添加
     * @param spend
     * @param category
     * @param comment
     * @param date
     */
    public void add(int spend, Category category, String comment, Date date){
        Record record = new Record();
        record.setSpend(spend);
        record.setCid(category.getId());
        record.setComment(comment);
        record.setDate(date);
        recordDAO.add(record);
    }


}
