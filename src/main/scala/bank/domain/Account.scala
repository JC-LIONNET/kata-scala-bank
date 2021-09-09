package bank.domain

import java.time.Clock

case class Account(
                    balance: Balance = Balance(),
                    history: Statement = Statement(),
                    clock: Clock = Clock.systemDefaultZone()
                  ) {
  def deposit(amount: Amount): Account = {
    val newValue: BigDecimal = balance.value + amount.value
    val newBalance: Balance = Balance(newValue)
    val updatedHistory: Statement = history.addDeposit(newBalance,amount,clock.instant())
    copy(balance = newBalance, history = updatedHistory)
  }

  def withdrawal(amount: Amount): Account = {
    val newValue: BigDecimal = balance.value - amount.value
    val newBalance: Balance = Balance(newValue)
    val updateHistory: Statement = history.addWithdrawal(newBalance, amount, clock.instant())
    copy(balance = newBalance, history = updateHistory)
  }

  def print(printer: StatementPrinter): Unit = printer.print(history)
}

object Account {
  def apply(amount: Amount): Account = Account(Balance(amount.value))
}