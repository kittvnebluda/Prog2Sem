package com.prog2sem.shared.net

import com.prog2sem.shared.Location
import com.prog2sem.shared.utils.MsgMarker
import com.prog2sem.shared.utils.Packets
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

class MsgMarkerTest {
    @Test
    fun functionCallMarkingTest() {
        val funName = "MOM!"
        val p1 = Location(1f, 2f, 3)
        val p2 = 123

        val msg = MsgMarker.markFun(funName, Json.encodeToString(p1), p2.toString())
        val returned = MsgMarker.getFun(msg)

        val rep1: Location = Json.decodeFromString(returned[1])
        val rep2 = returned[2].toInt()

        assert(funName == returned[0])
        assert(p1 == rep1)
        assert(p2 == rep2)
    }

    @Test
    fun whichMarkTest() {
        val s = "ERROR"
        val msg = MsgMarker.markError(s)
        assert(MsgMarker.Tags.ERR == MsgMarker.which(msg))
    }

    @Test
    fun testPackets() {
        val string = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"

    }
}