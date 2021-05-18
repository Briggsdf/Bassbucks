package io.codeworld.utils

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import io.micronaut.core.io.ResourceLoader
import io.micronaut.core.io.ResourceResolver
import io.micronaut.core.io.scan.ClassPathResourceLoader


object FirebaseUtil {

    private val loader: ResourceLoader = ResourceResolver().getLoader(ClassPathResourceLoader::class.java).get();

    private var initialised: Boolean = false

    private val options: FirebaseOptions? = FirebaseOptions.builder()
        .setCredentials(
            GoogleCredentials.getApplicationDefault()
        )
        .build()

    fun initialise() {
        if (initialised.not()) {
            FirebaseApp.initializeApp(options)
            initialised = true
        }
    }

}