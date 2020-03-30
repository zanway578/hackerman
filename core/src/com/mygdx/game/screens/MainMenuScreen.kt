package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.HackermanGame
import com.mygdx.game.Paths
import com.mygdx.game.ScreenController

class MainMenuScreen(val game: HackermanGame) : Screen {
    private var introMusic: Music = Gdx.audio.newMusic(Gdx.files.internal(Paths.Songs.INTRO_THEME))
    private var menuImg = Texture("title.png")

    // buttons
    private var playButtonNormal = Texture(Paths.Clickables.PLAY_BUTTON_NORMAL)
    private var playButtonClicked = Texture(Paths.Clickables.PLAY_BUTTON_CLICKED)
    private var optionsButtonNormal = Texture(Paths.Clickables.OPTIONS_BUTTON_NORMAL)
    private var optionsButtonClicked = Texture(Paths.Clickables.OPTIONS_BUTTON_CLICKED)
    private var aboutButtonNormal = Texture(Paths.Clickables.ABOUT_BUTTON_NORMAL)
    private var aboutButtonClicked = Texture(Paths.Clickables.ABOUT_BUTTON_CLICKED)

    // button locations
    private val CHAR_BUTTON_HEIGHT = HackermanGame.getHeight() / 16
    private val CHAR_BUTTON_WIDTH = HackermanGame.getWidth() / 32
    private val BUTTON_X = HackermanGame.getWidth() * 3f / 4
    private val PLAY_Y = HackermanGame.getHeight() * 2f/3
    private val BUTTON_VERT_SPACING = CHAR_BUTTON_HEIGHT + 50f;
    private val OPTIONS_Y = PLAY_Y - BUTTON_VERT_SPACING
    private val ABOUT_Y = OPTIONS_Y - BUTTON_VERT_SPACING

    // expressions that can determine if button is being hovered over given coordinates x and y
    // evaluate which button is being hovered
    private val isPlayHovered = { x: Float, y: Float -> x > BUTTON_X && x <= BUTTON_X + CHAR_BUTTON_WIDTH*"start".length &&
                                                        y < PLAY_Y + CHAR_BUTTON_HEIGHT && y >= PLAY_Y}
    private val isOptionsHovered = { x: Float, y: Float -> x > BUTTON_X && x <= BUTTON_X + CHAR_BUTTON_WIDTH*"options".length &&
                                                           y < OPTIONS_Y + CHAR_BUTTON_HEIGHT && y >= OPTIONS_Y }
    private val isAboutHovered = { x: Float, y: Float -> x > BUTTON_X && x <= BUTTON_X + CHAR_BUTTON_WIDTH*"about".length &&
                                                         y < ABOUT_Y + CHAR_BUTTON_HEIGHT && y >= ABOUT_Y }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        introMusic.play()
        game.batch?.begin()
        game.batch?.draw(menuImg, 0f, 0f, HackermanGame.getWidth(), HackermanGame.getHeight())

        val x = Gdx.input.getX().toFloat()
        // y is inverted
        val y = HackermanGame.getHeight() - Gdx.input.getY()

        highlightHoveredItems(x, y)

        if (Gdx.input.justTouched()) {
            handleClickAction(x, y)
        }

        game.batch?.end()
    }

    private fun handleClickAction(x: Float, y: Float) {
        if (isPlayHovered(x,y) ) {
            introMusic.stop()
            ScreenController.instance.setLevelMap()
        }
        else if (isOptionsHovered(x,y)) {
            ScreenController.instance.setOptionsMenu()
        }
        else if  (isAboutHovered(x,y)) {
            ScreenController.instance.setAboutScreen()
        }
    }

    fun highlightHoveredItems(x: Float, y: Float) {
        // draw button highlights based on location
        if (isPlayHovered(x,y) ) {
            game.batch?.draw(playButtonClicked, BUTTON_X, PLAY_Y, CHAR_BUTTON_WIDTH*"start".length, CHAR_BUTTON_HEIGHT)
        }
        else {
            game.batch?.draw(playButtonNormal, BUTTON_X, PLAY_Y, CHAR_BUTTON_WIDTH*"start".length, CHAR_BUTTON_HEIGHT)
        }
        if (isOptionsHovered(x,y)) {
            game.batch?.draw(optionsButtonClicked, BUTTON_X, OPTIONS_Y, CHAR_BUTTON_WIDTH*"options".length, CHAR_BUTTON_HEIGHT)
        }
        else {
            game.batch?.draw(optionsButtonNormal, BUTTON_X, OPTIONS_Y, CHAR_BUTTON_WIDTH*"options".length, CHAR_BUTTON_HEIGHT)
        }
        if (isAboutHovered(x,y)) {
            game.batch?.draw(aboutButtonClicked, BUTTON_X, ABOUT_Y, CHAR_BUTTON_WIDTH*"about".length, CHAR_BUTTON_HEIGHT)
        }
        else {
            game.batch?.draw(aboutButtonNormal, BUTTON_X, ABOUT_Y, CHAR_BUTTON_WIDTH*"about".length, CHAR_BUTTON_HEIGHT)
        }
    }


    override fun dispose() {
        menuImg.dispose()
        playButtonNormal.dispose()
        playButtonClicked.dispose()
        optionsButtonNormal.dispose()
        optionsButtonClicked.dispose()
        aboutButtonNormal.dispose()
        aboutButtonClicked.dispose()
    }

    override fun hide() {}
    override fun show() {}
    override fun pause() {}
    override fun resume() {}
    override fun resize(width: Int, height: Int) {}
}