package com.app.controller;

import com.app.dao.PackageDAO;
import com.app.model.Package;
import com.app.model.User;
import com.app.view.PackageView;

import java.util.ArrayList;

public class PackageController {
	private PackageDAO packageDAO;
	private PackageView packageView;
	private UserController userController;

	public PackageController() {
		userController = new UserController();
	}

	public PackageController(PackageDAO packageDAO, PackageView packageView) {
		this.packageDAO = packageDAO;
		this.packageView = packageView;
	}

	public void createPackage() {
		Package newPackage = packageView.createPackage();

		if (packageDAO.create(newPackage)) {
			System.out.println("Package created successfully!");
		} else {
			System.out.println("Package creation failed!");
		}
	}

	public void displayPackages() {
		ArrayList<Package> packages = packageDAO.read();
		packageView.displayPackages(packages);
	}

	public void displayPackage() {
		int packageId = packageView.getPackageId();
		Package packageObj = packageDAO.read(packageId);

		if (packageObj != null) {
			packageView.displayPackage(packageObj);
		} else {
			System.out.println("Package not found!");
		}
	}

	public void displayUserPackage() {
		int userId = packageView.getUserPackage();
		User user = userController.getUserInfo(userId);
		System.out.println("");
		Package packageObj = packageDAO.read(userId);

		if (packageObj != null) {
			packageView.displayUserPackage(user, packageObj);
		} else {
			System.out.println("Package not found!");
		}
	}

	public void searchPackages() {
		String criteria = packageView.searchPackage();
		ArrayList<Package> packages = packageDAO.search(criteria);
		packageView.displayPackages(packages); // result show
	}


	public void updatePackage() {
		int packageId = packageView.getPackageId();
		Package packageObj = packageDAO.read(packageId);

		if (packageObj != null) {
			Package updatedPackage = packageView.updatePackage(packageObj);
			if (packageDAO.update(updatedPackage)) {
				System.out.println("Package updated successfully!");
			} else {
				System.out.println("Package update failed!");
			}
		} else {
			System.out.println("Package not found!");
		}
	}

	public void deletePackage() {
		int packageId = packageView.getPackageId();
		if (packageDAO.delete(packageId)) {
			System.out.println("Package deleted successfully!");
		} else {
			System.out.println("Package deletion failed!");
		}
	}

	public void archivePackage() {
		int packageId = packageView.getPackageId(); //
		if (packageDAO.archive(packageId)) {
			System.out.println("Package archived successfully!");
		} else {
			System.out.println("Package archiving failed!");
		}
	}

	public void restorePackage() {
		int packageId = packageView.getPackageId();
		if (packageDAO.restore(packageId)) {
			System.out.println("Package restored successfully!");
		} else {
			System.out.println("Package restoration failed!");
		}
	}

	public int getTrainingDuration(int package_id) {
		return packageDAO.getTrainingDuration(package_id);
	}

}
