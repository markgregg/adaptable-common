import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	`maven-publish`
	signing
	id("org.jetbrains.dokka") version "1.4.20"
}

group = "io.github.markgregg"
version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

val projectDescription = "Adaptable common functionality"
val githubRepo = "markgregg/adaptable-common"
val licenseUrl = "https://opensource.org/licenses/Apache-2.0"
val licenseName = "Apache 2"

val kotestVersion = "5.4.2"
val mockitoKotlinVersion = "3.2.0"
val adaptableExpression = "1.0.0-SNAPSHOT"

repositories {
	mavenCentral()
	maven {
		url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
	}
}

dependencies {
	implementation("io.github.markgregg:adaptable-expression:$adaptableExpression")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.slf4j:slf4j-api")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
	testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
	testImplementation("io.kotest:kotest-property:$kotestVersion")
	testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
}

tasks.withType<Test>().configureEach {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	enabled = false
}

tasks.getByName<Jar>("jar") {
	enabled = true
	archiveClassifier.set("")
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = project.group.toString()
			artifactId = project.name
			version = project.version.toString()
			from(components["kotlin"])
			pom {
				name.set(project.name)
				description.set(projectDescription)
				url.set("https://github.com/$githubRepo")
				licenses {
					license {
						name.set(licenseName)
						url.set(licenseUrl)
					}
				}
				developers {
					developer {
						id.set("markgregg")
						name.set("Mark Gregg")
					}
				}
				scm {
					url.set(
						"https://github.com/$githubRepo.git"
					)
					connection.set(
						"scm:git:git://github.com/$githubRepo.git"
					)
					developerConnection.set(
						"scm:git:git://github.com/$githubRepo.git"
					)
				}
				issueManagement {
					url.set("https://github.com/$githubRepo/issues")
				}
			}
		}
	}

	repositories {
		maven {
			name = "mavenStaging"
			url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
			credentials {
				username = providers.gradleProperty("ossrhUsername").orElse("Not found").get()
				password = providers.gradleProperty("ossrhPassword").orElse("Not found").get()
			}
		}
		maven {
			name = "mavenSnapshots"
			url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
			credentials {
				username = providers.gradleProperty("ossrhUsername").orElse("Not found").get()
				password = providers.gradleProperty("ossrhPassword").orElse("Not found").get()
			}
		}
	}


	signing {
		sign(publishing.publications["maven"])
	}
}

