package io.codeworld.security

import com.google.firebase.auth.FirebaseToken
import io.micronaut.security.authentication.Authentication
import java.util.*


class FirebaseAuthentication internal constructor(private val firebaseToken: FirebaseToken) : Authentication {
    override fun getAttributes(): Map<String, Any> {
        return Collections.unmodifiableMap(firebaseToken.claims)
    }

    override fun getName(): String {
        return firebaseToken.uid
    }
}