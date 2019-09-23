pipeline {
	agent any
	options {
		disableConcurrentBuilds()
		timeout time: 45, unit: 'MINUTES'
		buildDiscarder logRotator( daysToKeepStr: "14" )
	}
	tools {
		jdk 'J8'
	}
	environment {
		JENKINS_NODE_COOKIE = 'dontkillme'
	}

	stages {
		stage('Compile') {
			steps {
				sh "git fetch --force --tags"
				sh "chmod +x gradlew"
				sh "./gradlew clean"
				sh "./gradlew classes testClasses"
			}
		}
		stage('Checks') {
			steps {
				sh "./gradlew check --continue || true"
				junit allowEmptyResults: true, testResults: "**/TEST-*.xml"
				step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
			}
		}
		stage('Archive') {
			steps {
				archiveArtifacts fingerprint: true,
				artifacts: '**/build/publications/*/pom-*.xml, **/build/libs/*.jar'
			}
		}
	}
}

