package accounts;

/**
 * @author V.Jechsmayr
 * Datum: 10.03.2016
 */

public enum ExceptionReason {
	OVER_MAXLIMIT("Das maximale Limit wurde erreicht!"), 
	BALANCE_NEGATIVE("Der Kontostand kann nicht negativ werden!"), 
	AMOUNT_NEGATIVE("Der Betrag kann nicht negativ sein!"), 
	CREDACC_OVERFLOW("Auf den Kreditaccount kann der Betrag nicht gebucht werden.");

	private String msg;

	private ExceptionReason(String message) {
		this.msg = message;
	}

	public String getMessage() {
		return msg;
	}
}
