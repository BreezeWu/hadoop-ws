class BankAccount {
	private var balance = 0

	def deposit(amount: Int) {
		if (amount > 0) balance += amount
	}

	def withdraw(amount: Int): Int =
		if (0 < amount && amount <= balance) {
			balance -= amount
			balance
		} else error("insufficient funds")
}

// 
val myAccount = new BankAccount
val account = new BankAccount

account deposit 50
account withdraw 20
account withdraw 20
account withdraw 15
