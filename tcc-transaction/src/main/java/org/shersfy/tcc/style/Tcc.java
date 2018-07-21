package org.shersfy.tcc.style;

import java.util.Date;

public interface Tcc {
	
	public boolean tryi();
	
	public boolean confirm();
	
	public boolean cancel();

	public Date getExpire();

	public String name();

	public int getTotal();
	
}
