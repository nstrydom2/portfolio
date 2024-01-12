pipeline {
    agent any

    stages {
        
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }

    stage('Test') {
            steps {
                sh 'mvn test'  // Use Maven to run unit tests
            }
        }

    stage('Publish to CodeArtifact') {
            steps {
                // Configure AWS credentials and region
                withCredentials([aws(credentialsId: 'AKIA3JMZJSW3TS3OQBUI', region: 'us-east-1')]) {
                    // Retrieve CodeArtifact domain and repository details
                    def domain = 'arun'
                    def repository = 'java-portfolio'

                    // Authenticate with CodeArtifact using AWS CLI
                    sh 'aws codeartifact login --tool maven --domain ${domain} --repository ${repository}'

                    // Publish artifacts using Maven
                    sh 'mvn deploy -Dmaven.repo.local=target/repository'
                }
            }
        }


}  