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
}
