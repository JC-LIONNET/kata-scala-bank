package bank.domain

import org.scalatest.flatspec.AnyFlatSpec

import java.time.{Clock, Instant, ZoneId}

class AccountSpec extends AnyFlatSpec {
  behavior of "AccountSpec"

  it should "Be initialized with 0" in {
    //Given
    val actualAccount: Account = Account()

    //When
    val actualBalance: Balance = actualAccount.balance

    //Then
    val expectedBalance: Balance = Balance()
    assertResult(expectedBalance, s"The starting value of money should be ${expectedBalance}")(actualBalance)
  }

  it should "Be initialized with 1000" in {
    val iniAmount: Amount = Amount(1000)
    val actualAccount: Account = Account(amount = iniAmount)

    //When
    val actualBalance: Balance = actualAccount.balance

    //Then
    val expectedBalance: Balance = Balance(1000)
    assertResult(expectedBalance, s"The starting value of money should be ${expectedBalance}")(actualBalance)
  }

  it should "Deposit 1000 on the account" in {
    //Given
    val initAccount: Account = Account()

    //When
    val accountWithDeposit: Account = initAccount.deposit(Amount(1000))

    //Then
    val expectedBalance: Balance = Balance(1000)
    assertResult(expectedBalance, s"The value of the balance should be ${expectedBalance}")(accountWithDeposit.balance)
  }

  it should "Deposit 1000 and then 500 on the account" in {
    //Given
    val initAccount: Account = Account()

    //When
    val accountWithDeposit: Account = initAccount.deposit(Amount(1000)).deposit(Amount(500))

    //Then
    val expectedBalance: Balance = Balance(1500)
    assertResult(expectedBalance, s"The value of the balance should be ${expectedBalance}")(accountWithDeposit.balance)
  }

  it should "Withdrawal 1000 from account" in {
    //Given
    val initAccount: Account = Account()

    //When
    val accountWithWithdrawal: Account = initAccount.withdrawal(Amount(500))

    //Then
    val expectedBalance: Balance = Balance(-500)
    assertResult(expectedBalance, s"The value of the balance should be ${expectedBalance}")(accountWithWithdrawal.balance)
  }

  it should "Withdrawal 1000 and then 500 from the account" in {
    //Given
    val initAccount: Account = Account()

    //When
    val accountWithWithdrawals: Account = initAccount.withdrawal(Amount(1000)).withdrawal(Amount(500))

    //Then
    val expectedBalance: Balance = Balance(-1500)
    assertResult(expectedBalance, s"The value of the balance should be ${expectedBalance}")(accountWithWithdrawals.balance)
  }

  it should "Deposit 1000 and then withdrawal 500 from the account" in {
    //Given
    val initAccount: Account = Account()

    //When
    val accountWithDepositAndWithdrawal: Account = initAccount.deposit(Amount(1000)).withdrawal(Amount(500))

    //Then
    val expectedBalance: Balance = Balance(500)
    assertResult(expectedBalance, s"The value of the balance should be ${expectedBalance}")(accountWithDepositAndWithdrawal.balance)
  }

  it should "Be initialized with empty list of StatementLine " in {
    //Given
    val initAccount: Account = Account()

    //When
    val actualStatement: Statement = initAccount.history

    //Then
    val expectedStatementLinesList: List[StatementLine] = List.empty
    assertResult(expectedStatementLinesList, "The Statement should be initialized with empty list of StatementLine")(actualStatement.listStatementLines)
  }

  it should "Deposit 1000 and then complete the account history with the operation" in {
    //Given
    val fixedInstant: Instant = Instant.now()
    val clockFixed: Clock = Clock.fixed(fixedInstant, ZoneId.systemDefault())
    val initAccount: Account = Account(clock = clockFixed)

    //When
    val accountWithDeposit: Account = initAccount.deposit(Amount(1000))

    //Then
    val expectedOperation: Operation = Operation(OperationType.DEPOSIT, Amount(1000), fixedInstant)
    val expectedStatementLine: StatementLine = StatementLine(expectedOperation, Balance(1000))
    val expectedHistory: Statement = Statement(List(expectedStatementLine))

    assertResult(expectedHistory, "The statement line should be the same")(accountWithDeposit.history)
  }

  it should "Deposit 1000 and 500 then complete the account history with the operations" in {
    //Given
    val fixedInstant: Instant = Instant.now()
    val clockFixed: Clock = Clock.fixed(fixedInstant, ZoneId.systemDefault())
    val initAccount: Account = Account(clock = clockFixed)

    //When
    val accountWithDeposit: Account = initAccount.deposit(Amount(1000)).deposit(Amount(500))

    //Then
    val expectedOperation: Operation = Operation(OperationType.DEPOSIT, Amount(1000), fixedInstant)
    val expectedOperation2: Operation = Operation(OperationType.DEPOSIT, Amount(500), fixedInstant)
    val expectedStatementLine: StatementLine = StatementLine(expectedOperation, Balance(1000))
    val expectedStatementLine2: StatementLine = StatementLine(expectedOperation2, Balance(1500))
    val expectedHistory: Statement = Statement(List(expectedStatementLine2, expectedStatementLine))

    assertResult(expectedHistory, "The statement lines should be the same")(accountWithDeposit.history)
  }

  it should "Withdrawal 500 then complete the account history with the operation" in {
    //Given
    val fixedInstant: Instant = Instant.now()
    val clockFixed: Clock = Clock.fixed(fixedInstant, ZoneId.systemDefault())
    val initAccount: Account = Account(clock = clockFixed)

    //When
    val accountWithWithdrawal: Account = initAccount.withdrawal(Amount(500))

    //Then
    val expectedOperation: Operation = Operation(OperationType.WITHDRAWAL, Amount(500), fixedInstant)
    val expectedStatementLine: StatementLine = StatementLine(expectedOperation, Balance(-500))
    val expectedHistory: Statement = Statement(List(expectedStatementLine))

    assertResult(expectedHistory, "The statement line should be the same")(accountWithWithdrawal.history)
  }

  it should "Withdrawal 500 and 400 then complete the account history with the operations" in {
    //Given
    val fixedInstant: Instant = Instant.now()
    val clockFixed: Clock = Clock.fixed(fixedInstant, ZoneId.systemDefault())
    val initAccount: Account = Account(clock = clockFixed)

    //When
    val accountWithWithdrawals: Account = initAccount.withdrawal(Amount(500)).withdrawal(Amount(400))

    //Then
    val expectedOperation: Operation = Operation(OperationType.WITHDRAWAL, Amount(500), fixedInstant)
    val expectedOperation2: Operation = Operation(OperationType.WITHDRAWAL, Amount(400), fixedInstant)
    val expectedStatementLine: StatementLine = StatementLine(expectedOperation, Balance(-500))
    val expectedStatementLine2: StatementLine = StatementLine(expectedOperation2, Balance(-900))
    val expectedHistory: Statement = Statement(List(expectedStatementLine2, expectedStatementLine))

    assertResult(expectedHistory, "The statement lines should be the same")(accountWithWithdrawals.history)
  }
}
