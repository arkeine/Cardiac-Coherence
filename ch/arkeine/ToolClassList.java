
package ch.arkeine;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Library which provide methods to list all class of a package.
 * <p>
 * @author Nils Ryter
 */
public class ToolClassList {

    /**
     * List all the classes of a given package.
     */
    public static Collection<Class> getClasses(String pckgName)
            throws ClassNotFoundException, IOException {
        LinkedList<Class> classes = new LinkedList<>();
        //Get all entry of the CLASSPATH
        String[] entries = System.getProperty("java.class.path").split(
                System.getProperty("path.separator"));
        //Check if entries are jar file or directory
        for (String s : entries) {
            if (s.endsWith(".jar")) {
                classes.addAll(scanJar(s, pckgName));
            } else {
                classes.addAll(scanDirectory(s, pckgName));
            }
        }
        return classes;
    }

    /**
     * List all the classes in a package if package is a files system.
     */
    private static Collection<Class> scanDirectory(String directory, String pckgName)
            throws ClassNotFoundException {
    	Collection<Class> classes = new LinkedList<>();
        /*
         * Create a absolute path of pakage Use forech instead
         * pckgName.remplaceAll in the way to make portable code
         */
        StringBuilder sb = new StringBuilder(directory);
        for (String s : pckgName.split("\\.")) {
            sb.append(File.separator).append(s);
        }
        //Create the directory of the package in order to look over it
        File dir = new File(sb.toString());
        if (dir.isDirectory()) {
            //Filter directory
            FilenameFilter filter = (File dir1, String name)
                    -> name.endsWith(".class");
            File[] lst = dir.listFiles(filter);
            //Add each class to the list
            for (File f : lst) {
                classes.add(Class.forName(
                        pckgName + "." + f.getName().split("\\.")[0]));
            }
        }
        return classes;
    }

    /**
     * List all the classes in a package if package is in a jar.
     */
    private static Collection<Class> scanJar(String jar, String pckgName)
            throws IOException, ClassNotFoundException {
    	Collection<Class> classes = new LinkedList<>();
        JarFile jf = new JarFile(jar);
        String pckgPath = pckgName.replace(".", "/");
        //Add each class in the jar to the list
        Enumeration<JarEntry> entries = jf.entries();
        while (entries.hasMoreElements()) {
            JarEntry element = entries.nextElement();
            /*
             * Add class only if the name begin with the package path and finish
             * by .class
             */
            if (element.getName().startsWith(pckgPath)
                    && element.getName().endsWith(".class")) {
                String nomFichier = element.getName().substring(
                        pckgName.length() + 1);
                classes.add(Class.forName(
                        pckgName + "." + nomFichier.split("\\.")[0]));
            }
        }
        return classes;
    }
}
