package labrinth.wrappers

import java.util.*

/**
 * Indicates mod's supported environments. Can be Client, Server or Client and Server.
 * @param displayName fallback display name
 * @param languageKey language key to user's selected language variation
 */
enum class SupportedEnvironment(
    private val displayName: String, private
    val languageKey: String
) {
    CLIENT("Client", ""),
    SERVER("Server", ""),
    BOTH("Client and Server", "")
    ;

    companion object {
        fun find(clientSide: String, serverSide: String): List<SupportedEnvironment> {
            val list = LinkedList<SupportedEnvironment>()
            if (clientSide == "required") list.add(CLIENT)
            if (serverSide == "required") list.add(SERVER)
            if (clientSide == "optional" && serverSide == "optional") list.add(BOTH)
           return list
        }
    }

}