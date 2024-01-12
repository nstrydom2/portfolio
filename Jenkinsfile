pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JDK8'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    withMaven(maven: 'Maven') {
                        sh 'mvn clean install'
                    }
                }
            }
        }
    }
}
        