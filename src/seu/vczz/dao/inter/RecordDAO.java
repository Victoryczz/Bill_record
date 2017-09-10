package seu.vczz.dao.inter;

import seu.vczz.entity.Record;

import java.util.Date;
import java.util.List;

public interface RecordDAO {

	/**
	 *  添加表项
	 */
	public void add(Record record);
	/**
	 *  更新表
	 */
	public void update(Record record);
	/**
	 * 删除表项
	 */
	public void delete(int id);
	/**
	 * 通过ID获得表项
	 */
	public Record get(int id);
	/**
	 * 列出所有表项
	 */
	public List<Record> getAll();
	/**
	 * 列出从start到start+number的表项
	 */
	public List<Record> getPart(int start, int number);
	/**
	 * 通过日期或得
	 */
	public List<Record> getByDate(Date dateFrom, Date dataTo);
	/**
	 * 通过categoryId获得表项该类目的所有记录
	 */
	public List<Record> getByCategoryId(int cid);
	/**
	 * 获得表中表项的总数目
	 */
	public int getTotalNum();
	/**
	 * 获得今天的记录
	 */
	public List<Record> getToday();
	/**
	 * 获得当月的记录
	 */
	public List<Record> getMonth();

	public List<Record> getByDate(Date date);
	
	
}
