package com.antsyferov.pidkova.constants

import com.antsyferov.network.ConstantsProvider
import com.antsyferov.pidkova.BASE_URL
import org.koin.core.annotation.Single

@Single
class ConstantsProviderImpl: ConstantsProvider {
    override fun getBaseUrl() = BASE_URL
}