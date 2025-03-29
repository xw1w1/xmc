package earth.groundctrl.fluent.lib

import javafx.stage.Stage
import earth.groundctrl.fluent.lib.dwm.DwmAttribute
import earth.groundctrl.fluent.lib.dwm.HwndLookupException
import earth.groundctrl.fluent.lib.dwm.WindowHandle
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * FluentLib library.
 * Thanks to Erocia for library and theme especially:
 *
 * [https://github.com/Eroica/javafx-fluent-theme](https://github.com/Eroica/javafx-fluent-theme)
 */
object Windows {
    private external fun isdarkmode(): Boolean

    private external fun setmica(title: String, useMica: Boolean): Int
    private external fun setheaderbar(title: String, useHeaderBar: Boolean): Int
    private external fun setdragarea(x: Int, width: Int, scale: Double)

    external fun buildnumber(): Int
    external fun subclass(title: String, useMica: Boolean, useHeaderBar: Boolean): Int

    fun erSetMicaFor(title: String, useMica: Boolean) {
        setmica(title, useMica)
    }

    fun setMicaFor(stage: Stage, useMica: Boolean) {
        try {
            val handle = WindowHandle.tryFind(stage)
            handle.dwmSetBooleanValue(DwmAttribute.DWMWA_USE_IMMERSIVE_DARK_MODE, useMica)
            if (!handle.dwmSetIntValue(
                    DwmAttribute.DWMWA_SYSTEMBACKDROP_TYPE,
                    DwmAttribute.DWMSBT_MAINWINDOW.value
                )) {
                handle.dwmSetBooleanValue(DwmAttribute.DWMWA_MICA_EFFECT, useMica)
            }
        } catch (ignored: HwndLookupException) {}
    }

    fun setHeaderBarFor(title: String, useHeaderBar: Boolean): Int {
        return setheaderbar(title, useHeaderBar)
    }

    fun setDragArea(x: Int, width: Int, scale: Double) {
        setdragarea(x, width, scale)
    }

    fun isAmdGpu(): Boolean {
        val process = Runtime.getRuntime().exec(
            arrayOf(
                "powershell",
                "-command",
                """"(Get-WmiObject -class Win32_VideoController -Property Description).Description""""
            )
        )
        val output = BufferedReader(InputStreamReader(process.inputStream)).use { it.readText() }
        return "AMD" in output.uppercase()
    }
}