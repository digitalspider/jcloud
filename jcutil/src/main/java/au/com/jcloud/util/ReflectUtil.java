package au.com.jcloud.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ReflectUtil {
	public static final Logger LOG = Logger.getLogger(ReflectUtil.class);

	/**
	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static List<Class> getClasses(String packageName, Class filterClassType, boolean excludeSelf) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		LOG.debug("classLoader="+classLoader);
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		LOG.debug("path="+path);
		Enumeration<URL> resources = classLoader.getResources(path);
		LOG.debug("resources="+resources);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		LOG.debug("dirs="+dirs);
		List<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName, filterClassType, excludeSelf));
		}
		LOG.debug("classes="+classes);
		return classes;
	}


	/**
	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static List<Class> getClasses(String packageName, Class<? extends Annotation> annotationClass) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		LOG.debug("classLoader="+classLoader);
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		LOG.debug("path="+path);
		Enumeration<URL> resources = classLoader.getResources(path);
		LOG.debug("resources="+resources);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		LOG.debug("dirs="+dirs);
		List<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName, annotationClass));
		}
		LOG.debug("classes="+classes);
		return classes;
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static List<Class> findClasses(File directory, String packageName, Class filterClassType, boolean excludeSelf) throws ClassNotFoundException {

		List<Class> classes = new ArrayList<Class>();

		if (!directory.exists()) {
			return classes;
		}

		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName(), filterClassType, excludeSelf));
			} else if (file.getName().endsWith(".class")) {
				Class classType = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
				if (filterClassType == null || (filterClassType!=null && filterClassType.isAssignableFrom(classType))) {
					if (!(excludeSelf && classType.equals(filterClassType))) {
						classes.add(classType);
					}
				}
			} else if (file.toString().startsWith("file:") && file.toString().contains(".jar!")) {
				int index = directory.toString().indexOf("!");
				String pathToJar = directory.toString().substring(5,index);
				classes.addAll(findClassesInJar(new File(pathToJar), packageName, filterClassType, excludeSelf));
			}
		}
		return classes;
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static List<Class> findClasses(File directory, String packageName, Class<? extends Annotation> annotationClass) throws ClassNotFoundException {

		List<Class> classes = new ArrayList<Class>();

		LOG.debug("directory="+directory);
		if (!directory.exists()) {
			if (directory.toString().startsWith("file:") && directory.toString().contains(".jar!")) {
				int index = directory.toString().indexOf("!");
				String pathToJar = directory.toString().substring(5,index);
				classes = findClassesInJar(new File(pathToJar), packageName, annotationClass);
			}
			return classes;
		}

		File[] files = directory.listFiles();
		LOG.debug("files="+files);
		for (File file : files) {
			LOG.debug("file="+file);
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName(), annotationClass));
			} else if (file.getName().endsWith(".class")) {
				Class classType = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
				LOG.debug("classType="+classType);
				if (classType.isAnnotationPresent(annotationClass)) {
					classes.add(classType);
				}
			}
		}
		return classes;
	}

	/**
	 * Find all classes in a given jar file
	 *
	 * @param pathToJar   The jar file
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static List<Class> findClassesInJar(File pathToJar, String packageName, Class filterClassType, boolean excludeSelf) throws ClassNotFoundException {

		List<Class> classes = new ArrayList<Class>();

		LOG.debug("pathToJar=" + pathToJar);
		if (!pathToJar.exists()) {
			return classes;
		}
		try {
			JarFile jarFile = new JarFile(pathToJar);
			Enumeration<JarEntry> e = jarFile.entries();

			String pathToFind = packageName.replace('.', '/');
			URL[] urls = {new URL("jar:file:" + pathToJar + "!/" + pathToFind)};
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if (je.isDirectory() || !je.getName().endsWith(".class") || !je.getName().startsWith(pathToFind)) {
					continue;
				}
				LOG.debug("je=" + je.getName());
				// -6 because of .class
				String className = je.getName().substring(0, je.getName().length() - 6);
				LOG.debug("className=" + className);
				className = className.replace('/', '.');
				Class classType = cl.loadClass(className);
				LOG.debug("classType=" + classType);
				if (filterClassType == null || (filterClassType!=null && filterClassType.isAssignableFrom(classType))) {
					if (!(excludeSelf && classType.equals(filterClassType))) {
						classes.add(classType);
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e, e);
		}
		return classes;
	}

	/**
	 * Find all classes in a given jar file
	 *
	 * @param pathToJar   The jar file
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static List<Class> findClassesInJar(File pathToJar, String packageName, Class<? extends Annotation> annotationClass) throws ClassNotFoundException {

		List<Class> classes = new ArrayList<Class>();

		LOG.debug("pathToJar=" + pathToJar);
		if (!pathToJar.exists()) {
			return classes;
		}
		try {
			JarFile jarFile = new JarFile(pathToJar);
			Enumeration<JarEntry> e = jarFile.entries();

			String pathToFind = packageName.replace('.', '/');
			URL[] urls = {new URL("jar:file:" + pathToJar + "!/" + pathToFind)};
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if (je.isDirectory() || !je.getName().endsWith(".class") || !je.getName().startsWith(pathToFind)) {
					continue;
				}
				LOG.debug("je=" + je.getName());
				// -6 because of .class
				String className = je.getName().substring(0, je.getName().length() - 6);
				LOG.debug("className=" + className);
				className = className.replace('/', '.');
				Class classType = cl.loadClass(className);
				LOG.debug("classType=" + classType);
				if (classType.isAnnotationPresent(annotationClass)) {
					classes.add(classType);
				}
			}
		} catch (Exception e) {
			LOG.error(e, e);
		}
		return classes;
	}
}
