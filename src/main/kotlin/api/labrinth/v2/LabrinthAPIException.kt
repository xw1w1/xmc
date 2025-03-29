package api.labrinth.v2

/**
 * Represents an error due to bad response code from [LabrinthAPIProvider]
 */
class LabrinthAPIException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}