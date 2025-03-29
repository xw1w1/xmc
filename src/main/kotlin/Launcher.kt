import frontend.element.NavigationBar
import earth.groundctrl.fluent.lib.Windows
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.stage.Stage
import backend.Preferences
import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.stage.StageStyle
import frontend.pages.UIPage
import translations.ClientLanguage
import translations.I18n

class Launcher : Application() {

    private val root: AnchorPane = AnchorPane().apply {
        background = Background(BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))
    }

    private val rootScene: Scene = Scene(this.root, 800.0, 500.0).apply {
        val platform = Preferences.platform().name.lowercase()
        stylesheets.add("embedded/styles/$platform/fluent-dark.css")
        fill = Color.TRANSPARENT
    }

    private lateinit var primaryStage: Stage

    private val navigationBar: NavigationBar = NavigationBar(this.rootScene)

    override fun start(stage: Stage) {
        this.primaryStage = stage
        this.primaryStage.minWidth = 800.0
        this.primaryStage.minHeight = 500.0
        this.primaryStage.scene = this.rootScene
        this.root.children.add(this.navigationBar)
        this.primaryStage.initStyle(StageStyle.UNIFIED)
        this.primaryStage.title = I18n.translate("xmc.launcher", ClientLanguage.ENGLISH)
        this.primaryStage.show()
        this.decorate()
    }

    fun openPage(page: UIPage) {
        this.root.children.clear()
        this.root.children.addAll(this.navigationBar, page.createContents())
        this.attachNavBarToAnchor()
    }

    private fun decorate() {
        val title = I18n.translate("xmc.launcher", ClientLanguage.ENGLISH)
        val useWindowsHeader = XMC.startupArgs().contains("--enable-window-header")
        Windows.setMicaFor(this.primaryStage, true)
        if (useWindowsHeader) Windows.setHeaderBarFor(title, true)
        this.primaryStage.icons.add(Image("appicon.png"))
    }

    private fun attachNavBarToAnchor() {
        AnchorPane.setTopAnchor(this.navigationBar, this.navigationBar.prefHeight)
    }

}