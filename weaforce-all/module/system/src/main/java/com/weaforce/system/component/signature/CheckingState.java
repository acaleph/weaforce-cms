package com.weaforce.system.component.signature;

/**
 * 检查
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class CheckingState implements ISignState {
	protected SignContext signContext;
	protected ISignState priorState;
	protected ISignState nextState;
	
	public CheckingState(){
		
	}

	public CheckingState(ISignState _priorState, ISignState _nextState) {
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
		this.signContext.setSignature(SignContext.checkingState);
		this.signContext.check();
	}

	
	public void confirm() {

	}

	
	public void approve() {

	}
}
