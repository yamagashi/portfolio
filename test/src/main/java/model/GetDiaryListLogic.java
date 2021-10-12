package model;

import java.util.List;

import dao.DiaryDAO;

public class GetDiaryListLogic {
	public List<Diary>execute() {
		DiaryDAO dao = new DiaryDAO();
		List<Diary>diaryList = dao.findAll();
		return diaryList;
	}

}
