package com.calculator.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Condition {
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;			//�������
	private double money;	//������
	private int limit;			//�������
	private double rate;	//����
	private String method;			//���ʽ
	
	private int limitIsDay;		//��������Ƿ�����,0�ǰ����㣬1������
	private int rateIsDay;		//�Ƿ��������ʣ�0�������ʣ�1��������
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getLimitIsDay() {
		return limitIsDay;
	}
	public void setLimitIsDay(int limitIsDay) {
		this.limitIsDay = limitIsDay;
	}
	public int getRateIsDay() {
		return rateIsDay;
	}
	public void setRateIsDay(int rateIsDay) {
		this.rateIsDay = rateIsDay;
	}
	@Override
	public String toString() {
		return "Condition [date=" + date + ", money=" + money + ", limit="
				+ limit + ", rate=" + rate + ", method=" + method
				+ ", limitIsDay=" + limitIsDay + ", rateIsDay=" + rateIsDay
				+ "]";
	}
	
}
