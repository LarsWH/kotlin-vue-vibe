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

Fill in the blanks....

      docker compose down && docker volume rm kotlin-vue-vibe_db_data &&docker compose build && docker compose up -d && docker logs kotlin-vue-vibe-db-1 -f
      docker logs kotlin-vue-vibe-frontend-1
      docker logs kotlin-vue-vibe-backend-1
      docker logs kotlin-vue-vibe-db-1

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
