import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var monthAmount = 0
    var transferAmount: Int
    while(true) {
        println("Введите размер перевода в копейках")
        transferAmount = scanner.nextInt()

        println("Введите тип перевода")
        val paymentTypeString = scanner.next()
        val paymentType = PaymentType.valueOf(paymentTypeString)
        if (paymentType == PaymentType.MasterCard || paymentType == PaymentType.Maestro) {
            monthAmount += transferAmount
        }
        println("Комиссия за перевод составит: ${calcOfCommission(transferAmount, monthAmount, paymentType)} копеек")
    }
}

fun calcOfCommission(transferAmount: Int,
                     monthAmount: Int = 0,
                     paymentType:PaymentType = PaymentType.Vk): Int {
    when(paymentType) {
        PaymentType.Maestro, PaymentType.MasterCard -> {
            return if(transferAmount >= 30_000 && monthAmount <= 7_500_000) {
                0
            } else {
                ((transferAmount * 0.006) + 2_000).toInt()
            }
        }
        
        PaymentType.Visa, PaymentType.Mir -> {
            return if((transferAmount * 0.0075) < 3_500) {
                3_500
            } else {
                (transferAmount * 0.0075).toInt()
            }
        }

        PaymentType.Vk -> return 0
    }
}

enum class PaymentType {
    Vk, MasterCard, Maestro, Visa, Mir
}