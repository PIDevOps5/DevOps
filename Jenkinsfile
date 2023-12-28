pipeline {

    agent any


    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git";
                git branch: "main",
                    url: "https://github.com/PIDevOps5/DevOps";
            }
        }

        stage("MVN Clean") {
            steps {
                sh "mvn clean"
            }
        }

        stage('Setup MySQL Connection') {
    steps {
        script {
            // Use MySQL client to connect to the running MySQL container
            sh 'mysql -h 172.18.0.2 -u root -e "USE SkiStationDB;"'
               }
          }
     }


        stage("SRC Analysis Testing") {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=adminsonar"
            }
        }

        stage("Build Docker image") {
            steps {
                sh "docker build -t aminesnoussi/devops:skistation ."
            }
        }

         stage('Push in dockerhub') {
            steps {
                sh "docker login -u aminesnoussi -p AliSnoussi1956."
                sh "docker push aminesnoussi/devops:skistation"
            }
        }

        stage('Unit Test') {
            steps {
                script {
                    // Run unit tests using Maven
                    sh 'mvn test'
                }
            }
        }

        stage("Deploy Artifact to private registry") {
            steps {
                sh "..............."
            }
        }

        stage("Deploy Dokcer Image to private registry") {
            steps {
                sh "..............."
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }

}
