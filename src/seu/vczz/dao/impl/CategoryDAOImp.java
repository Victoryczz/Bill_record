package seu.vczz.dao.impl;

import seu.vczz.dao.inter.CategoryDAO;
import seu.vczz.entity.Category;
import seu.vczz.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * category表的数据访问层
 */
public class CategoryDAOImp implements CategoryDAO{
	/**
	 * 增加一个条目（类别），一般增加的条目只有名字，所以参数只有string，传进去之后再获得该条目的ID
	 * @param category
	 */
	@Override
	public void add(Category category) {
		String sql = "INSERT INTO category VALUES(NULL, ?)";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,category.getName());
			ps.execute();
			//返回的是执行这次操作自动增加的key
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()){
				int id = rs.getInt(1);
				category.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新表，更新固定位置的条目信息
	 * @param category
	 */
	@Override
	public void update(Category category) {
		String sql = "UPDATE category SET name = ? WHERE id = ?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category.getName());
			ps.setInt(2, category.getId());

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过ID删除条目，反正是配合其他一起使用
	 * @param id
	 */
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM category WHERE id = " + id;
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得所有的条目
	 * @return
	 */
	@Override
	public List<Category> getAll() {
		return getPart(0, Short.MAX_VALUE);
	}

	/**
	 * 获得要求从指定位置开始共指定数量的条目
	 * @param start
	 * @param number
	 * @return
	 */
	@Override
	public List<Category> getPart(int start, int number) {
		List<Category> list = new ArrayList<Category>();

		String sql = "SELECT * FROM category ORDER BY id DESC LIMIT ?,?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,start);
			ps.setInt(2,start+number);
			//方法真是多种多样
			ResultSet rs = ps.executeQuery();
			//每次rs.next之后都指向下一行数据，rs.get 获得某一列的值
			while (rs.next()){
				Category category = new Category();
				category.setId(rs.getInt(1));
				category.setName(rs.getString(2));
				list.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 由id获得条目
	 * @param id
	 * @return
	 */
	@Override
	public Category get(int id) {
		String sql = "SELECT * FROM category WHERE id = "+id;
		Category category = new Category();
		try {
			Connection con = DBUtil.getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()){
				category.setName(rs.getString(2));
				category.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	/**
	 * 获得条目的总数
	 * @return
	 */
	@Override
	public int getTotalNum() {
		int totalNum = 0;
		//还能这样,对所有的条目数目计数，执行过后应该返回的是一条数据
		String sql = "SELECT COUNT(*) FROM category";
		try {
			Connection con = DBUtil.getConnection();
			ResultSet rs = con.createStatement().executeQuery(sql);
			while (rs.next()){
				totalNum = rs.getInt(1);
			}
			System.out.println("totalNum: "+totalNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalNum;
	}

}
