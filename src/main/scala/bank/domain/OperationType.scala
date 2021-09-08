package bank.domain

object OperationType extends Enumeration {
  type OperationType = Value
  val DEPOSIT, WITHDRAWAL = Value
}