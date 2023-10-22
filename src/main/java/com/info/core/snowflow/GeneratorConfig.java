package com.info.core.snowflow;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * 自定义 hibernate 主键产生策略为 雪花算法
 * @author 段洪杰
 **/

public class GeneratorConfig implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
			throws HibernateException {
		return SnowFlow.getNextId();
	}
}
