package com.app.view;

import com.app.model.Package;
import com.app.model.User;
import com.app.util.Helper;
import com.app.util.TextHelper;

import java.util.ArrayList;
import java.util.Scanner;

public class PackageView {

	private Scanner scanner;
	TextHelper th = new TextHelper();


	public PackageView() {
		scanner = new Scanner(System.in);
	}

	public Package createPackage() {

		System.out.println(th.DIVIDER);
		System.out.println(th.COLOR_BLUE + "\t\t\tCREATE PACKAGE" + th.COLOR_RESET);
		System.out.println(th.DIVIDER);

		System.out.print("Package Name: ");
		String packageName = scanner.nextLine();

		System.out.print("Package Access: ");
		String packageAccess = scanner.nextLine();

		System.out.print("Package Fee: ");
		int packageFee = Integer.parseInt(scanner.nextLine());

		System.out.print("Package Training Duration(Days): ");
		int packageTrainingDuration = Integer.parseInt(scanner.nextLine());

		System.out.print("Package Freebies: ");
		String packageFreebies = scanner.nextLine();

		System.out.print("Package Instructor (Yes/No): ");
		String packageInstructor = scanner.nextLine();

		return new Package(packageName, packageAccess, packageFee, packageTrainingDuration, packageFreebies, packageInstructor);
	}

	public void displayPackages(ArrayList<Package> packages) {
		if (packages.isEmpty()) {
			System.out.println(th.COLOR_RED + "No packages found" + th.COLOR_RESET);
		} else {

			System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-4s| %-20s| %-100s| %-5s| %-20s| %-80s| %-10s| %n", "ID", "NAME", "ACCESS", "FEE", "TRAINING DURATION", "FREEBIES", "INSTRUCTOR");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			packages.forEach((pack) -> {
				System.out.printf("%-4s| %-20s| %-100s| %-5s| %-20s| %-80s| %-10s| %n", pack.getPackageId(), pack.getPackageName(), pack.getPackageAccess(), pack.getPackageFee(), pack.getPackageTrainingDuration() + " days", pack.getPackageFreebies(), pack.getPackageInstructor());
			});
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		}
	}

	public void displayPackage(Package packageObj) {
		if (packageObj != null) {
			System.out.println("\u250c" + Helper.repeat('\u2500', 100) + "\u2510");
			System.out.printf("\u2502 %-100s \u2502%n", "PACKAGE AVAILMENT");
			System.out.println("\u251c" + Helper.repeat('\u2500', 100) + "\u2524");
			System.out.printf("\u2502 %-4s | %-20s | %-30s | %-5s | %-15s | %-15s \u2502%n",
					"ID", "NAME", "ACCESS", "FEE", "DURATION", "INSTRUCTOR");
			System.out.println("\u251c" + Helper.repeat('\u2500', 100) + "\u2524");

			System.out.printf("\u2502 %-4s | %-20s | %-30s | %-5s | %-15s | %-15s \u2502%n",
					packageObj.getPackageId(), packageObj.getPackageName(), packageObj.getPackageAccess(),
					packageObj.getPackageFee(), packageObj.getPackageTrainingDuration() + " days",
					packageObj.getPackageInstructor());
			System.out.println("\u2514" + Helper.repeat('\u2500', 100) + "\u2518\n");
		} else {
			System.out.println("Package not found!");
		}
	}

	public void displayUserPackage(User user, Package pack) {
		System.out.println("\n\u250c" + Helper.repeat('\u2500', 100) + "\u2510");
		System.out.printf("\u2502 %-100s \u2502%n", "USER INFORMATION");
		System.out.println("\u251c" + Helper.repeat('\u2500', 100) + "\u2524");
		System.out.printf("\u2502 Name: %-30s | ID: %-10d | Role: %-15s | Status: %-10s \u2502%n",
				user.getFirstname() + " " + user.getLastname(), user.getId(), user.getRole(), user.getStatus());
		System.out.printf("\u2502 Contact: %-20s | Address: %-50s \u2502%n", user.getContact(), user.getAddress());
		System.out.println("\u2514" + Helper.repeat('\u2500', 100) + "\u2518\n");

		System.out.println("\u250c" + Helper.repeat('\u2500', 100) + "\u2510");
		System.out.printf("\u2502 %-100s \u2502%n", "PACKAGE AVAILMENT");
		System.out.println("\u251c" + Helper.repeat('\u2500', 100) + "\u2524");
		System.out.printf("\u2502 %-4s | %-20s | %-30s | %-5s | %-15s | %-15s \u2502%n",
				"ID", "NAME", "ACCESS", "FEE", "DURATION", "INSTRUCTOR");
		System.out.println("\u251c" + Helper.repeat('\u2500', 100) + "\u2524");

		System.out.printf("\u2502 %-4s | %-20s | %-30s | %-5s | %-15s | %-15s \u2502%n",
				pack.getPackageId(), pack.getPackageName(), pack.getPackageAccess(),
				pack.getPackageFee(), pack.getPackageTrainingDuration() + " days",
				pack.getPackageInstructor());
		System.out.println("\u2514" + Helper.repeat('\u2500', 100) + "\u2518\n");
	}


	public String searchPackage() {

		System.out.println(th.DIVIDER);
		System.out.println(th.COLOR_BLUE + "\t\t\tSEARCH PACKAGE" + th.COLOR_RESET);
		System.out.println(th.DIVIDER);

		System.out.print("Enter search criteria (Package Name or Access): ");
		return scanner.nextLine();
	}

	public int getPackageId() {

		System.out.println(th.DIVIDER);
		System.out.println(th.COLOR_BLUE + "\t\t\tVIEW PACKAGE" + th.COLOR_RESET);
		System.out.println(th.DIVIDER);


		System.out.print("Enter Package ID: ");
		return Integer.parseInt(scanner.nextLine());
	}

	public int getUserPackage() {
		int userId = -1;
		int userPackageId = -1;
		boolean isValidUser = false;

		while (!isValidUser) {
			System.out.println(th.DIVIDER);
			System.out.println(th.COLOR_BLUE + "\t\t\tVIEW PACKAGE" + th.COLOR_RESET);
			System.out.println(th.DIVIDER);

			System.out.print("Enter User ID: ");
			String input = scanner.nextLine();

			if (Helper.isNumeric(input)) {
				userId = Integer.parseInt(input);
				User userInfo = Helper.getUserInfo(userId);
				if (null != userInfo) {

					isValidUser = true;
				} else {
					System.out.println("User ID not found. Please try again.");
				}
			} else {
				System.out.println("Invalid input. Please enter a valid numeric User ID.");
			}
		}

		return userId;
	}

	public Package updatePackage(Package packageObj) {
		System.out.println("Updating Package: " + packageObj.getPackageName());

		System.out.print("New Package Name (leave blank to keep same): ");
		String newName = scanner.nextLine();
		if (!newName.isEmpty()) {
			packageObj.setPackageName(newName);
		}


		System.out.print("New Package Access (leave blank to keep same): ");
		String newAccess = scanner.nextLine();
		if (!newAccess.isEmpty()) {
			packageObj.setPackageAccess(newAccess);
		}


		System.out.print("New Package Fee (leave blank to keep same): ");
		String feeInput = scanner.nextLine();
		if (!feeInput.isEmpty()) {
			int newFee = Integer.parseInt(feeInput);
			packageObj.setPackageFee(newFee);
		}


		System.out.print("New Package Training Duration (leave blank to keep same): ");
		String durationInput = scanner.nextLine();
		if (!durationInput.isEmpty()) {
			int newDuration = Integer.parseInt(durationInput);
			packageObj.setPackageTrainingDuration(newDuration);
		}


		System.out.print("New Package Freebies (leave blank to keep same): ");
		String newFreebies = scanner.nextLine();
		if (!newFreebies.isEmpty()) {
			packageObj.setPackageFreebies(newFreebies);
		}


		System.out.print("New Package Instructor (leave blank to keep same): ");
		String newInstructor = scanner.nextLine();
		if (!newInstructor.isEmpty()) {
			packageObj.setPackageInstructor(newInstructor);
		}

		return packageObj;
	}

	public void displayDeletionConfirmation() {
		System.out.println("Package deleted successfully");
	}

	public void displayArchiveConfirmation() {
		System.out.println("Package archived successfully");
	}

	public void displayRestoreConfirmation() {
		System.out.println("Package restored successfully");
	}

	public void confirmArchiveOrRestore() {
		System.out.println("Please choose an action:");
		System.out.println("1. Archive Package");
		System.out.println("2. Restore Package");
	}

}
