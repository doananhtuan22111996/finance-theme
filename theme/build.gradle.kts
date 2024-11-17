import vn.finance.src.Configs

plugins {
    vn.core.plugins.androidLibrary
    vn.core.plugins.androidCompose
    vn.core.plugins.androidPublishing
}

android {
    namespace = Configs.NAME_SPACE
}

publishing {
    publications {
        create<MavenPublication>(Configs.Artifact.ARTIFACT_ID) {
            afterEvaluate {
                from(components["all"])
            }
            groupId = Configs.Artifact.GROUP_ID // Replace with your GitHub username
            artifactId = Configs.Artifact.ARTIFACT_ID
            version = Configs.Artifact.VERSION // Set your desired version here
        }
    }
}
