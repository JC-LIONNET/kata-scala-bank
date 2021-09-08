package bank.domain

case class Account(
                    balance: Balance = Balance()
                  ) {
  def deposit(amount: Amount): Account = {
    val newValue: BigDecimal = balance.value + amount.value
    copy(balance = Balance(newValue))
  }

  def withdrawal(amount: Amount): Account = {
    val newValue: BigDecimal = balance.value - amount.value
    copy(balance = Balance(newValue))
  }
}

object Account {
  def apply(amount: Amount): Account = Account(
    Balance(amount.value)
  )
}