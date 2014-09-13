package com.weaforce.system.component.signature;

/**
 * 状态模式:执行签名动作上下文
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class SignContext {
	public static final ISignState makkingState = new MakingState();
	public static final ISignState checkingState = new CheckingState();
	public static final ISignState confirmingState = new ConfirmingState();
	public static final ISignState approvingState = new ApprovingState();

	private ISignState signState;

	public void setSignature(ISignState signature) {
		this.signState = signature;
		this.signState.setSignContext(this);
	}

	public void make() {
		this.signState.make();
	}

	public void check() {
		this.signState.check();
	}

	public void confirm() {
		this.signState.confirm();
	}

	public void approve() {
		this.signState.approve();
	}
}
