package model;

import java.io.Serializable;

//文章に関する情報をもつJavaBeansモデル

public class Diary implements Serializable {
	private String userName; //ユーザー名
	private String text; //文章

	public Diary() {}
	public Diary(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}
	public String getUserName() {return userName;}
	public String getText() {return text;}
}
