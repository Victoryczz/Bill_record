package seu.vczz.dao.inter;

import java.util.List;

import seu.vczz.entity.Config;

public interface ConfigDAO {
	/**
	 * @param config  添加配置
	 */
	public void add(Config config);
	/**
	 * @param 
	 */
	public void update(Config config);
	/**
	 * @param 
	 */
	public void delete(Config config);
	/**
	 * @param 
	 */
	public void delete(int id);
	/**
	 * @param 
	 */
	public List<Config> getAll();
	/**
	 * @param 
	 */
	public List<Config> getPart(int start, int number);
	/**
	 * @param 
	 */
	public Config get(int id);
	/**
	 * @param 
	 */
	public Config getByKey(String key);
	/**
	 * @param 
	 */
	public int getTotalNum();
	
}
