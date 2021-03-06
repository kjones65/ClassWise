package edu.ycp.cs320.tjones50.model;

import java.util.ArrayList;

public class Admin extends Account {
	private boolean approved = true;
	private String email;
	private String password;
	private int accountId;
	private boolean emailVerified = true;
	private ArrayList<Advice> adviceList;

	public Admin() {

	}

	public Admin(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public void setApproved(boolean activated) {
		this.approved = activated;
	}

	public boolean getApproved() {
		return this.approved;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	@Override
	public void setAccountId(int accountId) {
		this.accountId = accountId;

	}

	@Override
	public int getAccountId() {
		return this.accountId;
	}

	public Advice approveAdvice(Advice advice) {
		advice.setApproved(true);
		return advice;

	}

	public Advice disapproveAdvice(Advice advice) {
		advice.setApproved(false);
		return advice;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public boolean getEmailVerified() {
		return this.emailVerified;
	}

	public void setArrAdvice(ArrayList<Advice> adviceList) {
		this.adviceList = adviceList;
	}

	public ArrayList<Advice> getArrAdvice() {
		return this.adviceList;
	}

}