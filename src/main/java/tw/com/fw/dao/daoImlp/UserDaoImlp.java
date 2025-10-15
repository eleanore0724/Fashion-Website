package tw.com.fw.dao.daoImlp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tw.com.fw.dao.UserDao;
import tw.com.fw.model.User;

public class UserDaoImlp implements UserDao{
	private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
	
    
    public UserDaoImlp() {
		super();
	}

	public UserDaoImlp(Connection conn) {
    	this.conn=conn;
    }
    
	@Override
	public User userLogin(String email, String password) {
		User user=null;
		String query="select * from users where email=? and password=?";
		 try {
	            ps=this.conn.prepareStatement(query);
	            ps.setString(1, email);
	            ps.setString(2, password);
	            rs=ps.executeQuery();
	            
	            if(rs.next()) {
	                user=new User();
	                user.setId(rs.getInt("id"));
	                user.setName(rs.getString("name"));
	                user.setEmail(rs.getString("email"));
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return user;
	    }

}
