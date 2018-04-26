package org.warungikan.db.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
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

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer")
	private User customer;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "agent")
	private User agent;

	@Column(name="total_price", nullable=false)
	private Long totalPrice;
	
	@Column(name="transport_price", nullable=false)
	public Long transportPrice;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="transaction")
    public Set<TransactionDetail> transactionDetails; 
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="settlement_date", nullable=true)
	public Date settlementDate;
	
	
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

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Set<TransactionDetail> getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(Set<TransactionDetail> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	
	public void addTransactionDetail(TransactionDetail d) {
		if(transactionDetails == null) transactionDetails =new HashSet<TransactionDetail>();
		if(d != null) {
			transactionDetails.add(d);			
		}
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public Long getTransportPrice() {
		return transportPrice;
	}

	public void setTransportPrice(Long transportPrice) {
		this.transportPrice = transportPrice;
	}



}
