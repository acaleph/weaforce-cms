package com.weaforce.system.component.signature;

/**
 * 签名
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public interface ISignState {
	/**
	 * 签名上下文
	 * 
	 * @param signContext
	 */
	public void setSignContext(SignContext signContext);
	/**
	 * 上一状态
	 * @param priorState
	 */
	public void setPriorState(ISignState priorState);
	/**
	 * 下五状态
	 * @param nextState
	 */
	public void setNextState(ISignState nextState);

	/**
	 * 制作
	 */
	public void make();

	/**
	 * 检查
	 */
	public void check();

	/**
	 * 确认
	 */
	public void confirm();

	/**
	 * 承认
	 */
	public void approve();
}
