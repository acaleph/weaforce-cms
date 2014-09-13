package com.weaforce.system.component.signature;

/**
 * 承认
 * 
 * 应用代理模式,指定真正执行签名的人,利用构造方法,完成注入
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class ApprovingState implements ISignState {

	protected SignContext signContext;

	protected ISignState priorState;
	protected ISignState nextState;
	
	public ApprovingState(){
		
	}

	public ApprovingState(ISignState _priorState, ISignState _nextState) {
		this.priorState = _priorState;
		this.nextState = _nextState;
	}

	public void setPriorState(ISignState _priorState) {
		this.priorState = _priorState;
	}

	public void setNextState(ISignState _nextState) {
		this.nextState = _nextState;
	}

	
	public void setSignContext(SignContext signContext) {
		this.signContext = signContext;
	}

	
	public void make() {

	}

	
	public void check() {

	}

	
	public void confirm() {

	}

	
	public void approve() {
		this.signContext.setSignature(SignContext.approvingState);
		this.signContext.approve();
	}

}
