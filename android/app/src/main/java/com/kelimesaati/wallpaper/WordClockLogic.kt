package com.kelimesaati.wallpaper

object WordClockLogic {

    fun getActiveEN(hour24: Int, roundedMin: Int): Set<Pair<Int, Int>> {
        var dHour = hour24 % 12
        var dMin = roundedMin
        if (dMin == 60) { dMin = 0; dHour = (dHour + 1) % 12 }
        val nHour = (dHour + 1) % 12

        val activeKeys = mutableSetOf("IT", "IS")
        val words = WordClockData.WORDS_EN

        if (dMin == 0) {
            activeKeys.add(WordClockData.HOUR_KEYS_EN[dHour])
            activeKeys.add("OCLOCK")
        } else {
            when (dMin) {
                5 -> { activeKeys.add("FIVE_M"); activeKeys.add("MINUTES") }
                10 -> { activeKeys.add("TEN_M"); activeKeys.add("MINUTES") }
                15 -> { activeKeys.add("A"); activeKeys.add("FIFTEEN"); activeKeys.add("MINUTES") }
                20 -> { activeKeys.add("TWENTY"); activeKeys.add("MINUTES") }
                25 -> { activeKeys.add("TWENTY"); activeKeys.add("FIVE_M"); activeKeys.add("MINUTES") }
                30 -> { activeKeys.add("THIRTY"); activeKeys.add("MINUTES") }
                35 -> { activeKeys.add("TWENTY"); activeKeys.add("FIVE_M"); activeKeys.add("MINUTES") }
                40 -> { activeKeys.add("TWENTY"); activeKeys.add("MINUTES") }
                45 -> { activeKeys.add("A"); activeKeys.add("FIFTEEN"); activeKeys.add("MINUTES") }
                50 -> { activeKeys.add("TEN_M"); activeKeys.add("MINUTES") }
                55 -> { activeKeys.add("FIVE_M"); activeKeys.add("MINUTES") }
            }
            if (dMin <= 30) {
                activeKeys.add("PAST")
                activeKeys.add(WordClockData.HOUR_KEYS_EN[dHour])
            } else {
                activeKeys.add("TO")
                activeKeys.add(WordClockData.HOUR_KEYS_EN[nHour])
            }
        }

        return resolvePositions(activeKeys, words)
    }

    fun getActiveTR(hour24: Int, roundedMin: Int): Set<Pair<Int, Int>> {
        var dHour = hour24 % 12
        var dMin = roundedMin
        if (dMin == 60) { dMin = 0; dHour = (dHour + 1) % 12 }
        val nHour = (dHour + 1) % 12

        val activeKeys = mutableSetOf("SAAT")
        val words = WordClockData.WORDS_TR

        when {
            dMin == 0 -> {
                activeKeys.add(WordClockData.HOUR_BASE_TR[dHour])
            }
            dMin == 30 -> {
                activeKeys.add(WordClockData.HOUR_BASE_TR[dHour])
                activeKeys.add("BUÇUK")
            }
            dMin <= 25 -> {
                activeKeys.add(WordClockData.HOUR_ACC_TR[dHour])
                when (dMin) {
                    5 -> activeKeys.add("BEŞ_M")
                    10 -> activeKeys.add("ON_M")
                    15 -> activeKeys.add("ÇEYREK")
                    20 -> activeKeys.add("YİRMİ")
                    25 -> { activeKeys.add("YİRMİ"); activeKeys.add("BEŞ_M") }
                }
                activeKeys.add("GEÇİYOR")
            }
            else -> {
                activeKeys.add(WordClockData.HOUR_DAT_TR[nHour])
                val remaining = 60 - dMin
                when (remaining) {
                    5 -> activeKeys.add("BEŞ_M")
                    10 -> activeKeys.add("ON_M")
                    15 -> activeKeys.add("ÇEYREK")
                    20 -> activeKeys.add("YİRMİ")
                    25 -> { activeKeys.add("YİRMİ"); activeKeys.add("BEŞ_M") }
                }
                activeKeys.add("VAR")
            }
        }

        return resolvePositions(activeKeys, words)
    }

    private fun resolvePositions(keys: Set<String>, words: Map<String, List<Pair<Int, Int>>>): Set<Pair<Int, Int>> {
        val positions = mutableSetOf<Pair<Int, Int>>()
        for (key in keys) {
            words[key]?.let { positions.addAll(it) }
        }
        return positions
    }
}
