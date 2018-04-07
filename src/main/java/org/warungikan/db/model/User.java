package org.warungikan.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.web.JsonPath;
import org.warungikan.db.view.View;


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
	
	@Column(name="address", length=20, nullable=false)
	private String address;
	
	@Column(name="city", length=20, nullable=false)
	private String city;
	
	@Column(name="address_info", length=20, nullable=true)
	private String addressInfo;
	
	@Column(name="latitude",  nullable=false)
	private Double latitude;
	
	@Column(name="longitude",  nullable=false)
	private Double longitude;
	
	@Column(name="type")
	private Short type;
	
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
	
	

}
