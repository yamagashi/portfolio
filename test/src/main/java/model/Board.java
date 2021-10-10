package model;

import java.io.Serializable;

public class Board implements Serializable {
	private String userName; //ユーザー名
	private String text; //文章

	public Board() {}
	public Board(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}
	public String getUserName() {return userName;}
	public String getText() {return text;}
}
