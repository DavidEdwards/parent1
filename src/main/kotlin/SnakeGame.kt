import java.awt.Color
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.*
import kotlin.system.exitProcess

data class Position(val x: Int, val y: Int)

class SnakeGame : SnakeApp(), KeyListener {

    val playerSnake = LinkedList<Position>()

//    var playerx = 100
//    var playery = 100

    var direction = 1

    init {
        addKeyListener(this)

        playerSnake.add(Position(100, 100))
    }

    override fun loop(tick: Int) {
//        println("Game loop: $tick (delay $currentDelay)")

        val modDirection = direction % 4

        val movex = when (modDirection) {
            0 -> 0
            1 -> 1
            2 -> 0
            3 -> -1
            else -> 0
        }

        val movey = when (modDirection) {
            0 -> -1
            1 -> 0
            2 -> 1
            3 -> 0
            else -> 0
        }

        val head = playerSnake.last

        var newPosition = Position(head.x + movex, head.y + movey)

        if (newPosition.x < 0) {
            newPosition = newPosition.copy(x = width - 1)
        }
        if (newPosition.y < 0) {
            newPosition = newPosition.copy(y = height - 1)
        }
        if (newPosition.x >= width) {
            newPosition = newPosition.copy(x = 0)
        }
        if (newPosition.y >= height) {
            newPosition = newPosition.copy(y = 0)
        }

        val pixelAtPlayer = getPixel(newPosition.x, newPosition.y)

        if (pixelAtPlayer == Color.RED) {
            print("You lost")
            exitProcess(0)
        }

        setPixel(newPosition.x, newPosition.y, Color.RED)
        repaint(newPosition.x, newPosition.y, 1, 1)

        playerSnake.add(newPosition)



        while (playerSnake.size > 30) {
            val position = playerSnake.pop()
            setPixel(position.x, position.y, Color.BLACK)
            repaint(position.x, position.y, 1, 1)
        }




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
                direction = direction - 1
            }
//            UP -> {
//                println("GO UP")
//                movey = -1
//                movex = 0
//            }
            RIGHT -> {
                println("GO RIGHT")
                direction = direction + 1
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