pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                // Build the Java project using Maven
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }
    }
}
 
