package db.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Person")
public class Person implements Serializable{
	@Id
	@Column(name="personId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name",length=30,nullable=false)
	private String name;
	@Column(name="surname",length=30,nullable=false)
	private String surname;
	@Column(name="address",length=75,nullable=false)
	private String address;
	@Column(name="email",length=35,nullable=false)
	private String eMail;
	@OneToMany(cascade=CascadeType.ALL)
	private List<PersonPhone>phoneList;
	
	public Person() {
		phoneList=new ArrayList<PersonPhone>();
	}
	
	public Person(String name, String surname, String address, String eMail, List<PersonPhone> phoneList) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.eMail = eMail;
		this.phoneList = phoneList;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public List<PersonPhone> getPhoneList() {
		return phoneList;
	}
	public void setPhoneList(List<PersonPhone> phoneList) {
		this.phoneList = phoneList;
	}
	
}
