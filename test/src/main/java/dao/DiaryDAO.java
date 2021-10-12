package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Diary;

public class DiaryDAO {

	//データベース接続に使用する情報
	private final String JDBC_URL =
			"jdbc:mariadb:tcp://localhost/~/test";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public List<Diary> findAll() {
		List<Diary> diaryList = new ArrayList<>();

		//データベースに接続
		try(Connection conn =
				DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {

			//SELECT文の準備
			String sql =
					"SELECT ID,NAME,TEXT FROM DIARY ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SERECTを実行
			ResultSet rs = pStmt.executeQuery(sql);

			//SERECTの結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				Diary diary = new Diary(id,userName,text);
				diaryList.add(diary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return diaryList;
	}

	public boolean create(Diary diary) {
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {

			//INSERT分の準備（IDは連番なのでしてしなくてよい）
			String sql = "INSERT INTO DIARY(NAME,TEXT) VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1, diary.getUserName());
			pStmt.setString(2,diary.getText());

			//INSERT文を実行（RESULTには追加された行数が代入される）
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		}
	}

