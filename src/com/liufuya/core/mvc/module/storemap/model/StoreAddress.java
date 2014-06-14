package com.liufuya.core.mvc.module.storemap.model;

import java.io.Serializable;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 门店管理表实体类 lfy_store_address
 * 
 * @author liulili
 * 
 */
@Table("lfy_store_address")
public class StoreAddress implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long id;// 用户ID，唯一编号,系统自动增长

	@Column("store_code")
	private String storeCode;// 门店编号，编码唯一

	@Column("store_name")
	private String storeName;// 门店名称

	@Column("manage_type")
	private String manageType;// 经营类型,直营、加盟、联营、代销、托管、承包、合作门店

	@Column("store_type")
	private String storeType; // 店铺类型，社区型、菜场型、商圈型、超市型、复合型、商社型、校区型

	@Column("depart_own")
	private String departOwn; // 所属部门，直营、加盟、托管、

	/**
	 * 按国务院规定的顺序排列：
　　华北地区：北京市、天津市、河北省、山西省、内蒙古自治区；
　　东北地区：辽宁省、吉林省、黑龙江省；
　　华东地区：上海市、江苏省、浙江省、安徽省、福建省、江西省、山东省；
　　中南地区：河南省、湖北省、湖南省、广东省、海南省、广西壮族自治区；
　　西南地区：重庆市、四川省、贵州省、云南省、西藏自治区；
　　西北地区：陕西省、甘肃省、青海省、宁夏回族自治区、新疆维吾尔自治区；
　　香港特别行政区、澳门特别行政区、台湾省。
	 */
	@Column("area")
	private String area; // 所属大区,华东地区、华北地区、华南地区、中南地区、西南地区

	@Column("province")
	private String province;// 省份

	@Column("city")
	private String city; // 城市

	@Column("city_part")
	private String cityPart; // 城区

	@Column("city_id")
	private int city_id; // 城市id

	@Column("store_address")
	private String storeaAddress; // 门店地址

	@Column("store_director")
	private String storeDirector; // 主管姓名

	@Column("store_directorphone")
	private String storeDirectorphone; // 主管电话

	@Column("store_assistant")
	private String storeAssistant; // 店员姓名

	@Column("store_assistantphone")
	private String storeAssistantphone; // 店员电话

	@Column("gps_lng")
	private String gpsJLng; // 经度

	@Column("gps_lat")
	private String gpsWLat; // 纬度

	@Column("create_date")
	private Date createDate; // 创建时间

	@Column("status")
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getDepartOwn() {
		return departOwn;
	}

	public void setDepartOwn(String departOwn) {
		this.departOwn = departOwn;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityPart() {
		return cityPart;
	}

	public void setCityPart(String cityPart) {
		this.cityPart = cityPart;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getStoreaAddress() {
		return storeaAddress;
	}

	public void setStoreaAddress(String storeaAddress) {
		this.storeaAddress = storeaAddress;
	}

	public String getStoreDirector() {
		return storeDirector;
	}

	public void setStoreDirector(String storeDirector) {
		this.storeDirector = storeDirector;
	}

	public String getStoreDirectorphone() {
		return storeDirectorphone;
	}

	public void setStoreDirectorphone(String storeDirectorphone) {
		this.storeDirectorphone = storeDirectorphone;
	}

	public String getStoreAssistant() {
		return storeAssistant;
	}

	public void setStoreAssistant(String storeAssistant) {
		this.storeAssistant = storeAssistant;
	}

	public String getStoreAssistantphone() {
		return storeAssistantphone;
	}

	public void setStoreAssistantphone(String storeAssistantphone) {
		this.storeAssistantphone = storeAssistantphone;
	}

	public String getGpsJLng() {
		return gpsJLng;
	}

	public void setGpsJLng(String gpsJLng) {
		this.gpsJLng = gpsJLng;
	}

	public String getGpsWLat() {
		return gpsWLat;
	}

	public void setGpsWLat(String gpsWLat) {
		this.gpsWLat = gpsWLat;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
