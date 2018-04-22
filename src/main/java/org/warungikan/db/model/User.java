package org.warungikan.db.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="user_wi")
public class User extends Basic implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9085505335831736613L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long id;
	
	@Column(name="name", length=30,nullable=false)
	private String name;
	

	@Column(name="email", length=50, nullable=false)
	private String email;
	
	@Column(name="password", length=70, nullable=false)
	private String password;
	
	@Column(name="telp_no", length=20, nullable=false)
	private String telpNo;
	
	@Column(name="address", length=50, nullable=false)
	private String address;
	
	@Column(name="city", length=20, nullable=false)
	private String city;
	
	@Column(name="address_info", length=50, nullable=true)
	private String addressInfo;
	
	@Column(name="latitude",  nullable=false)
	private Double latitude;
	
	@Column(name="longitude",  nullable=false)
	private Double longitude;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login")
    private Date lastLogin;
	
	@ManyToMany
    @JoinTable( 
        name = "users_roles", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "oid"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "oid")) 
    private Collection<Role> roles;
	
	@Column(name="balance",  nullable=false)
	private Long balance;
	
	@Column(name="enable", nullable=false)
	private Boolean enable;
	
	public String getName() {
		return name;
	}
	public User setName(String name) {
		this.name = name;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	public Long getId() {
		return id;
	}
	public User setId(Long id) {
		this.id = id;
		return this;
	}
	public String getTelpNo() {
		return telpNo;
	}
	public User setTelpNo(String telpNo) {
		this.telpNo = telpNo;
		return this;
	}
	public String getAddress() {
		return address;
	}
	public User setAddress(String address) {
		this.address = address;
		return this;
	}
	public String getCity() {
		return city;
	}
	public User setCity(String city) {
		this.city = city;
		return this;
	}
	public String getAddressInfo() {
		return addressInfo;
	}
	public User setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
		return this;
	}
	public Double getLatitude() {
		return latitude;
	}
	public User setLatitude(Double latitude) {
		this.latitude = latitude;
		return this;
	}
	public Double getLongitude() {
		return longitude;
	}
	public User setLongitude(Double longitude) {
		this.longitude = longitude;
		return this;
	}
	public Long getBalance() {
		return balance;
	}
	public User setBalance(Long balance) {
		this.balance = balance;
		return this;
	}

	public Collection<Role> getRoles() {
		return roles;
	}
	public Boolean getEnable() {
		return enable;
	}
	public User setEnable(Boolean enable) {
		this.enable = enable;
		return this;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public User setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
		return this;
	}
	public User setRoles(Collection<Role> roles) {
		this.roles = roles;
		return this;
	}

	public void addRole(Role r){
		if(getRoles() == null){
			roles = new ArrayList<>();
			roles.add(r);
		}
	}
	
	public static User UserFactory(String name, String email, String telNo, String address, String city, String latitude,
			String longitude , String password){
		User t = new User();
		t.setName(name).setEmail(email).setTelpNo(telNo).setAddress(address).
		setCity(city).setLatitude(Double.parseDouble(latitude)).
		setLongitude(Double.parseDouble(longitude)).setPassword(password);
		return t;
	}
	
	

}
