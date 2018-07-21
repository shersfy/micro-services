package org.shersfy.tcc.style;

import java.util.Calendar;
import java.util.Date;

public class AppHz2Hk implements Tcc{

	private int total;
	private int type;
	private Date expire;
	
	public AppHz2Hk(int total) {
		this.total = total;
	}

	@Override
	public boolean tryi() {
		if(type==1) {
			System.out.println(name()+": 预定票位失败");
			return false;
		}
		total--;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 10);
		
		expire = cal.getTime();
		System.out.println(name()+": 已预订票位 1, 余票 "+total);
		return true;
	}

	@Override
	public boolean confirm() {
		return true;
	}

	@Override
	public boolean cancel() {
		if(type==3) {
			System.out.println(name()+": 释放票位失败");
			return false;
		}
		total++;
		System.out.println(name()+": 已释放票位 1, 余票 "+total);
		return true;
	}

	public int getTotal() {
		return total;
	}

	public Date getExpire() {
		return expire;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	@Override
	public String name() {
		return "杭州--香港";
	}

}
