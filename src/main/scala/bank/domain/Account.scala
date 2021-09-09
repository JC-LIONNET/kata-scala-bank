package bank.domain

import bank.domain.OperationType.OperationType

import java.time.{Clock, Instant}

case class Account(
                    balance: Balance = Balance(),
                    history: Statement = Statement(),
                    clock: Clock = Clock.systemDefaultZone()
                  ) {
  def deposit(amount: Amount): Account = {
    val newBalance: Balance = balance.add(amount)
    updateStatement(OperationType.DEPOSIT,newBalance,amount,clock.instant())
  }

  def withdrawal(amount: Amount): Account = {
    val newBalance: Balance = balance.subtract(amount)
    updateStatement(OperationType.WITHDRAWAL,newBalance, amount, clock.instant())
  }

  private def updateStatement(operationType: OperationType, newBalance: Balance, amount: Amount, instant: Instant): Account = {
    val updateHistory: Statement = history.addOperation(operationType, newBalance, amount, instant)
    copy(balance = newBalance, history = updateHistory)
  }

  def print(printer: StatementPrinter): Unit = printer.print(history)
}

object Account {
  def apply(amount: Amount): Account = Account(Balance(amount.value))
}