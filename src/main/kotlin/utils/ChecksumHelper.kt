package utils




object ChecksumHelper {

    suspend fun calculate(numberToConvert: String): String {
        var checkDigitB = 0
        var checkDigitC = 0
        var checkDigitD = 0
        var remainder = 0

        val transferNumber = "0$numberToConvert"

        for (i in 1 until transferNumber.length) {
            checkDigitB = transferNumber.substring(i, i+1).toInt()
            checkDigitC = if (checkDigitB > 4) checkDigitB * 2 - 9 else checkDigitB * 2
            remainder = (i - 1) % 2
            checkDigitD += if (remainder == 0) checkDigitC else checkDigitB
        }

        val checkDigit = when(checkDigitD % 10 ){
            0 -> 0
            else -> 10 - (checkDigitD % 10)
        }
        return "$numberToConvert$checkDigit"
    }
}

