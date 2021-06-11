package jp.co.aforce.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.DAO.UpdateDAO;
import jp.co.aforce.beans.Members;
import jp.co.aforce.tool.Page;




@WebServlet(urlPatterns = {"/member/Update"})
public class Update extends HttpServlet {
	public void doPost(
			HttpServletRequest request, HttpServletResponse response
	)throws ServletException,IOException{


		PrintWriter out = response.getWriter();
		Page.header(out);

		//ボタンの判定
		if (request.getParameter("buttonName").equals("表示")) {
			try {
				request.setCharacterEncoding("UTF-8");
				String member_no = request.getParameter("member_no");

				UpdateDAO dao = new UpdateDAO();
				List<Members> list = dao.search(member_no);

				if (member_no != null) {

					for (Members members : list) {

						request.setAttribute("member_bean", members);
					}
				} else {
					out.println("該当する会員情報が見つかりません。");
				}

			} catch (Exception e) {
				e.printStackTrace(out);
			}
		}else {
			try {
				request.setCharacterEncoding("UTF-8");
				String member_no = request.getParameter("member_no");
				String name = request.getParameter("name");

				int age = Integer.parseInt(request.getParameter("age"));
			    int birth_year = Integer.parseInt(request.getParameter("birth_year"));
				int birth_month = Integer.parseInt(request.getParameter("birth_month"));
				int birth_day = Integer.parseInt(request.getParameter("birth_day"));

				Members members = new Members();
				members.setMember_no(member_no);
				members.setName(name);
				members.setAge(age);
				members.setBirth_year(birth_year);
				members.setBirth_month(birth_month);
				members.setBirth_day(birth_day);

				UpdateDAO dao = new UpdateDAO();
				int rs = dao.update(members);

				if (rs > 0) {
					out.println("変更に成功しました。");
				} else {
					out.println("変更に失敗しました。");
				}
			} catch (Exception e) {
				e.printStackTrace(out);
			}
			Page.footer(out);

		}
		request.getRequestDispatcher("../jsp/update.jsp").forward(request, response);

	}

}