pipeline {
    agent any

      environment {
        // Define the Maven installation name configured in Jenkins Global Tools
        MAVEN_HOME = tool 'Maven'
        PATH = "$MAVEN_HOME/bin:$PATH"
    }
        stage('Build') {
            steps {
                // Build the Java project using Maven
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }
    }
 