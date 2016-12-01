package com.cxypub.baseframework.sdk.dao;

public interface IGenericDAO extends IReadDAO, IWriteDAO {
	/**
	 * 
	 * @功能说明：调用存储过程，对无返回值的存储过程调用
	 * @author xufei
	 * @date  2014-10-29 下午1:41:44
	 * @param procedure 存储过程名称
	 * @param inputParams 调用存储过程的传入参数
	 * @param outParamsTypes 存储过程执行后的返回值
	 * @return
	 */
	String[] execProcedure(final String procedure, final Object[] inputParams, final int[] outParamsTypes);
}
