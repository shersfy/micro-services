package org.shersfy.tcc.style;

import java.util.Calendar;
import java.util.Date;

public class AppHk2Tokyo implements Tcc{

	private int total;
	private int type;
	private Date expire;

	public AppHk2Tokyo(int total) {
		this.total = total;
	}

	@Override
	public boolean tryi() {
		total--;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 10);

		expire = cal.getTime();
		System.out.println(name()+": 已预订票位 1, 余票 "+total);
		return true;
	}

	@Override
	public boolean confirm() {
		if(type==2 || type==3) {
			return false;
		}
		return true;
	}

	@Override
	public boolean cancel() {
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
		return "香港--东京";
	}

}
