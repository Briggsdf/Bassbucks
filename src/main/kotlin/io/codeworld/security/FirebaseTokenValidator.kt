package io.codeworld.security

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import io.codeworld.utils.FirebaseUtil
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Prototype
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.token.validator.TokenValidator
import io.reactivex.Flowable
import javax.inject.Singleton


@Prototype
class FirebaseTokenValidator : TokenValidator {

    override fun validateToken(token: String?): Flowable<Authentication> {
        FirebaseUtil.initialise()

        val result: Flowable<Authentication> = if (token.isNullOrEmpty().not()) {
            try {
                val firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token)
                Flowable.just(FirebaseAuthInternal(firebaseToken) as Authentication)
            } catch (ex: FirebaseAuthException) {
                Flowable.empty<Authentication>()
            }
        } else {
            Flowable.empty<Authentication>()
        }

        return result
    }
}