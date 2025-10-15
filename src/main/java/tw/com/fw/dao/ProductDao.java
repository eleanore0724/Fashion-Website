package tw.com.fw.dao;

import java.util.List;
import tw.com.fw.model.Product;

public interface ProductDao {
    
    // 新增產品
    boolean addProduct(Product product);
    
    // 根據 ID 查詢產品
    Product getProductById(int ProductId);
    
    // 查詢所有產品
    List<Product> getAllProducts();
    
    // 更新產品資訊
    boolean updateProduct(Product product);
    
    // 刪除產品
    boolean deleteProduct(int id);
    
    // 依照名稱或關鍵字查詢
    List<Product> searchProducts(String keyword);
    
}