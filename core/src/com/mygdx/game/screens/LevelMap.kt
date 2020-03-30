package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.XmlReader
import com.mygdx.game.HackermanGame
import com.mygdx.game.LevelNodeActor
import com.mygdx.game.Paths
import java.util.*


class LevelMap(val game: HackermanGame) : Screen {
    private var levelBackground = Texture(Gdx.files.internal("title.png"))
    private var nodeImg = Texture(Gdx.files.internal(Paths.Clickables.PLAY_BUTTON_NORMAL))
    private var camera = OrthographicCamera()
    private var levelNodes: MutableList<LevelNodeActor> = NodeRenderer().getNodes()
    private var stage = Stage()
    private var lineRenderer = ShapeRenderer()

    private val BACKGROUND_WIDTH = 3000f
    private val BACKGROUND_HEIGHT = 2100f
    private val SCROLL_AMOUNT = 20f

    override fun show() {
        camera.setToOrtho(false, HackermanGame.getWidth(), HackermanGame.getHeight())
        Gdx.input.inputProcessor = stage
        for (node in levelNodes) {
            stage.addActor(node)
        }
    }

    override fun render(delta: Float) {
        game.batch?.setProjectionMatrix(camera.combined)
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.viewport.camera = camera
        game.batch?.begin()
        camera.update()
        //game.batch?.draw(levelBackground, 0f, 0f, BACKGROUND_WIDTH, BACKGROUND_HEIGHT)
        drawConnectingLines(1, camera.combined)
        handleKeyboardInput()
        game.batch?.end()

        // draw nodes
        stage.act(delta)
        stage.draw()
    }


    fun drawConnectingLines(lineWidth: Int, projectionMatrix: Matrix4?) {
        for (dependentActor in levelNodes) {
            var dependencyActor: LevelNodeActor? = null
            // find corresponding actor to dependency
            for (dependency in levelNodes) {
                if (dependentActor.dependency == dependency.name) {
                    dependencyActor = dependency
                    break
                }
            }
            if (dependencyActor == null) {
                continue
            }
            val color: Color = Color.LIGHT_GRAY
            Gdx.gl.glLineWidth(lineWidth.toFloat())
            lineRenderer.setProjectionMatrix(projectionMatrix)
            lineRenderer.begin(ShapeRenderer.ShapeType.Line)
            lineRenderer.setColor(color)
            lineRenderer.line(Vector2(dependencyActor.x, dependencyActor.y), Vector2(dependentActor.x, dependentActor.y))
            lineRenderer.end()
            Gdx.gl.glLineWidth(0.25f)
        }

    }


    // allows for scrolling around map. Handles case where use scrolls out of bounds
    fun handleKeyboardInput() {
        var deltaX = 0f
        var deltaY = 0f
        val curX = camera.position.x
        val curY = camera.position.y

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            deltaX = if (camera.position.x - HackermanGame.getWidth() /2 - SCROLL_AMOUNT > 0f) -1 * SCROLL_AMOUNT else 0f
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            deltaX = if (camera.position.x + HackermanGame.getWidth() /2 + SCROLL_AMOUNT < BACKGROUND_WIDTH) SCROLL_AMOUNT else 0f
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            deltaY = if (camera.position.y + HackermanGame.getHeight() /2 + SCROLL_AMOUNT < BACKGROUND_HEIGHT) SCROLL_AMOUNT else 0f
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            deltaY = if (camera.position.y - HackermanGame.getHeight() /2 - SCROLL_AMOUNT > 0f) -1*SCROLL_AMOUNT else 0f
        }
        camera.translate(deltaX, deltaY)
    }

    override fun dispose() {
        levelBackground.dispose()
        nodeImg.dispose()
    }

    override fun hide() {}

    override fun pause() {}
    override fun resume() {}
    override fun resize(width: Int, height: Int) {}

    // helper class used to generate node actors
    private class NodeRenderer {
        fun getNodes(): MutableList<LevelNodeActor> {
            val levelsFile = Paths.LevelFiles.LEVEL_LAYOUT
            val actors: MutableList<LevelNodeActor> = ArrayList()
            val reader = XmlReader()

            val root = reader.parse(Gdx.files.internal(Paths.LevelFiles.LEVEL_LAYOUT))
            for (i in 0 until root.childCount) {
                val level = root.getChild(i)
                val x: Float = level.getChildByName("x").text.toFloat()
                val y: Float = level.getChildByName("y").text.toFloat()
                val description = level.getChildByName("description").text
                val name = level.getChildByName("name").text
                var dependency = level.getChildByName("dependency").text
                val levelType = level.getChildByName("type").text
                val (normal, highlighted) = getNodeImages(level.getChildByName("img").text)
                actors.add(LevelNodeActor(x, y, highlighted, normal, description, name, dependency, levelType))
            }
            return actors
        }

        private fun getNodeImages(imgType: String): Pair<String, String> {
            when (imgType) {
                "default" -> return Pair(Paths.Clickables.LevelNodes.DEFAULT_NODE_NORMAL, Paths.Clickables.LevelNodes.DEFAULT_NODE_CLICKED)
                "warez" ->return Pair(Paths.Clickables.LevelNodes.WAREZ_NODE_NORMAL, Paths.Clickables.LevelNodes.WAREZ_NODE_CLICKED)
                else -> return Pair(Paths.Clickables.LevelNodes.DEFAULT_NODE_NORMAL, Paths.Clickables.LevelNodes.DEFAULT_NODE_CLICKED)
            }
        }
    }
}