# 베이스 이미지
FROM node:latest

# 클라우드 환경에서의 디렉토리
WORKDIR /frontend

# 현재 디렉토리에 있는 모든 파일을 WORKDIR로 이동
COPY . .
COPY package.json .

# 필요로 되어지는 의존관계 라이브러리를 설치 
# (JAVA에서는 jar파일에 포함되기 때문에 할 필요 X)
# (Vue에서는 프로그램 자체에 설치 )
RUN npm install
RUN npm i axios
RUN npm install vue-router@4

# 포트번호 노출
EXPOSE 8080

# script 실행 명령어
CMD ["npm", "run", "serve"]