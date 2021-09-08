package bank.domain

import java.time.Instant

case class Statement(
                      listStatementLines: List[StatementLine] = List.empty
                    ) {
  def addDeposit(balance: Balance, amount: Amount, timestamp: Instant): Statement = {
    val deposit: Operation = Operation(OperationType.DEPOSIT,  amount, timestamp)
    val depositStatementLine: StatementLine = StatementLine(deposit, balance)
    copy(listStatementLines = depositStatementLine :: listStatementLines)
  }
}
