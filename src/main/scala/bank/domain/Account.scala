package bank.domain

import bank.domain.OperationType.OperationType

import java.time.Clock

case class Account(
                    balance: Balance = Balance(),
                    history: Statement = Statement(),
                    clock: Clock = Clock.systemDefaultZone()
                  ) {
  def deposit(amount: Amount): Account = {
    val newBalance: Balance = balance.add(amount)
    updateStatement(OperationType.DEPOSIT,newBalance,amount)
  }

  def withdrawal(amount: Amount): Account = {
    val newBalance: Balance = balance.subtract(amount)
    updateStatement(OperationType.WITHDRAWAL,newBalance, amount)
  }

  private def updateStatement(operationType: OperationType, newBalance: Balance, amount: Amount): Account = {
    val updateHistory: Statement = history.addOperation(operationType, newBalance, amount, clock.instant())
    copy(balance = newBalance, history = updateHistory)
  }

  def print(printer: StatementPrinter): Unit = printer.print(history)
}

object Account {
  def apply(amount: Amount): Account = Account(Balance(amount.value))
}