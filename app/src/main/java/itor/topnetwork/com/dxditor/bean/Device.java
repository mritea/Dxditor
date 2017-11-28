package itor.topnetwork.com.dxditor.bean;

/**
 * 
 * @Title: Device.java
 *
 * @Description: 设备信息表
 *
 *
 * @date 2017-10-17
 *
 */
public class Device {
	/**
	 * id
	 */
	private long id;
	/**
	 * 设备编号
	 */
	private String code ;
	/**
	 * 业务id对应td_business表id
	 */
	private long businessId;
	/**
	 * 业务名称
	 */
	private String businessName;
	
	/**
	 * 线路id对应td_line表id
	 */
	private long lineId;
	/**
	 * 线路名称
	 */
	private String lineName;
	/**
	 * 公里标
	 */
	private String dk;
	/**
	 *  经度
	 */
	private double locationx;
	/**
	 * 纬度
	 */
	private double locationy;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String dimension;
	/**
	 * 信号强度（采集过来数据）
	 */
	private double signal;
	/**
	 * 电量
	 */
	private double electricity;
	/**
	 * 状态 0:异常  1:正常 2:告警
	 */
	private int status ;
	/**
	 * 备注
	 */
	private String descr;
	/**
	 * 设备安装日期
	 *//*
	private Date  installDate; */
	/**
	 * value
	 */
	private String  value;
	/**
	 * 数据类型id对应td_data_type表id
	 */
	private long dataTypeId;
	/**
	 * 设备类型编码
	 */
	private String typeCode;
	/**
	 * 设备类型
	 */
	private String typeName;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public long getLineId() {
		return lineId;
	}
	public void setLineId(long lineId) {
		this.lineId = lineId;
	}
	public String getDk() {
		return dk;
	}
	public void setDk(String dk) {
		this.dk = dk;
	}
	public double getLocationx() {
		return locationx;
	}
	public void setLocationx(double locationx) {
		this.locationx = locationx;
	}
	public double getLocationy() {
		return locationy;
	}
	public void setLocationy(double locationy) {
		this.locationy = locationy;
	}
	public double getSignal() {
		return signal;
	}
	public void setSignal(double signal) {
		this.signal = signal;
	}
	public double getElectricity() {
		return electricity;
	}
	public void setElectricity(double electricity) {
		this.electricity = electricity;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	/*public Date getInstallDate() {
		return installDate;
	}
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}*/
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public long getDataTypeId() {
		return dataTypeId;
	}
	public void setDataTypeId(long dataTypeId) {
		this.dataTypeId = dataTypeId;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	
}
