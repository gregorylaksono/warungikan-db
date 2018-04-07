package org.warungikan.db.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="transaction")
public class Transaction extends Basic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2847249342440634412L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long oid;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer")
	private User customer;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agent")
	private User agent;

	@Column(name="total_price", nullable=false)
	private Long totalPrice;
	
	
	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}



}
