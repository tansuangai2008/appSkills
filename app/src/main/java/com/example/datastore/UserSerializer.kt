package com.example.datastore

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

/**
 *    author : LIU YANG
 *    date   : 2024/8/20
 *    desc   :
 */
object UserSerializer : Serializer<User> {
    override val defaultValue: User
        get() = User.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): User {
        return User.parseFrom(input)
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        t.writeTo(output)
    }
}
