# SpringBootTemplate
[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

> 🛠 Template project for Spring Boot

**SpringBootTemplate** is a Spring Boot based scaffolding project which integrated some awesome features - multi-environment support, Redis, Redis Session, Redis Cache, Spring Validation, Spring Security... More funny features will come soon! 

This repository contains:

1. Source code of the project.
2. SQL Statements of creating schema, tables.

SpringBootTemplate is designed for building a back-end project quickly and efficiently.

## Features

Here is the highlights of **SpringBootTemplate**:

1. Multi-environment support for project deployment: development, test and production.
2. Customized banner for Spring Boot startup.
3. Enabled logging SQL statement, user's request information.
4. Integrated Redis, Redis Session, Redis Cache.
5. Integrated Spring Validation, Spring Security
6. **SpringBootTemplate** is actively developed. More features will come soon!

## Usage

This project uses [Java 8](http://nodejs.org). Go check them out if you don't have them locally installed.

1. Clone or download this project.

   ```shell
   $ git clone https://github.com/johnnymillergh/SpringBootTemplate.git
   ```

2. Build with IntelliJ IDEA or Eclipse.

3. Click the green triangle to Run.

## Standard Controller Template

A standard controller should be simple, good to read and maintain. It's  just a data transfer bridge for front-end and back-end and exposures APIs. Here is a standard controller template.

```java
/**
 * Description: StandardController, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-04-11
 * @time: 13:36
 **/
@RestController
@RequestMapping("/standard")
@Api(tags = {"/standard"})
public class StandardController {
    private final DemoService demoService;

    public StandardController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/some-path")
    @ApiOperation(value = "somePath", notes = "Notes for some path")
    public ResponseBodyBean somePath(String param) {
        return ResponseBodyBean.ofSuccess("Response message" + param);
    }
}
```



## Related Efforts

- [Art of Readme](https://github.com/noffle/art-of-readme) - 💌 Learn the art of writing quality READMEs.
- [open-source-template](https://github.com/davidbgk/open-source-template/) - A README template to encourage open-source contributions.

## Maintainers

[@johnnymillergh](https://github.com/johnnymillergh).

## Contributing

Feel free to dive in! [Open an issue](https://github.com/johnnymillergh/SpringBootTemplate/issues/new).

### Contributors

This project exists thanks to all the people who contribute. 

- Johnny Miller [[@johnnymillergh](https://github.com/johnnymillergh)]
- …


### Sponsors

Support this project by becoming a sponsor. Your logo will show up here with a link to your website. [[Become a sponsor](Become a sponsor)]

## License

[Apache License](LICENSE) © Johnny Miller

