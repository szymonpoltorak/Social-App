FROM node:18.15-alpine as node

WORKDIR /app

COPY package*.json .

RUN npm ci

COPY . .

RUN ./node_modules/.bin/ng build

FROM nginx:alpine

COPY --from=node /app/dist/social-app-frontend /usr/share/nginx/html
