package com.cxypub.rest.test;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Book")
public class Book {

	private Map<String, Object> test = new HashMap<String, Object>();

	private String bookId;

	private String bookISBNnumber;

	private String bookName;

	// Let assume one author only
	private String author;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookISBNnumber() {
		return bookISBNnumber;
	}

	public void setBookISBNnumber(String bookISBNnumber) {
		this.bookISBNnumber = bookISBNnumber;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Map<String, Object> getTest() {
		return test;
	}

	public void setTest(Map<String, Object> test) {
		this.test = test;
	}

}
