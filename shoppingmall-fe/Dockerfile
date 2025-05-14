# 1단계: 빌드용 (Node.js)
FROM node:18 AS build

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# 2단계: 실행용 (Nginx with SSL)
FROM nginx:alpine

# 정적 파일 복사
COPY --from=build /app/build /usr/share/nginx/html

# env.js 덮어쓰기
COPY docker/env.docker.js /usr/share/nginx/html/env.js

# nginx 설정 복사
COPY nginx.conf /etc/nginx/conf.d/default.conf

# SSL 인증서 디렉토리 마운트 대비
VOLUME /etc/letsencrypt
VOLUME /var/www/certbot

EXPOSE 80
EXPOSE 443
CMD ["nginx", "-g", "daemon off;"]
