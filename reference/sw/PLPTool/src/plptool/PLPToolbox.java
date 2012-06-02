/*
    Copyright 2012 David Fritz, Brian Gordon, Wira Mulia

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

import plptool.dmf.DynamicModuleFramework;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.jar.*;

/**
 *
 * @author wira
 */
public class PLPToolbox {
    /**
     * Parse a hexadecimal or binary number into unsigned long
     *
     * @param number Hex or binary number
     * @return number in long, -1 if error occurred
     */
    public static long parseNum(String number) {
        try {

        if(number.startsWith("0x") || number.startsWith("0h")) {
            number = number.substring(2);
            return Long.parseLong(number, 16);
        }
        else if(number.startsWith("0b")) {
            number = number.substring(2);
            return Long.parseLong(number, 2);
        }
        else
            return Long.parseLong(number);

        } catch(Exception e) {
            Msg.lastError = -1;
            return Msg.E("parseNum: '" + number + "' is not a valid number",
                            Constants.PLP_NUMBER_ERROR, null);
        }
    }

    /**
     * Parse a hexadecimal or binary number into unsigned long, but stay silent
     * if parsing failed.
     *
     * @param number Hex or binary number
     * @return number in long, -1 if error occurred
     */
    public static long parseNumSilent(String number) {
        try {

        if(number.startsWith("0x") || number.startsWith("0h")) {
            number = number.substring(2);
            return Long.parseLong(number, 16);
        }
        else if(number.startsWith("0b")) {
            number = number.substring(2);
            return Long.parseLong(number, 2);
        }
        else
            return Long.parseLong(number);

        } catch(Exception e) {
            Msg.lastError = -1;
            return Constants.PLP_NUMBER_ERROR;
        }
    }

    /**
     * Parse a hexadecimal or binary number into unsigned int
     *
     * @param number Hex or binary number
     * @return number in int, -1 if error occurred
     */
    public static int parseNumInt(String number) {
        try {

        if(number.startsWith("0x") || number.startsWith("0h")) {
            number = number.substring(2);
            return Integer.parseInt(number, 16);
        }
        else if(number.startsWith("0b")) {
            number = number.substring(2);
            return Integer.parseInt(number, 2);
        }
        else
            return Integer.parseInt(number);

        } catch(Exception e) {
            Msg.lastError = -1;
            return Msg.E("parseNum: '" + number + "' is not a valid number",
                            Constants.PLP_NUMBER_ERROR, null);
        }
    }

    /**
     * Try if the given label resolves to an address. If not, parse the
     * string as an address.
     *
     * @param label Label to resolve
     * @param asm Assembly object
     * @return The address of label if the label resolves, if not, the address
     * if it parses as an address, -1 otherwise
     */
    public static long tryResolveLabel(String label, PLPAsm asm) {
        long addr = -1;

        addr = asm.resolveAddress(label);
        if(addr == -1)
            addr = parseNumSilent(label);

        return addr;
    }

    /**
     * Format a long as 8 digit hexadecimal in String prefixed with '0x'
     *
     * @param num Number to format
     * @return 8 digit hex format of the number in String
     */
    public static String format32Hex(long num) {
        return String.format("0x%08x", num);
    }

    /**
     * Convert 32-bit word into printable ASCII
     *
     * @param word Word to format in long
     * @return String representation of the word
     */
    public static String asciiWord(long word) {
        String tStr = "";
        long tVal;
        for(int j = 3; j >= 0; j--) {
            tVal = word >> (8 * j);
            tVal &= 0xFF;
            if(tVal >= 0x21 && tVal <= 0x7E)
                tStr += (char) tVal + (j == 0 ? "" : " ");
            else
                tStr += "." + (j == 0 ? "" : " ");
        }

        return tStr;
    }

    /**
     * Get the configuration directory path
     *
     * @return Configuration directory path in String
     */
    public static String getConfDir() {
        File confDir = new File(System.getProperty("user.home") + "/.plp");
        if(!confDir.exists())
            confDir.mkdir();

        return confDir.getAbsolutePath();
    }

    /**
     * Get the temporary directory path
     *
     * @return Temporary directory path in String
     */
    public static String getTmpDir() {
        checkCreateTempDirectory();
        return getConfDir() + "/tmp";
    }

    /**
     * Check if the configuration directory exists.
     *
     * @return True if yes, false otherwise
     */
    public static boolean confDirExists() {
        java.io.File cfgDir = new java.io.File(getConfDir());
        return (cfgDir.exists() && cfgDir.isDirectory());
    }

    /**
     * Get host OS ID
     *
     * @param print Print host OS information to message out
     * @return OS code (See Constants.PLP_OS_*)
     */
    public static int getOS(boolean print) {
        String osIdentifier = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");

        if(print) Msg.M(osIdentifier + " " + osArch);

        if(osIdentifier.equals("Linux")) {
            if(osArch.equals("x86") || osArch.equals("i386"))
                return Constants.PLP_OS_LINUX_32;
            else if(osArch.equals("x86_64") || osArch.equals("amd64"))
                return Constants.PLP_OS_LINUX_64;
        }
        else if(osIdentifier.startsWith("Windows")) {
            if(osArch.equals("x86") || osArch.equals("i386"))
                return Constants.PLP_OS_WIN_32;
            else if(osArch.equals("x86_64") || osArch.equals("amd64"))
                return Constants.PLP_OS_WIN_64;
        }

        return Constants.PLP_OS_UNKNOWN;
    }

    /**
     * Check whether the host OS is GNU/Linux
     *
     * @return True for GNU/Linux, false otherwise
     */
    public static boolean isHostLinux() {
        return (getOS(false) == Constants.PLP_OS_LINUX_32 ||
                getOS(false) == Constants.PLP_OS_LINUX_64);
    }

    /**
     * Attach a keylistener that will hide the frame when the escape key is
     * pressed by the user
     *
     * @param frame Reference to the frame to attach the listener to
     */
    public static void attachHideOnEscapeListener(final javax.swing.JFrame frame) {
        javax.swing.KeyStroke escapeKeyStroke = javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false);
        javax.swing.Action escapeAction = new javax.swing.AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               frame.setVisible(false);
            }
        };

        frame.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        frame.getRootPane().getActionMap().put("ESCAPE", escapeAction);
    }

    /**
     * Attach a keylistener that will hide the dialog when the escape key is
     * pressed by the user
     *
     * @param frame Reference to the dialog to attach the listener to
     */
    public static void attachHideOnEscapeListener(final javax.swing.JDialog frame) {
        javax.swing.KeyStroke escapeKeyStroke = javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false);
        javax.swing.Action escapeAction = new javax.swing.AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               frame.setVisible(false);
            }
        };

        frame.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        frame.getRootPane().getActionMap().put("ESCAPE", escapeAction);
    }

    /**
     * Attach a keylistener that will bring up the debug console
     *
     * @param frame Reference to the frame to attach the listener to
     */
    public static void attachDebugConsoleMagicComboListener(final javax.swing.JFrame frame, final plptool.gui.ProjectDriver plp, final boolean show) {
        javax.swing.KeyStroke consoleKeyStroke = javax.swing.KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_BACK_SLASH,
                java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK);
        javax.swing.Action consoleAction = new javax.swing.AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(plptool.gui.PLPToolApp.con == null)
                    plptool.gui.PLPToolApp.con = new plptool.gui.frames.ConsoleFrame(plp);
                if(show)
                    plptool.gui.PLPToolApp.con.setVisible(true);
                else
                    plptool.gui.PLPToolApp.con.setVisible(false);
            }
        };

        frame.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(consoleKeyStroke, "CONSOLE");
        frame.getRootPane().getActionMap().put("CONSOLE", consoleAction);
    }

    /**
     * Setup Classroom extras
     */
    public static void setupClassroomExtras(plptool.gui.ProjectDriver plp) {
        javax.swing.JOptionPane.showMessageDialog(plp.g_dev, "Not implemented yet!");
    }

    /**
     * Attempt to download a JAR file from an URL. Also provides an option of
     * loading and applying the module's manifest immediately after.
     *
     * @param URL Location of the JAR file
     * @param plp Reference to the ProjectDriver instance
     * @param load Should the method load the module after downloading
     */
    public static boolean downloadJARForAutoload(
            String URL, plptool.gui.ProjectDriver plp, boolean load) {
       
        File autoloadDir = new File(getConfDir() + "/autoload");
        if(!autoloadDir.exists())
            autoloadDir.mkdir();
        int ret = 0;
        if(plp != null && plp.g())
            ret = javax.swing.JOptionPane.showConfirmDialog(plp.g_dev,
                          "Attempt to download " + URL +
                          " and cache it in user's directory?",
                          "Download JAR Module",
                          javax.swing.JOptionPane.YES_NO_OPTION);
        if(plp == null || !plp.g() ||
                ret == javax.swing.JOptionPane.YES_OPTION) {
            String fileName = "";
            try {
                Msg.I("Downloading " + URL + "...", null);
                java.net.URL jar = new java.net.URL(URL);
                java.nio.channels.ReadableByteChannel rbc = java.nio.channels.Channels.newChannel(jar.openStream());
                String[] tokens = jar.getFile().split("/");
                fileName = tokens[tokens.length-1];
                java.io.FileOutputStream fos = new java.io.FileOutputStream(
                        PLPToolbox.getConfDir() + "/autoload/" + fileName);
                fos.getChannel().transferFrom(rbc, 0, 1 << 24);
                fos.close();
                if(!DynamicModuleFramework.checkForManifest(
                        PLPToolbox.getConfDir() + "/autoload/" + fileName)) {
                    Msg.E("Downloaded JAR file does not contain plp.manifest",
                          Constants.PLP_DMOD_NO_MANIFEST_FOUND, null);
                    (new File(PLPToolbox.getConfDir() + "/autoload/" + fileName)).delete();
                    return false;
                }
            } catch(Exception e) {
                Msg.E("Failed to fetch " + URL + ".",
                        Constants.PLP_GENERIC_ERROR, null);
                if(Constants.debugLevel >= 2)
                    e.printStackTrace();
                return false;
            }
            if(load) {
                String searchPath = PLPToolbox.getConfDir() + "/autoload/" + fileName;
                String[] manifest = DynamicModuleFramework.loadJarWithManifest(searchPath);
                if(manifest != null)
                    DynamicModuleFramework.applyManifestEntries(
                            PLPToolbox.getConfDir() + "/autoload/" + fileName,
                            manifest, plp);
            }
            return true;
        }
        return false;
    }

    /**
     * Read a line of string from standard input
     *
     * @return A line of string if successful, null otherwise
     */
    public static String readLine() {
        try {
            java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            return stdin.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Copy a file from source to destination
     *
     * @param src Path to source file
     * @param dest Path to destination file
     * @return PLP_OK on successful copy, error code otherwise
     */
    public static int copyFile(String src, String dest) {
        File s = new File(src);
        File d = new File(dest);

        try {
            FileInputStream in = new FileInputStream(s);
            FileOutputStream out = new FileOutputStream(d);

            byte[] buf = new byte[Constants.DEFAULT_IO_BUFFER_SIZE];

            int readBytes;
            while((readBytes = in.read(buf)) != -1) {
                out.write(buf, 0, readBytes);
            }
            out.close();
            in.close();
            
        } catch(IOException e) {
            return Msg.E("File copy error: '" + src + "' to '" + dest +
                    "'." + (Constants.debugLevel >= 2 ? "Exception: " + e : "")
                    , Constants.PLP_GENERAL_IO_ERROR, null);
        }

        return Constants.PLP_OK;
    }

    /**
     * Write a new file filled with the provided data in string
     *
     * @param data Data to be written
     * @param file Destination file
     * @return PLP_OK on successful write, IO_WRITE_ERROR otherwise
     */
    public static int writeFile(String data, String file) {
        File d = new File(file);

        try {
            FileWriter out = new FileWriter(d);
            out.write(data);
            out.close();
        } catch(IOException e) {
            return Msg.E("File write error: '" + file +
                    "'." + (Constants.debugLevel >= 2 ? "Exception: " + e : "")
                    , Constants.PLP_IO_WRITE_ERROR, null);
        }

        return Constants.PLP_OK;
    }

    /**
     * Write a new file filled with the provided data in byte array
     *
     * @param data Data to be written
     * @param file Destination file
     * @return PLP_OK on successful write, IO_WRITE_ERROR otherwise
     */
    public static int writeFile(byte[] data, String file) {
        File d = new File(file);

        try {
            FileOutputStream out = new FileOutputStream(d);
            out.write(data);
            out.close();

        } catch(IOException e) {
            return Msg.E("File write error: '" + file +
                    "'." + (Constants.debugLevel >= 2 ? "Exception: " + e : "")
                    , Constants.PLP_IO_WRITE_ERROR, null);
        }

        return Constants.PLP_OK;
    }

    /**
     * Read a file and return the contents as string.
     *
     * @param path Path to the file to read from
     * @return Contents of the file as string, or null if there was an error
     */
    public static String readFileAsString(String path) {
        String data = null;
        try {
            FileInputStream in = new FileInputStream(new File(path));
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            String line;

            while((line = r.readLine()) != null) {
                if(data == null)
                    data = "";
                data += line + "\n";
            }

            r.close();
            in.close();

        } catch(Exception e) {
            Msg.E("File open error: '" + path +
                    "'." + (Constants.debugLevel >= 2 ? "Exception: " + e : "")
                    , Constants.PLP_GENERAL_IO_ERROR, null);
            return null;
        }

        return data;
    }

    /**
     * Read a file and return it as a byte array
     *
     * @param path Path to the file to read from
     * @return Contents of the file as byte array, or null if there was an error
     */
    public static byte[] readFile(String path) {
        byte[] data = new byte[0];
        int readBytes;
        try {
            FileInputStream in = new FileInputStream(new File(path));
            byte[] buf = new byte[Constants.DEFAULT_IO_BUFFER_SIZE];
            byte[] copyBuf;

            while((readBytes = in.read(buf)) != -1) {
                copyBuf = new byte[data.length+readBytes];
                System.arraycopy(buf, 0, copyBuf, data.length, readBytes);
                System.arraycopy(data, 0, copyBuf, 0, data.length);
                data = copyBuf;
            }

            in.close();

        } catch(Exception e) {
            Msg.E("File open error: '" + path +
                    "'." + (Constants.debugLevel >= 2 ? "Exception: " + e : "")
                    , Constants.PLP_GENERAL_IO_ERROR, null);
            return null;
        }
        return data;
    }

    /**
     * Extract a file from a jar file
     *
     * @param jar Path to JAR file to extract the file from
     * @param entry Name of the file to extract
     * @param dest Destination path to write the file to
     * @return PLP_OK on successful copy, error code otherwise
     */
    public static int copyFromJar(String jar, String entry, String dest) {
        try {
            JarFile jarFile = new JarFile(jar);
            JarEntry jarEntry = jarFile.getJarEntry(entry);
            if(jarEntry == null)
                return Msg.E("copyFromJar: " +
                        "Can not find entry: '" + entry +
                        "' in '" + jar + "'.",
                    Constants.PLP_GENERAL_IO_ERROR, null);
            File destFile = new File(dest);
            if(destFile.exists())
                return Msg.E("copyFromJar: " + "'" + dest + "' exists.",
                        Constants.PLP_GENERAL_IO_ERROR, null);
            InputStreamReader in = new InputStreamReader(jarFile.getInputStream(jarEntry));
            FileWriter out = new FileWriter(dest);
            char[] buf = new char[Constants.DEFAULT_IO_BUFFER_SIZE];
            int readLen;
            while((readLen = in.read(buf)) >= 0)
                out.write(buf, 0, readLen);
            out.close();
            in.close();
            jarFile.close();

        } catch(IOException e) {
            return Msg.E("copyFromJar: " +
                    "Jar extract error: '" + entry + "' to '" + dest +
                    "' from '" + jar + "'." +
                    (Constants.debugLevel >= 2 ? "Exception: " + e : "")
                    , Constants.PLP_GENERAL_IO_ERROR, null);
        }

        return Constants.PLP_OK;
    }

    /**
     * Pack a directory into a JAR file
     *
     * @param jar JAR file to create
     * @param dirPath Directory to pack
     * @return PLP_OK on successful packing, error code otherwise
     */
    public static int createJar(String jar, String dirPath) {
        File dir = new File(dirPath);
        int ret;
        if(!dir.exists())
            return Msg.E("'" + dir.getAbsolutePath() + "' does not exist",
                    Constants.PLP_IO_FILE_DOES_NOT_EXIST, null);
        else if(!dir.isDirectory())
            return Msg.E("'" + dir.getAbsolutePath() + "' is not a directory",
                    Constants.PLP_IO_IS_NOT_A_DIRECTORY, null);

        try {
            FileOutputStream fOut = new FileOutputStream(new File(jar));
            JarOutputStream out = new JarOutputStream(fOut);
            ret = addDirToJar(out, dir, dir);
            out.close();
            fOut.close();
            if(ret != Constants.PLP_OK)
                return ret;
        } catch(IOException e) {
            return Msg.E("I/O error while trying to archive '" +
                    dir.getAbsolutePath() + "' into '" + jar + "'",
                    Constants.PLP_GENERAL_IO_ERROR, null);
        }

        return Constants.PLP_OK;
    }

    /**
     * Helper method for the createJar method. This method will recurse into
     * directories and add entries to the JAR file. Entries are formatted
     * with UNIX-style path separator (forward slashes) for compatibility
     * with PLPTool class loader
     *
     * @param out JAR output stream
     * @param dir Directory to recurse into
     * @param root Root directory
     * @return PLP_OK on successful operation, error code otherwise
     */
    private static int addDirToJar(JarOutputStream out, File dir, File root) {
        String entryPath;
        File[] files = dir.listFiles();
        FileInputStream in;
        byte buf[] = new byte[Constants.DEFAULT_IO_BUFFER_SIZE];
        int readBytes;
        int ret;
        if(files != null) {
            for(int i = 0; i < files.length; i++) {
                if(files[i].isDirectory()) {
                    if((ret = addDirToJar(out, files[i], root)) != Constants.PLP_OK)
                        return ret;
                } else {
                    try {
                        in = new FileInputStream(files[i]);
                        entryPath = files[i].getAbsolutePath().replace(root.getAbsolutePath(), "").substring(1);
                        if(File.separatorChar == '\\')
                            entryPath = entryPath.replace("\\", "/");
                        Msg.M("- Adding '" + entryPath + "'...");
                        JarEntry entry = new JarEntry(entryPath);
                        entry.setSize(files[i].length());
                        out.putNextEntry(entry);
                        while((readBytes = in.read(buf)) != -1)
                            out.write(buf, 0, readBytes);
                        out.flush();
                        out.closeEntry();
                    } catch(IOException e) {
                        return Msg.E("I/O error while adding '" +
                                files[i].getAbsolutePath() + "' to JAR archive.",
                                Constants.PLP_GENERAL_IO_ERROR, null);
                    }
                }
            }
        }
        return Constants.PLP_OK;
    }

    /**
     * Check if ~/.plp/tmp exists and create it if it doesn't.
     */
    public static void checkCreateTempDirectory() {
        File temp = new File(getConfDir() + "/tmp");
        if(!temp.exists()) {
            temp.mkdir();
        } else if(temp.exists() && !temp.isDirectory()) {
            Msg.E("Temporary directory creation failed.",
                    Constants.PLP_GENERAL_IO_ERROR, null);
        }
    }

    /**
     * Convert a map to 2-dimensional array
     *
     * @param map Reference to the map object to convert
     * @return 2-dimensional array with keys in the first field and the matching
     * values in the second
     */
    public static Object[][] mapToArray(java.util.Map map) {
        Object[][] mapArray = new Object[map.size()][2];
        Object[] keys = map.keySet().toArray();
        for(int i = 0; i < mapArray.length; i++) {
            mapArray[i][0] = keys[i];
            mapArray[i][1] = map.get(keys[i]);
        }

        return mapArray;
    }

    /**
     * Execute an external program
     *
     * @param program Command to be executed by the Runtime class
     */
    public static void execute(final String program) {
        Msg.M("execute: '" + program + "'");
        Process p;
        try {
            p = Runtime.getRuntime().exec(program);
            p.waitFor();
        } catch(IOException ioe) {
            Msg.E("I/O error while attempting to execute '" + program + "'",
                    Constants.PLP_GENERAL_IO_ERROR, null);
        } catch(InterruptedException ie) {

        }
    }

    /**
     * Bring up the file save dialog
     *
     * @param startPath Starting path to browse from
     * @param filter Filter to be used with the dialog
     * @return The file object on successful browsing, null otherwise
     */
    public static File saveFileDialog(String startPath, javax.swing.filechooser.FileFilter filter) {
        final javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        fc.setFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setCurrentDirectory(new File(startPath));

        int retVal = fc.showSaveDialog(null);

        if(retVal == javax.swing.JFileChooser.APPROVE_OPTION)
            return fc.getSelectedFile();
        return null;
    }

    /**
     * Bring up the file open dialog
     *
     * @param startPath Starting path to browse from
     * @param filter Filter to be used with the dialog
     * @return The file object on successful browsing, null otherwise
     */
    public static File openFileDialog(String startPath, javax.swing.filechooser.FileFilter filter) {
        final javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        fc.setFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(true);
        fc.setCurrentDirectory(new File(startPath));

        int retVal = fc.showOpenDialog(null);

        if(retVal == javax.swing.JFileChooser.APPROVE_OPTION)
            return fc.getSelectedFile();
        return null;
    }

    /**
     * Create a filter for JFileChooser. Extensions are given in a comma-
     * delimited list with no preceding period. e.g. "txt,text" will create
     * a filter for files with .txt or .text extension
     *
     * @param extensions Comma-delimited list of extensions to filter for
     * @param description Description of the filter to be shown in the
     * filechooser
     * @return An instance of the requested FileFilter
     */
    public static javax.swing.filechooser.FileFilter createFileFilter(final String extensions, final String description) {
        javax.swing.filechooser.FileFilter filter = new javax.swing.filechooser.FileFilter() {

            @Override
            public boolean accept(File f) {
                if(f.isDirectory())
                    return true;

                String[] extensionTokens = extensions.split(",");
                for(int i = 0; i < extensionTokens.length; i++) {
                    if(f.getAbsolutePath().endsWith("." + extensionTokens[i]))
                        return true;
                }

                return false;
            }

            @Override
            public String getDescription() {
                return description;
            }
        };

        return filter;
    }

    // 0, 1, 2, 3, 4, 5   index = 3 ('3') len = 6
    // 0, 1, 2, x, x
    // 0, 1, 2, 4, 5      index+1 = 4 delta_len = 2

    /**
     * Consume an argument and return a string array without the consumed
     * argument
     *
     * @param args Input string array
     * @param index Index of element to gobble
     * @return String array without the gobbled element
     */
    public static String[] gobble(String[] args, int index) {
        String[] temp = new String[args.length-1];
        System.arraycopy(args, 0, temp, 0, index);
        System.arraycopy(args, index+1, temp, index, args.length-index-1);
        return temp;
    }

    /**
     * Transform a string with embedded escapes to a character array
     *
     * @param str String to trasnform
     * @param escapes Escape character strings. For example, to escape newlines
     * with
     * @return
     */
    public static char[] parseStringAsChars(String str, String[]... escapes) {


        return null;
    }

    /**
     * Copy a string to the system clipboard
     *
     * @param str String to copy
     */
    public static void copy(String str) {
        try {
            java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                    new java.awt.datatransfer.StringSelection(str), null);
        } catch(Exception e) {
            Msg.W("clipboard copy failed.", null);
        }
    }
}

