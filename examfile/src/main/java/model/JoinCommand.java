package model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Repository;

@Repository()
public class JoinCommand {
	@NotEmpty(message = "필수입력")
	private String emailId;
	@NotEmpty(message = "필수입력")
	private String emailAdd;
	@NotEmpty(message = "필수입력")
	private String password;
	@NotEmpty(message = "필수입력")
	private String passwordCHK;
	@NotEmpty(message = "필수입력")
	private String nickName;
	@NotEmpty(message = "필수입력")
	@Length(max = 4, min = 4)
	private String birthYear;
	@NotEmpty(message = "필수입력")
	@Length(max = 2)
	private String birthMonth;
	@NotEmpty(message = "필수입력")
	@Length(max = 2)
	private String birthDay;
	@NotEmpty(message = "필수입력")
	private String phoneNumberOne;
	@NotEmpty(message = "필수입력")
	private String phoneNumberTwo;
	@NotEmpty(message = "필수입력")
	private String phoneNumberThree;
	private String address;
	
	public JoinCommand() {}
	
	public JoinCommand(String emailId, String emailAdd, String password, String passwordCHK, String nickName, String birthYear,
			String birthMonth, String birthDay, String phoneNumberOne, String phoneNumberTwo, String phoneNumberThree,
			String address) {
		this.emailId = emailId;
		this.emailAdd = emailAdd;
		this.password = password;
		this.passwordCHK = passwordCHK;
		this.nickName = nickName;
		this.birthYear = birthYear;
		this.birthMonth = birthMonth;
		this.birthDay = birthDay;
		this.phoneNumberOne = phoneNumberOne;
		this.phoneNumberTwo = phoneNumberTwo;
		this.phoneNumberThree = phoneNumberThree;
		this.address = address;
	}


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailAdd() {
		return emailAdd;
	}

	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordCHK() {
		return passwordCHK;
	}
	public void setPasswordCHK(String passwordCHK) {
		this.passwordCHK = passwordCHK;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public String getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getPhoneNumberOne() {
		return phoneNumberOne;
	}
	public void setPhoneNumberOne(String phoneNumberOne) {
		this.phoneNumberOne = phoneNumberOne;
	}
	public String getPhoneNumberTwo() {
		return phoneNumberTwo;
	}
	public void setPhoneNumberTwo(String phoneNumberTwo) {
		this.phoneNumberTwo = phoneNumberTwo;
	}
	public String getPhoneNumberThree() {
		return phoneNumberThree;
	}
	public void setPhoneNumberThree(String phoneNumberThree) {
		this.phoneNumberThree = phoneNumberThree;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "JoinCommand [emailId=" + emailId + ", emailAdd=" + emailAdd + ", password=" + password + ", passwordCHK=" + passwordCHK + ", nickName="
				+ nickName + ", birthYear=" + birthYear + ", birthMonth=" + birthMonth + ", birthDay=" + birthDay
				+ ", phoneNumberOne=" + phoneNumberOne + ", phoneNumberTwo=" + phoneNumberTwo + ", phoneNumberThree="
				+ phoneNumberThree + ", address=" + address + "]";
	}
	
	public boolean passCheck() {
		if(!password.equals(passwordCHK)) {
			return false;
		}else {
			return true;
		}
	}
	
}
