package bank.domain

import bank.domain.OperationType.OperationType

import java.time.Instant

case class Operation(
                      operationType: OperationType,
                      amount: Amount,
                      timestamp: Instant
                    )
