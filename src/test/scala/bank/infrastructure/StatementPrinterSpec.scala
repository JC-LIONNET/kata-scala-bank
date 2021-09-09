package bank.infrastructure

import bank.domain.{Account, Amount}
import org.scalatest.flatspec.AnyFlatSpec

import java.time.{Clock, Instant, ZoneId}

class StatementPrinterSpec extends AnyFlatSpec{

  behavior of "StatementPrinter"

  "ConsoleStatementPrinter" should "Print in the console the statement lines" in {
    //Given
    val fixedInstant: Instant = Instant.now()
    val clockFixed: Clock = Clock.fixed(fixedInstant, ZoneId.systemDefault())
    val initAccount: Account = Account(clock = clockFixed)
    val stream = new java.io.ByteArrayOutputStream()
    val consoleStatementPrinter: ConsoleStatementPrinter = new ConsoleStatementPrinter()

    //When
    val accountWithOperations: Account = initAccount.deposit(Amount(1000)).withdrawal(Amount(500)).deposit(Amount(1500))
    Console.withOut(stream){
      accountWithOperations.print(consoleStatementPrinter)
    }
    //Then
    val expextedConsolePrint: StringBuilder = new StringBuilder()
    expextedConsolePrint.append("| OPERATION | DATE | AMOUNT | BALANCE |\n")
    expextedConsolePrint.append("| DEPOSIT | 09/09/2021 | 1500 | 2000 |\n")
    expextedConsolePrint.append("| WITHDRAWAL | 09/09/2021 | 500 | 500 |\n")
    expextedConsolePrint.append("| DEPOSIT | 09/09/2021 | 1000 | 1000 |\n")
    expextedConsolePrint.append("\n")
    assertResult(expextedConsolePrint.toString(), "The print should be the same.")(stream.toString)
  }
}
