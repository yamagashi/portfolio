package model;

import dao.DiaryDAO;

public class PostDiaryLogic {
	public void execute(Diary diary) {
		DiaryDAO dao = new DiaryDAO();
		dao.create(diary);
	}

}
