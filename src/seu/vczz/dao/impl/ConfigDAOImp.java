package seu.vczz.dao.impl;

import seu.vczz.dao.inter.ConfigDAO;
import seu.vczz.entity.Config;
import seu.vczz.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigDAOImp implements ConfigDAO{
	/**
	 * @param config  添加配置
	 */
	public void add(Config config) {
		String sql = "INSERT INTO config VALUE(NULL, ?, ?)";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, config.getKey());
			ps.setString(2, config.getValue());
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				config.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	/**
	 * 更新表
	 * @param config
	 */
	public void update(Config config) {
		//更新特定id的表的key和value吧，更新的是已经存在的配置
		String sql = "UPDATE config SET key_ = ?, value = ?, WHERE id = ?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, config.getKey());
			ps.setString(2, config.getValue());
			ps.setInt(3,config.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据id删除表项
	 * @param id
	 */
	public void delete(int id) {
		String sql = "DELETE FROM config WHERE id =" + id;
		try {
			Connection con = DBUtil.getConnection();
			Statement s = con.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Config> getAll() {
		return getPart(0, Short.MAX_VALUE);
	}

	/**
	 * 得到一部分表项
	 * @param start
	 * @param number
	 * @return
	 */
	public List<Config> getPart(int start, int number) {
		List<Config> list = new ArrayList<Config>();
		String sql = "SELECT * FROM config ORDER BY id DESC LIMIT ?, ?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,start);
			ps.setInt(2,start+number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Config config = new Config();
				config.setId(rs.getInt(1));
				config.setKey(rs.getString("key_"));//还可以这么操作
				config.setValue(rs.getString("value"));
				list.add(config);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据ID
	 * @param id
	 * @return
	 */
	public Config get(int id) {
		Config config = new Config();
		String sql = "SELECT * FROM config WHERE id ="+ id;
		try {
			Connection con = DBUtil.getConnection();
			ResultSet rs = con.createStatement().executeQuery(sql);
			if (rs.next()){
				config.setId(id);
				config.setKey(rs.getString("key_"));
				config.setValue(rs.getString("value"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return config;
	}

	/**
	 * 根据key
	 * @param key
	 * @return
	 */
	public Config getByKey(String key) {
		Config config = null;
		try {
			String sql = "SELECT * FROM config WHERE key_ = ?";
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				config = new Config();
				config.setId(rs.getInt(1));
				config.setKey(key);
				config.setValue(rs.getString("value"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return config;
	}

	/**
	 * 总数
	 * @return
	 */
	public int getTotalNum() {
		int totalNum = 0;
		try {
			Connection con = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) FROM config";
			ResultSet rs = con.createStatement().executeQuery(sql);
			while (rs.next()){
				totalNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalNum;
	}

	/**
	 * 不需要实现，已经重复造轮子了，可以直接delete（config.getId()）
	 * @param config
	 */
	@Override
	public void delete(Config config) {
		delete(config.getId());
	}

	

}
