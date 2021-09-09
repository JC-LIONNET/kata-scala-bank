package bank.domain

import bank.domain.OperationType.OperationType

import java.time.Instant

case class Statement(
                      listStatementLines: List[StatementLine] = List.empty
                    ) {
  def addOperation(operationType: OperationType, balance: Balance, amount: Amount, timestamp: Instant): Statement = {
    val operation: Operation = Operation(operationType, amount, timestamp)
    val statementLine: StatementLine = StatementLine(operation, balance)
    copy(listStatementLines = statementLine :: listStatementLines)
  }
}
