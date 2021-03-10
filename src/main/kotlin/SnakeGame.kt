import java.awt.Color
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import kotlin.system.exitProcess

class SnakeGame : SnakeApp(), KeyListener {

    var playerx = 100
    var playery = 100

    var direction = 1

    init {
        addKeyListener(this)
    }

    override fun loop(tick: Int) {
//        println("Game loop: $tick (delay $currentDelay)")

        val modDirection = direction % 4

        val movex = when(modDirection) {
            0 -> 0
            1 -> 1
            2 -> 0
            3 -> -1
            else -> 0
        }

        val movey = when(modDirection) {
            0 -> -1
            1 -> 0
            2 -> 2
            3 -> 0
            else -> 0
        }

        playerx = playerx + movex
        playery = playery + movey

        if (playerx < 0) {
            playerx = width - 1
        }
        if (playery < 0) {
            playery = height - 1
        }
        if(playerx >= width) {
            playerx = 0
        }
        if(playery >= height) {
            playery = 0
        }

        val pixelAtPlayer = getPixel(playerx, playery)

        if (pixelAtPlayer == Color.RED) {
            print("You lost")
            exitProcess(0)
        }

        setPixel(playerx, playery, Color.RED)
        repaint(playerx, playery, 1, 1)

        currentDelay = (currentDelay - 50).coerceIn(10L..1000L)
        reschedule()
    }

    override fun keyTyped(e: KeyEvent) {
    }

    override fun keyPressed(e: KeyEvent) {
        val LEFT = 37
        val UP = 38
        val RIGHT = 39
        val DOWN = 40

        when (e.keyCode) {
            LEFT -> {
                println("GO LEFT")
                direction=direction-1
            }
//            UP -> {
//                println("GO UP")
//                movey = -1
//                movex = 0
//            }
            RIGHT -> {
                println("GO RIGHT")
                direction=direction+1
            }
//            DOWN -> {
//                println("GO DOWN")
//                movey = 1
//                movex = 0
//            }
        }
    }

    override fun keyReleased(e: KeyEvent) {
    }
}