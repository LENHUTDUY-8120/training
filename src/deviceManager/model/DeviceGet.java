package deviceManager.model;

import java.util.Date;

public class DeviceGet {

	private String id;
	private String name;
	private Date expiryDate;
	private Date manufacturingDate;
	private String status;
	private int price;
	private Date sellDate;
	private int warrantyMonth;
	private int categoryId;
	private String categoryName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public int getWarrantyMonth() {
		return warrantyMonth;
	}

	public void setWarrantyMonth(int warrantyMonth) {
		this.warrantyMonth = warrantyMonth;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "DeviceGet [id=" + id + ", name=" + name + ", expiryDate=" + expiryDate + ", manufacturingDate="
				+ manufacturingDate + ", status=" + status + ", price=" + price + ", sellDate=" + sellDate
				+ ", warrantyMonth=" + warrantyMonth + ", categoryId=" + categoryId + ", categoryName=" + categoryName
				+ "]";
	}

}
