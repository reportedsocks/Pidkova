package com.antsyferov.pidkova

import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

data class MyMainClass(
    val id: String,
    val dep: SecondaryClass
)


interface SomeInterface {

}

class SomeImplementation: SomeInterface


interface AnotherInterface

@Single
@Named("impl1")
class AnotherImplementation: AnotherInterface

@Single
@Named("impl2")
class AnotherNamedImplementation: AnotherInterface