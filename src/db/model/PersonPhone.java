package db.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class PersonPhone implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int phoneId;
	@Column
	private long phoneNumber;
	
	
	public PersonPhone() {
	}
	public PersonPhone(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
