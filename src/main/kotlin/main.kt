import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    while(true) {
        println("Введите размер перевода в рублях")
        val transferAmount = scanner.nextInt() * 100

        println("Выберите платежную систему: \n1. VK Pay\n2. MasterCard\n3. Maestro\n4. Visa\n5. Mir")
        val paymentType = when (scanner.nextInt()) {
            1 -> PaymentType.Vk
            2 -> PaymentType.MasterCard
            3 -> PaymentType.Maestro
            4 -> PaymentType.Visa
            5 -> PaymentType.Mir
            else -> continue
        }

        println("Введите сумму предыдущих переводов (в рублях) в этом месяце (по умолчанию - 0)")
        val monthAmount = scanner.nextInt() * 100

        if (transferAmount < 5_000) {
            println("Минимальная сумма перевода 50 р.")
            continue
        } else if (paymentType != PaymentType.Vk && transferAmount > 15_000_000 || monthAmount > 60_000_000) {
            println("Максимальная сумма переводов по карте 150000 р. в сутки и 600000 р. в месяц")
            continue
        } else if (paymentType == PaymentType.Vk && transferAmount > 1_500_000
            || paymentType == PaymentType.Vk && monthAmount > 4_000_000) {
            println("Максимальная сумма переводов через VK Pay - 15 т.р за один раз и 40000 р. в месяц")
            continue
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