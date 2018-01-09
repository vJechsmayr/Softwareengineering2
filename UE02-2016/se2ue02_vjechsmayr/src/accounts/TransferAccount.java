package accounts;

/**
 * @author V.Jechsmayr
 * Datum: 10.03.2016
 */

public class TransferAccount extends Account {

	private int maxLimit;

	public TransferAccount() {
		this(0);
	}

	public TransferAccount(int bal) {
		this(bal, 400);
	}

	public TransferAccount(int bal, int limit) {
		super(bal);
		if (limit > 0) {
			this.maxLimit = limit;
		} else {
			this.maxLimit = 0;
		}
	}

	@Override
	public void withdraw(int a) throws AccException {
		if ((getBalance() - a) >= (-maxLimit)) {
			super.withdraw(a);
		} else {
			// System.out.println("Fehler! Maximales Limit überzogen");
			throw new AccException(ExceptionReason.OVER_MAXLIMIT, this);
		}
	}

	@Override
	public void deposit(int a) throws AccException {
		super.deposit(a);
	}
}
