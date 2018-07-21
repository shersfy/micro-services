package org.shersfy.tcc.style;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class TccTransactionManager {

	public static void main(String[] args) throws Exception {
		
		booking(0); // 全部OK
		System.out.println("=============");
		booking(1); // try NG
		System.out.println("=============");
		booking(2); // confirm NG
		System.out.println("=============");
		booking(3); // cancel NG

	}


	public static void booking(int type) throws Exception {

		CountDownLatch latch = new CountDownLatch(2);
		// 主业务：杭州--东京(转香港)订票系统
		// 从业务A：杭州飞香港航班订票
		// 从业务B：香港飞东京航班订票
		AppBooking booking  = new AppBooking();
		AppHz2Hk a    = new AppHz2Hk(5);
		AppHk2Tokyo b = new AppHk2Tokyo(10);

		a.setType(type);
		b.setType(type);

		Map<String, Boolean> result = new ConcurrentHashMap<>();

		// 阶段1：try，由主业务发起
		System.out.println("事务开始");
		print(a, b);
		Thread busiA = new Thread(new Runnable() {
			@Override
			public void run() {
				boolean return1 = booking.tryi(a);
				result.put("a", return1);
				latch.countDown();
			}
		});
		Thread busiB = new Thread(new Runnable() {
			@Override
			public void run() {
				boolean return2 = booking.tryi(b);
				result.put("b", return2);
				latch.countDown();
			}
		});

		busiA.start();
		busiB.start();
		latch.await();

		// 从业务A try失败，从业务B回滚预定
		if(!result.get("a")) {
			b.cancel();
			print(a, b);
			System.out.println("事务结束");
			return;
		}

		// 从业务B try败，从业务A回滚预定
		if(!result.get("b")) {
			a.cancel();
			print(a, b);
			System.out.println("事务结束");
		}



		// 阶段2：confirm，由事务管理器发起
		TccTransactionManager tm = new TccTransactionManager();

		CountDownLatch latchtm = new CountDownLatch(2);
		Thread busiAtm = new Thread(new Runnable() {
			@Override
			public void run() {
				// 业务A发起确认
				boolean return1 = tm.confirm(a);
				result.put("a", return1);
				latchtm.countDown();
			}
		});
		Thread busiBtm = new Thread(new Runnable() {
			@Override
			public void run() {
				// 业务B发起确认
				boolean return2 = tm.confirm(b);
				result.put("b", return2);
				latchtm.countDown();
			}
		});

		busiAtm.start();
		busiBtm.start();
		latchtm.await();
		
		// 从业务A confirm失败，主业务调用从业务A B回滚try
		if(!result.get("a")) {
			booking.cancel(a);
			booking.cancel(b);
			print(a, b);
			System.out.println("事务结束");
			return;
		}

		// 从业务B confirm败，主业务调用从业务A B回滚try
		if(!result.get("b")) {
			booking.cancel(a);
			booking.cancel(b);
			print(a, b);
			System.out.println("事务结束");
			return;
		}

		print(a, b);
		System.out.println("事务结束");
	}

	// 事务管理发起确认
	private boolean confirm(Tcc busi) {
		
		while(!busi.confirm()) {
			if(System.currentTimeMillis()>busi.getExpire().getTime()) {
				System.out.println(busi.name()+": 票位占用超时");
				return false;
			}
			System.out.println(busi.name()+": confirm失败重试");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}

	private static void print(Tcc ...busi) {
		for(Tcc tcc : busi) {
			System.out.println(String.format("%s: 余票 %s", tcc.name(), tcc.getTotal()));
		}

	}



}
