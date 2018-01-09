package tests;

/**
 * @author V.Jechsmayr
 * Datum: 11.03.2016
 */

import accounts.AccException;
import accounts.CreditAccount;
import accounts.SavingsAccount;
import accounts.TransferAccount;

public class TestAccounts {

	public static void main(String[] args) {
		testAccNumb();
		testBalance();
		testWithdraw();
		testDeposit();
		testUndo();

		// Exception-Tests
		testCreditInitException();
		testWithdrawException();
		testDepositException();

		// Testfall Angabe
		testCaseAngabe();
	}

	static void testAccNumb() {
		TransferAccount t = new TransferAccount(500, 700);
		SavingsAccount s = new SavingsAccount(1000);

		test("testAccNumb - TransferAccount", 1, t.getAccNumb());
		test("testAccNumb - SavingsAccount", 2, s.getAccNumb());
		try {
			CreditAccount c = new CreditAccount(10000);
			test("testAccNumb - CreditAccount", 3, c.getAccNumb());
		} catch (AccException e) {
			System.out.println(" Fehler: AccException nicht definiert!");
		}
	}

	static void testBalance() {
		TransferAccount t = new TransferAccount(500, 700);
		SavingsAccount s = new SavingsAccount(1000);

		test("testBalance - TransferAccount", 500, t.getBalance());
		test("testBalance - SavingsAccount", 1000, s.getBalance());

		try {
			CreditAccount c = new CreditAccount(10000);
			test("testBalance - CreditAccount", -10000, c.getBalance());
		} catch (AccException e) {
			System.out.println(" Fehler: AccException nicht definiert!");
		}
	}

	static void testWithdraw() {
		TransferAccount t = new TransferAccount(500, 700);
		SavingsAccount s = new SavingsAccount(1000);

		try {
			test("testWithdraw - TransferAccount", 500, t.getBalance());
			t.withdraw(200);
			test("testWithdraw - TransferAccount", 300, t.getBalance());

			test("testWithdraw - SavingsAccount", 1000, s.getBalance());
			s.withdraw(500);
			test("testWithdraw - SavingsAccount", 500, s.getBalance());
		} catch (AccException e1) {
			System.out.println(" Fehler: AccException nicht definiert!");
		}
	}

	static void testDeposit() {
		TransferAccount t = new TransferAccount(500, 700);
		SavingsAccount s = new SavingsAccount(1000);

		try {
			CreditAccount c = new CreditAccount(10000);

			test("testDeposit - TransferAccount", 500, t.getBalance());
			t.deposit(400);
			test("testDeposit - TransferAccount", 900, t.getBalance());

			test("testDeposit - SavingsAccount", 1000, s.getBalance());
			s.deposit(200);
			test("testDeposit - SavingsAccount", 1200, s.getBalance());

			test("testDeposit - CreditAccount", -10000, c.getBalance());
			c.deposit(5000);
			test("testDeposit - CreditAccount", -5000, c.getBalance());
		} catch (AccException e) {
			System.out.println(" Fehler: AccException nicht definiert!");
		}

	}

	static void testUndo() {
		TransferAccount t = new TransferAccount(500, 700);
		SavingsAccount s = new SavingsAccount(1000);

		try {
			CreditAccount c = new CreditAccount(10000);

			test("testUndo - TransferAccount", 500, t.getBalance());
			t.withdraw(200);
			t.undoLastOperation();
			test("testUndo - TransferAccount", 500, t.getBalance());

			test("testUndo - SavingsAccount", 1000, s.getBalance());
			s.deposit(200);
			s.undoLastOperation();
			test("testUndo - SavingsAccount", 1000, s.getBalance());

			test("testUndo - CreditAccount", -10000, c.getBalance());
			c.deposit(1000);
			c.undoLastOperation();
			test("testUndo - CreditAccount", -10000, c.getBalance());

		} catch (AccException e) {
			System.out.println(" Fehler: AccException nicht definiert!");
		}

	}

	static void testCreditInitException() {
		try {
			CreditAccount c = new CreditAccount(-10000);
			System.out.println(" Fehler: AccException wird erwartet!");
		} catch (AccException e) {
			test("testCreditInitException - negativer Init-Wert", 0, e.getAccount().getBalance(), e);
		}
	}

	static void testWithdrawException() {
		TransferAccount t = new TransferAccount(500, 700);
		SavingsAccount s = new SavingsAccount(1000);
		TransferAccount t2 = new TransferAccount();

		try {
			// System.out.println(" -- Test: TransferAccount zu viel
			// abgehoben(Limit überschritten -- ");
			t.withdraw(2000);
			System.out.println(" Fehler: AccException wird erwartet!");

		} catch (AccException e) {
			test("testWithdrawException - Limit überschritten", 500, e.getAccount().getBalance(), e);
		}

		try {
			// System.out.println(" -- Test: SavingsAccount negativer Betrag
			// abgehoben -- ");
			s.withdraw(-200);
			System.out.println(" Fehler: AccException wird erwartet!");

		} catch (AccException e) {
			test("testWithdrawException - negativer Betrag abgehoben", 1000, e.getAccount().getBalance(), e);
		}

		try {
			// System.out.println(" -- Test: TransferAccount Standard-Limit
			// überschritten -- ");
			t2.withdraw(500);
			System.out.println(" Fehler: AccException wird erwartet!");
		} catch (AccException e) {
			test("testWithdrawException - Standard-Limit überschritten", 0, e.getAccount().getBalance(), e);
		}

	}

	static void testDepositException() {
		SavingsAccount s = new SavingsAccount(1000);
		try {
			CreditAccount c = new CreditAccount(10000);
			c.deposit(12000);
			System.out.println(" Fehler: AccException wird erwartet!");
		} catch (AccException e) {
			test("testDepositException - zu viel aufgebucht (KreditLimit überbucht)", -10000,
					e.getAccount().getBalance(), e);
		}

		try {
			s.deposit(-5000);
			System.out.println(" Fehler: AccException wird erwartet!");
		} catch (AccException e) {
			test("testDepositEeption - negativer Betrag eingezahlt", 1000, e.getAccount().getBalance(), e);
		}
	}

	static void testCaseAngabe() {
		SavingsAccount s = new SavingsAccount(1000);

		try {
			CreditAccount c = new CreditAccount(-500);

			s.withdraw(800);
			c.deposit(800);
		} catch (AccException e) {
			s.undoLastOperation();
			test("Spezieller Testfall nach Angabe", 0, e.getAccount().getBalance(), e);
		}

	}

	// +++++++++++++++++++++++

	private static void test(String testName, int exp, Object res) {
		System.out.println("--- " + testName + " ---");
		System.out.println("     + Erwarteter Wert: " + exp);
		System.out.println("    ++ Tatsächlicher Wert: " + res);

		if (isNull(exp) && isNull(res)) {
			System.out.println("Fehler! Werte sind eventuell null!");
		}
		System.out.println("");
	}

	private static void test(String testName, int exp, Object res, AccException e) {
		System.out.println("--- " + testName + " ---");
		System.out.println("     + Erwarteter Wert: " + exp);
		System.out.println("    ++ Tatsächlicher Wert: " + res);

		System.out.println("Account: " + e.getAccount().toString());
		System.out.println(" - Exception: " + e.getMessage());

		if (isNull(exp) && isNull(res)) {
			System.out.println("Fehler! Werte sind eventuell null!");
		}
		System.out.println("");
	}

	private static boolean isNull(Object o) {
		if (o == null) {
			return true;
		}
		return false;
	}
}
