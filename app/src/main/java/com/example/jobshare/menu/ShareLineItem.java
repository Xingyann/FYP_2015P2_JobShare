package com.example.jobshare.menu;

public class ShareLineItem {

	private String id;
	private String title;
	private String desc;
	private String pubdate;
	private String link;

//	public Item(String id,String title,String desc,String pubdate,String link)
//	{
//		this.id = id;
//		this.title = title;
//		this.desc = desc;
//		this.pubdate = pubdate;
//		this.link = link;
//	}

	public ShareLineItem(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
