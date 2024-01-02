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

        stage("Build") {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }

        stage('Setup MySQL Connection') {
    steps {
        script {
            // Use MySQL client to connect to the running MySQL container
            sh 'mysql -h 172.18.0.3 -u root -proot -e "USE skistationDB;"'
               }
          }
     }


        stage("SRC Analysis Testing") {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
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

        stage("Docker compose") {
            steps {
                sh "docker compose up -d"
            }
        }

        stage('Nexus') {
            steps {
                sh "mvn clean compile -DskipTests"
            }
        }
	

	stage('Junit Mockito') {
            steps {
                sh "mvn clean test"
            }
        }



    }

    post {
        always {
            cleanWs()
        }
    }

}
