package bank.infrastructure
import bank.domain.{Statement, StatementPrinter}

import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ConsoleStatementPrinter extends StatementPrinter {

  private val Header: String = "| OPERATION | DATE | AMOUNT | BALANCE |\n"
  private val Line: String = "| %s | %s | %s | %s |\n"
  private val DateFormater: DateTimeFormatter  = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.systemDefault())

  override def print(statement: Statement): Unit = {
    val stringBuilder: StringBuilder = new StringBuilder(Header)
    for (
      line <- statement.listStatementLines
    ) yield {
      val lineFormatted: String = String.format(
        Line,
        line.operation.operationType,
        DateFormater.format(line.operation.timestamp),
        line.operation.amount.value,
        line.balance.value
      )
      stringBuilder.append(lineFormatted)
    }
    println(stringBuilder)
  }
}
