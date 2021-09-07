package bank.domain

import org.scalatest.flatspec.AnyFlatSpec

class AccountSpec extends AnyFlatSpec {
  behavior of "AccountSpec"

  it should "Be initialized at 0" in {
    //Given
    val actualAccount: Account = Account()

    //When
    val actualBalance: Balance = actualAccount.balance

    //Then
    val expectedBalance: Balance = Balance()
    assertResult(expectedBalance, s"The starting value of money should be ${expectedBalance}")(actualBalance)
  }
}
