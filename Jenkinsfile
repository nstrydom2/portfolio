pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JDK8'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout scm
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    withMaven(maven: 'Maven') {
                        sh 'mvn clean install'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Add deployment steps if needed
                    // For example, deploying to a Tomcat server
                    // sh 'mvn tomcat7:deploy'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    withMaven(maven: 'Maven') {
                        sh 'mvn test'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Build and tests passed! Ready for deployment.'
        }
        failure {
            echo 'Build or tests failed. Please check the logs for details.'
        }
    }
}
