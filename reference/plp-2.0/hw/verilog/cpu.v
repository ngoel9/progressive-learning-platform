/*

David Fritz

pipelined mips machine

*/

module cpu(clk, daddr, dout, din, drw, iaddr, iin, rst);
	input clk, rst;
	output [31:0] daddr;
	output [31:0] dout;
	input [31:0] din;
	output drw;
	output [31:0] iaddr;
	input [31:0] iin;

	wire 	[31:0] 	ifid_pc;
	wire		wbid_rfw;
	wire	[4:0]	wbid_rf_waddr;
	wire	[31:0]	wbid_rf_wdata;
	wire	[31:0]	idex_rfa;
	wire	[31:0]	idex_rfb;
	wire	[31:0]	idex_rfbse;
	wire	[4:0]	idex_shamt;
	wire	[5:0]	idex_func;
	wire	[4:0]	idex_rf_waddr;
	wire		idex_c_rfw;
	wire	[1:0]	idex_c_wbsource;
	wire		idex_c_drw;
	wire	[5:0]	idex_c_alucontrol;
	wire		idex_c_j;
	wire		idex_c_b;
	wire		idex_c_jjr;
	wire	[25:0]	idex_jaddr;
	wire	[31:0]	idex_pc;
	wire		exmem_c_rfw;
	wire	[1:0]	exmem_c_wbsource;
	wire		exmem_c_drw;
	wire	[31:0]	exmem_alu_r;
	wire	[31:0]	exmem_rfb;
	wire	[4:0]	exmem_rf_waddr;
	wire	[31:0]	exmem_jalra;
	wire	[31:0]	exif_baddr;
	wire	[31:0]	exif_jaddr;
	wire		exif_b;
	wire		exif_j;
	wire		memwb_c_rfw;
	wire	[1:0]	memwb_c_wbsource;
	wire	[31:0]	memwb_alu_r;
	wire	[4:0]	memwb_rf_waddr;
	wire	[31:0]	memwb_jalra;
	wire	[31:0]	ifid_inst;
	wire	[31:0]	memwb_dmem_in;

	cpu_if  stage_if (rst, clk, iaddr, ifid_pc, exif_j, exif_b, exif_baddr, exif_jaddr, iin, ifid_inst);
	cpu_id  stage_id (rst, clk, ifid_pc, ifid_inst, wbid_rfw, wbid_rf_waddr, wbid_rf_wdata, idex_rfa, idex_rfb, idex_rfbse, idex_shamt, idex_func, idex_rf_waddr, idex_c_rfw, idex_c_wbsource, idex_c_drw, idex_c_alucontrol, idex_c_j, idex_c_b, idex_c_jjr, idex_jaddr, idex_pc);
	cpu_ex  stage_ex (rst, clk, idex_c_rfw, idex_c_wbsource, idex_c_drw, idex_c_alucontrol, idex_c_j, idex_c_b, idex_c_jjr, idex_rfa, idex_rfb, idex_rfbse, idex_shamt, idex_func, idex_rf_waddr, idex_pc, idex_jaddr, exmem_c_rfw, exmem_c_wbsource, exmem_c_drw, exmem_alu_r, exmem_rfb, exmem_rf_waddr, exmem_jalra, exif_baddr, exif_jaddr, exif_b, exif_j);
	cpu_mem stage_mem(rst, clk, exmem_c_rfw, exmem_c_wbsource, exmem_c_drw, exmem_alu_r, exmem_rfb, exmem_rf_waddr, exmem_jalra, memwb_c_rfw, memwb_c_wbsource, memwb_alu_r, dout, memwb_rf_waddr, memwb_jalra, daddr, drw, din, memwb_dmem_in);
	cpu_wb  stage_wb (memwb_c_rfw, memwb_c_wbsource, memwb_alu_r, memwb_dmem_in, memwb_rf_waddr, memwb_jalra, wbid_rfw, wbid_rf_wdata, wbid_rf_waddr);
endmodule 
