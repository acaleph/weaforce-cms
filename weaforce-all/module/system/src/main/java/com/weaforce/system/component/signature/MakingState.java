package com.weaforce.system.component.signature;

/**
 * 制作
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class MakingState implements ISignState {
	protected SignContext signContext;
	protected ISignState priorState;
	protected ISignState nextState;

	public MakingState() {

	}

	public MakingState(ISignState _priorState, ISignState _nextState) {
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

		System.out.println("Making...");
	}

	
	public void check() {
		if (priorState instanceof CheckingState
				|| nextState instanceof CheckingState) {
			this.signContext.setSignature(SignContext.checkingState);
			this.signContext.check();
		}
	}

	
	public void confirm() {
		if (priorState instanceof ConfirmingState
				|| nextState instanceof ConfirmingState) {
			this.signContext.setSignature(SignContext.confirmingState);
			this.signContext.check();
		}
	}

	
	public void approve() {
		if (priorState instanceof ApprovingState
				|| nextState instanceof ApprovingState) {
			this.signContext.setSignature(SignContext.approvingState);
			this.signContext.check();
		}
	}

}
