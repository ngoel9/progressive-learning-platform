/*
    Copyright 2010 David Fritz, Brian Gordon, Wira Mulia

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

/**
 * PLP Serial Programmer abstract class.
 *
 * @author wira
 */
public abstract class PLPSerialProgrammer extends Thread {
    protected plptool.gui.ProjectDriver plp;

    public boolean busy = false;
    public int     progress = 0;

    public PLPSerialProgrammer(plptool.gui.ProjectDriver plp) {
        this.plp = plp;
    }

    @Override
    public void run() {
        try {

        busy = true;
        if(plp.p_port != null)
            programWithAsm();
        close();
        
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    abstract public int connect(String portName, int baudRate) throws Exception;
    abstract public int close();
    abstract public int programWithAsm() throws Exception;
}