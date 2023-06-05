package com.prog2sem.shared.utils

import com.prog2sem.shared.utils.JsonWorker.json
import com.prog2sem.shared.utils.KnowledgeFactorySHA1.encryptThisString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.util.Base64

object MakeAccessToken {
    private const val header = "{\n" +
            "\"typ\": \"JWT\",\n" +
            "\"alg\": \"HS1\"\n" +
            "}"

    fun generateToken(payload: TokenPayload): String{
        val header = Base64.getEncoder().encodeToString(header.toByteArray())
        payload.password = encryptThisString(payload.password)
        val payload = Base64.getEncoder().encodeToString(json.encodeToString(payload).toByteArray())

        val data = "$header.$payload"

        val signature = Base64.getEncoder().encodeToString(encryptThisString(data).toByteArray())

        return "$data.$signature"
    }

    fun checkVer(token: String): Boolean{
        val data = token.split('.')

        val headpay = data[0] + "." + data[1]

        return data[2] == Base64.getEncoder().encodeToString(encryptThisString(headpay).toByteArray())
    }

    fun getInfoFromToken(token: String): TokenPayload{
        val data = token.split('.')

        return json.decodeFromString(Base64.getDecoder().decode(data[1]).decodeToString())
    }
}