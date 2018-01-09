package accounts;

/**
 * @author V.Jechsmayr
 * Datum: 10.03.2016
 */

public class SavingsAccount extends Account {

	public SavingsAccount() {
		this(0);
	}

	public SavingsAccount(int bal) {
		super(bal);
		if (bal < 0) {
			this.setBalance(0);
		}
	}

	@Override
	public void withdraw(int a) throws AccException {
		if ((getBalance() - a) >= 0) {
			super.withdraw(a);
		} else {
			// System.out.println("Konto darf nicht negativ belastet werden!");
			throw new AccException(ExceptionReason.BALANCE_NEGATIVE, this);
		}
	}

	@Override
	public void deposit(int a) throws AccException {
		super.deposit(a);
	}
}
