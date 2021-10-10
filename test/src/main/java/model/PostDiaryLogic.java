package model;

import java.util.List;

public class PostDiaryLogic {
	public void execute(Diary diary, List<Diary>diaryList) {
		diaryList.add(0,diary);  //先頭に追加
	}

}
