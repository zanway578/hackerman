package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.mygdx.game.HackermanGame
import com.mygdx.game.Paths
import com.mygdx.game.ScreenController


class AboutScreen(val game: HackermanGame) : Screen {
    var font = BitmapFont()

    override fun render(delta: Float) {
        var fileStr = Gdx.files.internal(Paths.TextFiles.ABOUT).readString()
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        game.batch?.begin()
        font.draw(game.batch, fileStr, 100f, HackermanGame.getHeight() - 50f)

        if (Gdx.input.justTouched()) {
            ScreenController.instance.setMainMenu()
        }
        game.batch?.end()
    }

    override fun dispose() {}

    override fun pause() {}
    override fun resume() {}
    override fun resize(width: Int, height: Int) {}
    override fun hide() {}
    override fun show() {}
}