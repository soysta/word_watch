package com.kelimesaati.wallpaper

object WordClockData {
    val GRID_EN = arrayOf(
        "ITLISXATHPMA",
        "ACFIFTEENDCO",
        "TWENTYFIVEXW",
        "THIRTYFTENOS",
        "MINUTESETOUR",
        "PASTORUFOURT",
        "SEVENXTWELVE",
        "NINEFIVECTWO",
        "EIGHTFELEVEN",
        "SIXTHREEONEG",
        "TENSEZOCLOCK"
    )

    val GRID_TR = arrayOf(
        "SAATXASOYSTASO",
        "BİRİXİKİYİXÜÇÜ",
        "DÖRDÜXBEŞİXXXX",
        "ALTIYIXXYEDİYİ",
        "SEKİZİXDOKUZUX",
        "ONUXONBİRİDÖRT",
        "ONİKİYİXBİREXX",
        "İKİYEXÜÇEDÖRDE",
        "BEŞEXALTIYAONA",
        "YEDİYEXXSEKİZE",
        "DOKUZAXXONBİRE",
        "ONİKİYEXXYİRMİ",
        "ONXBEŞXXÇEYREK",
        "BUÇUKXXXVARXXX",
        "XXXXXXXGEÇİYOR"
    )

    // English word positions
    val WORDS_EN = mapOf(
        "IT" to listOf(0 to 0, 0 to 1),
        "IS" to listOf(0 to 3, 0 to 4),
        "A" to listOf(1 to 0),
        "FIFTEEN" to listOf(1 to 2, 1 to 3, 1 to 4, 1 to 5, 1 to 6, 1 to 7, 1 to 8),
        "TWENTY" to listOf(2 to 0, 2 to 1, 2 to 2, 2 to 3, 2 to 4, 2 to 5),
        "FIVE_M" to listOf(2 to 6, 2 to 7, 2 to 8, 2 to 9),
        "THIRTY" to listOf(3 to 0, 3 to 1, 3 to 2, 3 to 3, 3 to 4, 3 to 5),
        "TEN_M" to listOf(3 to 7, 3 to 8, 3 to 9),
        "MINUTES" to listOf(4 to 0, 4 to 1, 4 to 2, 4 to 3, 4 to 4, 4 to 5, 4 to 6),
        "PAST" to listOf(5 to 0, 5 to 1, 5 to 2, 5 to 3),
        "TO" to listOf(4 to 8, 4 to 9),
        "FOUR" to listOf(5 to 7, 5 to 8, 5 to 9, 5 to 10),
        "SEVEN" to listOf(6 to 0, 6 to 1, 6 to 2, 6 to 3, 6 to 4),
        "TWELVE" to listOf(6 to 6, 6 to 7, 6 to 8, 6 to 9, 6 to 10, 6 to 11),
        "NINE" to listOf(7 to 0, 7 to 1, 7 to 2, 7 to 3),
        "FIVE_H" to listOf(7 to 4, 7 to 5, 7 to 6, 7 to 7),
        "TWO" to listOf(7 to 9, 7 to 10, 7 to 11),
        "EIGHT" to listOf(8 to 0, 8 to 1, 8 to 2, 8 to 3, 8 to 4),
        "ELEVEN" to listOf(8 to 6, 8 to 7, 8 to 8, 8 to 9, 8 to 10, 8 to 11),
        "SIX" to listOf(9 to 0, 9 to 1, 9 to 2),
        "THREE" to listOf(9 to 3, 9 to 4, 9 to 5, 9 to 6, 9 to 7),
        "ONE" to listOf(9 to 8, 9 to 9, 9 to 10),
        "TEN_H" to listOf(10 to 0, 10 to 1, 10 to 2),
        "OCLOCK" to listOf(10 to 6, 10 to 7, 10 to 8, 10 to 9, 10 to 10, 10 to 11)
    )

    // Turkish word positions
    val WORDS_TR = mapOf(
        "SAAT" to listOf(0 to 0, 0 to 1, 0 to 2, 0 to 3),
        "BİR" to listOf(1 to 0, 1 to 1, 1 to 2),
        "İKİ" to listOf(1 to 5, 1 to 6, 1 to 7),
        "ÜÇ" to listOf(1 to 11, 1 to 12),
        "DÖRT" to listOf(5 to 11, 5 to 12, 5 to 13, 5 to 14),
        "BEŞ_H" to listOf(2 to 6, 2 to 7, 2 to 8),
        "ALTI" to listOf(3 to 0, 3 to 1, 3 to 2, 3 to 3),
        "YEDİ" to listOf(3 to 8, 3 to 9, 3 to 10, 3 to 11),
        "SEKİZ" to listOf(4 to 0, 4 to 1, 4 to 2, 4 to 3, 4 to 4),
        "DOKUZ" to listOf(4 to 7, 4 to 8, 4 to 9, 4 to 10, 4 to 11),
        "ON_H" to listOf(5 to 0, 5 to 1),
        "ONBİR" to listOf(5 to 4, 5 to 5, 5 to 6, 5 to 7, 5 to 8),
        "ONİKİ" to listOf(6 to 0, 6 to 1, 6 to 2, 6 to 3, 6 to 4),
        "BİRİ" to listOf(1 to 0, 1 to 1, 1 to 2, 1 to 3),
        "İKİYİ" to listOf(1 to 5, 1 to 6, 1 to 7, 1 to 8, 1 to 9),
        "ÜÇÜ" to listOf(1 to 11, 1 to 12, 1 to 13),
        "DÖRDÜ" to listOf(2 to 0, 2 to 1, 2 to 2, 2 to 3, 2 to 4),
        "BEŞİ" to listOf(2 to 6, 2 to 7, 2 to 8, 2 to 9),
        "ALTIYI" to listOf(3 to 0, 3 to 1, 3 to 2, 3 to 3, 3 to 4, 3 to 5),
        "YEDİYİ" to listOf(3 to 8, 3 to 9, 3 to 10, 3 to 11, 3 to 12, 3 to 13),
        "SEKİZİ" to listOf(4 to 0, 4 to 1, 4 to 2, 4 to 3, 4 to 4, 4 to 5),
        "DOKUZU" to listOf(4 to 7, 4 to 8, 4 to 9, 4 to 10, 4 to 11, 4 to 12),
        "ONU" to listOf(5 to 0, 5 to 1, 5 to 2),
        "ONBİRİ" to listOf(5 to 4, 5 to 5, 5 to 6, 5 to 7, 5 to 8, 5 to 9),
        "ONİKİYİ" to listOf(6 to 0, 6 to 1, 6 to 2, 6 to 3, 6 to 4, 6 to 5, 6 to 6),
        "BİRE" to listOf(6 to 8, 6 to 9, 6 to 10, 6 to 11),
        "İKİYE" to listOf(7 to 0, 7 to 1, 7 to 2, 7 to 3, 7 to 4),
        "ÜÇE" to listOf(7 to 6, 7 to 7, 7 to 8),
        "DÖRDE" to listOf(7 to 9, 7 to 10, 7 to 11, 7 to 12, 7 to 13),
        "BEŞE" to listOf(8 to 0, 8 to 1, 8 to 2, 8 to 3),
        "ALTIYA" to listOf(8 to 5, 8 to 6, 8 to 7, 8 to 8, 8 to 9, 8 to 10),
        "ONA" to listOf(8 to 11, 8 to 12, 8 to 13),
        "YEDİYE" to listOf(9 to 0, 9 to 1, 9 to 2, 9 to 3, 9 to 4, 9 to 5),
        "SEKİZE" to listOf(9 to 8, 9 to 9, 9 to 10, 9 to 11, 9 to 12, 9 to 13),
        "DOKUZA" to listOf(10 to 0, 10 to 1, 10 to 2, 10 to 3, 10 to 4, 10 to 5),
        "ONBİRE" to listOf(10 to 8, 10 to 9, 10 to 10, 10 to 11, 10 to 12, 10 to 13),
        "ONİKİYE" to listOf(11 to 0, 11 to 1, 11 to 2, 11 to 3, 11 to 4, 11 to 5, 11 to 6),
        "BEŞ_M" to listOf(12 to 3, 12 to 4, 12 to 5),
        "ON_M" to listOf(12 to 0, 12 to 1),
        "YİRMİ" to listOf(11 to 9, 11 to 10, 11 to 11, 11 to 12, 11 to 13),
        "ÇEYREK" to listOf(12 to 8, 12 to 9, 12 to 10, 12 to 11, 12 to 12, 12 to 13),
        "BUÇUK" to listOf(13 to 0, 13 to 1, 13 to 2, 13 to 3, 13 to 4),
        "VAR" to listOf(13 to 8, 13 to 9, 13 to 10),
        "GEÇİYOR" to listOf(14 to 7, 14 to 8, 14 to 9, 14 to 10, 14 to 11, 14 to 12, 14 to 13)
    )

    val HOUR_KEYS_EN = arrayOf("TWELVE","ONE","TWO","THREE","FOUR","FIVE_H","SIX","SEVEN","EIGHT","NINE","TEN_H","ELEVEN","TWELVE")
    val HOUR_BASE_TR = arrayOf("ONİKİ","BİR","İKİ","ÜÇ","DÖRT","BEŞ_H","ALTI","YEDİ","SEKİZ","DOKUZ","ON_H","ONBİR","ONİKİ")
    val HOUR_ACC_TR = arrayOf("ONİKİYİ","BİRİ","İKİYİ","ÜÇÜ","DÖRDÜ","BEŞİ","ALTIYI","YEDİYİ","SEKİZİ","DOKUZU","ONU","ONBİRİ","ONİKİYİ")
    val HOUR_DAT_TR = arrayOf("ONİKİYE","BİRE","İKİYE","ÜÇE","DÖRDE","BEŞE","ALTIYA","YEDİYE","SEKİZE","DOKUZA","ONA","ONBİRE","ONİKİYE")
}
