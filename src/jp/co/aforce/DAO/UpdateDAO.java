package jp.co.aforce.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Members;


	public class UpdateDAO extends DAO{
		public List<Members>search(String member_no)throws Exception{
			List<Members>list = new ArrayList<>();

			Connection con = getConnection();


			PreparedStatement st = con.prepareStatement("select * from members where member_no like?");
			st.setString(1,member_no);
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				Members members = new Members();
				members.setMember_no(rs.getString("member_no"));
				members.setName(rs.getString("name"));
				members.setAge(rs.getInt("age"));
				members.setBirth_year(rs.getInt("birth_year"));
				members.setBirth_month(rs.getInt("birth_month"));
				members.setBirth_day(rs.getInt("birth_day"));
				list.add(members);
		}

			st.close();
			con.close();
			return list;
		}

		public int update(Members members) throws Exception{
			Connection con = getConnection();

			PreparedStatement st = con.prepareStatement("update members set  ");
			st.setString(1,members.getMember_no());
			st.setString(2,members.getName());
			st.setInt(3, members.getAge());
			st.setInt(4,members.getBirth_year());
			st.setInt(5, members.getBirth_month());
			st.setInt(6, members.getBirth_day());
			int line = st.executeUpdate();

			st.close();
			con.close();
			return line;
		}
	}





