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
@Table(name="topup_wallet_history")
public class TopupWalletHistory extends Basic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 691445749425489607L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long oid;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_wi")
	private User user;
	
	@Column(name="amount", nullable=false)
	private Long amount;
	
	@Column(name="description", nullable=true, length=30)
	private String description;

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	
	

}
