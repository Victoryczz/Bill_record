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

public class RecordDAOImp implements RecordDAO{

	@Override
	public void add(Record record) {
		String sql = "INSERT INTO record VALUE(NULL, ?, ?, ?, ?)";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, record.getSpend());
			ps.setInt(2, record.getCid());
			ps.setString(3, record.getComment());
			ps.setDate(4, DateUtil.util2sql(record.getDate()));//待更新  DateUtil.util2sql(record.getDate())
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				record.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Record record) {
		String sql = "UPDATE record SET spend = ?, cid = ?, comment = ?, date = ?, WHERE id = ?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, record.getSpend());
			ps.setInt(2, record.getCid());
			ps.setString(3, record.getComment());
			ps.setDate(4, (java.sql.Date) record.getDate());
			ps.setInt(5, record.getId());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

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

	@Override
	public List<Record> getAll() {
		return getPart(0, Short.MAX_VALUE);
	}

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

	@Override
	public List<Record> getToday() {
		List<Record> list = new ArrayList<Record>();

		try {
			String sql = "SELECT * FROM record ORDER BY id DESC WHERE date =? ";
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, DateUtil.util2sql(DateUtil.today()));
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

	@Override
	public List<Record> getMonth() {

		return getByDate(DateUtil.monthBegin(), DateUtil.monthEnd());
	}

	
}
