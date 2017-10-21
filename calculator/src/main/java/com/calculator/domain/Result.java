package com.calculator.domain;

public class Result {
	
	private int num;				//�ں�
	private double total;		//����
	private double principal;	//����
	private double interest;	//��Ϣ
	private String date;			//��������
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interst) {
		this.interest = interst;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Result [num=" + num + ", total=" + total + ", principal="
				+ principal + ", interest=" + interest + ", date=" + date + "]";
	}
	
}
