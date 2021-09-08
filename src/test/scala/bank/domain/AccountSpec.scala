package bank.domain

import org.scalatest.flatspec.AnyFlatSpec

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
    val iniAmount : Amount = Amount(1000)
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
}
