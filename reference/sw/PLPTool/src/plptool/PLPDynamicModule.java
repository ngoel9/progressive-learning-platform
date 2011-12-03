/*
    Copyright 2011 David Fritz, Brian Gordon, Wira Mulia

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */

package plptool;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * PLPDynamicModule is the dynamic module loading framework for PLPTool. The
 * class loader and the class load routine are generic, they can be used
 * to load generic classes and attach them to the list to be used in plptool
 * later for any reason. Object instantiations of dynamic classes are
 * superclass specific. Instantiation routines are expected to return a null
 * reference if they failed and call error #1040 (e.g. typecasting error).
 *
 * @author wira
 */
public class PLPDynamicModule {
    /**
     * Index tracker for loaded module classes
     */
    private static int index = 0;

    /**
     * A list of references to dynamic module classes
     */
    private static ArrayList<Class> dynamicModuleClasses;

    /**
     * Boolean to give warning about loading 3rd party modules once
     */
    private static boolean warn = false;

    /**
     * Reference to the class loader
     */
    private static PLPDynamicModuleClassLoader loader;

    /**
     * Module class loading routine for PLPTool dynamic module framework. This
     * function is called by PLPToolApp class during program startup via
     * the --load-class parameter.
     *
     * @param path Path of the Java class file
     * @param className Java class name (package.subpackage...class)
     * @return True if class is loaded and attached to the list, false otherwise
     */
    public static boolean loadModuleClass(String path, String className) {
        if(!warn) {
            Msg.W("YOU ARE LOADING A DYNAMIC MODULE, THIS COULD BE POTENTIALLY DANGEROUS", null);
            Msg.W("Make sure you only use trusted third party modules!", null);
            warn = true;
        }
        Msg.M("--- Loading module class " + className + " from " + path +
              " (index: " + index + ")...");
        if(loader == null) {
            ClassLoader parent = PLPDynamicModule.class.getClassLoader();
            loader = new PLPDynamicModuleClassLoader(parent);
        }
        loader.setClassToLoad(path, className);
        try {
            Class dynamicModuleClass = loader.loadClass(className);
            if(dynamicModuleClass == null) {
                return false;
            }
            if(dynamicModuleClasses == null)
                dynamicModuleClasses = new ArrayList<Class>();
            dynamicModuleClasses.add(dynamicModuleClass);
            index++;
        } catch(ClassNotFoundException e) {
            Msg.E("The class " + className + " is not found in " + path,
                  Constants.PLP_DBUSMOD_CLASS_NOT_FOUND_ERROR, null);
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Get a reference to a registered module class specified by its index
     *
     * @param index Index of the class
     * @return Reference to the module class
     */
    public static Class getDynamicModuleClass(int index) {
        if(index < 0 || index >= dynamicModuleClasses.size())
            return null;

        return dynamicModuleClasses.get(index);
    }

    /**
     * Get the number of classes that are loaded
     *
     * @return Number of classes loaded
     */
    public static int getNumberOfClasses() {
        return dynamicModuleClasses.size();
    }

    /**
     * Create a new instance of a module class and cast it to PLPSimBusModule.
     *
     * @param index Index of the class
     * @return Reference to the newly instantiated object casted to
     * a simulation bus module. Returns null pointer if instantiation failed
     * for any reason.
     */
    public static PLPSimBusModule newBusModuleInstance(int index) {
        try {
            PLPSimBusModule mod = (PLPSimBusModule) getDynamicModuleClass(index).newInstance();
            return mod;

        } catch (InstantiationException e) {
            Msg.E("Instantiation exception for module " + getDynamicModuleClass(index).getName() + ". " +
                  "Dynamic bus modules have to extend plptool.PLPSimBusModule class.",
                  Constants.PLP_DBUSMOD_INSTANTIATION_ERROR, null);
            return null;
        } catch(IllegalAccessException e) {
            Msg.E("Illegal access exception for module " + getDynamicModuleClass(index).getName(),
                  Constants.PLP_DBUSMOD_ILLEGAL_ACCESS, null);
            return null;
        }
    }
}

class PLPDynamicModuleClassLoader extends ClassLoader {

    private String path;
    private String nameToCheck;

    public PLPDynamicModuleClassLoader(ClassLoader parent) {
        super(parent);
    }

    public void setClassToLoad(String path, String name) {
        this.path = path;
        this.nameToCheck = name;
    }

    @Override
    public Class loadClass(String name) throws ClassNotFoundException {
        if(!name.equals(nameToCheck))
            return super.loadClass(name, true);

        try {
            File file = new File(path);
            FileInputStream in = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            in.read(data, 0, (int) file.length());

            Class ret = defineClass(name, data, 0, data.length);
            return ret;
            
        } catch(Exception e) {
            Msg.E("Unable to load dynamic module " + name + " from the file " + path,
                  Constants.PLP_DBUSMOD_PATH_ERROR, null);
            return null;
        }
    }
}
