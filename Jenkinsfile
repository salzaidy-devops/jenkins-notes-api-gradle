#!/usr/bin/env groovy

library identifier: 'jenkins-shared-library@main', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/salzaidy-devops/jenkins-shared-library.git',
    credentialsID: 'github-credentials'
    ]
)



pipeline {
    agent any

    tools {
        gradle 'gradle-8.12'
    }

    environment {
        IMAGE_NAME = 'salzaidy/notes-api:1.0'
    }

    stages {
        stage('Initialize') {
            steps {
                echo 'Initializing...'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'gradle test'
            }
        }
        stage('buildJarFile') {
            steps {
                echo 'Building JAR file...'
                // sh 'gradlew bootJar'
                buildGradleBootJar()
            }
        }
        stage('BuildDockerImage') {
            steps {
                script {
                    echo 'Building Docker image...'
                    buildImage(ENV.IMAGE_NAME)
                    dockerLogin()
                    dockerPush(ENV.IMAGE_NAME)
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo 'Deploying Docker image...'
                    def dockerCMD = "docker run -d -p 4000:4000 ${ENV.IMAGE_NAME}"
                    
                    sshagent(['ec2-server-key']) {
                        // this flag is to avoid host key verification issue
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@3.17.150.175 ${dockerCMD}"
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}