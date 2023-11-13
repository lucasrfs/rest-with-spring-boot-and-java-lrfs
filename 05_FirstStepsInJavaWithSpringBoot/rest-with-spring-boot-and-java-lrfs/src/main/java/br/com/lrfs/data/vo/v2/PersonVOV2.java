package br.com.lrfs.data.vo.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class PersonVOV2 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String firstName;
	private String lastName;
	private String address;
	private String gender;
	private Date birthDate;
	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public PersonVOV2() {}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(address, birthDate, firstName, gender, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVOV2 other = (PersonVOV2) obj;
		return Objects.equals(address, other.address) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender) && id == other.id
				&& Objects.equals(lastName, other.lastName);
	}

	public void setId(long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	
}
