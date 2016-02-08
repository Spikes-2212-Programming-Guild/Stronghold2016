package com.spikes2212.robot2016;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.TYPE })
public @interface Calibrate {
	String desc();

	String unit();

	double min() default 0;

	double max() default 0;
}
