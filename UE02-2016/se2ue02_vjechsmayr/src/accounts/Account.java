package accounts;

/**
 * @author V.Jechsmayr
 * Datum: 10.03.2016
 */

public abstract class Account {

	private int accNumb;
	private int balance;

	private static int nextID = 1;

	private Operation undoOp;
	private int lastBalance;

	public Account(int b) {
		accNumb = nextID;
		nextID++;
		this.balance = b;
		lastBalance = b;
		undoOp = Operation.NONE;
	}

	public Account() {
		this(0);
	}

	public int getBalance() {
		return balance;
	}

	public int getAccNumb() {
		return accNumb;
	}

	public void setBalance(int b) {
		lastBalance = this.balance;
		this.balance = b;
	}

	public void withdraw(int a) throws AccException {
		if (a > 0) {
			setBalance(balance - a);
			undoOp = Operation.WITHDRAW;
		} else {
			// System.out.println("Negativer Wert! Bitte Eingabe kontrollieren!");
			throw new AccException(ExceptionReason.AMOUNT_NEGATIVE, this);
		}
	}

	public void deposit(int a) throws AccException {
		if (a > 0) {
			setBalance(this.balance + a);
			undoOp = Operation.DEPOSIT;
		} else {
			// System.out.println("Negativer Wert! Bitte Eingabe kontrollieren!");
			throw new AccException(ExceptionReason.AMOUNT_NEGATIVE, this);
		}
	}

	public void undoLastOperation() {
		if (undoOp != Operation.NONE) {
			setBalance(lastBalance);
			undoOp = Operation.NONE;
		}
	}

	@Override
	public String toString() {
		String s = "ID: " + getAccNumb() + " Balance: " + getBalance();
		return s;
	}
}
