# 1단계: 빌드용 (Node.js)
FROM node:18 AS build

WORKDIR /app
COPY package*.json ./
RUN npm install

COPY . .

RUN chmod +x node_modules/.bin/react-scripts
RUN npm run build

# 2단계: 실행용 (Nginx)
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY docker/env.docker.js /usr/share/nginx/html/env.js

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]