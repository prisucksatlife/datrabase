package products;

/**
 * Transaction record (mostly just to hold information)
 *
 * @author Priyangkar Ghosh
 * @version 2022-11-23
 */
public record Transaction(Account from, Account to, String desc, double amount) {
	@Override
	public String toString() {
		return desc;
	}
	
	/**
	 * @summary validates a transaction
	 * @param {t} transaction that should be validated
	 * @return {boolean} whether or not the transaction is valid
	 */
	public static boolean validate(Transaction t) {
		if (t.amount <= 0)
			return false;
		if (t.from == null || t.to == null)
			return true;

		switch (t.from.type) {
			case Chequing:
				if (t.to.type == Account.Type.Chequing)
					return false;
				break;
	
			case Savings:
				if (t.to.type == Account.Type.Savings)
					return false;
				break;
	
			case Credit:
				if (t.to.type == Account.Type.Credit)
					return false;
				break;
	
			default:
				if (t.to.type == Account.Type.Credit)
					return false;
				break;
		}
		return true;
	}
}
