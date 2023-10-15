pipeline{
	agent any
	environment{
	   dockerImage=''
	   registry='ad1989/docker-k8s:1'
	   registryAuth='DockerHubId'
	   
	}
	stages{
	stage("Checkout from GitHub"){
		steps{
		checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/AD-Chauhan/docker-k8s-app.git']])
		}
	}
	
	stage("Build With Gradle"){
		 steps{
	   withGradle {
	bat './gradlew build'
	}
		 }
	}
	
	 stage("Build Image"){
		 steps{
		 script {
	   dockerImage=docker.build registry
		}
	  }
	}
	
	 stage("Push Image in Hub"){
		 steps{
		 script {
	   docker.withRegistry('',registryAuth){
	   dockerImage.push()
	   }
		}
	  }
	}
	
	  stage('Deploying container to Kubernetes') {
	  steps {
		  script {
			  kubernetesDeploy(configs: "docker-k8s-service.yaml",kubeconfigId: "k8sId")
			}
	
	  }
	}
	
  }
}
	