package bank.domain

case class Account(
                    balance: Balance = Balance()
                  ) {
}

object Account {
  def apply(amount: Amount): Account = Account(
    Balance(amount.amount)
  )
}