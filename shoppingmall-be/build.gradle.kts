plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.jpa") version "1.9.25"
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.jetbrains.kotlin.kapt") version "1.9.25"
}

group = "com.shoppingmall"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://oss.sonatype.org/content/repositories/releases/") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// QueryDSL 의존성
	implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
	implementation("com.querydsl:querydsl-apt:5.1.0:jakarta")
	implementation("jakarta.persistence:jakarta.persistence-api")
	implementation("jakarta.annotation:jakarta.annotation-api")

	kapt("com.querydsl:querydsl-apt:5.1.0:jakarta")
	kapt("org.springframework.boot:spring-boot-configuration-processor")

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
	sourceSets.named("main") {
		kotlin.srcDir("build/generated")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

kapt {
	arguments {
		arg("querydsl.entityAccessors", "true")
		arg("querydsl.useFields", "false")
		arg("querydsl.jakarta", "true") // ← 요거 추가
	}
}

// querydsl QClass 경로 설정
sourceSets["main"].java.srcDirs("build/generated/source/kapt/main")

// compileQuerydsl task 등록
tasks.register("compileQuerydsl") {
	group = "build"
	description = "Generate QueryDSL Q classes"
	dependsOn("kaptKotlin") // kaptKotlin을 실행하게 연결
}