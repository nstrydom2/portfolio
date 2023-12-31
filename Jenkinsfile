pipeline {
    agent any

    environment {
        // Define the Maven installation name configured in Jenkins Global Tools
        MAVEN_HOME = tool 'Maven'
        PATH = "$MAVEN_HOME/bin:$PATH"
    }

    stages {
        stage('Setup Maven') {
            steps {
                // Set up Maven using the configured tool
                script {
                    MAVEN_HOME = tool 'Maven'
                    env.PATH = "$MAVEN_HOME/bin:$PATH"
                }
            }
        }

        stage('Build') {
            steps {
                // Build the Java project using Maven
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }

        stage('Test') {
            steps {
                // Run tests (if applicable)
                sh "${MAVEN_HOME}/bin/mvn test"
            }
        }
    }

    post {
        success {
            // Actions to be taken if the pipeline succeeds
            echo 'Build and tests passed! Deploying...'
        }
        failure {
            // Actions to be taken if the pipeline fails
            echo 'Build or tests failed. Please check the logs for details.'
        }
    }

