package com.openapi.starter.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BookVO {

	private int id;
	private String title;
	private String author;
	private String image;
	private String link;
	private int price;
	private Date regdate;
	
}
