package io.codeworld.security
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import io.codeworld.utils.FirebaseUtil
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.token.validator.TokenValidator
import io.reactivex.Flowable
import org.intellij.lang.annotations.Flow
import org.reactivestreams.Publisher
import javax.inject.Singleton

@Singleton
class FirebaseTokenValidator : TokenValidator {
    override fun validateToken(token: String?): Publisher<Authentication>? {
        FirebaseUtil.initialise()

        val result: Flowable<Authentication> = if (token.isNullOrEmpty().not()) {
            try {
                val firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token)
                Flowable.just(FirebaseAuthentication(firebaseToken) as Authentication)
            } catch (ex: FirebaseAuthException) {
                Flowable.empty<Authentication>()
            }
        } else {
            Flowable.empty<Authentication>()
        }

        return result
    }

}