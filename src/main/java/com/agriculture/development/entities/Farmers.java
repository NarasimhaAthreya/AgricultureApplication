package com.agriculture.development.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Farmers {

	@Column
	public String name;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int id;

	@Column
	public int age;

	@Column
	public String city;

	@Column
	public String number;

	@Column
	public String verificationStatus;

	@Column
	public String landImagesPath;

	@Column
	public String DocImagesPath;
	
	

	public String getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public String getLandImagesPath() {
		return landImagesPath;
	}

	public void setLandImagesPath(String landImagesPath) {
		this.landImagesPath = landImagesPath;
	}

	public String getDocImagesPath() {
		return DocImagesPath;
	}

	public void setDocImagesPath(String docImagesPath) {
		DocImagesPath = docImagesPath;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Farmers [name=" + name + ", id=" + id + ", age=" + age + ", city=" + city + ", number=" + number + "]";
	}

}
