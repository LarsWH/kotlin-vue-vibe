#FROM node:20-alpine AS build
#WORKDIR /app
#COPY . .
#RUN npm install && npm run build
#
#FROM nginx:alpine
#COPY --from=build /app/dist /usr/share/nginx/html
#EXPOSE 5173

FROM node:20-alpine

WORKDIR /app
COPY . .
RUN npm install && npm run build

EXPOSE 5173
CMD ["npm", "run", "preview"]