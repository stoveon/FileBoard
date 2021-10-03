package model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Repository;

@Repository
public class AgreeCommand {
	private static String agreeAll;
	private static String agreeTerms;
	private static String agreeTermsSel;
	public static String getAgreeTerms() {
		return agreeTerms;
	}
	public void setAgreeTerms(String agreeTerms) {
		this.agreeTerms = agreeTerms;
	}
	public String getAgreeTermsSel() {
		return agreeTermsSel;
	}
	public void setAgreeTermsSel(String agreeTermsSel) {
		this.agreeTermsSel = agreeTermsSel;
	}
	public String getAgreeAll() {
		return agreeAll;
	}
	public void setAgreeAll(String agreeAll) {
		this.agreeAll = agreeAll;
	}
	@Override
	public String toString() {
		return "AgreeCommand [agreeAll=" + agreeAll + ", agreeTerms=" + agreeTerms + ", agreeTermsSel=" + agreeTermsSel
				+ "]";
	}
	public static boolean essential() {
		if(agreeTerms == null) {
			return false;
		}else if(agreeTerms.equals("serviceAgree,personalInformationAgree")){
			return true;
		}else if(agreeAll != null){
			return true;
		}else {
			return false;
		}
	}
}
