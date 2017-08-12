/**
 * 
 */
package com.henrybear.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xrong2011 2017/8/12 自定义类加载器
 * @param classPath:class文件路径
 */
public class ParentClassLoader extends ClassLoader {

	private volatile static boolean flag = true;
	private static ParentClassLoader instance;
	private static ClassLoader parent;
	private static String classPath = "E:/myworkspace/struts/WebRoot/WEB-INF/classes/";
	
	private ParentClassLoader(){
		if(this.parent == null){
			parent = super.getParent();
		}
	}
	
	public static ParentClassLoader getInstance(){
		if(instance == null){
			synchronized (ParentClassLoader.class) {
				instance = new ParentClassLoader();
			}
		}
		return instance;
	}

	public static boolean isFlag() {
		return flag;
	}

	public static void setFlag(boolean flag) {
		ParentClassLoader.flag = flag;
	}
	
	public Class<?> loadClass(String className) throws ClassNotFoundException{
		return loadClass(className,false);
	}
	
	protected Class<?> loadClass(String className, boolean resolve) throws ClassNotFoundException{
		Class<?> c = findLoadedClass(className);
		if(c != null){
			return c;
		}
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		try{
			if(parent == null){
				c = systemClassLoader.loadClass(className);
			}else{
				c = parent.loadClass(className);
			}
		}catch(ClassNotFoundException e){
		}
		
		if(c == null){
			c = findClass(className);
		}
		if (resolve) {
            resolveClass(c);
        }
		return c;
	}
	
	protected Class<?> findClass(String className){
		String name = className.replaceAll("\\.", "/");
		
		String fileName = name + ".class";
		InputStream is = null;
		byte[] bytes = null;
		try {
			is = new FileInputStream(new File(classPath+fileName));
			if(is == null){
				return super.loadClass(className);
			}
			bytes = new byte[is.available()];
			is.read(bytes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defineClass(className,bytes,0,bytes.length);
	}
	
	public static void main(String[] args){
		ParentClassLoader loader = ParentClassLoader.getInstance();
		try {
			Object queueDemo = loader.loadClass("com.henrybear.bean.Person").newInstance();
			System.out.println(queueDemo.getClass().getName());
			System.out.println(queueDemo.getClass().getClassLoader());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
