/* Handles displaying dialog box when a node is clicked on. */
package com.mygdx.game.dialogs

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Dialog
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.mygdx.game.LevelNodeActor
import com.mygdx.game.Paths
import com.mygdx.game.ScreenController

class LevelInfoDialog: Dialog {
    val WIDTH = 800f
    val HEIGHT = 300f
    var actorInstance: LevelNodeActor

    init {
        // only add texture region once
        if (!addedRegion) {
            skin.addRegions(atlas)
            addedRegion = true
        }
    }

    // static instance of resources used all the time. Reduces memory needed for each dialog box
    companion object {
        var addedRegion = false
        val atlas = TextureAtlas(Gdx.files.internal(Paths.Skins.Sgx.ATLAS))
        val skin = Skin(Gdx.files.internal(Paths.Skins.Sgx.JSON))
    }

    constructor (actorInst: LevelNodeActor): super(actorInst.name, LevelInfoDialog.skin) {
        Gdx.app.debug("DIALOG: ", "CONSTRUCTOR")

        name = actorInst.name
        text(actorInst.levelDes)
        actorInstance = actorInst

        button("Enter", true)
        button("Exit", false)
    }

    override fun show(stage: Stage): Dialog {
        super.show(stage)
        setBounds(actorInstance.x, actorInstance.y, WIDTH, HEIGHT)
        return this
    }

    /* Expects object to be boolean. Enters level if true, stays at menu otherwise*/
    override fun result(resObject: Any?) {
        // if true, call Controller
        if (resObject as Boolean) {
            when(actorInstance.levelType) {
                "level" -> ScreenController.instance.setLevelScreen()
                "warez" -> ScreenController.instance.setWarezScreen()
                else -> return
            }
        }
    }
}