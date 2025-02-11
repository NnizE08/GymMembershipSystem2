package com.app.model;

public class Package {
	private int packageId;
	private String packageName;
	private String packageAccess;
	private int packageFee;
	private int packageTrainingDuration;
	private String packageFreebies;
	private String packageInstructor;
	private int packageArchive;


	public Package(String packageName, String packageAccess, int packageFee,
	               int packageTrainingDuration, String packageFreebies,
	               String packageInstructor) {
		this.packageName = packageName;
		this.packageAccess = packageAccess;
		this.packageFee = packageFee;
		this.packageTrainingDuration = packageTrainingDuration;
		this.packageFreebies = packageFreebies;
		this.packageInstructor = packageInstructor;
		this.packageArchive = 0;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageAccess() {
		return packageAccess;
	}

	public void setPackageAccess(String packageAccess) {
		this.packageAccess = packageAccess;
	}

	public int getPackageFee() {
		return packageFee;
	}

	public void setPackageFee(int packageFee) {
		this.packageFee = packageFee;
	}

	public int getPackageTrainingDuration() {
		return packageTrainingDuration;
	}

	public void setPackageTrainingDuration(int packageTrainingDuration) {
		this.packageTrainingDuration = packageTrainingDuration;
	}

	public String getPackageFreebies() {
		return packageFreebies;
	}

	public void setPackageFreebies(String packageFreebies) {
		this.packageFreebies = packageFreebies;
	}

	public String getPackageInstructor() {
		return packageInstructor;
	}

	public void setPackageInstructor(String packageInstructor) {
		this.packageInstructor = packageInstructor;
	}

	public int getPackageArchive() {
		return packageArchive;
	}

	public void setPackageArchive(int packageArchive) {
		this.packageArchive = packageArchive;
	}

	@Override
	public String toString() {
		return "Package ID: " + packageId + "\n" +
				"Package Name: " + packageName + "\n" +
				"Package Access: " + packageAccess + "\n" +
				"Package Fee: " + packageFee + "\n" +
				"Package Duration: " + packageTrainingDuration + " days\n" +
				"Package Freebies: " + packageFreebies + "\n" +
				"Package Instructor: " + packageInstructor + "\n" +
				"Package Archived: " + (packageArchive == 1 ? "Yes" : "No");
	}
}
