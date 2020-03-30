package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.awt.Toolkit

class HackermanGame : Game() {
    var batch: SpriteBatch? = null

    companion object {
        private var WIDTH = 0f
        private var HEIGHT = 0f

        fun getWidth() : Float {return WIDTH}
        fun getHeight(): Float {return HEIGHT}
    }

    override fun create() {
        ScreenController.init(this)
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        WIDTH = screenSize.width.toFloat()
        HEIGHT = screenSize.height.toFloat()

        batch = SpriteBatch()
        ScreenController.instance.setMainMenu()
    }

    override fun dispose() {
      batch?.dispose()
    }
}