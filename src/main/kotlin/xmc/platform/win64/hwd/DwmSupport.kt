package xmc.platform.win64.hwd

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.PointerType
import com.sun.jna.platform.win32.WinDef.HWND
import com.sun.jna.platform.win32.WinNT.HRESULT

internal interface DwmSupport : Library {
    fun DwmSetWindowAttribute(
        hwnd: HWND?,
        dwAttribute: Int,
        pvAttribute: PointerType?,
        cbAttribute: Int
    ): HRESULT?

    companion object {
        val INSTANCE: xmc.platform.win64.hwd.DwmSupport = Native.load("dwmapi", xmc.platform.win64.hwd.DwmSupport::class.java)
    }
}