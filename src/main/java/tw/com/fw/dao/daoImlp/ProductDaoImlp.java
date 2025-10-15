package tw.com.fw.dao.daoImlp;

import java.sql.*;
import java.util.*;
import tw.com.fw.model.Product;
import tw.com.fw.dao.ProductDao;
import tw.com.fw.databaseutils.DBUtils;

public class ProductDaoImlp implements ProductDao {

/*	
    @Override
    public boolean addProduct(Product product) {
        // 這裡用 JDBC 連接資料庫，執行 INSERT INTO
        return false; // 先放 false，之後再寫實作
    }
*/
    @Override
    public Product getProductById(int id) {
        return null; // 之後會用 SELECT 查詢
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return new ArrayList<>();
    }
}