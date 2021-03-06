package org.warungikan.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@Table(name="shop_item")
public class ShopItem extends Basic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4341247891631739639L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long id;
	
	@Column(name = "name", length=40, nullable=false)
	private String name;
	
	@Column(name = "description", length=200)
	private String description;
	
	@Column(name = "weight", length=30)
	private String weight;
	
	@Column(name = "url", length=50, nullable=false)
	private String url;
	
	@Column(name = "price", nullable=false)
	private Long price;
	
	@Column(name = "is_enable", nullable=false)
	private Boolean isEnable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	
	public static ShopItem createNew(String name, String url, Long price, String weight, String description, Boolean isEnable){
		ShopItem i = new ShopItem();
		i.setName(name);
		i.setUrl(url);
		i.setWeight(weight);
		i.setPrice(price);
		i.setIsEnable(isEnable);
		i.setCreationDate(new Date());
		return i;
		
	}

}
