package com.weaforce.system.component.signature;

/**
 * 确认
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class ConfirmingState implements ISignState {
	protected SignContext signContext;
	
	protected ISignState priorState;
	protected ISignState nextState;
	
	public ConfirmingState(){
		
	}
	
	public ConfirmingState(ISignState _priorState, ISignState _nextState) {
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
		this.signContext.setSignature(SignContext.confirmingState);
		this.signContext.confirm();
	}

	
	public void approve() {

	}

}
