package com.henrybear.flows;

import java.lang.reflect.InvocationTargetException;

import com.henrybear.util.Context;

public abstract class Step {
	public abstract void executor(Context context) throws Exception;
}
