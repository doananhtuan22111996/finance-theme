import vn.finance.src.Configs

plugins {
    alias(mobilex.plugins.androidLibrary)
    alias(mobilex.plugins.kotlinAndroid)
    `maven-publish`
}

android {
    namespace = Configs.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk
    }

    compileOptions {
        sourceCompatibility = Configs.javaVersion
        targetCompatibility = Configs.javaVersion
    }
    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Configs.kotlinCompilerExtensionVersion
    }
    publishing {
        multipleVariants("all") {
            allVariants()
            withSourcesJar()
            withJavadocJar()
        }
    }
}

publishing {
    val ghUsername = System.getenv("GH_USERNAME")
    val ghPassword = System.getenv("GH_TOKEN")
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("${Configs.mavenDomain}/${ghUsername}/finance-theme")
            credentials {
                username = ghUsername
                password = ghPassword
            }
        }
    }
    publications {
        create<MavenPublication>("mavenAndroid") {
            afterEvaluate {
                from(components["all"])
            }
            groupId = "vn.finance.libs"
            artifactId = "theme"
            version = "1.0.0" // Set your desired version here
        }
    }
}

dependencies {
    implementation(mobilex.androidxCoreKtx)
    implementation(mobilex.androidxComposeUi)
    implementation(mobilex.androidxComposeMaterial3Android)
    implementation(mobilex.androidxComposeUiTextGoogleFonts)
}