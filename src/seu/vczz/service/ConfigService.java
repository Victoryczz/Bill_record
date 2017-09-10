package seu.vczz.service;


import seu.vczz.dao.impl.ConfigDAOImp;
import seu.vczz.dao.inter.ConfigDAO;
import seu.vczz.entity.Config;

/**
 * config的service层
 */
public class ConfigService {
    static ConfigDAO configDAO = new ConfigDAOImp();

    public static final String budget = "budget";
    public static final String mysqlPath = "mysqlPath";
    public static final String defaultBudget = "1000";

    static {
        init();
    }
    //最开始的时候先初始化这些默认的，这样的话就不能多次new ConfigService了????static????
    public static void init(){
        init(budget, defaultBudget);
        init(mysqlPath, "");
    }

    public static void init(String key_, String value){
        Config config = configDAO.getByKey(key_);
        if (config == null){
            config = new Config();
            config.setKey(key_);
            config.setValue(value);
            configDAO.add(config);
        }
    }

    /**
     * 获得value
     * @param key_
     * @return
     */
    public String get(String key_){
        Config config = configDAO.getByKey(key_);
        return config.getValue();
    }

    /**
     * 更新
     * @param key_
     * @param value
     */
    public void update(String key_, String value){
        Config config = configDAO.getByKey(key_);
        config.setValue(value);
        configDAO.update(config);
    }

    /**
     * 获得预算的数值
     * @return
     */
    public int getBudget(){
        return Integer.parseInt(get(budget));
    }

}
