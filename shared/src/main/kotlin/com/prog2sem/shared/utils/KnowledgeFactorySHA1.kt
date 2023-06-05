package com.prog2sem.shared.utils

import java.math.BigInteger
import java.security.MessageDigest


object KnowledgeFactorySHA1 {
    fun encryptThisString(input: String): String {
        val md = MessageDigest.getInstance("SHA-1")

        val pepper = "kFz<Q%ps"

        val input = pepper + input

        val messageDigest = md.digest(input.toByteArray())

        val no = BigInteger(1, messageDigest)

        var hashtext = no.toString(16)

        while (hashtext.length < 32) {
            hashtext = "0$hashtext"
        }

        return hashtext
    }
}