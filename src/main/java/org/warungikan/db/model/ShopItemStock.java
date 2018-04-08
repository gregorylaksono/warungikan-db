package org.warungikan.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="item_stock")
public class ShopItemStock extends Basic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3648719937607865223L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long oid;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="agent")
	private User agent;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="item")
	private ShopItem item;
	
	@Column(name="amount", nullable=false)
	private Integer amount;

	public Long getOid() {
		return oid;
	}

	public ShopItemStock setOid(Long oid) {
		this.oid = oid;
		return this;
	}

	public User getAgent() {
		return agent;
	}

	public ShopItemStock setAgent(User agent) {
		this.agent = agent;
		return this;
	}

	public ShopItem getItem() {
		return item;
	}

	public ShopItemStock setItem(ShopItem item) {
		this.item = item;
		return this;
	}

	public Integer getAmount() {
		return amount;
	}

	public ShopItemStock setAmount(Integer amount) {
		this.amount = amount;
		return this;
	}
	
	
}
