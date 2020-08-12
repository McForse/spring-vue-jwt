# Spring Vue JWT

## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Before installation](#before-installation)
  * [Installation](#installation)
* [Usage](#usage)
* [License](#license)

# About The Project

Full stack application with Spring Boot for backend and Vue.js for frontend. The system is secured by Spring Security with JWT Authentication.

# Built With

Component         | Technology
---               | ---
Frontend          | [Vue.js 2](https://github.com/vuejs/vue) with [Vuex](https://vuex.vuejs.org/) and [Vue router](https://router.vuejs.org/)
Backend (REST)    | [Spring Boot](https://projects.spring.io/spring-boot) (Java)
Security          | Token Based (Spring Security, JWT)
Client Build Tools| [Vue CLI](https://cli.vuejs.org/), Webpack, npm
Server Build Tools| Maven

## Getting Started

The project will consist of two modules - backend and frontend
This is an instruction for setting up a project locally.
Follow these simple sample steps to run a local copy.

### Prerequisites

List of software to be installed to run the project:
* Java
* npm

### Before installation

<aside class="notice">
    To run the project you need to set environment variables!
</aside>

Such as the:
* SPRING_DATASOURCE_URL
* SPRING_DATASOURCE_USERNAME
* SPRING_DATASOURCE_PASSWORD

This can be done in the configuration parameters of your IDE, as Heroku Config Vars or manually changing the parameters in the file /backend/src/main/resources/application.properties

### Installation

1. Clone the repo
```sh
git clone https://github.com/McForse/spring-vue-jwt.git
```
2. In frontend folder install NPM packages
```sh
npm install
```
3. Start building the project
```sh
mvn clean install
```
4. Run the compiled application
```sh
mvn --project backend spring-boot:run
```
If everything is fine, the application will run at http://localhost:8080/

## Usage
#### Import the project (Intellij IDEA)

1. Create project from existing source
2. From the Project Structure dialog: Modules | new | Import Module | Select backend folder | Import module from external model
3. From the Project Structure dialog: Modules | new | Import Module | Select frontend folder | Create module from existing sources

#### Run Backend
```sh
mvn --projects backend spring-boot:run
```

OR

You can add new Spring Boot configuration in Intellij IDEA by setting the **main class** as *BackendApplication*.

Spring Boot application will run at http://localhost:8080.

#### Run Frontend

```sh
cd frontend
npm run serve
```

Vue.js application will run at http://localhost:8081.

OR

You can add new NPM configuration in Intellij IDEA by setting the **command** as *run* and **scripts** - *serve*.

## License

Distributed under the MIT License. See `LICENSE` for more information.