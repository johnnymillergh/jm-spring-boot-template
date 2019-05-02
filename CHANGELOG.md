#  (2019-05-02)


### Bug Fixes

* pom.xml to reduce vulnerabilities ([0b2eb53](https://github.com/johnnymillergh/jm-spring-boot-template/commit/0b2eb53))
* **$Compile:** This static field public but not final, and could be changed by malicious code or by accident from another package. The field could be made final to avoid this vulnerability. ([97a8dd3](https://github.com/johnnymillergh/jm-spring-boot-template/commit/97a8dd3))
* **$LOGBack:** resolve problem name of log file is ambiguous ([894ef92](https://github.com/johnnymillergh/jm-spring-boot-template/commit/894ef92))
* **$WebSecurity:** enable ignore URLs to avoid 403 error ([10289bf](https://github.com/johnnymillergh/jm-spring-boot-template/commit/10289bf))
* **$WebSecurity:** solve problem that server responses error-403 to any POST request ([6aa4192](https://github.com/johnnymillergh/jm-spring-boot-template/commit/6aa4192))
* **$YML:** fix wrong YML configuration ([db7bd7b](https://github.com/johnnymillergh/jm-spring-boot-template/commit/db7bd7b))


### Code Refactoring

* **$UniversalStatus:** new rules for HTTP status code ([edc7142](https://github.com/johnnymillergh/jm-spring-boot-template/commit/edc7142))
* **$UniversalStatus:** new rules for HTTP status code (2) ([ffb373a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/ffb373a))


### Features

* **$API:** add API annotation for AuthController ([3621107](https://github.com/johnnymillergh/jm-spring-boot-template/commit/3621107))
* **$API:** add API getApiAnalysis (undone) ([929a36c](https://github.com/johnnymillergh/jm-spring-boot-template/commit/929a36c))
* **$API:** add new API for validating username ([72687a6](https://github.com/johnnymillergh/jm-spring-boot-template/commit/72687a6))
* **$API:** change response data format for getting api information ([ebe5b9a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/ebe5b9a))
* **$API:** new API for /getApiAnalysis ([e9b33d0](https://github.com/johnnymillergh/jm-spring-boot-template/commit/e9b33d0))
* **$API:** new API for getting controller information ([4019de6](https://github.com/johnnymillergh/jm-spring-boot-template/commit/4019de6))
* **$auth:** provide apis for authentication and authorization ([ddaf459](https://github.com/johnnymillergh/jm-spring-boot-template/commit/ddaf459))
* **$banner:** add author information in banner ([a3ea53f](https://github.com/johnnymillergh/jm-spring-boot-template/commit/a3ea53f))
* **$banner:** add banner text link ([1c611c9](https://github.com/johnnymillergh/jm-spring-boot-template/commit/1c611c9))
* **$build): feat($build:** update maven version to 1.2.0-SNAPSHOT; support multi-env packaging ([474822a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/474822a))
* **$compile:** add new dependency jasypt-spring-boot ([fdbbfde](https://github.com/johnnymillergh/jm-spring-boot-template/commit/fdbbfde))
* **$compile:** log deployment duration ([5ae4be3](https://github.com/johnnymillergh/jm-spring-boot-template/commit/5ae4be3))
* **$compile:** support RBAC ([779c070](https://github.com/johnnymillergh/jm-spring-boot-template/commit/779c070)), closes [#jqh13](https://github.com/johnnymillergh/jm-spring-boot-template/issues/jqh13)
* **$compile:** update application .yml configuration ([a23b868](https://github.com/johnnymillergh/jm-spring-boot-template/commit/a23b868))
* **$Compile:** upgrade version to 2.0.0.RELEASE ([d7a99b1](https://github.com/johnnymillergh/jm-spring-boot-template/commit/d7a99b1))
* **$Druid:** enable Druid monitor ([8b56637](https://github.com/johnnymillergh/jm-spring-boot-template/commit/8b56637))
* **$Exception:** update BaseException, BizException and SecurityException ([b5ccbe9](https://github.com/johnnymillergh/jm-spring-boot-template/commit/b5ccbe9))
* **$Git:** update Git ignore rule ([b7643dc](https://github.com/johnnymillergh/jm-spring-boot-template/commit/b7643dc))
* **$Log:** configure logging level of project as DEBUG to enable SQL logging ([09a88b7](https://github.com/johnnymillergh/jm-spring-boot-template/commit/09a88b7))
* **$Log:** support logback for multi-environment ([5e33d7a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/5e33d7a))
* **$LOGBack:** add LOGBack configuration; support output log file ([a590f86](https://github.com/johnnymillergh/jm-spring-boot-template/commit/a590f86))
* **$LOGBack:** update configuration ([7070d48](https://github.com/johnnymillergh/jm-spring-boot-template/commit/7070d48))
* **$MyBatisPlus:** support pagination for SQL query ([d974dbb](https://github.com/johnnymillergh/jm-spring-boot-template/commit/d974dbb))
* **$MySQL:** alter column name and comment ([c35f0e9](https://github.com/johnnymillergh/jm-spring-boot-template/commit/c35f0e9))
* **$ProjectProperty:** complete POM information ([4dbf549](https://github.com/johnnymillergh/jm-spring-boot-template/commit/4dbf549))
* **$run:** add api management controller ([9415bee](https://github.com/johnnymillergh/jm-spring-boot-template/commit/9415bee))
* **$run:** add custom error response ([06f9d04](https://github.com/johnnymillergh/jm-spring-boot-template/commit/06f9d04))
* **$SpringBoot:** update Spring Boot version 2.1.4.RELEASE ([f843aae](https://github.com/johnnymillergh/jm-spring-boot-template/commit/f843aae))
* **$UniversalStatus:** add new status - PARAM_INVALID ([b183f00](https://github.com/johnnymillergh/jm-spring-boot-template/commit/b183f00))
* **$WebSecurity:** add enable/disable Web Security feature switch ([8a2bd4d](https://github.com/johnnymillergh/jm-spring-boot-template/commit/8a2bd4d))
* **$YML:** update application.yml configuration ([492d900](https://github.com/johnnymillergh/jm-spring-boot-template/commit/492d900))
* **$YML:** update global application.yml ([4f3b8ff](https://github.com/johnnymillergh/jm-spring-boot-template/commit/4f3b8ff))


### BREAKING CHANGES

* **$MyBatisPlus:** enable pagination
* **$Log:** output SQL logs to file
* **$WebSecurity:** csrf().disable()
* **$build): feat($build:** support multi-env packaging
* **$WebSecurity:** turn on/off Web Security feature dynamically
* **$UniversalStatus:** new rules for HTTP status code
* **$UniversalStatus:** new rules for HTTP status code
* **$YML:** project back to alive
* **$compile:** support RBAC; login refactor.
