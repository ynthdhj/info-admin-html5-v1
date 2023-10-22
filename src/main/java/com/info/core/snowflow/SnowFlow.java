package com.info.core.snowflow;

/*
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
public class SnowFlow {
	// 因为二进制里第一个 bit 为如果是 1，那么都是负数，但是我们生成的 id 都是正数，所以第一个 bit 统一都是 0。

	// 机器ID 2进制5位 32位减掉1位 31个
	private long workerId;
	// 机房ID 2进制5位 32位减掉1位 31个
	private long datacenterId;
	// 代表一毫秒内生成的多个id的最新序号 12位 4096 -1 = 4095 个
	private long sequence;
	// 设置一个时间初始值 2^41 - 1 差不多可以用69年
	private long twepoch = 1420041600000L;
	// 5位的机器id
	private long workerIdBits = 5L;
	// 5位的机房id；。‘
	private long datacenterIdBits = 5L;
	// 每毫秒内产生的id数 2 的 12次方
	private long sequenceBits = 12L;
	// 这个是二进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
	private long maxWorkerId = -1L ^ (-1L << workerIdBits);
	// 这个是一个意思，就是5 bit最多只能有31个数字，机房id最多只能是32以内
	private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

	private long workerIdShift = sequenceBits;
	private long datacenterIdShift = sequenceBits + workerIdBits;
	private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

	// -1L 二进制就是1111 1111 为什么？
	// -1 左移12位就是 1111 1111 0000 0000 0000 0000
	// 异或 相同为0 ，不同为1
	// 1111 1111 0000 0000 0000 0000
	// ^
	// 1111 1111 1111 1111 1111 1111
	// 0000 0000 1111 1111 1111 1111 换算成10进制就是4095
	private long sequenceMask = -1L ^ (-1L << sequenceBits);
	// 记录产生时间毫秒数，判断是否是同1毫秒
	private long lastTimestamp = -1L;

	public long getWorkerId() {
		return workerId;
	}

	public long getDatacenterId() {
		return datacenterId;
	}

	public long getTimestamp() {
		return System.currentTimeMillis();
	}

	// 是否发生了时钟回拨
	private boolean isBackwordsFlag = false;
	// 是否是第一次发生时钟回拨, 用于设置时钟回拨时间点
	private boolean isFirstBackwordsFlag = true;
	// 记录时钟回拨发生时间点, 用于判断回拨后的时间达到回拨时间点时, 跳过 已经用过的 时钟回拨发生时间点 之后的时间 到 未来时间的当前时间点
	private long backBaseTimestamp = -1L;

	public SnowFlow() {
	}

	public SnowFlow(long workerId, long datacenterId, long sequence) {

		// 检查机房id和机器id是否超过31 不能小于0
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}

		if (datacenterId > maxDatacenterId || datacenterId < 0) {

			throw new IllegalArgumentException(
					String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
		this.sequence = sequence;
	}

	// 这个是核心方法，通过调用nextId()方法，
	// 让当前这台机器上的snowflake算法程序生成一个全局唯一的id
	public synchronized long nextId() {
		// 这儿就是获取当前时间戳，单位是毫秒
		long timestamp = timeGen();

		// --20220813--1---------------------------------------
		if (isBackwordsFlag) {
			// 当回拨时间再次叨叨回拨时间点时, 跳过回拨这段时间里已经使用了的未来时间
			if (timestamp >= backBaseTimestamp && timestamp < lastTimestamp) {
				// 直接将当前时间设置为最新的未来时间
				timestamp = lastTimestamp;
			} else if (timestamp > lastTimestamp) {
				// 当前时间已经大于上次时间, 重置时钟回拨标志
				isBackwordsFlag = false;
				isFirstBackwordsFlag = true;
				System.out.println("时间已恢复正常-->" + timestamp);
			} else {
				// timestamp == lastTimestamp 等于的情况在后面
			}
		}
		// --20220813--1----------------------------------------

		// 判断是否小于上次时间戳，如果小于的话，就抛出异常
		if (timestamp < lastTimestamp) {

			System.err.printf("lastTimestamp=%d, timestamp=%d, l-t=%d \n", lastTimestamp, timestamp,
					lastTimestamp - timestamp);
//            throw new RuntimeException(
//                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
//                            lastTimestamp - timestamp));

			// --20220813--2---------------------------------------
			// 这里不再抛出异常, 改为记录时钟回拨发生时间点

			// 发生时钟回拨后, 当前时间 timestamp 就变成了 过去的时间
			// 此时将 timestamp 设置为 上一次时间, 即相对于当前时间的未来时间
			timestamp = lastTimestamp;
			isBackwordsFlag = true;

			// 记录时钟回拨发生的时间点, 后续需要跳过已经使用的未来时间段
			if (isFirstBackwordsFlag) {
				backBaseTimestamp = timestamp;
				isFirstBackwordsFlag = false;
				System.out.println("时钟回拨已发生-->" + backBaseTimestamp);
			}
			// --20220813--2---------------------------------------
		}

		// 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
		// 这个时候就得把seqence序号给递增1，最多就是4096
		if (timestamp == lastTimestamp) {

			// 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，
			// 这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
			sequence = (sequence + 1) & sequenceMask;
			// 当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
			if (sequence == 0) {
				// timestamp = tilNextMillis(lastTimestamp);

				// --20220813--3---------------------------------------
				// 这里也不能阻塞了, 因为阻塞方法中需要用到当前时间, 改为将此时代表未来的时间 加 1
				if (isBackwordsFlag) {
					lastTimestamp++;
				} else {
					timestamp = tilNextMillis(lastTimestamp);
				}
				// --20220813--3---------------------------------------
			}

		} else {
			// sequence = 0;
			// 每毫秒的序列号都从0开始的话，会导致没有竞争情况返回的都是偶数。解决方法是用时间戳&1，这样就会随机得到1或者0。
			sequence = timestamp & 1;
		}
		// 这儿记录一下最近一次生成id的时间戳，单位是毫秒
		// lastTimestamp = timestamp;

		// --20220813--4---------------------------------------
		if (isBackwordsFlag) {
			// 什么都不做
		} else {
			lastTimestamp = timestamp;
		}
		// --20220813--4---------------------------------------

		// 这儿就是最核心的二进制位运算操作，生成一个64bit的id
		// 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
		// 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
		long sn = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
				| (workerId << workerIdShift) | sequence;

		if (isBackwordsFlag) {
			System.out.printf("sn=%d\n", sn);
		}
		return sn;
	}

	/**
	 * 当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
	 * 
	 * @param lastTimestamp
	 * @return
	 */
	private long tilNextMillis(long lastTimestamp) {

		long timestamp = timeGen();

		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	// 获取当前时间戳
	private long timeGen() {
		return System.currentTimeMillis();
	}

	//////////////////////////////////

	private volatile static SnowFlow snowFlow;

	public static SnowFlow getInstance() {
		if (snowFlow == null) {
			synchronized (SnowFlow.class) {
				if (snowFlow == null) {
					snowFlow = new SnowFlow(1, 1, 1);
				}
			}
		}
		return snowFlow;
	}

	/**
	 * 获得下一个ID (该方法是线程安全的)
	 *
	 * @return SnowflakeId
	 */
	public static Long getNextId() {
		long l = getInstance().nextId();
		return l;
	}

}
