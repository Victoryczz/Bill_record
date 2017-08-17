package seu.vczz.dao.inter;

import java.util.List;

import seu.vczz.entity.Category;

public interface CategoryDAO {

	/**
	 *  添加表项
	 */
	public void add(Category category);
	/**
	 *  更新表
	 */
	public void update(Category category);
	/**
	 * 删除表项
	 */
	public void delete(int id);
	/**
	 * 列出所有表项
	 */
	public List<Category> getAll();
	/**
	 * 列出从start到start+number的表项
	 */
	public List<Category> getPart(int start, int number);
	/**
	 * 通过ID获得表项
	 */
	public Category get(int id);
	/**
	 * 获得表中表项的总数目
	 */
	public int getTotalNum();
}
