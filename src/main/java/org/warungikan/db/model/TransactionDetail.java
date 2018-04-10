package org.warungikan.db.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@Table(name="transaction_detail")
public class TransactionDetail extends Basic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6775282335761403100L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long oid;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item")
	private ShopItem item;
	
	@Column(name = "amount")
	private Integer amount;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="transaction")
	private Transaction transaction;

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public ShopItem getItem() {
		return item;
	}

	public void setItem(ShopItem item) {
		this.item = item;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	

}
