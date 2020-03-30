/* ScreenController: Handles transitions between screens in the game. Stores instances of previous screens for quick restoration.
*  Behaves similar to a singleton, but with a fron facing method that allows for a single instantiation. */
package com.mygdx.game

import com.badlogic.gdx.Screen
import com.mygdx.game.screens.*

class ScreenController {
    private var currentScreen: Screen? = null
    private var game: HackermanGame

    companion object{
        lateinit var instance: ScreenController
        fun init(game: HackermanGame) {
            instance = ScreenController(game)
        }
    }

    // pass in the screens that are init with the game instance
    private constructor(game: HackermanGame) {
        this.game = game
    }

    private fun resetCurrentScreen(newScreen: Screen) {
        currentScreen?.dispose()
        currentScreen = newScreen
    }

    fun setMainMenu() {
        var newScreen = MainMenuScreen(game)
        resetCurrentScreen(newScreen)
        game.setScreen(newScreen)
    }

    fun setAboutScreen() {
        var newScreen = AboutScreen(game)
        resetCurrentScreen(newScreen)
        game.setScreen(newScreen)
    }

    fun setOptionsMenu() {
        val newScreen = OptionsMenu(game)
        resetCurrentScreen(newScreen)
        game.setScreen(newScreen)
    }

    fun setLevelMap() {
        val newScreen = LevelMap(game)
        resetCurrentScreen(newScreen)
        game.setScreen(newScreen)
    }

    fun setWarezScreen() {
        val newScreen = WarezNode(game)
        resetCurrentScreen(newScreen)
        // TODO: Grab pre-dialog
        // displayPreDialog(nodeActor.infoFile)
        game.setScreen(newScreen)
    }

    fun setLevelScreen() {

    }
}