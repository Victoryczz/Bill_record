package seu.vczz.service;

import seu.vczz.dao.impl.CategoryDAOImp;
import seu.vczz.dao.impl.RecordDAOImp;
import seu.vczz.dao.inter.CategoryDAO;
import seu.vczz.dao.inter.RecordDAO;
import seu.vczz.entity.Category;
import seu.vczz.entity.Record;
import java.util.Collections;
import java.util.List;

/**
 * category的service层
 */
public class CategoryService {
    static CategoryDAO categoryDAO = new CategoryDAOImp();
    static RecordDAO recordDAO = new RecordDAOImp();

    /**
     * 获得所有的条目
     * @return
     */
    public List<Category> list(){
        //所有分类条目
        List<Category> categories = categoryDAO.getAll();
        for (Category c : categories){
            //每个条目下的所有记录
            List<Record> records = recordDAO.getByCategoryId(c.getId());
            //设置每个条目下记录的数目
            c.setRecordNum(records.size());
        }
        //神奇的lamda表达式，应该是从小到大
        Collections.sort(categories, (c1, c2) -> {
            return c2.getRecordNum() - c1.getRecordNum();
        });
        return categories;
    }

    /**
     * 添加
     * @param item
     */
    public void add(String item){
        Category category = new Category();
        category.setName(item);
        categoryDAO.add(category);
    }

    /**
     * 更新某个id对应的name,使用情形是获得条目之后，修改条目的名字，从而更新表中对应的记录
     * @param id
     * @param name
     */
    public void update(int id, String name){
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        categoryDAO.update(category);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(int id){
        categoryDAO.delete(id);
    }
}
