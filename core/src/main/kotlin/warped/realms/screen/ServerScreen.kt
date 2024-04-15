package warped.realms.screen

import com.badlogic.gdx.*
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.Align.center
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.actors.onClick
import ktx.app.KtxGame
import ktx.app.KtxScreen
import java.lang.Thread.sleep
import kotlin.system.exitProcess

class ServerScreen : KtxGame<KtxScreen>()
{
    private lateinit var stage: Stage
    private lateinit var skin: Skin
    private lateinit var createRoomButton: TextButton
    private lateinit var signInButton: TextButton
    private lateinit var signUpButton: TextButton
    private lateinit var backButton: TextButton
    private lateinit var closeButton: TextButton
    private lateinit var infoLabel: Label
    private lateinit var sendTable: Table
    private lateinit var chooseTable: Table
    private lateinit var textField1: TextField
    private lateinit var textField2: TextField

    override fun create() {
        stage = Stage(FitViewport(900f, 600f))
        skin = Skin(Gdx.files.internal("ui/uiskin.json"))

        createRoomButton = TextButton("Create Game Room", skin, "default").apply {
            pad(8f)
            center()
            onClick {
                closeButton.isDisabled = true
                createRoomButton.isDisabled = true
                backButton.isDisabled = true
                Gdx.app.exit()
            }
        }
        closeButton = TextButton("Close", skin, "default").apply {
            pad(8f)
            x = (Gdx.graphics.width.toFloat() * 0.9).toFloat()
            y = (Gdx.graphics.height.toFloat() * 0.1).toFloat()
            onClick {
                Gdx.app.exit()
                    //exitProcess(0)
                textField1.text = "Hello Warped Realms!"
            }
        }

        infoLabel = Label("default", skin, "default").apply {
            setAlignment(center)
            isVisible = false
        }

        val listener = object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                createRoomButton.setText("Create Game Room")
                infoLabel.isVisible = false
            }
        }

        signInButton = TextButton("Server Controller", skin, "default").apply {
            pad(8f)
            center()
            onClick {
                chooseTable.isVisible = false
                sendTable.isVisible = true
            }
        }

        signUpButton = TextButton("Start Server", skin, "default").apply {
            pad(8f)
            center()
            onClick {
                chooseTable.isVisible = false
                sendTable.isVisible = true
                Gdx.app.exit()
            }
        }

        backButton = TextButton("Back", skin, "default").apply {
            pad(8f)
            center()
            onClick {
                sendTable.isVisible = false
                chooseTable.isVisible = true
                textField1.text = ""
            }
            addListener(listener)
        }

        textField1 = TextField("", skin, "spinner").apply {
            messageText = "username"
            alignment = center
            addListener(listener)
        }

        textField2 = TextField("", skin, "spinner").apply {
            messageText = "password"
            alignment = center
            addListener(listener)
        }

        sendTable = Table(skin).apply {
            isVisible = false
            center()
            addListener {
            if (it is InputEvent && it.type == InputEvent.Type.keyDown && it.keyCode == Input.Keys.ENTER) {
                closeButton.isDisabled = true
                createRoomButton.isDisabled = true
                backButton.isDisabled = true
                Gdx.app.exit()
                return@addListener true
            }
                false
            }
            setFillParent(true)
            add(infoLabel).padBottom(10f).row()
            add(textField1).padBottom(10f).row()
            add(textField2).padBottom(10f).row()
            add(createRoomButton).padBottom(10f).row()
            add(backButton)
        }

        chooseTable = Table(skin).apply {
            isVisible = true
            center()
            setFillParent(true)
            add(signUpButton).padBottom(10f).row()
            add(signInButton)
        }

        stage.apply {
            addActor(sendTable)
            addActor(chooseTable)
            addActor(closeButton)
        }

        Gdx.input.inputProcessor = stage
    }
    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }
    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height)
    }
    override fun dispose() {
        stage.dispose()
        skin.dispose()
        super.dispose()
    }
}
