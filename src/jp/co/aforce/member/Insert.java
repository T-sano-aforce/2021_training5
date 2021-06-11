package jp.co.aforce.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.DAO.InsertDAO;
import jp.co.aforce.beans.Members;
import jp.co.aforce.tool.Page;





@WebServlet(urlPatterns = {"/jp.co.aforce.member/Insert"})
public class Insert extends HttpServlet {

	public void doPost(
			HttpServletRequest request, HttpServletResponse response
	)throws ServletException,IOException{
		PrintWriter out = response.getWriter();
		Page.header(out);

		try {
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");


			if((name.length()!=0)

					&& request.getParameter("age") != ""
					&& request.getParameter("birth_year") !=""
					&& request.getParameter("birth_month")!=""
					&& request.getParameter("birth_day")!=""){

				int age = Integer.parseInt(request.getParameter("age"));
				int birth_year = Integer.parseInt(request.getParameter("birth_year"));
				int birth_month = Integer.parseInt(request.getParameter("birth_month"));
				int birth_day = Integer.parseInt(request.getParameter("birth_day"));

			Members members = new Members();
			members.setName(name);
			members.setAge(age);
			members.setBirth_year(birth_year);
			members.setBirth_month(birth_month);
			members.setBirth_day(birth_day);

			InsertDAO dao = new InsertDAO();
			int rs = dao.insert(members);


			if(rs > 0) {
				out.println("登録に成功しました。");
			}else {
				out.println("登録に失敗しました。");
			}
		  }else {
			  out.println("入力されていない項目があります");
		  }
		}catch(Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
  }

}