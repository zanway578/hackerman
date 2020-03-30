package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Dialog
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.mygdx.game.dialogs.LevelInfoDialog


class LevelNodeActor : Actor {
    // used to pass object instance in anonymous InputListener child class
    var actorInstance = this
    // different images used for when sprite is hovered over
    var highlight_img: Sprite
    var normal_img: Sprite = Sprite(Texture(Gdx.files.internal(Paths.Clickables.PLAY_BUTTON_NORMAL)))

    var highlight = false
    // description of level
    var levelDes = ""
    // level that must be defeated for this one to be unlocked
    var dependency = ""
    var levelType = ""

    constructor (x: Float, y: Float, highlight_img_path: String, normal_img_path: String,
                 levelDescription: String, levelName: String, levelDependency: String,
                 lType: String) {
        // bind data
        highlight_img = Sprite(Texture(Gdx.files.internal(highlight_img_path)))
        normal_img = Sprite(Texture(Gdx.files.internal(normal_img_path)))
        levelDes = levelDescription
        touchable = Touchable.enabled
        name = levelName
        dependency = levelDependency
        levelType = lType

        // bind actor and set input response
        setBounds(x, y, highlight_img.width, highlight_img.height)
        touchable = Touchable.enabled
        addListener(constructInputListener())
    }

    override fun positionChanged() {
        normal_img.setPosition(x, y)
        highlight_img.setPosition(x, y)
        super.positionChanged()
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (highlight)
            highlight_img.draw(batch)
        else
            normal_img.draw(batch)
    }

    /* Handles when node is hovered over as well as when it  */
    private fun constructInputListener(): InputListener {
        return object: InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                super.touchDown(event, x, y, pointer, button)
                val dialog = LevelInfoDialog(actorInstance)
                dialog.show(actorInstance.stage)
                return false
            }
            // handles mouse hovering
            override fun enter(event: InputEvent?, x: Float, y: Float, pointer: Int, fromActor: Actor?) {
                super.enter(event, x, y, pointer, fromActor)
                highlight = true
            }
            // handle mouse leaving - unhighlight actor
            override fun exit(event: InputEvent?, x: Float, y: Float, pointer: Int, toActor: Actor?) {
                super.exit(event, x, y, pointer, toActor)
                highlight = false
            }
        }
    }

}