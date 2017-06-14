package com.ljxxz.web.filter.annotation;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2016/12/1.
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCheck {
}
