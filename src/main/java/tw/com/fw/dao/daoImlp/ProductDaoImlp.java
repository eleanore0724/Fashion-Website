package tw.com.fw.dao.daoImlp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tw.com.fw.model.Product;
import tw.com.fw.dao.ProductDao;
import tw.com.fw.databaseutils.DBUtils;

public class ProductDaoImlp implements ProductDao {
	
	private Connection conn = DBUtils.getDataBase().getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// 查詢
	@Override
	public List<Product> queryAll() {

		List<Product> list = new ArrayList<Product>();
		try {
			String querySQL = "SELECT * FROM product ORDER BY product_id;";
			ps = conn.prepareStatement(querySQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setSize(rs.getString("size"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getInt("price"));
				product.setStock(rs.getInt("stock"));
				product.setDescription(rs.getString("description"));
				product.setImage_url1(rs.getString("image_url1"));
				product.setImage_url2(rs.getString("image_url2"));
				product.setImage_url3(rs.getString("image_url3"));
				product.setProduct_url(rs.getString("product_url"));
				list.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 新增產品
	@Override
	public int addProduct(Product product) {
		String sql = "INSERT INTO products (product_name, size, category, description, price, stock,"
				+ " image_url1, image_url2, image_url3, product_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(addSQL);
			ps.setString(1, product.getProductName());
			ps.setString(2, product.getSize());
			ps.setString(3, product.getCategory());
			ps.setString(4, product.getDescription());
			ps.setInt(4, product.getPrice());
			ps.setInt(5, product.getStock());
			ps.setString(6, product.getImage_url1());
			ps.setString(7, product.getImage_url2());
			ps.setString(8, product.getImage_url3());
			ps.setString(9, product.getProduct_url());

			return ps.executeUpdate(); // 回傳影響筆數
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.getDataBase().close(ps);
			DBUtils.getDataBase().close(conn);
		}

	}

    
    @Override
    public Product getProductById(int id) {
        return null; // 之後會用 SELECT 查詢
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

	@Override
	public List<Product> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}
}