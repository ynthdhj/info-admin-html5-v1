package com.info.core.snowflow;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * 自定义 hibernate 主键产生策略为 雪花算法
 **/
/*
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
public class GeneratorConfig implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
			throws HibernateException {
		return SnowFlow.getNextId();
	}
}
