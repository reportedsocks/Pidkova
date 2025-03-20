## Pidkova
#### This is my sandbox project for trying out some technologies, patterns and best practices.

Below you can find more information about the project together with some highlights.
To perform network calls, you will need to setup a Mockoon server locally, using the pidkova_env.json.

### Tech stack
 - Kotlin
 - Compose
 - Ktor
 - Realm (wanted to give it a try, Room is better imo)
 - Koin
 - JUnit, Mockk

### Architecture
I have implemented a multimodal layered architecture. Data layer is implemented with :network, :database and :repository modules.
Project also has a :domain module, with is kind of excessive here so it is rather a blueprint for future projects.
UI layer is implemented with :feature modules.

I have also used Gradle convention plugins to configure all the modules.

### Features

#### Offline first
Data is first loaded from database and is refreshed as network updates come in.

#### Type-safe navigation with nested graphs
See how navigation is implemented in com.antsyferov.main.navigation.MainNavHost. 
Project also supports the following deeplinks, which you can try with adb:
1. to product details "adb shell am start -a android.intent.action.VIEW -d "app://pidkova/?destination=products&productId=1" com.antsyferov.pidkova.demo.whitelabel "
2. to cart "adb shell am start -a android.intent.action.VIEW -d "app://pidkova/?destination=cart" com.antsyferov.pidkova.demo.whitelabel"

#### Navigation logging
I created a wrapper for navigation to automatically log all the destinations based on custom annotations.
Can be hooked up with analytics. See com.antsyferov.ui.analytics.composableWithAnalytics

#### Custom gradle task to generate strings res
Had an idea for gradle task that would generate a res file for string based on CVS,
which can be extracted from google sheets for example. See more in StringGenerationPlugin.

#### Ktor Auth
Configured token authorization with ktor. Authorized client will add required headers to each request and refresh tokens when needed.

#### Testing
Added unit tests for repositories. And some UI tests, can be found in :feature:products and :app.

#### Custom theme
Opted for implementation of a custom UI theme instead of material theme. See :core:ui module.
Additionally project supports flavours to potentially switch theme implementation and network base url.

