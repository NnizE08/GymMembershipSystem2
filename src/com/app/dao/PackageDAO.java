package com.app.dao;

import com.app.model.Package;

import java.util.ArrayList;

public interface PackageDAO {
	boolean create(Package packageObj);

	ArrayList<Package> read();

	Package read(int packageId);

	boolean update(Package packageObj);

	boolean delete(int packageId);

	ArrayList<Package> search(String criteria);

	boolean archive(int packageId);

	boolean restore(int packageId);

	int getTrainingDuration(int package_id);
}