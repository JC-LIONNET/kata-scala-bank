package bank.domain

case class Account(
                    balance: Balance = Balance()
                  ) {
  def deposit(amount: Amount): Account = copy(balance = Balance(amount.value))
}

object Account {
  def apply(amount: Amount): Account = Account(
    Balance(amount.value)
  )
}