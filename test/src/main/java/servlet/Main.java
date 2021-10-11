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
import model.PostDiaryLogic;
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

		@SuppressWarnings("unchecked")
		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			//リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");
			String text = request.getParameter("text");

			//入力値のチェック
			if(text != null && text.length() != 0) {
			//アプリケーションスコープに保存されたリストを取得
				ServletContext application = this.getServletContext();
				List<Diary> diaryList =
						(List<Diary>) application.getAttribute("diaryList");

			//セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			//日記リストに追加
			Diary diary = new Diary(loginUser.getName(),text);
			PostDiaryLogic postDiaryLogic = new PostDiaryLogic();
			postDiaryLogic.execute(diary, diaryList);

			//アプリケーションスコープに保存
			application.setAttribute("diaryList", diaryList);

			} else {
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg","日記が投稿されていません");

		}

		//メイン画面にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);

       }
    }
