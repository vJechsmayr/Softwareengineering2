package accounts;

/**
 * @author V.Jechsmayr
 * Datum: 11.03.2016
 */

public class AccException extends Exception {

	private Account acc;

	public AccException(ExceptionReason reason, Account acc) {
		super(reason.getMessage());
		this.acc = acc;
	}

	public Account getAccount() {
		return acc;
	}
}
