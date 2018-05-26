package org.warungikan.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	
	@Column(name="reference_bank_no", nullable=true, length=30)
	private String referenceBankNo;
	
	@Column(name="top_up_id", nullable=true, length=30, unique=true)
	private String top_up_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="topup_date")
	protected Date topupDate;
	
	@Column(name="release")
	private Boolean release;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReferenceBankNo() {
		return referenceBankNo;
	}

	public void setReferenceBankNo(String referenceBankNo) {
		this.referenceBankNo = referenceBankNo;
	}

	public String getTop_up_id() {
		return top_up_id;
	}

	public void setTop_up_id(String top_up_id) {
		this.top_up_id = top_up_id;
	}

	public Date getTopupDate() {
		return topupDate;
	}

	public void setTopupDate(Date topupDate) {
		this.topupDate = topupDate;
	}

	public Boolean getRelease() {
		return release;
	}

	public void setRelease(Boolean release) {
		this.release = release;
	}
	
	
	

}
