package com.coding.sales.entiy;

import java.math.BigDecimal;

public class Member {
	private String memberName;
	private String level;
	private String memberId;
	private BigDecimal score;
	
	public Member(String memberName, String level, String memberId,
			BigDecimal score) {
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
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	public BigDecimal addScore(BigDecimal increasedScore) {
		BigDecimal newScore = new BigDecimal("0.00");
		if ("普卡".equals(level)) {
			newScore = score.add(increasedScore); 
		} else if ("金卡".equals(level)) {
			newScore = new BigDecimal("1.5").multiply(increasedScore).add(score).setScale(2); 
		} else if ("白金卡".equals(level)) {
			newScore = new BigDecimal("1.8").multiply(increasedScore).add(score).setScale(2);
		} else if ("钻石卡".equals(level)) {
			newScore = new BigDecimal("2").multiply(increasedScore).add(score).setScale(2);
		}
		return newScore;
	}
	
}
