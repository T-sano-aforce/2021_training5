package jp.co.aforce.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.DAO.DeleteDAO;
import jp.co.aforce.beans.Members;
import jp.co.aforce.tool.Page;

@WebServlet(urlPatterns = { "/member/Delete" })
public class Delete extends HttpServlet {

	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Page.header(out);

		//ボタンの判定
		if (request.getParameter("buttonName").equals("表示")) {
			try {
				request.setCharacterEncoding("UTF-8");
				String member_no = request.getParameter("member_no");

				DeleteDAO dao = new DeleteDAO();
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
		} else if(request.getParameter("buttonName").equals("削除")) {
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

				DeleteDAO dao = new DeleteDAO();
				int rs = dao.delete(members);

				if (rs > 0) {
					out.println("削除に成功しました。");
				} else {
					out.println("削除に失敗しました。");
				}
			} catch (Exception e) {
				e.printStackTrace(out);
			}
		}else if(request.getParameter("buttonName").equals("戻る")) {
			request.getRequestDispatcher("../jsp/menu.jsp").forward(request, response);

		}else {


	}

		Page.footer(out);
		request.getRequestDispatcher("../jsp/delete.jsp").forward(request, response);
	}

}
