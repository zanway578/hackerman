/* Builds */
package com.mygdx.game.screens

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.HackermanGame

class WarezNode : Screen {
    private var game: HackermanGame
    //private var ownerImage: Texture

    constructor(game: HackermanGame) {
        this.game = game
    }

    override fun show() {}

    override fun render(delta: Float) {}

    override fun dispose() {}

    override fun hide() {}
    override fun pause() {}
    override fun resume() {}
    override fun resize(width: Int, height: Int) {}

    // parses file
    private class LevelParser {
        // expects file name
        fun parseData(warezName: String) {

        }
    }
}