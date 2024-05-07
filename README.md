[![Typing SVG](https://readme-typing-svg.demolab.com?font=Fira+Code&pause=1000&color=FFFFFF&background=000000&random=false&width=435&lines=Pick+Your+Best+Outfit%2C+OutPICK)](https://git.io/typing-svg)

## 목차
[1. 프로젝트 개요](#1-프로젝트-개요)<br>
[2. API 명세서](#2-API-명세서)<br/>
[3. 팀 소개](#3-팀-소개)<br/>
[4. 기술 스택](#4-기술-스택)<br/>
[5. 빌드 과정 및 배포 문서](#2-빌드-과정-및-배포-문서)<br/>
[6. 기능 테스트](#6-기능-테스트)<br/>

   <br/>
   <br/>
   <br/>
   
## 1. 프로젝트 개요
<br/>
<br/>
<br/>

## 2. API 명세서
<img src="https://github.com/beyond-sw-camp/be05-4th-6team-OutPick/assets/137466623/b077350a-c3c6-465c-9bfc-c5016b3b7541">(https://docs.google.com/spreadsheets/d/1gCxWgb2HCPqeUkymqBYCLILEuOzy5x106LlM4JyODRA/edit?usp=sharing)
<br/>
<br/>
<br/>


## 3. 팀 소개
|<img src="https://avatars.githubusercontent.com/u/83454423?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/134581020?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/113917104?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/137466623?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/134200142?v=4" width="150" height="150"/>|
|:-:|:-:|:-:|:-:|:-:|
|혜경<br/>[@BakHyegyeong](https://github.com/BakHyegyeong)|경원<br/>[@kyungwon2won2](https://github.com/kyungwon2won2)|영원<br/>[@nbbb9](https://github.com/nbbb9)|설화<br/>[@Noononda](https://github.com/Noononda)|승호<br/>[@sttoend](https://github.com/sttoend)|

<br/>
<br/>
<br/>

## 4. 기술 스택
<img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=mariadb&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/vue-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/dockerhub-2496ED?style=for-the-badge&logo=dockerhub&logoColor=white">
<img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white">
<br/>
<img src="https://github.com/beyond-sw-camp/be05-4th-6team-OutPick/assets/137466623/292630c5-ec68-442d-ade5-513af1a72551">
<br/>
<br/>
<br/>

## 5. 빌드 과정 및 배포 문서
1. jar파일 생성 <br/>
명령어를 통해 jar파일을 생성합니다.
```
./gradlew clean build
```
<img src="https://github.com/beyond-sw-camp/be05-4th-6team-OutPick/assets/137466623/558aeae4-f3e0-4492-98ad-fd6037522a85">
<br/>
2. DOCKER FILE<br/>

```dockerfile
FROM openjdk:17-alpine

# 외부 환경요소 : jar이 위치할 공간을 지정
ARG JAR_FILE=build/libs/*.jar

# JAR_FILE을 아래의 이름으로 COPY하겠다.
COPY ${JAR_FILE} backend.jar

# env, java는 CMD가 아니라 jar를 실행시키기 위해 entrypoint
ENTRYPOINT ["java", "-jar", "/backend.jar"]
```
<br/>
3. DOCKER COMPOSE

```yaml
version: "3"

services:
  app:
    container_name: OutPick_Backend
    build: .
    image: lywon/outpick_backend:latest
    depends_on:
      - database
    ports:
      - "8972:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mariadb://database:3306/outpick?useUnicode=true
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: 123456789
    restart: always
    links:
      - database
    # platform: linux/arm64

  database:
    image: mariadb:10.6.16
    container_name: OutPick_Backend_DB
    environment:
      MARIADB_ROOT_PASSWORD: 123456789
      MARIADB_DATABASE: outpick
    volumes:
      - ./src/main/resources/db_config/initdb.d:/docker-entrypoint-initdb.d
    ports:
      - "6033:3306"
```
<br/>

4. webhook<br/>
프론트엔드와 백엔드 깃허브 repository에서 WebHook 설정
<br/>

5. jenkins credentials<br/>
프론트엔드, 백엔드의 git repository와 docker hub에 접근하기 위한 설정
<br/>

6. Jenkins item 생성<br/>
프론트엔드와 백엔드 item 생성
<br/>

7. Jenkins item General<br/>
- Jenkins item_git 연결<br/>
`각 item에 git hub repository url 입력을 통한 연결 `
- Jenkins item_build Triggers 설정<br/>
`GitHub hook trigger for GITScm polling 체크`
- Jenkins item_Pipeline<br/>
  - FrontEnd
```pipeline
pipeline {
    agent any
    // 변수 정의
    environment{
        GIT_HUB_CREDENTIALS = credentials('OutPick_Frontend')
        DOCKER_HUB_CREDENTIALS =credentials('OutPick_Docker')
        DOCKER_IMAGE_NAME = 'lywon/outpick_frontend'
        DOCKER_IMAGE_TAG = '1.0'
    }
    stages{
        stage('Build'){
            steps{
                // Build 영역 : Git 연동
                git branch : 'main', // 후에 각각의 branch로 설정
                    credentialsId : "${GIT_HUB_CREDENTIALS}",
                    url : 'https://github.com/kyungwon2won2/OutPick_Frontend.git'
            }
        }
        // 라이브러리 다운로드 - 이미 dockerfile에서 정의해두었기 때문에
        // 오류가 날 경우에만 정의
        stage('Install Dependency'){
            steps{
                bat 'npm install'
            }
        }
        // npm build
        stage('Npm build'){
            steps{
                bat 'npm run build'
            }
        }
        // 이미지 생성
        stage('Build Docker Image'){
            steps{
                script{
                    docker.build("${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}", '-f Dockerfile .')
                    // compose가 아닌 Dockerfile로 생성할 때는 -f 옵션을 주어야 한다.
                }
            }
        }
        // Docker Hub에 Push
        stage('Push To Docker Hub'){
            steps{
                script{
                    docker.withRegistry('https://registry.hub.docker.com','OutPick_Docker'){
                        docker.image("${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
        // container 생성과 실행
        stage('Run Container'){
            steps{
                script{
                	// 기존의 Container를 삭제
                    bat 'docker ps -a'
                    bat 'docker stop OutPick_Frontend || exit 0'
                    bat 'docker rm OutPick_Frontend || exit 0'
                   	docker.withRegistry('https://registry.hub.docker.com','OutPick_Docker'){
                        docker.image("${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}").run('-d -p 8080:8080 --name OutPick_Frontend')
                    }
                }
            }
        }
    }
}
```
  - BackEnd
```pipeline
pipeline {
    agent any
    stages{
        stage('Build'){
            steps{
                // Build 영역 : Git 연동
                git branch : 'main', // 후에 각각의 branch로 설정
                    credentialsId : 'OutPick_Backend',
                    url : 'https://github.com/nbbb9/OutPick_Backend.git'
                bat './gradlew.bat build bootJAR'
            }
        }
        stage('Jar Clean'){
            steps{
                bat './gradlew.bat clean bootJAR'
            }
        }
        stage('Build Docker Image'){
            steps{
                script{
                    docker.build('lywon/outpick_backend')
                }
            }
        }
        // Docker hub에 push 추가
        stage('Push to Docker Hub'){
            steps{
                script{
                    docker.withRegistry('https://registry.hub.docker.com','OutPick_Docker'){
                        docker.image('lywon/outpick_backend').push()
                    }
                }
            }
        }
        stage('Deploy with Docker Compose'){
            steps{
                script{
                    // docker compose 다운로드
                    // 실행 파일 다운로드
                    bat "curl -L https://github.com/docker/compose/releases/download/2.24.6/docker-compose-Linux-x86_64 -o docker-compose"	
                    // docker compose 설치
                    bat "icacls docker-compose /grant Playdata:RX"
                    // docker compose up
                    bat ".\\docker-compose build"
                    bat ".\\docker-compose up -d"
                }
            }
        }
    }
}
```

8. Docker Hub Images 생성<br/>
` Jenkins를 통한 Docker Hub Image 자동 생성`

9. Docker Container 생성<br/>
 `Jenkins를 통한 Docker Container 자동 생성`
<br/>

10. Build 결과<br/>
- FrontEnd
<br/>
- BackEnd
<br/>

<br/>
<br/>
<br/>

## 6. 기능 테스트
<br/>
<br/>
<br/>
