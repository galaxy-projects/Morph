package org.galaxy.morph.annotations;

import org.jetbrains.annotations.ApiStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiStatus.Experimental
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Comment {

    String[] value();

}
