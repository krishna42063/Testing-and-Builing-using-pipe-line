pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'   // Define Maven tool in Jenkins global tools config
        jdk 'JDK21'          // Define JDK 21 in Jenkins global tools config
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'git@github.com:codefeeding/codefeeding-app.git',
                    credentialsId: 'git-privatekey'   // your Jenkins credential ID
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean compile"
            }
        }

        stage('Run Tests') {
            steps {
                sh "mvn test"
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh "mvn package -DskipTests"
            }
        }

        stage('Deploy (Optional)') {
            when {
                branch 'main'
            }
            steps {
                sh "java -jar target/*.jar &"
            }
        }
    }

    post {
        success {
            echo "✅ Build and tests passed!"
        }
        failure {
            echo "❌ Build or tests failed. Check logs."
        }
    }
}
