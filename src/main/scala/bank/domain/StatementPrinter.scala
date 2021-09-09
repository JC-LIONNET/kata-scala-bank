package bank.domain

trait StatementPrinter {

  def print(statement: Statement): Unit
}
