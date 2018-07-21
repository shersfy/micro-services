package org.shersfy.tcc.style;

public class AppBooking{
	
	private int retry = 5;

	public boolean tryi(Tcc busi) {
		return busi.tryi();
	}

	public boolean cancel(Tcc busi) throws InterruptedException {
		while(!busi.cancel()) {
			if(retry-- == 0) {
				System.err.println(busi.name()+"警告：产生异常记录, 回滚已确认异常,进行人工干预");
				return false;
			}
			Thread.sleep(1000);
		}
		return true;
	}

}
