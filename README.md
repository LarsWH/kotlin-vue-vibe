# kotlin-vue-vibe

# 1

If you were to make a demonstrator (kotlin + vue + typescript based) application, that
allows users to create an account by providing their mail address:

- what database would you use?
- what authentication method would you use?

# 1.2

What would using oauth imply

# 1.2

Is there any way to try out oauth2 for free as this is only a proof of concept?

# 1.3

Since I already have a Github account, will i be able to use that to identify myself?
How do i register my application with Github?

# 1.4

Do i have to deploy my application to a public server to try this out, or can i run it locally?

# 1.5

You mentioned PostgreSQL as a database option, for holding user data. What other options are there?
I just need something super simple that will survive a power cycle of the application

# 1.6

Is it possible to make a docker/docker-compose based setup for this? Something that I can run locally
in my WSL environment, or on a server?

# 1.7

Later i will need things like: build pipeline, unit tests, integration tests, sonarcloud check.
Can you add that later?

# 1.7.1

can we continue the discussion from yesterday?

# 1.8

Would this make sense: When a user is logged in, she can see

- a list of the information stored about her in the data base
- the total number of users

# 1.9

Can the user remain logged in for e.g. 10 minutes? Is this acchieved with some kind of JWT?

# 1.10

can you summarize the requirements for this small application? does it sound like a good demonstrator? is there anything
i have forgotten to consider?

# 1.11

I forgot to mention this: i also need a logging framework. What would you recommend for the Kotlin backend? What about
the Vue frontend?

what is best practice for vue/typescript logging

# 1.12

loglevel sound good.

Also:
So, there will be a docker-compose file (to spin up the application, a postgres database,
and a vue frontend that communicates with the backend via REST API). I also need a docker file
that i can use to easily build the application (front-end and back-end) from my WSL command line. Does that sound like a
good idea?

# 1.13

What build tool would you recommend for the Kotlin backend? I have used Maven (with Java) before, but I am open to other
options.
What is needed for the frontend?

# 1.14

Gradle will be fine, but will it be simple to change to Maven later if I want to? Is the project structure similar?

# 1.15

Will it be possible for gradle and maven files to coexist, so i can try out both build tools?

# 1.16

Please summarize the requirements, and let me know if you can provide such a demonstrator application. In what form will
you provide it?

# 1.17
Will it be simple to add Google OAuth2 for authentication at a later stage? You indicated it is free for my testing, and
at a later stage, I may want to verify multiple users.

# 2.0
Please constuct the application and please provide a README.md file with instruction of
- how to register the application with Github
- how to build and package the application (using a docker image with the compilers)
- how to deploy the application (using docker-compose)

# 2.1
can you make list of 'touch' instructions the will just create empty files?

      pwd
      cd software/kotlin/kotlin-vue-vibe/

Create the project structure with empty files:
      
      mkdir -p backend/src/main/kotlin/com/example
      mkdir -p backend/src/main/resources
      mkdir -p frontend/src
      
      touch backend/Dockerfile
      touch backend/build.gradle.kts
      touch backend/src/main/kotlin/com/example/Application.kt
      touch backend/src/main/resources/application.yml
      
      touch frontend/Dockerfile
      touch frontend/package.json
      touch frontend/vite.config.ts
      touch frontend/src/main.ts
      touch frontend/src/App.vue
      touch frontend/index.html

      touch docker-compose.yml
      touch README.md

## DB

      docker compose up db -d


Fill in the blanks....

      docker compose down && docker volume rm kotlin-vue-vibe_db_data && docker compose build && docker compose up -d && docker logs kotlin-vue-vibe-db-1 -f
      docker compose down         && docker compose build         && docker compose up -d
      docker compose down backend && docker compose build backend && docker compose up backend -d

      docker compose down backend && docker compose build backend && docker compose up backend -d

      docker compose down backend
         docker ps

      ls -al ~/software/kotlin/kotlin-vue-vibe/backend/build/libs/*.jar
      rm -f ~/software/kotlin/kotlin-vue-vibe/backend/build/libs/*.jar
      docker compose build backend
      docker compose up -d backend
         docker exec backend pwd
         docker exec backend gradle --version
         docker exec backend ls -al
         docker exec backend gradle build --no-daemon --info --stacktrace
      

# Build docker images

    cd ~/software/kotlin/kotlin-vue-vibe
    docker compose build backend
    docker run --rm backend:latest ls -al


# Compiile the backend

    docker run --rm backend:latest
    docker run --rm -v $PWD/.gradle:/home/gradle/.gradle -v $PWD/backend:/app backend:latest ls -al /app
    ...docker run --rm -v $PWD/.gradle:/home/gradle/.gradle -v $PWD/backend:/app backend:latest /bin/bash -c 'cd /app &&    gradle build -x test --no-daemon --info --stacktrace'

Clean

    docker run --rm -v $PWD/.gradle:/home/gradle/.gradle -v $PWD/backend:/app backend:latest /bin/bash -c 'cd /app && rm build/libs/*.jar'
    docker run --rm -v $PWD/.gradle:/home/gradle/.gradle -v $PWD/backend:/app backend:latest /bin/bash -c 'cd /app && ls -al build/libs/'

Build without running the integration test with the DB

    docker run --rm -v $PWD/.gradle:/home/gradle/.gradle -v $PWD/backend:/app backend:latest /bin/bash -c 'cd /app && ./gradlew build -x test --no-daemon --info --stacktrace'
    docker run --rm -v $PWD/.gradle:/home/gradle/.gradle -v $PWD/backend:/app backend:latest /bin/bash -c 'cd /app && ls -al build/libs/*.jar'
        -rw-r--r-- 1 root root 59224889 Jun 15 19:41 build/libs/app-0.0.1-SNAPSHOT.jar
        -rw-r--r-- 1 root root     8849 Jun 15 19:41 build/libs/app-0.0.1-SNAPSHOT-plain.jar

Run the jar (dependencies first...)

    docker compose up db -d
    ...docker run --rm -v $PWD/.gradle:/home/gradle/.gradle -v $PWD/backend:/app               backend:latest /bin/bash -c 'cd /app/build/libs/ && java -jar app-0.0.1-SNAPSHOT.jar --server.port=8081'
    docker run --rm -v $PWD/.gradle:/home/gradle/.gradle -v $PWD/backend:/app  -p 8081:8080 backend:latest /bin/bash -c 'cd /app/build/libs/ && java -jar app-0.0.1-SNAPSHOT.jar'

    docker run --rm backend ls -al

    docker run backend gradle build --no-daemon --info --stacktrace
    docker exec backend ls -al ./build/libs/*.jar
    docker exec backend ls -al ./build/libs/app-0.0.1-SNAPSHOT.jar
# Run the backend


      docker exec backend gradle build
      docker exec backend gradle build bootRun
      docker ps -a
      docker exec backend ps -ax | grep ApplicationKt
      You can check if anything is listening on port 8081 locally by running:

      ```sh
      lsof -i :8081
      ```
      or

      ```sh
      netstat -tuln | grep 8081
      ```
      or

      ```sh
      ss -tuln | grep 8081
      ```
      docker exec backend curl backend:8081/health
      docker exec backend curl localhost:8081/health
      docker exec backend curl 127.0.0.1:8081/health
      docker exec backend curl http://localhost:8081/manager/html
      docker exec backend lsof -i :8081
      docker exec backend netstat -tuln | grep 808
      docker exec backend netstat -tuln | grep 8081
      docker exec backend netstat -tuln
      docker exec backend /bin/bash -c 'apt-get update && apt-get install -y net-tools'

      docker exec backend pkill -f ApplicationKt
      docker exec db curl --version
      docker exec db curl backend:8080/health
      docker exec db curl backend:8081/health
      docker exec db curl backend:8081/actuator

         docker exec backend ls -al
         docker exec backend find . -name *.jar
         docker exec backend ls -al ./build/libs/*.jar
         docker exec backend rm /app/build/libs/*.jar
         docker exec backend find / -name *.log
      docker exec -d backend java -jar ./build/libs/app-0.0.1-SNAPSHOT.jar
      docker logs -f backend
health
      docker compose down backend && docker compose build backend && docker compose up backend -d
      docker compose down backend && docker compose build backend && docker compose up backend -d
      
      docker logs kotlin-vue-vibe-frontend-1
      docker logs kotlin-vue-vibe-backend-1
      docker logs kotlin-vue-vibe-db-1

      curl http://localhost:8081/actuator
      curl http://localhost:8081/actuator/mappings
      curl http://localhost:8081/actuator/beans
      curl http://localhost:8080/health

# Test

      docker exec backend gradle test --warning-mode all
      docker exec backend gradle build test --warning-mode all
      docker exec backend gradle       test --tests com.example.DatabaseIntegrationTest --warning-mode all
      docker exec backend gradle build test --tests com.example.DatabaseIntegrationTest --warning-mode all
      docker exec backend ls -al src/test/kotlin/com/example/DatabaseIntegrationTest.kt
      docker exec backend gradle test --tests "com.example.DatabaseIntegrationTest"
      docker exec backend gradle test --tests "com.example.DatabaseIntegrationTest.write and delete value in database"

# 3.0
      # kotlin-vue-vibe
      
      ## 1. Register the Application with GitHub
      
      1. Go to [GitHub Developer Settings](https://github.com/settings/developers).
         2. Click "New OAuth App".
         3. Set:
            - Application name: Your app name
            - Homepage URL: `http://localhost:8080`
            - Authorization callback URL: `http://localhost:8080/api/oauth2/callback/github`
         4. Note the Client ID and Client Secret.
         5. Create a `.env` file in the project root:

# 4.0
how can i initially disable the login and just see things starting up


# So.....
- Ensure actuator is running (curl http://localhost:8080/actuator/mappings)
- Ensure health is running (curl http://localhost:8080/health)
- Ensure volume mapping of the '~/.gradle/caches' for faster builds


# 5.0
- add an integration test to the kotlin backend, that verifies a value can be written to the database and deleted again

   cd ~/software/kotlin/kotlin-vue-vibe/backend/build
   sudo chown -R $USER:$USER .

curl https://plugins.gradle.org/


   cd ~/software/kotlin/kotlin-vue-vibe/backend
   ./gradlew clean build

# 5.1
After assigning port 8081 to tha backend, it can be started from the command line with:

      ./gradlew clean build bootRun

Alternatively try:

      ./gradlew clean build bootRun --args='--server.port=8081'





# 5.2 Frontend
Dockerfile:
I want to use the same image for building and running.

# 5.2.1
Run frontend from CLI

   cd ~/software/kotlin/kotlin-vue-vibe/frontend
   npm install
   npm run dev

Opening http://localhost:5173/ shows "Hello Vue"






# 5.2.2
Running the frontend from IntelliJ...


#

Here is a sample `README.md` section with instructions for your requirements. This assumes a project structure with a `backend` (Kotlin), `frontend` (Vue + TypeScript), and a `docker-compose.yml` at the root.

---

# Setup Instructions

## 1. Register the Application with GitHub

1. Go to [GitHub Developer Settings](https://github.com/settings/developers).
2. Click **"New OAuth App"**.
3. Set the following:
    - **Application name:** Your app name
    - **Homepage URL:** `http://localhost:8080`
    - **Authorization callback URL:** `http://localhost:8080/api/oauth2/callback/github`
4. After creation, note the **Client ID** and **Client Secret**.
5. Add these to your backend environment (e.g., `.env` or `docker-compose.yml`):

   ```
   GITHUB_CLIENT_ID=your_client_id
   GITHUB_CLIENT_SECRET=your_client_secret
   ```

## 2. Build and Package the Application

The project uses Docker for building both backend and frontend.

1. Build the backend and frontend images:

   ```
   docker compose build
   ```

   This will use the provided `Dockerfile` in each subproject to compile and package the apps.

## 3. Deploy the Application (using Docker Compose)

1. Start all services (backend, frontend, database):

   ```
   docker compose up
   ```

2. The backend will be available at `http://localhost:8080`, and the frontend at `http://localhost:5173` (or as configured).

3. To stop the application:

   ```
   docker compose down
   ```

---

**Note:**
- Make sure to set the required environment variables for OAuth2 in your `docker-compose.yml` or `.env` file.
- For development, you can use the default ports or adjust as needed in the compose file.

This setup allows you to build, package, and run the full stack locally with Docker.
