package model;

import java.io.Serializable;

//文章に関する情報をもつJavaBeansモデル

public class Diary implements Serializable {
	private int id; //id
	private String userName; //ユーザー名
	private String text; //文章

	public Diary() {}
	public Diary(int id, String userName, String text) {
		this.id = id;
		this.userName = userName;
		this.text = text;
	}
	public int getId() {return id; }
	public String getUserName() {return userName;}
	public String getText() {return text;}
}
