/*
 * Copyright 2023 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import com.antsyferov.pidkova.configureKotlinJvm
import com.antsyferov.pidkova.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
                apply("com.google.devtools.ksp")
            }
            configureKotlinJvm()
            dependencies {
                add("testImplementation", kotlin("test"))
                add("implementation", libs.findLibrary("koin-jvm").get())
                add("implementation", libs.findLibrary("koin-annotations").get())
                add("ksp", libs.findLibrary("koin-ksp").get())
            }
        }
    }
}
