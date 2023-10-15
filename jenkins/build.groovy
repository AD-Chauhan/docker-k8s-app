pipeline{
    agent any
    stages{
    stage("Checkout from GitHub"){
        steps{
        git 'https://github.com/AD-Chauhan/docker-k8s-app.git'
        }
    }
    
    stage("Build With Gradle"){
         steps{
       withGradle {
    bat './gradlew build'
    }
         }
    }
    
    stage("Build Docker Image"){
         steps{
      bat 'docker build -f Dockerfile -t docker-k8s-app:1.0 .'  
    }
    }
	
	 stage("Deploy Image->K8s"){
         steps{
      bat 'kubectl apply -f docker-k8s.yaml'  
    }
    }
    }
}