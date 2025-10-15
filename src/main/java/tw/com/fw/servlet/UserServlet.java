package tw.com.fw.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.com.fw.dao.UserDao;
import tw.com.fw.dao.daoImlp.UserDaoImlp;
import tw.com.fw.model.User;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
	        try(PrintWriter out=response.getWriter()){
	            UserDao dao=new UserDaoImlp();
	            String email=request.getParameter("email");
	            String password=request.getParameter("password");
	            User user=dao.userLogin(email, password);
	            if(user != null) {
	                out.print("user login");
	            }else {
	                out.print("user login failed !");
	                
	            }
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	}

}
