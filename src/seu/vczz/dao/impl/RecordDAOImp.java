package seu.vczz.dao.impl;

import seu.vczz.dao.inter.RecordDAO;
import seu.vczz.entity.Record;
import seu.vczz.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			ps.setDate(4, (java.sql.Date) record.getDate());//待更新  DateUtil.util2sql(record.getDate())
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Record get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getPart(int start, int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getByDate(Date dateFrom, Date dataTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getByCategoryId(int cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Record> getToday() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
