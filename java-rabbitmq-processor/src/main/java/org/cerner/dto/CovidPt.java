package org.cerner.dto;

public class CovidPt {

	private int id;

	private String lastName;

	private String firstName;

	private int age;

	private String gender;

	private String coMorbiditiesFlag;

	private String deceasedFlag;

	private String city;

	public CovidPt(int id, String lastName, String firstName, int age, String gender, String coMorbiditiesFlag,
			String deceasedFlag, String city) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
		this.gender = gender;
		this.coMorbiditiesFlag = coMorbiditiesFlag;
		this.deceasedFlag = deceasedFlag;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCoMorbiditiesFlag() {
		return coMorbiditiesFlag;
	}

	public void setCoMorbiditiesFlag(String coMorbiditiesFlag) {
		this.coMorbiditiesFlag = coMorbiditiesFlag;
	}

	public String getDeceasedFlag() {
		return deceasedFlag;
	}

	public void setDeceasedFlag(String deceasedFlag) {
		this.deceasedFlag = deceasedFlag;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
