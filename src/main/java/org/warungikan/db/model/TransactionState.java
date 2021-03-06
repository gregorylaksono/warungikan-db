package org.warungikan.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="transaction_state")
public class TransactionState extends Basic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -795942653314607993L;
	public enum TransactionStateEnum{
		SENT(new Short("1")),
		PAID(new Short("2")),
		PROCESSING(new Short("3")),
		DELIVERING(new Short("4")),
		RECEIVED(new Short("5")),
		CANCELED(new Short("6"));

		private Short state;
		TransactionStateEnum(Short state){
			this.state = state;
		}
		public Short getState() {
			return state;
		}
		
		public static String getStateName(Short code) {
			switch (code) {
			case 1: return SENT.name();
			case 2: return PAID.name();
			case 3: return PROCESSING.name();
			case 4: return DELIVERING.name();
			case 5: return RECEIVED.name();
			case 6: return CANCELED.name();
			default: return "";
			}
		}
		
		public static Short getStateCode(String name){
			switch(name){
			case "SENT":  return SENT.getState();
			case "PAID" : return PAID.getState() ;
			case "PROCESSING" : return PROCESSING.getState() ;
			case "DELIVERING" : return DELIVERING.getState() ;
			case "RECEIVED" : return RECEIVED.getState() ;
			case "CANCELED" : return CANCELED.getState() ;
			default: return null;
			}
		}

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long oid;
	
	@Column(name="state")
	private Short state;
	
	@Column(name="name", length= 15)
	private String name;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "oid")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="transaction")
	private Transaction transaction;

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	
}
