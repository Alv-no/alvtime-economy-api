import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.cloud.tools.gradle.appengine.appyaml.AppEngineAppYamlExtension

plugins {
	id("org.springframework.boot") version "2.2.2.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
}

buildscript {
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("com.google.cloud.tools:appengine-gradle-plugin:2.2.0")
	}
}

group = "no.alv"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "Hoxton.SR1"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.cloud:spring-cloud-gcp-starter")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.cloud:spring-cloud-gcp-starter")
	implementation("org.springframework.cloud:spring-cloud-gcp-starter-storage")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}
dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

apply(plugin = "com.google.cloud.tools.appengine")

the<AppEngineAppYamlExtension>().apply {
	tools {
		// configure the Cloud Sdk tooling
	}
	stage {
		// configure staging for deployment
	}
	deploy {
		deploy.projectId = "GCLOUD_CONFIG"
		deploy.version = "GCLOUD_CONFIG"
	}
}


