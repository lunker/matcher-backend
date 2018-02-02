package org.lunker.matcher.annotation;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by dongqlee on 2018. 1. 9..
 */

@Target(value = ElementType.TYPE)
@RequestMapping("/api")
public @interface RequestAPI {
}
