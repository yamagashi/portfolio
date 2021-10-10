package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Diary;
import model.User;


@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 日記リストをアプリケーションスコープから取得
		ServletContext application = this.getServletContext();
		List<Diary> diaryList =
				(List<Diary>) application.getAttribute("diaryList");
		//取得出来なかった場合は、日記リストを新規作成し
		//アプリケーションスコープに保存
		if(diaryList == null) {
			diaryList = new ArrayList<>();
			application.setAttribute("diaryList", diaryList);
		}

		//ログインしているか確認するため
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) { //ログインしていない場合
			//リダイレクト
			response.sendRedirect("/diary/");
		} else { //ログイン済みの場合
			//フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

}
