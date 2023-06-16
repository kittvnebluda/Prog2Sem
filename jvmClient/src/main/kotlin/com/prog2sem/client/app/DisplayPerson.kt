package com.prog2sem.client.app

import com.prog2sem.shared.Coordinates
import java.awt.*
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class DisplayPerson(
    private val coords: Coordinates,
    private val hairColor: Color
) : Canvas() {
    private val stickman = ImageIO.read(File("C:\\ItmoProject2023\\Prog2Sem\\jvmClient\\src\\main\\resources\\images\\stickman.png"))
    private val hairPoly = Polygon(
        arrayOf(130, 150, 200, 260, 280, 275, 170, 160, 122).toIntArray(),
        arrayOf(35, 30, 25, 40, 100, 120, 50, 70, 100).toIntArray(),
        9
    )
    init {
        minimumSize = Dimension(400, 400)
    }
    override fun paint(g: Graphics?) {
        background = Color.WHITE

        if (g != null) {
            g.drawImage(stickman,
                coords.x.roundToInt().absoluteValue/10,
                coords.y.toInt().absoluteValue/10, Color.CYAN,
                null)
            g.color = hairColor
            g.fillPolygon(hairPoly)
        }


    }
}