# [v2.0.4](https://github.com/johnnymillergh/jm-spring-boot-template/compare/v2.0.3...v2.0.4) (2019-10-18)


### Bug Fixes

* **$Docker, MySQL:** correct MySQL database name ([523d368](https://github.com/johnnymillergh/jm-spring-boot-template/commit/523d368))
* **$Role:** remove `@NotNull` annotation ([6093a88](https://github.com/johnnymillergh/jm-spring-boot-template/commit/6093a88))


### Build System

* **$POM:** update version tag to avoid warning when maven builds project ([c570717](https://github.com/johnnymillergh/jm-spring-boot-template/commit/c570717))
* **$POM:** upgrade project version to 2.0.4.SNAPSHOT ([975d4be](https://github.com/johnnymillergh/jm-spring-boot-template/commit/975d4be))


### Code Refactoring

* **$DependencyInjection:** use Lombok annotation `@RequiredArgsConstructor` to inject dependency ([3294cc5](https://github.com/johnnymillergh/jm-spring-boot-template/commit/3294cc5))
* **$HTTPStatus:** rename UniversalStatus.java to HttpStatus.java ([8dc0896](https://github.com/johnnymillergh/jm-spring-boot-template/commit/8dc0896))
* **$Pagination:** not initialize pagination's fields ([6785d4a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/6785d4a))
* **$ResponseBody, HTTPStatus:** use generics to constrain the type of response body and redefine HTTP status ([4cfaa44](https://github.com/johnnymillergh/jm-spring-boot-template/commit/4cfaa44))


### Features

* **$$AOP, SFTP:** add method's argument validation aspect ([ba9db71](https://github.com/johnnymillergh/jm-spring-boot-template/commit/ba9db71))
* **$API:** add an API `/authorization/get-permissions` ([216631a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/216631a))
* **$API:** add an API `/authorization/get-roles` ([8db2eb7](https://github.com/johnnymillergh/jm-spring-boot-template/commit/8db2eb7))
* **$API:** add API `/authorization/submit-authorization` ([827c6f9](https://github.com/johnnymillergh/jm-spring-boot-template/commit/827c6f9))
* **$API:** add API to edit user's basic information ([44ecdad](https://github.com/johnnymillergh/jm-spring-boot-template/commit/44ecdad))
* **$API:** add API to retrieve roles that user has ([3deef66](https://github.com/johnnymillergh/jm-spring-boot-template/commit/3deef66))
* **$API:** new API to retrieve user page list ([28cd849](https://github.com/johnnymillergh/jm-spring-boot-template/commit/28cd849))
* **$Auth:** add ignored URL `/auth/logout` ([7eac73b](https://github.com/johnnymillergh/jm-spring-boot-template/commit/7eac73b))
* **$Auth:** response username after user signed in ([9f00c21](https://github.com/johnnymillergh/jm-spring-boot-template/commit/9f00c21))
* **$Docker:** dockerize application ([33ee303](https://github.com/johnnymillergh/jm-spring-boot-template/commit/33ee303))
* **$File:** add a file util method - generate date-format storage path ([c7ff635](https://github.com/johnnymillergh/jm-spring-boot-template/commit/c7ff635))
* **$FTPStatus:** add FtpStatus.java ([e77441b](https://github.com/johnnymillergh/jm-spring-boot-template/commit/e77441b))
* **$IgnoredAPI, auth:** update the URLs of ignored API ([e8bf036](https://github.com/johnnymillergh/jm-spring-boot-template/commit/e8bf036))
* **$JWT:** distribute JWT configuration for different envs ([f4f5aeb](https://github.com/johnnymillergh/jm-spring-boot-template/commit/f4f5aeb))
* **$pom:** upgrade version to 2.0.3.RELEASE ([666b306](https://github.com/johnnymillergh/jm-spring-boot-template/commit/666b306))
* **$SFTP:** add configuration for caching SFTP connection ([085be1d](https://github.com/johnnymillergh/jm-spring-boot-template/commit/085be1d))
* **$SFTP:** add method to get file size from SFTP server ([7627c63](https://github.com/johnnymillergh/jm-spring-boot-template/commit/7627c63))
* **$SFTP:** init SFTP server's sub directory ([1f8868d](https://github.com/johnnymillergh/jm-spring-boot-template/commit/1f8868d))
* **$SFTP:** make directories for SFTP server if remote directory doesn't exist ([ce09ee7](https://github.com/johnnymillergh/jm-spring-boot-template/commit/ce09ee7))
* **$SFTP:** reuse SFTP session by using CachingSessionFactory ([efca03d](https://github.com/johnnymillergh/jm-spring-boot-template/commit/efca03d))
* **$SFTP:** support SFTP basic operations ([bd42f37](https://github.com/johnnymillergh/jm-spring-boot-template/commit/bd42f37))
* **$SFTP:** use Message to upload file ([26991d4](https://github.com/johnnymillergh/jm-spring-boot-template/commit/26991d4))
* **$SpringValidation:** get field error message from exception ([b35267c](https://github.com/johnnymillergh/jm-spring-boot-template/commit/b35267c))
* **$User:** add a field `avatar` in table `t_user` ([db372ba](https://github.com/johnnymillergh/jm-spring-boot-template/commit/db372ba))
* **$User:** add API operation annotation ([596bade](https://github.com/johnnymillergh/jm-spring-boot-template/commit/596bade))
* **$User:** add API to retrieve user information ([aa1bc05](https://github.com/johnnymillergh/jm-spring-boot-template/commit/aa1bc05))
* **$User:** add field `usersRoles` for API `/user/get-user-info` ([0519f9e](https://github.com/johnnymillergh/jm-spring-boot-template/commit/0519f9e))
* **$User:** add gender enumeration ([1e9da59](https://github.com/johnnymillergh/jm-spring-boot-template/commit/1e9da59))
* **$User:** new API - assign role(s) to user ([dfb5c7b](https://github.com/johnnymillergh/jm-spring-boot-template/commit/dfb5c7b))
* **$User:** new API `/get-user-list-for-selection` ([5e9671e](https://github.com/johnnymillergh/jm-spring-boot-template/commit/5e9671e))
* **$User:** new API for updating user's avatar `/user/update-avatar` ([784b6cd](https://github.com/johnnymillergh/jm-spring-boot-template/commit/784b6cd))
* **$User:** new API to search user by username ([20d2f61](https://github.com/johnnymillergh/jm-spring-boot-template/commit/20d2f61))
* **$User, Stream:** add API `/user/get-avatar` to get user's avatar ([91cbcca](https://github.com/johnnymillergh/jm-spring-boot-template/commit/91cbcca))
* **$WebSecurity:** enable web security ([579f9e6](https://github.com/johnnymillergh/jm-spring-boot-template/commit/579f9e6))


### Performance Improvements

* **$HTTPS:** disable HTTPS feature for all environments ([260992a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/260992a))
* **$POM:** upgrade dependencies ([9893cd2](https://github.com/johnnymillergh/jm-spring-boot-template/commit/9893cd2))
* **$SpringBoot:** upgrade Spring Boot version to 2.2.0.RELEASE ([e9bcb43](https://github.com/johnnymillergh/jm-spring-boot-template/commit/e9bcb43))


### BREAKING CHANGES

* **$DependencyInjection:** use Lombok annotation `@RequiredArgsConstructor` to
inject dependency
* **$ResponseBody, HTTPStatus:** use generics to constrain the type of response body and redefine HTTP status
* **$$AOP, SFTP:** add method's argument validation aspect
* **$SFTP:** reuse SFTP session by using CachingSessionFactory

Closes https://app.clickup.com/t/13g8eh
* **$SFTP:** init SFTP server's sub directory

Closes https://app.clickup.com/t/13g8n3
* **$SFTP:** support SFTP basic operations
* **$POM:** POM's version should be `*-SNAPSHOT`
* **$Pagination:** not initialize pagination's fields
* **$WebSecurity:** enable web security
* **$FTPStatus:** add FtpStatus.java
* **$HTTPStatus:** rename UniversalStatus.java to HttpStatus.java
* **$POM:** SFTP integration

Closes https://app.clickup.com/t/162mf4



# [2.0.3](https://github.com/johnnymillergh/jm-spring-boot-template/compare/v2.0.2...v) (2019-07-02)


### Bug Fixes

* **$Role:** remove `@NotNull` annotation ([6093a88](https://github.com/johnnymillergh/jm-spring-boot-template/commit/6093a88))


### Code Refactoring

* **$HTTPStatus:** rename UniversalStatus.java to HttpStatus.java ([8dc0896](https://github.com/johnnymillergh/jm-spring-boot-template/commit/8dc0896))
* **$Pagination:** not initialize pagination's fields ([6785d4a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/6785d4a))
* **$ResponseBody, HTTPStatus:** use generics to constrain the type of response body and redefine HTTP status ([4cfaa44](https://github.com/johnnymillergh/jm-spring-boot-template/commit/4cfaa44))


### Features

* **$API:** add an API `/authorization/get-permissions` ([216631a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/216631a))
* **$API:** add an API `/authorization/get-roles` ([8db2eb7](https://github.com/johnnymillergh/jm-spring-boot-template/commit/8db2eb7))
* **$API:** add API `/authorization/submit-authorization` ([827c6f9](https://github.com/johnnymillergh/jm-spring-boot-template/commit/827c6f9))
* **$API:** add API to edit user's basic information ([44ecdad](https://github.com/johnnymillergh/jm-spring-boot-template/commit/44ecdad))
* **$API:** add API to retrieve roles that user has ([3deef66](https://github.com/johnnymillergh/jm-spring-boot-template/commit/3deef66))
* **$API:** new API to retrieve user page list ([28cd849](https://github.com/johnnymillergh/jm-spring-boot-template/commit/28cd849))
* **$FTPStatus:** add FtpStatus.java ([e77441b](https://github.com/johnnymillergh/jm-spring-boot-template/commit/e77441b))
* **$IgnoredAPI, auth:** update the URLs of ignored API ([e8bf036](https://github.com/johnnymillergh/jm-spring-boot-template/commit/e8bf036))
* **$JWT:** distribute JWT configuration for different envs ([f4f5aeb](https://github.com/johnnymillergh/jm-spring-boot-template/commit/f4f5aeb))
* **$pom:** upgrade version to 2.0.3.RELEASE ([666b306](https://github.com/johnnymillergh/jm-spring-boot-template/commit/666b306))
* **$SpringValidation:** get field error message from exception ([b35267c](https://github.com/johnnymillergh/jm-spring-boot-template/commit/b35267c))
* **$User:** add API to retrieve user information ([aa1bc05](https://github.com/johnnymillergh/jm-spring-boot-template/commit/aa1bc05))
* **$User:** add field `usersRoles` for API `/user/get-user-info` ([0519f9e](https://github.com/johnnymillergh/jm-spring-boot-template/commit/0519f9e))
* **$User:** add gender enumeration ([1e9da59](https://github.com/johnnymillergh/jm-spring-boot-template/commit/1e9da59))
* **$User:** new API - assign role(s) to user ([dfb5c7b](https://github.com/johnnymillergh/jm-spring-boot-template/commit/dfb5c7b))
* **$User:** new API `/get-user-list-for-selection` ([5e9671e](https://github.com/johnnymillergh/jm-spring-boot-template/commit/5e9671e))
* **$User:** new API to search user by username ([20d2f61](https://github.com/johnnymillergh/jm-spring-boot-template/commit/20d2f61))
* **$WebSecurity:** enable web security ([579f9e6](https://github.com/johnnymillergh/jm-spring-boot-template/commit/579f9e6))


### BREAKING CHANGES

* **$Pagination:** not initialize pagination's fields
* **$WebSecurity:** enable web security
* **$FTPStatus:** add FtpStatus.java
* **$HTTPStatus:** rename UniversalStatus.java to HttpStatus.java
* **$ResponseBody, HTTPStatus:** use generics to constrain the type of response body and redefine HTTP status



## [2.0.2](https://github.com/johnnymillergh/jm-spring-boot-template/compare/779c070...v2.0.2) (2019-05-24)


### Bug Fixes

* **$APICounting:** solve the problem that the statistic of API is not correct ([104fc8c](https://github.com/johnnymillergh/jm-spring-boot-template/commit/104fc8c))
* **$APIService:** solve getBean problem ([ad04d87](https://github.com/johnnymillergh/jm-spring-boot-template/commit/ad04d87))
* **$Compile:** This static field public but not final, and could be changed by malicious code or by accident from another package. The field could be made final to avoid this vulnerability. ([97a8dd3](https://github.com/johnnymillergh/jm-spring-boot-template/commit/97a8dd3))
* **$HTTP:** resolve HTTP status inconsistent problem ([e80cf63](https://github.com/johnnymillergh/jm-spring-boot-template/commit/e80cf63))
* **$HTTPS:** setProperty() method may solve the HTTP header parsing error ([f0ce5bf](https://github.com/johnnymillergh/jm-spring-boot-template/commit/f0ce5bf))
* pom.xml to reduce vulnerabilities ([0b2eb53](https://github.com/johnnymillergh/jm-spring-boot-template/commit/0b2eb53))
* **$JDBC, timezone:** solve problem that datetime is not correct ([6936714](https://github.com/johnnymillergh/jm-spring-boot-template/commit/6936714))
* **$logback:** solve logging error that all type of log will be written in the same file ([e571b0a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/e571b0a))
* **$LOGBack:** resolve problem name of log file is ambiguous ([894ef92](https://github.com/johnnymillergh/jm-spring-boot-template/commit/894ef92))
* **$SQL:** solve user not found problem ([e93a094](https://github.com/johnnymillergh/jm-spring-boot-template/commit/e93a094))
* **$WebSecurity:** enable ignore URLs to avoid 403 error ([10289bf](https://github.com/johnnymillergh/jm-spring-boot-template/commit/10289bf))
* **$WebSecurity:** solve problem that server responses error-403 to any POST request ([6aa4192](https://github.com/johnnymillergh/jm-spring-boot-template/commit/6aa4192))
* **$YML:** fix wrong YML configuration ([db7bd7b](https://github.com/johnnymillergh/jm-spring-boot-template/commit/db7bd7b))


### Build System

* **$compile:** upgrade version to 2.0.1.RELEASE ([dcda3d7](https://github.com/johnnymillergh/jm-spring-boot-template/commit/dcda3d7))
* **$POM:** upgrade version to 2.0.2.RELEASE ([9f5bfdd](https://github.com/johnnymillergh/jm-spring-boot-template/commit/9f5bfdd))


### chore

* **$Class:** remove MonitorController.java ([911e95d](https://github.com/johnnymillergh/jm-spring-boot-template/commit/911e95d))


### Code Refactoring

* **$API:** rename API resources ([fb44686](https://github.com/johnnymillergh/jm-spring-boot-template/commit/fb44686))
* **$UniversalStatus:** new rules for HTTP status code ([edc7142](https://github.com/johnnymillergh/jm-spring-boot-template/commit/edc7142))
* **$UniversalStatus:** new rules for HTTP status code (2) ([ffb373a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/ffb373a))
* **$WholeProject:** create a sub Maven module `jm-spring-boot-starter-domain` ([8fb9f82](https://github.com/johnnymillergh/jm-spring-boot-template/commit/8fb9f82))


### Features

* **$API:** add API `/setApiInUse` and `/setAllApiInUse` ([c66ff0a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/c66ff0a))
* **$API:** add API annotation for AuthController ([3621107](https://github.com/johnnymillergh/jm-spring-boot-template/commit/3621107))
* **$API:** add API getApiAnalysis (undone) ([929a36c](https://github.com/johnnymillergh/jm-spring-boot-template/commit/929a36c))
* **$API:** add AuthorizationController ([7a62319](https://github.com/johnnymillergh/jm-spring-boot-template/commit/7a62319))
* **$API:** add new API for validating username ([72687a6](https://github.com/johnnymillergh/jm-spring-boot-template/commit/72687a6))
* **$API:** change response data format for getting api information ([ebe5b9a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/ebe5b9a))
* **$API:** create role ([c9b4f0b](https://github.com/johnnymillergh/jm-spring-boot-template/commit/c9b4f0b))
* **$API:** edit role ([722e3be](https://github.com/johnnymillergh/jm-spring-boot-template/commit/722e3be))
* **$API:** get role list ([03d7199](https://github.com/johnnymillergh/jm-spring-boot-template/commit/03d7199))
* **$API:** new API `/getApiList` ([e6f8821](https://github.com/johnnymillergh/jm-spring-boot-template/commit/e6f8821))
* **$API:** new API for /getApiAnalysis ([e9b33d0](https://github.com/johnnymillergh/jm-spring-boot-template/commit/e9b33d0))
* **$API:** new API for getting controller information ([4019de6](https://github.com/johnnymillergh/jm-spring-boot-template/commit/4019de6))
* **$API:** search role ([99ac5b7](https://github.com/johnnymillergh/jm-spring-boot-template/commit/99ac5b7))
* **$auth:** provide apis for authentication and authorization ([ddaf459](https://github.com/johnnymillergh/jm-spring-boot-template/commit/ddaf459))
* **$banner:** add author information in banner ([a3ea53f](https://github.com/johnnymillergh/jm-spring-boot-template/commit/a3ea53f))
* **$banner:** add banner text link ([1c611c9](https://github.com/johnnymillergh/jm-spring-boot-template/commit/1c611c9))
* **$build): feat($build:** update maven version to 1.2.0-SNAPSHOT; support multi-env packaging ([474822a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/474822a))
* **$compile:** add new dependency jasypt-spring-boot ([fdbbfde](https://github.com/johnnymillergh/jm-spring-boot-template/commit/fdbbfde))
* **$compile:** log deployment duration ([5ae4be3](https://github.com/johnnymillergh/jm-spring-boot-template/commit/5ae4be3))
* **$compile:** support RBAC ([779c070](https://github.com/johnnymillergh/jm-spring-boot-template/commit/779c070)), closes [#jqh13](https://github.com/johnnymillergh/jm-spring-boot-template/issues/jqh13)
* **$compile:** update application .yml configuration ([a23b868](https://github.com/johnnymillergh/jm-spring-boot-template/commit/a23b868))
* **$Compile:** upgrade version to 2.0.0.RELEASE ([d7a99b1](https://github.com/johnnymillergh/jm-spring-boot-template/commit/d7a99b1))
* **$Date, JSON format:** make WebMvcConfiguration implement WebMvcConfigurer ([b9d659c](https://github.com/johnnymillergh/jm-spring-boot-template/commit/b9d659c))
* **$DomainModel:** update naming rule of domain model ([97290ba](https://github.com/johnnymillergh/jm-spring-boot-template/commit/97290ba))
* **$Druid:** enable Druid monitor ([8b56637](https://github.com/johnnymillergh/jm-spring-boot-template/commit/8b56637))
* **$Exception:** update BaseException, BizException and SecurityException ([b5ccbe9](https://github.com/johnnymillergh/jm-spring-boot-template/commit/b5ccbe9))
* **$Git:** update Git ignore rule ([b7643dc](https://github.com/johnnymillergh/jm-spring-boot-template/commit/b7643dc))
* **$HTTP:** set HTTP response status code ([4e55477](https://github.com/johnnymillergh/jm-spring-boot-template/commit/4e55477))
* **$IP:** get real public IP address ([b6cef10](https://github.com/johnnymillergh/jm-spring-boot-template/commit/b6cef10))
* **$IP:** update API provided by https://whatismyipaddress.com/api ([96143a2](https://github.com/johnnymillergh/jm-spring-boot-template/commit/96143a2))
* **$JWT:** response user's full name ([a76eda8](https://github.com/johnnymillergh/jm-spring-boot-template/commit/a76eda8))
* **$Log:** add @WebLog annotation to log access information ([fb938e0](https://github.com/johnnymillergh/jm-spring-boot-template/commit/fb938e0))
* **$Log:** configure logging level of project as DEBUG to enable SQL logging ([09a88b7](https://github.com/johnnymillergh/jm-spring-boot-template/commit/09a88b7))
* **$Log:** support logback for multi-environment ([5e33d7a](https://github.com/johnnymillergh/jm-spring-boot-template/commit/5e33d7a))
* **$Log, Domain model:** format request params ([f89b9b5](https://github.com/johnnymillergh/jm-spring-boot-template/commit/f89b9b5))
* **$LogAspect:** web request log aspect enhancement ([4f1e750](https://github.com/johnnymillergh/jm-spring-boot-template/commit/4f1e750))
* **$LOGBack:** add LOGBack configuration; support output log file ([a590f86](https://github.com/johnnymillergh/jm-spring-boot-template/commit/a590f86))
* **$LOGBack:** update configuration ([7070d48](https://github.com/johnnymillergh/jm-spring-boot-template/commit/7070d48))
* **$Monitor:** enable druid monitor for all env; enable swagger in test env ([9e37d11](https://github.com/johnnymillergh/jm-spring-boot-template/commit/9e37d11))
* **$MyBatisPlus:** support pagination for SQL query ([d974dbb](https://github.com/johnnymillergh/jm-spring-boot-template/commit/d974dbb))
* **$MySQL:** alter column name and comment ([c35f0e9](https://github.com/johnnymillergh/jm-spring-boot-template/commit/c35f0e9))
* **$ProjectProperty:** complete POM information ([4dbf549](https://github.com/johnnymillergh/jm-spring-boot-template/commit/4dbf549))
* **$run:** add api management controller ([9415bee](https://github.com/johnnymillergh/jm-spring-boot-template/commit/9415bee))
* **$run:** add custom error response ([06f9d04](https://github.com/johnnymillergh/jm-spring-boot-template/commit/06f9d04))
* **$SpringBoot:** update Spring Boot version 2.1.4.RELEASE ([f843aae](https://github.com/johnnymillergh/jm-spring-boot-template/commit/f843aae))
* **$SSL:** enable HTTPS traffic under development environment ([f57e5e7](https://github.com/johnnymillergh/jm-spring-boot-template/commit/f57e5e7))
* **$SSL:** support HTTPS traffic to solve (blocked:mixed-content) problem ([7503d1c](https://github.com/johnnymillergh/jm-spring-boot-template/commit/7503d1c))
* **$Swagger:** integrate swagger-bootstrap-ui ([f9331c2](https://github.com/johnnymillergh/jm-spring-boot-template/commit/f9331c2))
* **$UniversalStatus:** add new status - PARAM_INVALID ([b183f00](https://github.com/johnnymillergh/jm-spring-boot-template/commit/b183f00))
* **$Validation:** enable custom validator message ([ce09fb0](https://github.com/johnnymillergh/jm-spring-boot-template/commit/ce09fb0))
* **$WebSecurity:** add enable/disable Web Security feature switch ([8a2bd4d](https://github.com/johnnymillergh/jm-spring-boot-template/commit/8a2bd4d))
* **$WebSecurity, Permission:** support super user to access any system's resources ([ce38e60](https://github.com/johnnymillergh/jm-spring-boot-template/commit/ce38e60))
* **$YML:** update application.yml configuration ([492d900](https://github.com/johnnymillergh/jm-spring-boot-template/commit/492d900))
* **$YML:** update global application.yml ([4f3b8ff](https://github.com/johnnymillergh/jm-spring-boot-template/commit/4f3b8ff))


### Reverts

* **$SSL:** enable HTTPS under development environment ([39e8bf5](https://github.com/johnnymillergh/jm-spring-boot-template/commit/39e8bf5))


### BREAKING CHANGES

* **$POM:** upgrade version to 2.0.2.RELEASE
* **$compile:** support RBAC; login refactor.
* **$HTTPS:** Add an experimental statement to solve the HTTP header parsing error
* **$WholeProject:** create a sub Maven module `jm-spring-boot-starter-domain`
* **$API:** API resources named by kebab-case naming rule
* **$Log, Domain model:** Not sending backend exception information to client
* **$DomainModel:** A new naming rule of domain model
* **$WebSecurity, Permission:** Super user has no restriction to access any resources
* **$LogAspect:** Log every web request information by default
* **$HTTP:** set HTTP response status code
* **$Log:** Add spring-boot-starter-aop dependency
* **$Class:** remove MonitorController.java
* **$SSL:** support HTTPS traffic
* **$compile:** upgrade version to 2.0.1.RELEASE
* **$MyBatisPlus:** enable pagination
* **$Log:** output SQL logs to file
* **$WebSecurity:** csrf().disable()
* **$build): feat($build:** support multi-env packaging
* **$WebSecurity:** turn on/off Web Security feature dynamically
* **$UniversalStatus:** new rules for HTTP status code
* **$UniversalStatus:** new rules for HTTP status code
* **$YML:** project back to alive
* **$SSL:** support HTTPS traffic



