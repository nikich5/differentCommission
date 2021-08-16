import org.junit.Assert.*
import org.junit.Test

class MainKtTest {

    @Test
    fun calcOfCommission_maestroOrMastercardPromotion() {
        val transferAmount = 100_000
        val monthAmount = 500_000
        val paymentType = PaymentType.Maestro

        val result = calcOfCommission(
            transferAmount = transferAmount,
            monthAmount = monthAmount,
            paymentType = paymentType
        )

        assertEquals(0, result)
    }

    @Test
    fun calcOfCommission_maestroOrMastercardWithoutPromotion() {
        val transferAmount = 100_000
        val monthAmount = 700_500_000
        val paymentType = PaymentType.Maestro

        val result = calcOfCommission(
            transferAmount = transferAmount,
            monthAmount = monthAmount,
            paymentType = paymentType
        )

        assertEquals(2600, result)
    }

    @Test
    fun calcOfCommission_vk() {
        val transferAmount = 10_000
        val monthAmount = 500_000
        val paymentType = PaymentType.Vk

        val result = calcOfCommission(
            transferAmount = transferAmount,
            monthAmount = monthAmount,
            paymentType = paymentType
        )

        assertEquals(0, result)
    }

    @Test
    fun calcOfCommission_visaOrMirMoreThenMinimalCommission() {
        val transferAmount = 10_000_000
        val monthAmount = 500_000
        val paymentType = PaymentType.Visa

        val result = calcOfCommission(
            transferAmount = transferAmount,
            monthAmount = monthAmount,
            paymentType = paymentType
        )

        assertEquals(75000, result)
    }

    @Test
    fun calcOfCommission_visaOrMirWithMinimalCommission() {
        val transferAmount = 100_000
        val monthAmount = 500_000
        val paymentType = PaymentType.Visa

        val result = calcOfCommission(
            transferAmount = transferAmount,
            monthAmount = monthAmount,
            paymentType = paymentType
        )

        assertEquals(3500, result)
    }

    @Test
    fun calcOfCommission_default() {
        val transferAmount = 100_000

        val result = calcOfCommission(
            transferAmount = transferAmount,
        )

        assertEquals(0, result)
    }
}