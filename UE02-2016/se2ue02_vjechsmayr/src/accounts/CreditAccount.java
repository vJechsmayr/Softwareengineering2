package accounts;

/**
 * @author V.Jechsmayr
 * Datum: 10.03.2016
 */

public class CreditAccount extends Account {

	public CreditAccount() throws AccException {
		this(0);
	}

	public CreditAccount(int bal) throws AccException {
		super();
		if (bal > 0) {
			this.setBalance(-bal);
		} else {
			throw new AccException(ExceptionReason.CREDACC_OVERFLOW, this);
		}
	}

	@Override
	public void deposit(int a) throws AccException {
		if ((getBalance() + a) <= 0) {
			super.deposit(a);
		} else {
			// System.out.println("Betrag nicht passend für
			// Kreditrückzahlung!");
			throw new AccException(ExceptionReason.CREDACC_OVERFLOW, this);
		}
	}
}
