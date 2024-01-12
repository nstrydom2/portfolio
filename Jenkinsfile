pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JDK'
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
        