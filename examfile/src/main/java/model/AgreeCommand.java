package model;

import org.hibernate.validator.constraints.NotEmpty;

public class AgreeCommand {
	private String agreeAll;
	private String agreeTerms;
	private String agreeTermsSel;
	public String getAgreeTerms() {
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
	public boolean essential() {
		if(agreeTerms == null) {
			return false;
		}else if(agreeTerms.equals("serviceAgree") && agreeTerms.equals("personalInformationAgree")){
			return true;
		}else if(agreeAll != null){
			return true;
		}else {
			return false;
		}
	}
}
