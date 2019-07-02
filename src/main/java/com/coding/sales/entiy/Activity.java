package com.coding.sales.entiy;

public class Activity {

	private String activityId;
	private String activityDescripe;
	
	public Activity(String activityId, String activityDescripe) {
		super();
		this.activityId = activityId;
		this.activityDescripe = activityDescripe;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityDescripe() {
		return activityDescripe;
	}
	public void setActivityDescripe(String activityDescripe) {
		this.activityDescripe = activityDescripe;
	}
	
}
