package com.prog2sem.shared.net

import com.prog2sem.shared.utils.Buffer
import com.prog2sem.shared.utils.MsgMarker
import com.prog2sem.shared.utils.Packets
import java.net.SocketAddress
import java.nio.ByteBuffer

/**
 * Класс для общения по протоколу UDP с помощью собственных пакетов (без ограничения в размере сообщения)
 */
open class PacketsUDP(
    protected val timeout: Int = 3000  // Время ожидания в миллисекундах
) : UDP() {
    /**
     * Метод блокирует программу, но имеет таймаут.
     * Обеспечивает получение датаграмм с матрировкий пакетов и собирает их в сообщение.
     * @return сообщение или null, если превышено время ожидания
     */
    override fun receive(): String? {
        val buffer: ByteBuffer = ByteBuffer.allocate(bufferCapacity)

        val packets = mutableListOf<String>()

        var address: SocketAddress?

        var packetsCount = 1000000000  // Ставим большое начальное количество пакетов в сообщении
        var received = 0  // Счетчик полученных датаграмм

        var timeStart = System.currentTimeMillis()
        var timeDiff: Long

        do {
            timeDiff = System.currentTimeMillis() - timeStart
            buffer.clear() // Чистим для записи новый данных

            address = channel.receive(buffer)

            // Если мы получили датаграмму
            address?.let {
                timeStart = System.currentTimeMillis()
                sendToAddress = address

                val packet = Buffer.toString(buffer)
                packets.add(packet)

                if (packet.length > 7) {
                    received++
                    // Определяем количество пакетов (пытаемся найти пакет с отрицательным порядковым номером)
                    packetsCount = MsgMarker.getPacket(packet).third
                } else
                    println("WARNING!!! Говно, а не пакет")

            // Если мы не получили датаграмму
            }?:run {
                when(timeDiff % 10000) {
                    1000L -> print("\rЖдем    ")
                    3000L -> print("\rЖдем..  ")
                    6000L -> print("\rЖдем... ")
                    9000L -> print("\rЖдем... ")
                }
            }
        } while (timeDiff < timeout && received < packetsCount)

        return if (packetsCount == received) {
            Packets.merge(packets)
        } else {
            println("Время ожидания вышло!")
            null
        }
    }

    /**
     * Метод делит строку на пакеты с соответсвующей маркеровкой, а затем поочередно посылает их по адресу
     * @param msg сообщение для отправки
     * @param address адрес получателя
     */
    override fun send(msg: String, address: SocketAddress) {
        var cnt = 0
        Packets.generate(msg).forEach {
            cnt++
            val buffer: ByteBuffer = ByteBuffer.wrap(it.toByteArray())
            channel.send(buffer, address)
        }
    }
}