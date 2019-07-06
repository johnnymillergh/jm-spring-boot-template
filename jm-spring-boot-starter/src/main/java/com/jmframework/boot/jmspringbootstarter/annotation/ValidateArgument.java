package com.jmframework.boot.jmspringbootstarter.annotation;

import java.lang.annotation.*;

/**
 * <h1>ValidateArgument</h1>
 * <p>Annotation for validating method's argument.</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-06 12:08
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ValidateArgument {
}
