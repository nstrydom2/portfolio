pipeline {
    agent any

      environment {
        // Define the Maven installation name configured in Jenkins Global Tools
        MAVEN_HOME = tool 'Maven'
        PATH = "$MAVEN_HOME/bin:$PATH"
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the Git repository
                git 'https://github.com/your-username/your-java-project.git'
            }
        }
    }

        stage('Build') {
            steps {
                // Build the Java project using Maven
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }
    }
 
