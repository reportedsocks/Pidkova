package com.antsyferov.network

import com.antsyferov.network.models.Session
import org.koin.core.annotation.Single

@Single
class InMemoryStorage {
    private var session: Session? = null

    fun getSession() = session

    fun setSession(session: Session) {
        this.session = session
    }
}