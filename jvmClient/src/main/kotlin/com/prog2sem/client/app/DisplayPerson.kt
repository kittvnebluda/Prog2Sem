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
    private val stickman = ImageIO.read(File("C:\\Users\\sizgr\\IdeaProjects\\Prog2Sem\\jvmClient\\src\\main\\resources\\images\\stickman.png"))
    init {
        minimumSize = Dimension(400, 400)
    }
    override fun paint(g: Graphics?) {
        background = Color.WHITE

        val x = coords.x.roundToInt().absoluteValue/10
        val y =  coords.y.toInt().absoluteValue/10

        val hairX = arrayOf(130, 150, 200, 260, 280, 275, 170, 160, 122)
        val hairY = arrayOf(35, 30, 25, 40, 100, 120, 50, 70, 100)

        val newHairX = hairX.map { it + x }
        val newHairY = hairY.map { it + y }

        val hairPoly = Polygon(newHairX.toIntArray(), newHairY.toIntArray(), 9)

        if (g != null) {
            g.drawImage(stickman, x, y, null)
            g.color = hairColor
            g.fillPolygon(hairPoly)
        }


    }
}