package pt.joja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pt.joja.dao.BookDao;

@Service
public class BookService {

    @Autowired
    BookDao bookDao;

    // @Transactional(timeout=3)
    // @Transactional(readOnly = true)
    // @Transactional(noRollbackFor = {ArithmeticException.class, NullPointerException.class})
    // @Transactional(rollbackFor = { java.io.IOException.class })
    // @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Transactional(propagation = Propagation.REQUIRED)
    public void checkout(String username, String isbn, int amount) {

        // 1. 减库存
        bookDao.updateStock(isbn, amount);

        // 2. 减余额
        int price = bookDao.getPrice(isbn);
        int totalPrice = price * amount;
        bookDao.updateBalance(username, totalPrice);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updatePrice(String isbn, int price) {
        bookDao.updatePrice(isbn, price);
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public int getPrice() {
        int price = bookDao.getPrice("ISBN-002");
        return price;
    }
}
