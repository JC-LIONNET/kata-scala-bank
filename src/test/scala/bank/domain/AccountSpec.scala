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

  it should "Be initialized with 100" in {
    val iniAmount : Amount = Amount(1000)
    val actualAccount: Account = Account(amount = iniAmount)

    //When
    val actualBalance: Balance = actualAccount.balance

    //Then
    val expectedBalance: Balance = Balance(1000)
    assertResult(expectedBalance, s"The starting value of money should be ${expectedBalance}")(actualBalance)
  }
}
