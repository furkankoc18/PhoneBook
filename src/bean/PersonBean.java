package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.secure.spi.PermissionCheckEntityInformation;
import db.model.Person;
import db.model.PersonPhone;

@ManagedBean
@SessionScoped
public class PersonBean implements Serializable {
	
	private Person person;
	private String number;
	private List<Long>numberList;
	private SessionFactory factory=new Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();
	private Session session=factory.openSession();
	private Transaction transaction=session.getTransaction();
	private List<Long>modalNumbers;
	private Person modalPerson;
	
	
	public PersonBean() {
		person=new Person();
		numberList=new ArrayList<Long>();
		modalNumbers=new ArrayList<Long>();
		modalPerson=new Person();
	}

	public void numberAdd() {
		numberList.add(Long.parseLong(number));
	}
	
	public void personAdd() {
		for (Long long1 : numberList) {
			PersonPhone personPhone=new PersonPhone(long1);
			person.getPhoneList().add(personPhone);
		}
		try {
		
		transaction.begin();
			session.save(person);
		transaction.commit();
		}catch (Exception e) {
			System.out.println("person eklemede hata meydana geldi rollback() yapýlýyor => "+e.toString());
			transaction.rollback();
		}finally {
			
		}
		person=new Person();
		numberList.clear();
	}
	
	public void getContactsInformation(int id) {
		modalNumbers.clear();
		modalPerson=new Person();
		modalPerson=session.get(Person.class, id);
		for(PersonPhone personPhone:modalPerson.getPhoneList()) {
			modalNumbers.add(personPhone.getPhoneNumber());
		}
	}
	
	public List<?>personList(){
		return session.createQuery("From Person p").getResultList();
	}
	
	public void personRemove(Person person) {
		transaction.begin();
			session.remove(person);
		transaction.commit();
	}
	
	public void numberRemove(long number) {
		numberList.remove(number);
	}
	public void deleteContactsNumber(int personId,long number) {
		PersonPhone personPhone=session.createQuery("from PersonPhone p where p.phoneNumber="+number,PersonPhone.class).getSingleResult();
		Person person=session.get(Person.class, personId);
		transaction.begin();
			person.getPhoneList().remove(personPhone);
			session.remove(personPhone);
		transaction.commit();
		modalNumbers.remove(number);
	}
	
	//Getter and Setter
	
	public List<Long> getNumberList() {
		return numberList;
	}
	public void setNumberList(List<Long> numberList) {
		this.numberList = numberList;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public List<Long> getModalNumbers() {
		return modalNumbers;
	}
	public void setModalNumbers(List<Long> modalNumbers) {
		this.modalNumbers = modalNumbers;
	}
	public Person getModalPerson() {
		return modalPerson;
	}
	public void setModalPerson(Person modalPerson) {
		this.modalPerson = modalPerson;
	}
}