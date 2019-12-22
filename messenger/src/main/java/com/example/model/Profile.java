package com.example.model;

public class Profile {
	
	private long profileId;
	private String firstName;
	private String lastName;
	private String profileName;
	
	public Profile() {
		super();
	}
	public Profile(Long profileId, String firstName, String lastName, String profileName) {
		this.profileId = profileId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profileName = profileName;
	}
	
	public long getProfileId() {
		return profileId;
	}
	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
}
