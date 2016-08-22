package org.specnaz.kotlin.utils

import kotlin.properties.Delegates

fun <T : Any> deferred() = Deferred<T>()

class Deferred<T : Any> {
    var v: T by Delegates.notNull<T>()
}
