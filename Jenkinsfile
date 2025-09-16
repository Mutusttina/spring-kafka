pipeline {
    agent any

    environment {
        APP_NAME = "demo-app"
        DOCKER_IMAGE = "spring-demo-app:latest"
        CONTAINER_NAME = "spring-demo-app-container"
        APP_PORT = 8080
        EXPOSE_PORT = 9001
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                sh './mvnw -B clean package -DskipTests'
                // If you don't use mvnw, replace with: sh 'mvn -B clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Deploy Container') {
            steps {
                sh """
                    # Stop and remove old container if running
                    docker ps -q --filter name=${CONTAINER_NAME} | grep -q . && docker stop ${CONTAINER_NAME} && docker rm ${CONTAINER_NAME} || true

                    # Run new container
                    docker run -d --name ${CONTAINER_NAME} -p ${EXPOSE_PORT}:${APP_PORT} ${DOCKER_IMAGE} --spring.profiles.active=docker-dev
                """
            }
        }
    }

    post {
        success {
            echo "✅ Build & deployment successful! Access app at http://localhost:8080/"
        }
        failure {
            echo "❌ Build or deployment failed. Check logs."
        }
    }
}
