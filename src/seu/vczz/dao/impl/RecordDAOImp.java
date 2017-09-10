package seu.vczz.dao.impl;

import seu.vczz.dao.inter.RecordDAO;
import seu.vczz.entity.Record;
import seu.vczz.util.DBUtil;
import seu.vczz.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * record表的数据访问层
 */
public class RecordDAOImp implements RecordDAO{
    /**
     * 添加
     * @param record
     */
	@Override
	public void add(Record record) {
		String sql = "INSERT INTO record VALUES(NULL, ?, ?, ?, ?)";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, record.getSpend());
			ps.setInt(2, record.getCid());
			ps.setString(3, record.getComment());
			ps.setDate(4, DateUtil.util2sql(record.getDate()));//util的date和sql的date的转换
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				record.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    /**
     * 更新
     */
	@Override
	public void update(Record record) {
		String sql = "UPDATE record SET spend = ?, cid = ?, comment = ?, date = ?, WHERE id = ?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, record.getSpend());
			ps.setInt(2, record.getCid());
			ps.setString(3, record.getComment());
			ps.setDate(4, DateUtil.util2sql(record.getDate()));
			ps.setInt(5, record.getId());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

    /**
     * 删除
     * @param id
     */
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM record WHERE id = "+id;
		try {
			Connection con = DBUtil.getConnection();
			con.createStatement().execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

    /**
     * 根据id获得记录
     * @param id
     * @return
     */
	@Override
	public Record get(int id) {
		Record record = null;
		try {
			String sql = "SELECT * FROM record WHERE id = "+id;
			Connection con = DBUtil.getConnection();
			ResultSet rs = con.createStatement().executeQuery(sql);
			if (rs.next()){
				record = new Record();
				record.setId(id);
				record.setCid(rs.getInt("cid"));
				record.setComment(rs.getString("comment"));
				record.setDate(rs.getDate("date"));
				record.setSpend(rs.getInt("spend"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}

    /**
     * 获得表中所有的记录
     * @return
     */
	@Override
	public List<Record> getAll() {
		return getPart(0, Short.MAX_VALUE);
	}

    /**
     * 或得表中从start开始到start+number的所有记录
     * @param start
     * @param number
     * @return
     */
	@Override
	public List<Record> getPart(int start, int number) {
		List<Record> records = new ArrayList<Record>();
		try {
			String sql = "SELECT * FROM record ORDER BY id DESC LIMIT ?, ?";
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, start+number);

			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Record record = new Record();
				record.setId(rs.getInt("id"));
				record.setCid(rs.getInt("cid"));
				record.setComment(rs.getString("comment"));
				record.setDate(rs.getDate("date"));
				record.setSpend(rs.getInt("spend"));
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

    /**
     * 获得从日期dateFrom到日期dateTo的所有记录
     * @param dateFrom
     * @param dataTo
     * @return
     */
	@Override
	public List<Record> getByDate(Date dateFrom, Date dataTo) {
		List<Record> records = new ArrayList<Record>();

		try {
			String sql = "SELECT * FROM record ORDER BY id DESC WHERE date >=? and date <=? ";
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, DateUtil.util2sql(dateFrom) );
			ps.setDate(2, DateUtil.util2sql(dataTo));
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Record record = new Record();
				record.setId(rs.getInt("id"));
				record.setCid(rs.getInt("cid"));
				record.setComment(rs.getString("comment"));
				record.setDate(rs.getDate("date"));
				record.setSpend(rs.getInt("spend"));
				records.add(record);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

    /**
     * 获得某一分类下的所有记录
     * @param cid
     * @return
     */
	@Override
	public List<Record> getByCategoryId(int cid) {
		List<Record> list = new ArrayList<Record>();

		try {
			String sql = "SELECT * FROM record ORDER BY id DESC WHERE cid = "+cid;
			Connection con = DBUtil.getConnection();
			ResultSet rs = con.createStatement().executeQuery(sql);
			while (rs.next()){
				Record record = new Record();
				record.setId(rs.getInt("id"));
				record.setCid(rs.getInt("cid"));
				record.setComment(rs.getString("comment"));
				record.setDate(rs.getDate("date"));
				record.setSpend(rs.getInt("spend"));
				list.add(record);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

    /**
     * 或得记录总数
     * @return
     */
	@Override
	public int getTotalNum() {
		String sql = "SELECT COUNT(*) FROM record";
		int totalNum = 0;
		try {
			Connection con = DBUtil.getConnection();
			ResultSet rs = con.createStatement().executeQuery(sql);
			if (rs.next()){
				totalNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalNum;
	}

    /**
     * 获得今天的记录，重复造轮子，直接调用getByDate
     * @return
     */
	@Override
	public List<Record> getToday() {
		return getByDate(DateUtil.today());
	}

    /**
     * 或得本月
     * @return
     */
	@Override
	public List<Record> getMonth() {

		return getByDate(DateUtil.monthBegin(), DateUtil.monthEnd());
	}

    /**
     * 根据日期获得所有记录
     * @param date
     * @return
     */
    @Override
    public List<Record> getByDate(Date date) {
        List<Record> list = new ArrayList<Record>();

        try {
            String sql = "SELECT * FROM record ORDER BY id DESC WHERE date =? ";
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, DateUtil.util2sql(date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Record record = new Record();
                record.setId(rs.getInt("id"));
                record.setCid(rs.getInt("cid"));
                record.setComment(rs.getString("comment"));
                record.setDate(rs.getDate("date"));
                record.setSpend(rs.getInt("spend"));
                list.add(record);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}
