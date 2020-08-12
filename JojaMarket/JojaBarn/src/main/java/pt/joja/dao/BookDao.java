package pt.joja.dao;

import org.springframework.stereotype.Repository;
import pt.joja.bean.Book;

@Repository
public class BookDao extends BaseDao<Book>{

    @Override
    public void save() {
        System.out.println("图书保存了！");
    }

    @Override
    public String toString() {
        return "BookDao{}";
    }
}
