package bank.domain

case class Balance(
                    value: BigDecimal = BigDecimal(0)
                  ){

  def add(amount: Amount): Balance = copy(value = value + amount.value )
  def subtract(amount: Amount): Balance = copy(value = value - amount.value)
}
