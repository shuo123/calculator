package com.calculator.service.impl;

import java.math.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.calculator.domain.Condition;
import com.calculator.domain.Result;
import com.calculator.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {
	// ���ڻ���Ϣ
	public Result[] alsoDuePrincipalAndInterest(Condition c) {
		Result[] tr = new Result[1];
		Result r = new Result();

		// ����������
		double dayRate = getDayRate(c.getRate(), c.getRateIsDay());

		// ���㵽������
		Calendar endCalendar = getEndCalendar(c.getDate(), c.getLimit(),
				c.getLimitIsDay());

		// ���������ת��Ϊ����
		long totalDay = getTotalDay(c.getDate(), endCalendar);

		// ������Ϣ
		double interest = c.getMoney() * dayRate * totalDay;
		interest = new BigDecimal(String.valueOf(interest)).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();

		// ����result
		r.setNum(1);
		r.setDate(new SimpleDateFormat("yyyy-MM-dd").format(endCalendar
				.getTime()));
		r.setInterest(interest);
		r.setPrincipal(c.getMoney());
		r.setTotal(interest + r.getPrincipal());

		tr[0] = r;
		return tr;
	}

	// �»�Ϣ���ڻ���
	public Result[] monthInterestDueDebt(Condition c) {
		Result[] tr = new Result[c.getLimit()];

		// ����������
		double dayRate = getDayRate(c.getRate(), c.getRateIsDay());

		// ����ÿ�ڽ��
		int i = 0;
		Date startDate = c.getDate();
		while (i < c.getLimit()) {
			Result r = new Result();

			// ���㵽������
			Calendar endCalendar = getEndCalendar(startDate, 1,
					c.getLimitIsDay());

			// ���������ת��Ϊ����
			long totalDay = getTotalDay(startDate, endCalendar);

			// ������Ϣ
			double interest = c.getMoney() * dayRate * 30;
			interest = new BigDecimal(String.valueOf(interest)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();

			// ����result
			r.setNum(i + 1);
			Date endDate = endCalendar.getTime();
			r.setDate(new SimpleDateFormat("yyyy-MM-dd").format(endDate));
			r.setInterest(interest);
			r.setPrincipal(i == c.getLimit() - 1 ? c.getMoney() : 0);
			r.setTotal(i == c.getLimit() - 1 ? interest + r.getPrincipal()
					: interest);

			tr[i] = r;
			startDate = endDate;
			i++;
		}

		return tr;
	}

	// �ȶϢ
	public Result[] equalPrincipalInterest(Condition c) {
		// TODO Auto-generated method stub
		Result[] tr = new Result[c.getLimit()];
		double principal = c.getMoney();// �����
		// ����������
		double dayRate = getDayRate(c.getRate(), c.getRateIsDay());

		// ����������
		double monthRate = dayRate * 30;

		// ����ÿ�ڽ��
		int i = 0;
		Date startDate = c.getDate();
		while (i < c.getLimit()) {
			Result r = new Result();

			// ����ʣ����
			double remainMoney = c.getMoney()
					- (c.getMoney() / c.getLimit() * i);

			// ����ÿ���¹���
			double total = principal * monthRate
					* Math.pow((1 + monthRate), c.getLimit())
					/ (Math.pow((1 + monthRate), c.getLimit()) - 1);
			total = new BigDecimal(String.valueOf(total)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();

			// ���㵽������
			Calendar endCalendar = getEndCalendar(startDate, 1,
					c.getLimitIsDay());

			// ����ÿ��Ӧ����Ϣ
			double dueInterest = remainMoney * monthRate;
			dueInterest = new BigDecimal(String.valueOf(dueInterest)).setScale(
					2, BigDecimal.ROUND_HALF_UP).doubleValue();

			// ����ÿ��Ӧ������
			double duemonthPrinc = total - dueInterest;
			duemonthPrinc = new BigDecimal(String.valueOf(duemonthPrinc))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

			// ����result
			r.setNum(i + 1);
			Date endDate = endCalendar.getTime();
			r.setDate(new SimpleDateFormat("yyyy-MM-dd").format(endDate));
			r.setInterest(dueInterest);
			r.setPrincipal(duemonthPrinc);
			r.setTotal(total);

			tr[i] = r;
			startDate = endDate;
			i++;
		}

		return tr;
	}

	
	// �ȶ��
	public Result[] equalPrincipal(Condition c) {

		Result[] tr = new Result[c.getLimit()];

		// ����������
		double dayRate = getDayRate(c.getRate(), c.getRateIsDay());

		// ����ÿ�ڽ��
		int i = 0;
		Date startDate = c.getDate();
		while (i < c.getLimit()) {
			Result r = new Result();

			// ����ʣ��������

			double remainMoney = c.getMoney()
					- (c.getMoney() / c.getLimit() * i);
			remainMoney = new BigDecimal(String.valueOf(remainMoney)).setScale(
					2, BigDecimal.ROUND_HALF_UP).doubleValue();

			// ���㵽������
			Calendar endCalendar = getEndCalendar(startDate, 1,
					c.getLimitIsDay());

			// ������Ϣ
			double interest = remainMoney * dayRate * 30;
			interest = new BigDecimal(String.valueOf(interest)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();

			// ����ÿ��Ӧ������
			double monthPrin = c.getMoney() / c.getLimit();
			monthPrin = new BigDecimal(String.valueOf(monthPrin)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();

			// ����result
			r.setNum(i + 1);
			Date endDate = endCalendar.getTime();
			r.setDate(new SimpleDateFormat("yyyy-MM-dd").format(endDate));
			r.setInterest(interest);
			r.setPrincipal(monthPrin);
			r.setTotal(r.getPrincipal() + interest);

			tr[i] = r;
			startDate = endDate;
			i++;
		}

		return tr;
	}

	// ����������
	private double getDayRate(double rate, int rateIsDay) {
		double dayRate;
		if (rateIsDay == 1) {
			dayRate = rate;
		} else {
			dayRate = rate / 365.0;
		}
		return dayRate;
	}

	// ���㵽������
	private Calendar getEndCalendar(Date d, int limit, int limitIsDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if (limitIsDay == 1) {
			// ������ް�����
			calendar.add(Calendar.DAY_OF_YEAR, limit);
		} else {
			// ������ް�����
			calendar.add(Calendar.MONTH, limit);
		}
		return calendar;
	}

	// ���������ת��Ϊ����
	private long getTotalDay(Date startDate, Calendar endCalendar) {
		long totalDay;
		long startTime = startDate.getTime();
		long endTime = endCalendar.getTimeInMillis();
		totalDay=(endTime-startTime)/(1000*3600*24);
		return totalDay;
	}
}
