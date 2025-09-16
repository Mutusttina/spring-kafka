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
                bat '.\\mvnw.cmd -B clean package -DskipTests'
                // Or: bat 'mvn -B clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t %DOCKER_IMAGE% ."
            }
        }

        stage('Deploy Container') {
            steps {
                bat """
                docker ps -q --filter "name=%CONTAINER_NAME%" | findstr . >nul && (
                    docker stop %CONTAINER_NAME%
                    docker rm %CONTAINER_NAME%
                )
                docker run -d --name %CONTAINER_NAME% -p %EXPOSE_PORT%:%APP_PORT% %DOCKER_IMAGE% --spring.profiles.active=docker-dev
                """
            }
        }
    }

    post {
        success {
            echo "✅ Build & deployment successful! Access app at http://localhost:${EXPOSE_PORT}/"
        }
        failure {
            echo "❌ Build or deployment failed. Check logs."
        }
    }
}
