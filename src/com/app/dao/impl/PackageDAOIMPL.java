package com.app.dao.impl;

import com.app.dao.PackageDAO;
import com.app.model.Package;
import com.app.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;

public class PackageDAOIMPL extends DbConnection implements PackageDAO {

	@Override
	public boolean create(Package packageObj) {
		String query = "INSERT INTO tblpackages (package_name, package_access, package_fee, package_training_duration, package_freebies, package_instructor, package_archive) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			connect();
			prep = con.prepareStatement(query);
			prep.setString(1, packageObj.getPackageName());
			prep.setString(2, packageObj.getPackageAccess());
			prep.setInt(3, packageObj.getPackageFee());
			prep.setInt(4, packageObj.getPackageTrainingDuration());
			prep.setString(5, packageObj.getPackageFreebies());
			prep.setString(6, packageObj.getPackageInstructor());
			prep.setInt(7, packageObj.getPackageArchive());

			int result = prep.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			System.out.println("Error creating package: " + e.getMessage());
			return false;
		} finally {
			closeConnection();
		}
	}

	@Override
	public ArrayList<Package> read() {
		ArrayList<Package> packages = new ArrayList<>();
		String query = "SELECT * FROM tblpackages WHERE package_archive = 0";
		try {
			connect();
			state = con.createStatement();
			result = state.executeQuery(query);

			while (result.next()) {
				Package packageObj = new Package(result.getString("package_name"),
						result.getString("package_access"),
						result.getInt("package_fee"),
						result.getInt("package_training_duration"),
						result.getString("package_freebies"),
						result.getString("package_instructor"));

				packageObj.setPackageId(result.getInt("package_id"));
				packageObj.setPackageArchive(result.getInt("package_archive"));  // Get the correct archive status (0 or 1)
				packages.add(packageObj);
			}
		} catch (SQLException e) {
			System.out.println("Error reading packages: " + e.getMessage());
		} finally {
			closeConnection();
		}

		return packages;
	}

	@Override
	public Package read(int packageId) {
		Package packageObj = null;
		String query = "SELECT * FROM tblpackages WHERE package_id = ?";
		try {
			connect();
			prep = con.prepareStatement(query);
			prep.setInt(1, packageId);
			result = prep.executeQuery();

			if (result.next()) {
				packageObj = new Package(result.getString("package_name"),
						result.getString("package_access"),
						result.getInt("package_fee"),
						result.getInt("package_training_duration"),
						result.getString("package_freebies"),
						result.getString("package_instructor"));
				packageObj.setPackageId(result.getInt("package_id"));
				packageObj.setPackageArchive(result.getInt("package_archive"));
			}
		} catch (SQLException e) {
			System.out.println("Error reading package by ID: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return packageObj;
	}

	@Override
	public boolean update(Package packageObj) {
		String query = "UPDATE tblpackages SET package_name = ?, " +
				"package_access = ?, " +
				"package_fee = ?, " +
				"package_training_duration = ?, " +
				"package_freebies = ?, " +
				"package_instructor = ?, " +
				"package_archive = ? " +
				"WHERE package_id = ?";
		try {
			connect();
			prep = con.prepareStatement(query);
			prep.setString(1, packageObj.getPackageName());
			prep.setString(2, packageObj.getPackageAccess());
			prep.setInt(3, packageObj.getPackageFee());
			prep.setInt(4, packageObj.getPackageTrainingDuration());
			prep.setString(5, packageObj.getPackageFreebies());
			prep.setString(6, packageObj.getPackageInstructor());
			prep.setInt(7, packageObj.getPackageArchive());
			prep.setInt(8, packageObj.getPackageId());

			int result = prep.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			System.out.println("Error updating package: " + e.getMessage());
			return false;
		} finally {
			closeConnection();
		}
	}

	@Override
	public boolean delete(int packageId) {
		String query = "DELETE FROM tblpackages WHERE package_id = ?";
		try {
			connect();
			prep = con.prepareStatement(query);
			prep.setInt(1, packageId);

			int result = prep.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			System.out.println("Error deleting package: " + e.getMessage());
			return false;
		} finally {
			closeConnection();
		}
	}

	@Override
	public ArrayList<Package> search(String criteria) {
		ArrayList<Package> packages = new ArrayList<>();
		String query = "SELECT * FROM tblpackages WHERE package_name LIKE ?";
		try {
			connect();
			prep = con.prepareStatement(query);
			prep.setString(1, "%" + criteria + "%");
			result = prep.executeQuery();

			while (result.next()) {
				Package packageObj = new Package(result.getString("package_name"),
						result.getString("package_access"),
						result.getInt("package_fee"),
						result.getInt("package_training_duration"),
						result.getString("package_freebies"),
						result.getString("package_instructor"));
				packageObj.setPackageId(result.getInt("package_id"));
				packageObj.setPackageArchive(result.getInt("package_archive"));  // Get the correct archive status (0 or 1)
				packages.add(packageObj);
			}
		} catch (SQLException e) {
			System.out.println("Error searching packages: " + e.getMessage());
		} finally {
			closeConnection();
		}

		return packages;
	}

	@Override
	public boolean archive(int packageId) {
		String query = "UPDATE tblpackages SET package_archive = 1 WHERE package_id = ?";
		try {
			connect();
			prep = con.prepareStatement(query);
			prep.setInt(1, packageId);
			int result = prep.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			System.out.println("Error archiving package: " + e.getMessage());
			return false;
		} finally {
			closeConnection();
		}
	}

	@Override
	public boolean restore(int packageId) {
		String query = "UPDATE tblpackages SET package_archive = 0 WHERE package_id = ?";
		try {
			connect();
			prep = con.prepareStatement(query);
			prep.setInt(1, packageId);
			int result = prep.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			System.out.println("Error restoring package: " + e.getMessage());
			return false;
		} finally {
			closeConnection();
		}
	}

	@Override
	public int getTrainingDuration(int package_id) {
		String query = "SELECT package_training_duration FROM tblpackages WHERE package_id = ?";
		int training_duration = 0;

		try {
			connect();
			prep = con.prepareStatement(query);
			prep.setInt(1, package_id);
			result = prep.executeQuery();

			if (result.next()) {
				training_duration = result.getInt("package_training_duration");
			}
		} catch (SQLException e) {
			System.out.println("Error retrieving training duration of package: " + e.getMessage());
		}

		return training_duration;
	}
}
