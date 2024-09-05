package javaweb;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class javaweb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// JDBC 驱动名及数据库 URL
	private static final String JDBC_DRIVER = "org.h2.Driver";
	private static final String DB_URL = "jdbc:h2:tcp://localhost/~/Downloads/h2-2024-08-11/h2/bin/java-course"; // H2
																													// 数据库
																													// URL
	private static final String USER = "sa"; // 默认用户名
	private static final String PASS = "1234"; // 默认密码

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		try {
			
			 // 載入 H2 JDBC 驅動
            Class.forName("org.h2.Driver");
			PrintWriter out = response.getWriter();
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			// 查询数据
			String sql = "SELECT * FROM users";
			ResultSet rs = stmt.executeQuery(sql);

			// 输出 HTML 内容
			out.println("<html>");
			out.println("<head><title>Users Table</title></head>");
			out.println("<body>");
			out.println("<h1>Users Table</h1>");
			out.println("<table border='1'>");
			out.println("<tr><th>ID</th><th>Name</th><th>Email</th></tr>");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				out.println("<tr>");
				out.println("<td>" + id + "</td>");
				out.println("<td>" + name + "</td>");
				out.println("<td>" + email + "</td>");
				out.println("</tr>");
			}

			out.println("</table>");
			out.println("</body>");
			out.println("</html>");

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Database error: " + e.getMessage());
		}
	}
}
