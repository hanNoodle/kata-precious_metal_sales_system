package com.coding.sales.entiy;

public class Member {
	private String memberName;
	private String level;
	private String memberId;
	private int score;
	
	public Member(String memberName, String level, String memberId, int score) {
		super();
		this.memberName = memberName;
		this.level = level;
		this.memberId = memberId;
		this.score = score;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
