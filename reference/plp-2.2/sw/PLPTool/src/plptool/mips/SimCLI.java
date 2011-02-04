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

package plptool.mips;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import plptool.PLPMsg;
import plptool.PLPToolbox;
import plptool.Constants;

/**
 * PLPTool command line simulator interface for the MIPS simulation core
 *
 * @author wira
 */
public class SimCLI {
    
    static boolean silent = false;

    public static void simCLCommand(String input, plptool.gui.ProjectDriver plp) {
        if(plp.g())
            plp.g_err.clearError();

        SimCore core = (SimCore) plp.sim;
        Asm asm = (Asm) plp.asm;
        plptool.mods.IORegistry ioReg = plp.ioreg;

        String tokens[] = input.split(" ");
        if(input.equals("version")) {
            PLPMsg.M(plptool.Constants.versionString);
        }
        if(input.equals("i")) {
        }
        else if(input.equals("s")) {
            if(core.step() != Constants.PLP_OK)
                 PLPMsg.E("Simulation is stale. Please reset.",
                          Constants.PLP_SIM_STALE, null);
            else if(!silent) {
                PLPMsg.M("");
                core.wb_stage.printinstr();
                core.mem_stage.printinstr();
                core.ex_stage.printinstr();
                core.id_stage.printinstr();
                core.printfrontend();
                PLPMsg.M("-------------------------------------");
            }
        }

        else if(tokens[0].equals("s")) {
            if(tokens.length != 2) {
                PLPMsg.M("Usage: s <number of instructions>");
            }
            else {
                int steps = PLPToolbox.parseNumInt(tokens[1]);
                long time = 0;
                if(steps > Constants.PLP_LONG_SIM) {
                    if(!silent) {
                        PLPMsg.M("This might take a while, turning on silent mode.");
                        silent = true;
                    }
                    time = System.currentTimeMillis();
                }
                for(int i = 0; i < steps; i++) {
                    if(core.step() != Constants.PLP_OK)
                    PLPMsg.E("Simulation is stale. Please reset.",
                             Constants.PLP_SIM_STALE, null);
                    else if(!silent) {
                        PLPMsg.M("");
                        core.wb_stage.printinstr();
                        core.mem_stage.printinstr();
                        core.ex_stage.printinstr();
                        core.id_stage.printinstr();
                        core.printfrontend();
                        PLPMsg.M("-------------------------------------");
                    }
                }
                if(steps > Constants.PLP_LONG_SIM)
                    PLPMsg.M("That took " + (System.currentTimeMillis() - time) + " milliseconds.");
            }
        }
        else if(input.equals("r")) {
            core.reset();
            core.printfrontend();
        }
        else if(input.equals("pram")) {

            PLPMsg.M("\nMain memory listing");
            PLPMsg.M("===================");
            core.memory.printAll(core.pc.eval());
        }
        else if(tokens[0].equals("pram")) {
            if(tokens.length != 2) {
                PLPMsg.M("Usage: pram <address>");
            }
            else {
                core.memory.print(PLPToolbox.parseNum(tokens[1]));
            }
        }
        else if(input.equals("preg")) {
            long data;
            PLPMsg.M("\nRegisters listing");
            PLPMsg.M("=================");
            for(int j = 0; j < 32; j++) {
                data = (Long) core.regfile.read(j);
                PLPMsg.M(j + "\t" +
                                   String.format("%08x", data) + "\t" +
                                   PLPToolbox.asciiWord(data));
            }
        }
        else if(tokens[0].equals("preg")) {
            if(tokens.length != 2) {
                PLPMsg.M("Usage: preg <address>");
            }
            else {
                long addr = PLPToolbox.parseNum(tokens[1]);
                core.regfile.print(addr);
            }
        }
        else if(input.equals("pfd")) {
            PLPMsg.M("\nFrontend / fetch stage state");
            PLPMsg.M("============================");
            core.printfrontend();
        }
        else if(input.equals("pprg")) {
            PLPMsg.M("\nProgram Listing");
            PLPMsg.M("===============");
            core.printProgram(core.pc.eval());
        }
        else if(input.equals("pasm")) {
            Formatter.prettyPrint(asm);
        }
        else if(input.equals("pinstr")) {
            PLPMsg.M("\nIn-flight instructions");
            PLPMsg.M("======================");
            core.wb_stage.printinstr();
            core.mem_stage.printinstr();
            core.ex_stage.printinstr();
            core.id_stage.printinstr();
            core.printfrontend();
        }
        else if(tokens[0].equals("wpc")) {
            if(tokens.length != 2) {
                PLPMsg.M("Usage: wpc <address>");
            }
            else {
                core.softreset();
                core.pc.write(Asm.sanitize32bits(tokens[1]));
                core.printfrontend();
            }
        }
        else if(tokens[0].equals("w")) {
            if(tokens.length != 3) {
                PLPMsg.M("Usage: w <address> <data>");
            }
            else {
                if(core.memory.write(Asm.sanitize32bits(tokens[1]),
                                  Asm.sanitize32bits(tokens[2]), false) == Constants.PLP_OK)
                  core.memory.print(Asm.sanitize32bits(tokens[1]));
            }
        }
        else if(tokens[0].equals("wbus")) {
            if(tokens.length != 3) {
                PLPMsg.M("Usage: wbus <address> <data>");
            }
            else {
                core.bus.write(Asm.sanitize32bits(tokens[1]),
                                  Asm.sanitize32bits(tokens[2]), false);
            }
        }
        else if(tokens[0].equals("rbus")) {
            if(tokens.length != 2) {
                PLPMsg.M("Usage: rbus <address>");
            }
            else {
                long addr = Asm.sanitize32bits(tokens[1]);
                Object ret = core.bus.read(addr);
                if(ret != null) {
                    long value = (Long) ret;
                    PLPMsg.M(String.format("0x%08x=", addr) +
                                       String.format("0x%08x", value));
                }
            }
        }
        else if(input.equals("listio")) {
            for(int i = 0; i < core.bus.getNumOfMods(); i++)
                PLPMsg.M(i + ": " +
                                   String.format("0x%08x", core.bus.getModStartAddress(i)) + "-" +
                                   String.format("0x%08x", core.bus.getModEndAddress(i)) + " " +
                                   core.bus.introduceMod(i) +
                                   (core.bus.getEnabled(i) ? " (enabled)" : " (disabled)"));
        }
        else if(input.equals("enableio")) {
            core.bus.enableAllModules();
        }
        else if(tokens[0].equals("enableio")) {
            if(tokens.length != 2) {
                PLPMsg.M("Usage: enableio <index>");
            }
            else {
                core.bus.enableMod((int) PLPToolbox.parseNum(tokens[1]));
            }
        }
        else if(input.equals("evalio")) {
            core.bus.eval();
        }
        else if(tokens[0].equals("evalio")) {
            if(tokens.length != 2) {
                PLPMsg.M("Usage: evalio <index>");
            }
            else {
                core.bus.eval((int) PLPToolbox.parseNum(tokens[1]));
            }
        }
        else if(input.equals("disableio")) {
            core.bus.disableAllModules();
        }
        else if(tokens[0].equals("disableio")) {
            if(tokens.length != 2) {
                PLPMsg.M("Usage: disableio <index>");
            }
            else {
                core.bus.disableMod((int) PLPToolbox.parseNum(tokens[1]));
            }
        }
        else if(tokens[0].equals("cleario")) {
            if(tokens.length != 2) {
                PLPMsg.M("Usage: cleario <index>");
            }
            else {
                core.bus.clearModRegisters((int) PLPToolbox.parseNum(tokens[1]));
            }
        }
        else if(input.equals("listmods")) {
            PLPMsg.M("Registered modules:");
            Object modInfo[][] = ioReg.getAvailableModulesInformation();
            for(int i = 0; i < modInfo.length; i++) {
                PLPMsg.M(i + ": " + modInfo[i][0] + " - " + modInfo[i][3]);
            }
        }
        else if(input.equals("attachedmods")) {
            PLPMsg.M("Attached modules:");
            Object mods[] = ioReg.getAttachedModules();
            for(int i = 0; i < mods.length; i++) {
                PLPMsg.M(i + ": "
                        + ((plptool.PLPSimBusModule)mods[i]).introduce() +
                        " - position in bus: " + ioReg.getPositionInBus(i));
            }
        }
        else if(input.equals("listpresets")) {
            PLPMsg.M("Registered presets:");
            Object[][] presets = plptool.mods.Presets.presets;
            for(int i = 0; i < presets.length; i++) {
                PLPMsg.M(i + ": " + presets[i][0]);
            }
        }
        else if(tokens[0].equals("loadpreset")) {
            if(tokens.length != 2) {
                PLPMsg.M("Usage: loadpreset <index>");
            }
            else {
                plptool.mods.IORegistry.loadPreset(PLPToolbox.parseNumInt(tokens[1]), plp);
            }
        }
        else if(tokens[0].equals("addmod")) {
            if(tokens.length != 4) {
                PLPMsg.M("Usage: addmod <mod ID> <address> <register file size>");
            }
            else {
                ioReg.attachModuleToBus((int) PLPToolbox.parseNum(tokens[1]),
                                        PLPToolbox.parseNum(tokens[2]),
                                        PLPToolbox.parseNum(tokens[3]),
                                        core, null);
            }
        }
        else if(tokens[0].equals("rmmod")) {
            if(tokens.length != 2) {
                PLPMsg.M("Usage: rmmod <mod index in the REGISTRY>");
            }
            else {
                ioReg.removeModule((int) PLPToolbox.parseNum(tokens[1]), core);
            }
        }

        else if(tokens[0].equals("j")) {
            if(tokens.length != 2) {
                PLPMsg.M("Usage: j <address>");
            }
            else {
                core.pc.write(Asm.sanitize32bits(tokens[1]));
                core.printfrontend();
            }
        }
        else if(tokens[0].equals("asm")) {
            if(tokens.length < 3) {
                PLPMsg.M("Usage: asm <address> <in-line assembly>");
            }
            else {
                String iAsm = "";
                long addr;
                for(int j = 2; j < tokens.length; j++)
                    iAsm += tokens[j] + " ";
                Asm inlineAsm = new Asm(iAsm, "PLPSimCL inline asm");
                if(inlineAsm.preprocess(0) == Constants.PLP_OK)
                    inlineAsm.assemble();
                if(inlineAsm.isAssembled()) {
                    PLPMsg.M("\nCode injected:");
                    PLPMsg.M("==============");
                    for(int j = 0; j < inlineAsm.getObjectCode().length; j++) {
                        addr = PLPToolbox.parseNum(tokens[1]) + 4 * j;
                        core.memory.write(addr, inlineAsm.getObjectCode()[j], (inlineAsm.isInstruction(j) == 0) ? true : false);
                        PLPMsg.M(String.format("%08x", addr) +
                                           "   " + PLPToolbox.asciiWord(inlineAsm.getObjectCode()[j]) +
                                           "  " + MIPSInstr.format(inlineAsm.getObjectCode()[j]));
                    }
                }
            }
        }
        else if(input.equals("pvars")) {
            PLPMsg.M("\nOutput side values of pipeline stages");
            PLPMsg.M("=====================================");
            core.wb_stage.printvars();
            core.mem_stage.printvars();
            core.ex_stage.printvars();
            core.id_stage.printvars();

        }
        else if(input.equals("pnextvars")) {
            PLPMsg.M("\nInput side values of pipeline registers");
            PLPMsg.M("=======================================");
            core.wb_stage.printnextvars();
            core.mem_stage.printnextvars();
            core.ex_stage.printnextvars();
            core.id_stage.printnextvars();
        }
        else if(input.equals("silent")) {
            if(silent) {
                silent = false;
                PLPMsg.M("Silent mode off.");
            } else {
                silent = true;
                PLPMsg.M("Silent mode on.");
            }
        }
        else if(input.equals("mod_forwarding")) {
            PLPMsg.M("EX->EX R-type: " + core.forwarding.ex_ex_rtype);
            PLPMsg.M("EX->EX I-type: " + core.forwarding.ex_ex_itype);
            PLPMsg.M("MEM->EX R-type: " + core.forwarding.mem_ex_rtype);
            PLPMsg.M("MEM->EX I-type: " + core.forwarding.mem_ex_itype);
            PLPMsg.M("MEM->EX LW-use: " + core.forwarding.mem_ex_lw);
        }
        else if(input.equals("flags")) {
            long f = core.getFlags();
            if((f & Constants.PLP_SIM_FWD_EX_EX_ITYPE) == Constants.PLP_SIM_FWD_EX_EX_ITYPE)
                PLPMsg.M("PLP_SIM_FWD_EX_EX_ITYPE");
            if((f & Constants.PLP_SIM_FWD_EX_EX_RTYPE) == Constants.PLP_SIM_FWD_EX_EX_RTYPE)
                PLPMsg.M("PLP_SIM_FWD_EX_EX_RTYPE");
            if((f & Constants.PLP_SIM_FWD_MEM_EX_RTYPE) == Constants.PLP_SIM_FWD_MEM_EX_RTYPE)
                PLPMsg.M("PLP_SIM_FWD_MEM_EX_RTYPE");
            if((f & Constants.PLP_SIM_FWD_MEM_EX_ITYPE) == Constants.PLP_SIM_FWD_MEM_EX_ITYPE)
                PLPMsg.M("PLP_SIM_FWD_MEM_EX_ITYPE");
            if((f & Constants.PLP_SIM_FWD_MEM_EX_LW) == Constants.PLP_SIM_FWD_MEM_EX_LW)
                PLPMsg.M("PLP_SIM_FWD_MEM_EX_LW");
            if((f & Constants.PLP_SIM_FWD_MEM_MEM) == Constants.PLP_SIM_FWD_MEM_MEM)
                PLPMsg.M("PLP_SIM_FWD_MEM_MEM");
            if((f & Constants.PLP_SIM_IF_STALL_SET) == Constants.PLP_SIM_IF_STALL_SET)
                PLPMsg.M("PLP_SIM_IF_STALL_SET");
            if((f & Constants.PLP_SIM_ID_STALL_SET) == Constants.PLP_SIM_ID_STALL_SET)
                PLPMsg.M("PLP_SIM_ID_STALL_SET");
            if((f & Constants.PLP_SIM_EX_STALL_SET) == Constants.PLP_SIM_EX_STALL_SET)
                PLPMsg.M("PLP_SIM_EX_STALL_SET");
            if((f & Constants.PLP_SIM_MEM_STALL_SET) == Constants.PLP_SIM_MEM_STALL_SET)
                PLPMsg.M("PLP_SIM_MEM_STALL_SET");

        }
        else if(input.equals("jvm")) {
            Runtime runtime = Runtime.getRuntime();
            PLPMsg.M("Free JVM memory:     " + runtime.freeMemory());
            PLPMsg.M("Total JVM memory:    " + runtime.totalMemory());
            PLPMsg.M("Total - Free (Used): "  + (runtime.totalMemory() -  runtime.freeMemory()));
        }
        else if(input.toLowerCase().equals("wira sucks")) {
            PLPMsg.M("No, he doesn't.");
        }
        else if(input.equals("help sim")) {
            simCLHelp(1);
        }
        else if(input.equals("help print")) {
            simCLHelp(2);
        }
        else if(input.equals("help bus")) {
            simCLHelp(3);
        }
        else if(input.equals("help mods")) {
            simCLHelp(4);
        }
        else if(input.equals("help misc")) {
            simCLHelp(5);
        }
        else {
            simCLHelp(0);
        }

        PLPMsg.M("");

        if(PLPMsg.lastError != 0 && plp.g())
            plp.g_err.setError(PLPMsg.lastError);

        PLPMsg.m(String.format("%08x", core.getFlags()) +
                             " " + core.getinstrcount() +
                             " sim > ");
    }

    public static void simCL(plptool.gui.ProjectDriver plp) {
        try {

        String input;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        plp.ioreg = new plptool.mods.IORegistry();
        plp.sim = new SimCore((plptool.mips.Asm) plp.asm, plp.asm.getAddrTable()[0], 0x1000000);
        plp.sim.setStartAddr(plp.asm.getAddrTable()[0]);
        plp.sim.reset();
        ((plptool.mips.SimCore)plp.sim).printfrontend();
        plp.sim.bus.enableAllModules();
        PLPMsg.M("Simulation core initialized with nigh-infinite RAM.");
        PLPMsg.M("Reset vector: " + String.format("0x%08x", plp.asm.getAddrTable()[0]));
        PLPMsg.m(String.format("\n%08x", plp.sim.getFlags()) +
                             " " + plp.sim.getinstrcount() +
                             " sim > ");

        while(!(input = stdIn.readLine().trim()).equals("q"))
           simCLCommand(input, plp);

        plp.ioreg.removeAllModules(plp.sim);
        PLPMsg.M("See ya!");

        } catch(Exception e) {
            System.err.println(e);
        }
    }

    public static void simCLHelp(int commandGroup) {
        switch(commandGroup) {
            case 0:
                PLPMsg.M("\nPLPTool MIPS Command Line Interface Help.");
                PLPMsg.M("\n help sim\n\tList general simulator commands.");
                PLPMsg.M("\n help print\n\tList commands to print various simulator information to screen.");
                PLPMsg.M("\n help bus\n\tList I/O bus commands.");
                PLPMsg.M("\n help mods\n\tList PLP modules commands.");
                PLPMsg.M("\n help misc\n\tMiscellaneous commands.");

                break;

            case 1:
                PLPMsg.M("\nGeneral Simulation Control.");
                PLPMsg.M("\n s <steps> ..or.. s\n\tAdvance <steps> number of cycles. Step 1 cycle if no argument is given.");
                PLPMsg.M("\n r\n\tReset simulated CPU (clears memory elements and reloads program).");
                PLPMsg.M("\n wpc <address>\n\tOverwrite program counter with <address>.");
                PLPMsg.M("\n j <address>\n\tJump to <address>.");
                PLPMsg.M("\n w <address> <value>\n\tWrite <value> to memory at <address>.");
                PLPMsg.M("\n flags\n\tPrint out simulation flags that are set.");

                break;

            case 2:
                PLPMsg.M("\nPrint information to screen.");
                PLPMsg.M("\n pinstr\n\tPrint instructions currently in-flight.");
                PLPMsg.M("\n pvars\n\tPrint pipeline registers' values.");
                PLPMsg.M("\n pnextvars\n\tPrint pipeline registers' input values.");
                PLPMsg.M("\n pram <address> ..or.. pram\n\tPrint value of RAM at <address>. Print all if no argument is given.");
                PLPMsg.M("\n preg <address> ..or.. preg\n\tPrint contents of a register or print contents of register file.");
                PLPMsg.M("\n pprg\n\tPrint disassembly of current program loaded in the CPU.");
                PLPMsg.M("\n pasm\n\tPrint program object code.");
                PLPMsg.M("\n pfd\n\tPrint CPU frontend states / IF stage input side values.");

                break;

            case 3:
                PLPMsg.M("\nBus control.");
                PLPMsg.M("\n wbus <address> <value>\n\tWrite <value> to FSB with <address>.");
                PLPMsg.M("\n rbus <address>\n\tIssue read of <addr> to FSB.");
                PLPMsg.M("\n enableio <index> ..or.. enableio\n\tEnable evaluation of I/O device <index>. Enable all if no argument is given.");
                PLPMsg.M("\n disableio <index> ..or.. disableio\n\tDisable evaluation of I/O device <index>. Disable all if no argument is given.");
                PLPMsg.M("\n listio\n\tList I/O modules loaded.");
                PLPMsg.M("\n evalio <index> ..or.. evalio\n\tEvaluate I/O module <index>. Evaluate all if no argument is given.");

                break;

            case 4:
                PLPMsg.M("\nModule management.");
                PLPMsg.M("\n listmods\n\tList avaiable modules in the PLP I/O registry.");
                PLPMsg.M("\n addmod <mod ID> <address> <regfile size>\n\tAttach module with <mod ID> starting at <address> with <regfile size> to the registry and the bus.");
                PLPMsg.M("\n rmmod <index in the REGISTRY>\n\tRemove the module with <index in the REGISTRY> from the registry and the bus.");
                PLPMsg.M("\n attachedmods\n\tList all modules attached to the simulation.");

                break;

            case 5:
                PLPMsg.M("\nMiscellaneous.");
                PLPMsg.M("\n asm <address> <asm>\n\tAssemble <asm> and inject code starting at <address>.");
                PLPMsg.M("\n silent\n\tToggle silent mode (default off).");

                break;
        }
    }
}
