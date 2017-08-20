package com.model;

public class Hobby {
	private String name_of_hobby;
	private String address;
	private String suburb;
	private String state;
	private int postcode;
	private String phone_number;
	private String comments;
	private Long kind;

	public String getAddress() {
		return address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public String getState() {
		return state;
	}

	public int postcode() {
		return postcode;
	}

	public String getComments() {
		return comments;
	}

	public String getName_of_hobby() {
		return name_of_hobby;
	}

	public String getSuburb() {
		return suburb;
	}

	public Long getKind() {
		return kind;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setName_of_hobby(String name_of_hobby) {
		this.name_of_hobby = name_of_hobby;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public void setKind(Long kind) {
		this.kind = kind;
	}

}