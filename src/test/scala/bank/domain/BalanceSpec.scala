package bank.domain

import org.scalatest.flatspec.AnyFlatSpec

class BalanceSpec extends AnyFlatSpec {

  behavior of "BalanceSpec"

  it should "Add 1000 to the balance value" in {
    //Given
    val initBalance: Balance = Balance()

    //Then
    val actualBalance: Balance = initBalance.add(Amount(1000))

    //When
    val expectedBalance: Balance = Balance(1000)

    assertResult(expectedBalance, "The value should be the same.")(actualBalance)
  }

  it should "Add 1000 and 500 to the balance value" in {
    //Given
    val initBalance: Balance = Balance()

    //Then
    val actualBalance: Balance = initBalance.add(Amount(1000)).add(Amount(500))

    //When
    val expectedBalance: Balance = Balance(1500)

    assertResult(expectedBalance, "The value should be the same.")(actualBalance)
  }

  it should "Subtract 1000 to the balance value" in {
    //Given
    val initBalance: Balance = Balance()

    //Then
    val actualBalance: Balance = initBalance.subtract(Amount(1000))

    //When
    val expectedBalance: Balance = Balance(-1000)

    assertResult(expectedBalance, "The value should be the same.")(actualBalance)
  }

  it should "Subtract 1000 and 500 to the balance value" in {
    //Given
    val initBalance: Balance = Balance()

    //Then
    val actualBalance: Balance = initBalance.subtract(Amount(1000)).subtract(Amount(500))

    //When
    val expectedBalance: Balance = Balance(-1500)

    assertResult(expectedBalance, "The value should be the same.")(actualBalance)
  }

  it should "Add 1000 and subtract 500 to the balance value" in {
    //Given
    val initBalance: Balance = Balance()

    //Then
    val actualBalance: Balance = initBalance.add(Amount(1000)).subtract(Amount(500))

    //When
    val expectedBalance: Balance = Balance(500)

    assertResult(expectedBalance, "The value should be the same.")(actualBalance)
  }
}
