import java.awt.Color
import java.awt.FlowLayout
import java.awt.Frame
import java.awt.Graphics
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.image.BufferedImage
import java.util.*
import kotlin.random.Random
import kotlin.system.exitProcess


fun main(vararg s: String) {
    SnakeGame()
}

open abstract class SnakeApp(w: Int = 500, h: Int = 500, startLoopDelay: Long = 500L) : Frame("Vincent") {
    var image: BufferedImage
    val timer = Timer("mainloop")
    var tick = 0
    var currentDelay = startLoopDelay

    init {
        this.setSize(w, h)
        layout = FlowLayout()

        this.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                timer.cancel()
                dispose()
                exitProcess(0)
            }
        })

        image = BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR)

        image.graphics.apply {
            color = Color.BLACK
            fillRect(0, 0, image.width, image.height)
        }

        isVisible = true

        reschedule()
    }

    fun reschedule() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                loop(++tick)
            }
        }, currentDelay)
    }

    override fun paint(g: Graphics?) {
        super.paint(g)
        g?.drawImage(image, 0, 0, this)
    }

    fun setPixel(x: Int, y: Int, c: Color) = image.setRGB(x % width, y % height, c.rgb)

    fun getPixel(x: Int, y: Int) = Color(image.getRGB(x % width, y % height))

    abstract fun loop(tick: Int)
}


