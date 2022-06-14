package deviceManager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;

import deviceManager.model.DeviceAdd;
import deviceManager.model.DeviceGet;
import deviceManager.util.DateUtil;
import deviceManager.util.JDBCConnection;
import deviceManager.util.VnCharacterUtils;

public class DeviceDao {

	public List<DeviceGet> getAll() {
		List<DeviceGet> devices = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		String sql = "SELECT * FROM DEVICE dv inner join category ca on dv.category_id = ca.category_id";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				DeviceGet device = new DeviceGet();
				device.setId(rs.getString(1));
				device.setName(rs.getString(2));
				device.setExpiryDate(rs.getDate(4));
				device.setManufacturingDate(rs.getDate(5));
				device.setStatus(rs.getString(6));
				device.setPrice(rs.getInt(7));
				device.setBuyDate(rs.getDate(8));
				device.setWarrantyMonth(rs.getInt(9));
				device.setCategoryId(rs.getInt(10));
				device.setCategoryName(rs.getString(12));
				devices.add(device);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return devices;

	}

	public void save(DeviceAdd device) {
		String sqlInsert = "INSERT INTO DEVICE(DEVICE_NAME,SEARCH_KEY,EXPIRY_DATE,MANUFACTURING_DATE,STATUS,PRICE,BUY_DATE,WARRANTY_MONTH,CATEGORY_ID)"
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		String sqlUpdate = "UPDATE device\r\n"
				+ "	SET device_name=?, search_key=?, expiry_date=?, manufacturing_date=?, status=?, price=?, buy_date=?, warranty_month=?, category_id=?\r\n"
				+ "	WHERE device_id::text = ?";
		Connection connection = JDBCConnection.getConnection();
		if (device.getId() == null) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
				preparedStatement.setString(1, device.getName());
				preparedStatement.setString(2, VnCharacterUtils.removeAccent(device.getName()));
				preparedStatement.setDate(3, DateUtil.stringDateUtilToDateSql(device.getExpiryDate()));
				preparedStatement.setDate(4, DateUtil.stringDateUtilToDateSql(device.getManufacturingDate()));
				preparedStatement.setString(5, device.getStatus());
				preparedStatement.setInt(6, device.getPrice());
				preparedStatement.setDate(7, DateUtil.stringDateUtilToDateSql(device.getBuyDate()));
				preparedStatement.setInt(8, device.getWarrantyMonth());
				preparedStatement.setInt(9, device.getCategoryId());
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
				preparedStatement.setString(1, device.getName());
				preparedStatement.setString(2, VnCharacterUtils.removeAccent(device.getName()));
				preparedStatement.setDate(3, DateUtil.stringDateUtilToDateSql(device.getExpiryDate()));
				preparedStatement.setDate(4, DateUtil.stringDateUtilToDateSql(device.getManufacturingDate()));
				preparedStatement.setString(5, device.getStatus());
				preparedStatement.setInt(6, device.getPrice());
				preparedStatement.setDate(7, DateUtil.stringDateUtilToDateSql(device.getBuyDate()));
				preparedStatement.setInt(8, device.getWarrantyMonth());
				preparedStatement.setInt(9, device.getCategoryId());
				preparedStatement.setString(10, device.getId());
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public DeviceGet getOneById(String deviceId) {
		String sql = "SELECT * FROM DEVICE dv inner join category ca on dv.category_id = ca.category_id where device_id::text = ?";
		Connection connection = JDBCConnection.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, deviceId);
			ResultSet rs = preparedStatement.executeQuery();
			DeviceGet device = new DeviceGet();
			while (rs.next()) {
				device.setId(rs.getString(1));
				device.setName(rs.getString(2));
				device.setExpiryDate(rs.getDate(4));
				device.setManufacturingDate(rs.getDate(5));
				device.setStatus(rs.getString(6));
				device.setPrice(rs.getInt(7));
				device.setBuyDate(rs.getDate(8));
				device.setWarrantyMonth(rs.getInt(9));
				device.setCategoryId(rs.getInt(10));
				device.setCategoryName(rs.getString(12));
			}
			return device;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void delete(String id) {
		String sql = "delete from device where device_id::text = ?";
		Connection connection = JDBCConnection.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<DeviceGet> findByCategory(int categoryId) {
		List<DeviceGet> devices = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		String sql = "SELECT * FROM DEVICE dv inner join category ca on dv.category_id = ca.category_id where ca.category_id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DeviceGet device = new DeviceGet();
				device.setId(rs.getString(1));
				device.setName(rs.getString(2));
				device.setExpiryDate(rs.getDate(4));
				device.setManufacturingDate(rs.getDate(5));
				device.setStatus(rs.getString(6));
				device.setPrice(rs.getInt(7));
				device.setBuyDate(rs.getDate(8));
				device.setWarrantyMonth(rs.getInt(9));
				device.setCategoryId(rs.getInt(10));
				device.setCategoryName(rs.getString(12));
				devices.add(device);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return devices;
	}

	public List<DeviceGet> listByWarranty(boolean still) {
		List<DeviceGet> deviceGets = getAll();
		if (still) {
			return deviceGets.stream().filter(device -> checkOutOfWarranty(device)).collect(Collectors.toList());
		} else {
			return deviceGets.stream().filter(device -> !checkOutOfWarranty(device)).collect(Collectors.toList());
		}
	}

	public boolean checkOutOfWarranty(DeviceGet deviceGet) {
		Date outOfWanrrantyDate = DateUtils.addMonths(deviceGet.getBuyDate(), deviceGet.getWarrantyMonth());
		return DateUtils.truncatedCompareTo(outOfWanrrantyDate, DateUtil.today(), Calendar.DATE) > 0;
	}

	public List<DeviceGet> listByExpiryDate(boolean isStill) {
		List<DeviceGet> deviceGets = getAll();
		if (isStill) {
			return deviceGets.stream().filter(device -> checkExpire(device)).collect(Collectors.toList());
		} else {
			return deviceGets.stream().filter(device -> !checkExpire(device)).collect(Collectors.toList());
		}
	}

	public boolean checkExpire(DeviceGet deviceGet) {
		if (deviceGet.getExpiryDate() == null) {
			return true;
		}
		return DateUtils.truncatedCompareTo(deviceGet.getExpiryDate(), DateUtil.today(), Calendar.DATE) > 0;
	}

	public List<DeviceGet> searchByName(String searchKey) {
		List<DeviceGet> devices = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		String sql = "SELECT * FROM DEVICE dv inner join category ca on dv.category_id = ca.category_id where search_key like ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + VnCharacterUtils.removeAccent(searchKey) + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DeviceGet device = new DeviceGet();
				device.setId(rs.getString(1));
				device.setName(rs.getString(2));
				device.setExpiryDate(rs.getDate(4));
				device.setManufacturingDate(rs.getDate(5));
				device.setStatus(rs.getString(6));
				device.setPrice(rs.getInt(7));
				device.setBuyDate(rs.getDate(8));
				device.setWarrantyMonth(rs.getInt(9));
				device.setCategoryId(rs.getInt(10));
				device.setCategoryName(rs.getString(12));
				devices.add(device);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return devices;
	}

	public List<DeviceGet> getBrokenDevice() {
		List<DeviceGet> devices = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		String sql = "SELECT * FROM DEVICE dv inner join category ca on dv.category_id = ca.category_id where status = 'broken'";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {

				DeviceGet device = new DeviceGet();
				device.setId(rs.getString(1));
				device.setName(rs.getString(2));
				device.setExpiryDate(rs.getDate(4));
				device.setManufacturingDate(rs.getDate(5));
				device.setStatus(rs.getString(6));
				device.setPrice(rs.getInt(7));
				device.setBuyDate(rs.getDate(8));
				device.setWarrantyMonth(rs.getInt(9));
				device.setCategoryId(rs.getInt(10));
				device.setCategoryName(rs.getString(12));
				devices.add(device);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return devices;
	}

	public List<DeviceGet> getByPriceMoreThan100k() {
		List<DeviceGet> devices = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		String sql = "SELECT * FROM DEVICE dv inner join category ca on dv.category_id = ca.category_id where price > 100000";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {

				DeviceGet device = new DeviceGet();
				device.setId(rs.getString(1));
				device.setName(rs.getString(2));
				device.setExpiryDate(rs.getDate(4));
				device.setManufacturingDate(rs.getDate(5));
				device.setStatus(rs.getString(6));
				device.setPrice(rs.getInt(7));
				device.setBuyDate(rs.getDate(8));
				device.setWarrantyMonth(rs.getInt(9));
				device.setCategoryId(rs.getInt(10));
				device.setCategoryName(rs.getString(12));
				devices.add(device);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return devices;
	}

	public static void main(String[] args) throws ParseException {
		DeviceDao deviceDao = new DeviceDao();
//		List<DeviceGet> deviceGets = deviceDao.getAll();
//		for (DeviceGet device : deviceGets) {
//			System.out.println(device.toString());
//		}
		
		DeviceGet deviceGet = deviceDao.getOneById("378dc3f5-0279-4838-8fcc-c4af69fb2bbe");
		System.out.println(deviceGet.toString());
		
//		DeviceAdd device = new DeviceAdd();
//		device.setName("abc");
//		device.setCategoryId(1);
//		device.setExpiryDate("20-01-2022");
//		device.setManufacturingDate("20-01-2021");
//		device.setStatus("ok");
//		device.setPrice(200000);
//		device.setSellDate("20-01-2021");
//		device.setWarrantyMonth(24);
//		device.setCategoryId(1);
//		deviceDao.insert(device);
//		System.out.println(deviceDao.checkOutOfWarranty(deviceGets.get(0).getSellDate(),deviceGets.get(0).getWarrantyMonth()));
	}
}
