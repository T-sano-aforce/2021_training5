package jp.co.aforce.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.co.aforce.beans.Members;


public class InsertDAO extends DAO {

		public int insert(Members members)throws Exception{
			Connection con = getConnection();

			Date date = new Date();
			//SimpleDateFormatクラスでフォーマットパターンを設定する
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			String member_no = "A" + sdf.format(date);

			PreparedStatement st = con.prepareStatement("insert into members values (?,?,?,?,?,?)");
			st.setString(1,member_no);
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

