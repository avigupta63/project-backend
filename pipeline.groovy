pipeline {
    agent any
    stages {
        stage('code-pull') {
            steps {
                git branch: 'dev', url: 'https://github.com/avigupta63/project-backend.git'
            }
        }
        stage('code-build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('docker-build-push') {
            steps {
                sh '''
                    docker build . -t avigupta63/project-backend-img:latest
                    docker push avigupta63/project-backend-img:latest
                    docker rmi avigupta63/project-backend-img:latest
                '''
            }
        }
        stage('deploy-k8s') {
            steps {
                sh 'kubectl apply -f ./deploy/'
            }
        }
    }
}
