
module inferred_rom(clka, clkb, ena, enb, addra, addrb, doa, dob);
        input clka, clkb;
        input ena, enb;
        input [8:0] addra, addrb;
        output reg [31:0] doa, dob;
        reg [31:0] RAM [511:0];

        always @(posedge clka) begin
                if (ena) begin
                        doa <= RAM[addra];
                end
        end

        always @(posedge clkb) begin
                if (enb) begin
                        dob <= RAM[addrb];
                end
        end

        initial begin
		RAM[0] = 32'h08000042;
		RAM[1] = 32'h00000000;
		RAM[2] = 32'h706c7032;
		RAM[3] = 32'h2e300000;
		RAM[4] = 32'h3c08f060;
		RAM[5] = 32'h03e00008;
		RAM[6] = 32'h8d020000;
		RAM[7] = 32'h3c08f060;
		RAM[8] = 32'h03e00008;
		RAM[9] = 32'had040000;
		RAM[10] = 32'h3c08f060;
		RAM[11] = 32'had000000;
		RAM[12] = 32'h8d090000;
		RAM[13] = 32'h0124502a;
		RAM[14] = 32'h1540fffd;
		RAM[15] = 32'h00000000;
		RAM[16] = 32'h03e00008;
		RAM[17] = 32'h00000000;
		RAM[18] = 32'h3c08f020;
		RAM[19] = 32'h03e00008;
		RAM[20] = 32'had040000;
		RAM[21] = 32'h3c08f020;
		RAM[22] = 32'h03e00008;
		RAM[23] = 32'h8d020000;
		RAM[24] = 32'h3c08f050;
		RAM[25] = 32'h03e00008;
		RAM[26] = 32'h8d020000;
		RAM[27] = 32'h3c08f050;
		RAM[28] = 32'h03e00008;
		RAM[29] = 32'h8d020004;
		RAM[30] = 32'h3c08f000;
		RAM[31] = 32'h8d090004;
		RAM[32] = 32'h31290002;
		RAM[33] = 32'h1009fffd;
		RAM[34] = 32'h00000000;
		RAM[35] = 32'h8d020008;
		RAM[36] = 32'had090000;
		RAM[37] = 32'h03e00008;
		RAM[38] = 32'h00000000;
		RAM[39] = 32'h3c08f000;
		RAM[40] = 32'h8d090004;
		RAM[41] = 32'h31290001;
		RAM[42] = 32'h1009fffd;
		RAM[43] = 32'h00000000;
		RAM[44] = 32'had04000c;
		RAM[45] = 32'had090000;
		RAM[46] = 32'h03e00008;
		RAM[47] = 32'h00000000;
		RAM[48] = 32'h001fc825;
		RAM[49] = 32'h0004c025;
		RAM[50] = 32'h8f040000;
		RAM[51] = 32'h10040009;
		RAM[52] = 32'h00000000;
		RAM[53] = 32'h340f0004;
		RAM[54] = 32'h0c000027;
		RAM[55] = 32'h21efffff;
		RAM[56] = 32'h00042202;
		RAM[57] = 32'h1404fffb;
		RAM[58] = 32'h00000000;
		RAM[59] = 32'h140ffff6;
		RAM[60] = 32'h23180004;
		RAM[61] = 32'h03200008;
		RAM[62] = 32'h00000000;
		RAM[63] = 32'h3c08f010;
		RAM[64] = 32'h03e00008;
		RAM[65] = 32'h8d020000;
		RAM[66] = 32'h0c00001b;
		RAM[67] = 32'h00000000;
		RAM[68] = 32'h00029025;
		RAM[69] = 32'h001298c2;
		RAM[70] = 32'h3415ff00;
		RAM[71] = 32'h34140010;
		RAM[72] = 32'h00152025;
		RAM[73] = 32'h0c000012;
		RAM[74] = 32'h2694ffff;
		RAM[75] = 32'h00132025;
		RAM[76] = 32'h0c00000a;
		RAM[77] = 32'h0015a842;
		RAM[78] = 32'h1414fff9;
		RAM[79] = 32'h00000000;
		RAM[80] = 32'h0c00003f;
		RAM[81] = 32'h34100001;
		RAM[82] = 32'h10100003;
		RAM[83] = 32'h00000000;
		RAM[84] = 32'h08000045;
		RAM[85] = 32'h00000000;
		RAM[86] = 32'h34100061;
		RAM[87] = 32'h34110064;
		RAM[88] = 32'h3412006a;
		RAM[89] = 32'h34130076;
		RAM[90] = 32'h34140066;
		RAM[91] = 32'h0000a825;
		RAM[92] = 32'h0c00001e;
		RAM[93] = 32'h00000000;
		RAM[94] = 32'h00022025;
		RAM[95] = 32'h0c000012;
		RAM[96] = 32'h00000000;
		RAM[97] = 32'h12020014;
		RAM[98] = 32'h1222001a;
		RAM[99] = 32'h12420021;
		RAM[100] = 32'h12420024;
		RAM[101] = 32'h00000000;
		RAM[102] = 32'h0800005c;
		RAM[103] = 32'h00000000;
		RAM[104] = 32'h001fb025;
		RAM[105] = 32'h0c00001e;
		RAM[106] = 32'h00000000;
		RAM[107] = 32'h0c00001e;
		RAM[108] = 32'h0002be00;
		RAM[109] = 32'h00024400;
		RAM[110] = 32'h0c00001e;
		RAM[111] = 32'h02e8b825;
		RAM[112] = 32'h00024200;
		RAM[113] = 32'h0c00001e;
		RAM[114] = 32'h02e8b825;
		RAM[115] = 32'h02e2b825;
		RAM[116] = 32'h02c00008;
		RAM[117] = 32'h00171025;
		RAM[118] = 32'h0c000068;
		RAM[119] = 32'h00000000;
		RAM[120] = 32'h0002a825;
		RAM[121] = 32'h0c000027;
		RAM[122] = 32'h00142025;
		RAM[123] = 32'h0800005c;
		RAM[124] = 32'h00000000;
		RAM[125] = 32'h0c000068;
		RAM[126] = 32'h00000000;
		RAM[127] = 32'haea20000;
		RAM[128] = 32'h26b50004;
		RAM[129] = 32'h0c000027;
		RAM[130] = 32'h00142025;
		RAM[131] = 32'h0800005c;
		RAM[132] = 32'h00000000;
		RAM[133] = 32'h0c000027;
		RAM[134] = 32'h00142025;
		RAM[135] = 32'h02a00008;
		RAM[136] = 32'h00000000;
		RAM[137] = 32'h0c000030;
		RAM[138] = 32'h3c040000;
		RAM[139] = 32'h34840008;
		RAM[140] = 32'h0800005c;
		RAM[141] = 32'h00000000;
		RAM[142] = 32'h00000000;
		RAM[143] = 32'h00000000;
		RAM[144] = 32'h00000000;
		RAM[145] = 32'h00000000;
		RAM[146] = 32'h00000000;
		RAM[147] = 32'h00000000;
		RAM[148] = 32'h00000000;
		RAM[149] = 32'h00000000;
		RAM[150] = 32'h00000000;
		RAM[151] = 32'h00000000;
		RAM[152] = 32'h00000000;
		RAM[153] = 32'h00000000;
		RAM[154] = 32'h00000000;
		RAM[155] = 32'h00000000;
		RAM[156] = 32'h00000000;
		RAM[157] = 32'h00000000;
		RAM[158] = 32'h00000000;
		RAM[159] = 32'h00000000;
		RAM[160] = 32'h00000000;
		RAM[161] = 32'h00000000;
		RAM[162] = 32'h00000000;
		RAM[163] = 32'h00000000;
		RAM[164] = 32'h00000000;
		RAM[165] = 32'h00000000;
		RAM[166] = 32'h00000000;
		RAM[167] = 32'h00000000;
		RAM[168] = 32'h00000000;
		RAM[169] = 32'h00000000;
		RAM[170] = 32'h00000000;
		RAM[171] = 32'h00000000;
		RAM[172] = 32'h00000000;
		RAM[173] = 32'h00000000;
		RAM[174] = 32'h00000000;
		RAM[175] = 32'h00000000;
		RAM[176] = 32'h00000000;
		RAM[177] = 32'h00000000;
		RAM[178] = 32'h00000000;
		RAM[179] = 32'h00000000;
		RAM[180] = 32'h00000000;
		RAM[181] = 32'h00000000;
		RAM[182] = 32'h00000000;
		RAM[183] = 32'h00000000;
		RAM[184] = 32'h00000000;
		RAM[185] = 32'h00000000;
		RAM[186] = 32'h00000000;
		RAM[187] = 32'h00000000;
		RAM[188] = 32'h00000000;
		RAM[189] = 32'h00000000;
		RAM[190] = 32'h00000000;
		RAM[191] = 32'h00000000;
		RAM[192] = 32'h00000000;
		RAM[193] = 32'h00000000;
		RAM[194] = 32'h00000000;
		RAM[195] = 32'h00000000;
		RAM[196] = 32'h00000000;
		RAM[197] = 32'h00000000;
		RAM[198] = 32'h00000000;
		RAM[199] = 32'h00000000;
		RAM[200] = 32'h00000000;
		RAM[201] = 32'h00000000;
		RAM[202] = 32'h00000000;
		RAM[203] = 32'h00000000;
		RAM[204] = 32'h00000000;
		RAM[205] = 32'h00000000;
		RAM[206] = 32'h00000000;
		RAM[207] = 32'h00000000;
		RAM[208] = 32'h00000000;
		RAM[209] = 32'h00000000;
		RAM[210] = 32'h00000000;
		RAM[211] = 32'h00000000;
		RAM[212] = 32'h00000000;
		RAM[213] = 32'h00000000;
		RAM[214] = 32'h00000000;
		RAM[215] = 32'h00000000;
		RAM[216] = 32'h00000000;
		RAM[217] = 32'h00000000;
		RAM[218] = 32'h00000000;
		RAM[219] = 32'h00000000;
		RAM[220] = 32'h00000000;
		RAM[221] = 32'h00000000;
		RAM[222] = 32'h00000000;
		RAM[223] = 32'h00000000;
		RAM[224] = 32'h00000000;
		RAM[225] = 32'h00000000;
		RAM[226] = 32'h00000000;
		RAM[227] = 32'h00000000;
		RAM[228] = 32'h00000000;
		RAM[229] = 32'h00000000;
		RAM[230] = 32'h00000000;
		RAM[231] = 32'h00000000;
		RAM[232] = 32'h00000000;
		RAM[233] = 32'h00000000;
		RAM[234] = 32'h00000000;
		RAM[235] = 32'h00000000;
		RAM[236] = 32'h00000000;
		RAM[237] = 32'h00000000;
		RAM[238] = 32'h00000000;
		RAM[239] = 32'h00000000;
		RAM[240] = 32'h00000000;
		RAM[241] = 32'h00000000;
		RAM[242] = 32'h00000000;
		RAM[243] = 32'h00000000;
		RAM[244] = 32'h00000000;
		RAM[245] = 32'h00000000;
		RAM[246] = 32'h00000000;
		RAM[247] = 32'h00000000;
		RAM[248] = 32'h00000000;
		RAM[249] = 32'h00000000;
		RAM[250] = 32'h00000000;
		RAM[251] = 32'h00000000;
		RAM[252] = 32'h00000000;
		RAM[253] = 32'h00000000;
		RAM[254] = 32'h00000000;
		RAM[255] = 32'h00000000;
		RAM[256] = 32'h00000000;
		RAM[257] = 32'h00000000;
		RAM[258] = 32'h00000000;
		RAM[259] = 32'h00000000;
		RAM[260] = 32'h00000000;
		RAM[261] = 32'h00000000;
		RAM[262] = 32'h00000000;
		RAM[263] = 32'h00000000;
		RAM[264] = 32'h00000000;
		RAM[265] = 32'h00000000;
		RAM[266] = 32'h00000000;
		RAM[267] = 32'h00000000;
		RAM[268] = 32'h00000000;
		RAM[269] = 32'h00000000;
		RAM[270] = 32'h00000000;
		RAM[271] = 32'h00000000;
		RAM[272] = 32'h00000000;
		RAM[273] = 32'h00000000;
		RAM[274] = 32'h00000000;
		RAM[275] = 32'h00000000;
		RAM[276] = 32'h00000000;
		RAM[277] = 32'h00000000;
		RAM[278] = 32'h00000000;
		RAM[279] = 32'h00000000;
		RAM[280] = 32'h00000000;
		RAM[281] = 32'h00000000;
		RAM[282] = 32'h00000000;
		RAM[283] = 32'h00000000;
		RAM[284] = 32'h00000000;
		RAM[285] = 32'h00000000;
		RAM[286] = 32'h00000000;
		RAM[287] = 32'h00000000;
		RAM[288] = 32'h00000000;
		RAM[289] = 32'h00000000;
		RAM[290] = 32'h00000000;
		RAM[291] = 32'h00000000;
		RAM[292] = 32'h00000000;
		RAM[293] = 32'h00000000;
		RAM[294] = 32'h00000000;
		RAM[295] = 32'h00000000;
		RAM[296] = 32'h00000000;
		RAM[297] = 32'h00000000;
		RAM[298] = 32'h00000000;
		RAM[299] = 32'h00000000;
		RAM[300] = 32'h00000000;
		RAM[301] = 32'h00000000;
		RAM[302] = 32'h00000000;
		RAM[303] = 32'h00000000;
		RAM[304] = 32'h00000000;
		RAM[305] = 32'h00000000;
		RAM[306] = 32'h00000000;
		RAM[307] = 32'h00000000;
		RAM[308] = 32'h00000000;
		RAM[309] = 32'h00000000;
		RAM[310] = 32'h00000000;
		RAM[311] = 32'h00000000;
		RAM[312] = 32'h00000000;
		RAM[313] = 32'h00000000;
		RAM[314] = 32'h00000000;
		RAM[315] = 32'h00000000;
		RAM[316] = 32'h00000000;
		RAM[317] = 32'h00000000;
		RAM[318] = 32'h00000000;
		RAM[319] = 32'h00000000;
		RAM[320] = 32'h00000000;
		RAM[321] = 32'h00000000;
		RAM[322] = 32'h00000000;
		RAM[323] = 32'h00000000;
		RAM[324] = 32'h00000000;
		RAM[325] = 32'h00000000;
		RAM[326] = 32'h00000000;
		RAM[327] = 32'h00000000;
		RAM[328] = 32'h00000000;
		RAM[329] = 32'h00000000;
		RAM[330] = 32'h00000000;
		RAM[331] = 32'h00000000;
		RAM[332] = 32'h00000000;
		RAM[333] = 32'h00000000;
		RAM[334] = 32'h00000000;
		RAM[335] = 32'h00000000;
		RAM[336] = 32'h00000000;
		RAM[337] = 32'h00000000;
		RAM[338] = 32'h00000000;
		RAM[339] = 32'h00000000;
		RAM[340] = 32'h00000000;
		RAM[341] = 32'h00000000;
		RAM[342] = 32'h00000000;
		RAM[343] = 32'h00000000;
		RAM[344] = 32'h00000000;
		RAM[345] = 32'h00000000;
		RAM[346] = 32'h00000000;
		RAM[347] = 32'h00000000;
		RAM[348] = 32'h00000000;
		RAM[349] = 32'h00000000;
		RAM[350] = 32'h00000000;
		RAM[351] = 32'h00000000;
		RAM[352] = 32'h00000000;
		RAM[353] = 32'h00000000;
		RAM[354] = 32'h00000000;
		RAM[355] = 32'h00000000;
		RAM[356] = 32'h00000000;
		RAM[357] = 32'h00000000;
		RAM[358] = 32'h00000000;
		RAM[359] = 32'h00000000;
		RAM[360] = 32'h00000000;
		RAM[361] = 32'h00000000;
		RAM[362] = 32'h00000000;
		RAM[363] = 32'h00000000;
		RAM[364] = 32'h00000000;
		RAM[365] = 32'h00000000;
		RAM[366] = 32'h00000000;
		RAM[367] = 32'h00000000;
		RAM[368] = 32'h00000000;
		RAM[369] = 32'h00000000;
		RAM[370] = 32'h00000000;
		RAM[371] = 32'h00000000;
		RAM[372] = 32'h00000000;
		RAM[373] = 32'h00000000;
		RAM[374] = 32'h00000000;
		RAM[375] = 32'h00000000;
		RAM[376] = 32'h00000000;
		RAM[377] = 32'h00000000;
		RAM[378] = 32'h00000000;
		RAM[379] = 32'h00000000;
		RAM[380] = 32'h00000000;
		RAM[381] = 32'h00000000;
		RAM[382] = 32'h00000000;
		RAM[383] = 32'h00000000;
		RAM[384] = 32'h00000000;
		RAM[385] = 32'h00000000;
		RAM[386] = 32'h00000000;
		RAM[387] = 32'h00000000;
		RAM[388] = 32'h00000000;
		RAM[389] = 32'h00000000;
		RAM[390] = 32'h00000000;
		RAM[391] = 32'h00000000;
		RAM[392] = 32'h00000000;
		RAM[393] = 32'h00000000;
		RAM[394] = 32'h00000000;
		RAM[395] = 32'h00000000;
		RAM[396] = 32'h00000000;
		RAM[397] = 32'h00000000;
		RAM[398] = 32'h00000000;
		RAM[399] = 32'h00000000;
		RAM[400] = 32'h00000000;
		RAM[401] = 32'h00000000;
		RAM[402] = 32'h00000000;
		RAM[403] = 32'h00000000;
		RAM[404] = 32'h00000000;
		RAM[405] = 32'h00000000;
		RAM[406] = 32'h00000000;
		RAM[407] = 32'h00000000;
		RAM[408] = 32'h00000000;
		RAM[409] = 32'h00000000;
		RAM[410] = 32'h00000000;
		RAM[411] = 32'h00000000;
		RAM[412] = 32'h00000000;
		RAM[413] = 32'h00000000;
		RAM[414] = 32'h00000000;
		RAM[415] = 32'h00000000;
		RAM[416] = 32'h00000000;
		RAM[417] = 32'h00000000;
		RAM[418] = 32'h00000000;
		RAM[419] = 32'h00000000;
		RAM[420] = 32'h00000000;
		RAM[421] = 32'h00000000;
		RAM[422] = 32'h00000000;
		RAM[423] = 32'h00000000;
		RAM[424] = 32'h00000000;
		RAM[425] = 32'h00000000;
		RAM[426] = 32'h00000000;
		RAM[427] = 32'h00000000;
		RAM[428] = 32'h00000000;
		RAM[429] = 32'h00000000;
		RAM[430] = 32'h00000000;
		RAM[431] = 32'h00000000;
		RAM[432] = 32'h00000000;
		RAM[433] = 32'h00000000;
		RAM[434] = 32'h00000000;
		RAM[435] = 32'h00000000;
		RAM[436] = 32'h00000000;
		RAM[437] = 32'h00000000;
		RAM[438] = 32'h00000000;
		RAM[439] = 32'h00000000;
		RAM[440] = 32'h00000000;
		RAM[441] = 32'h00000000;
		RAM[442] = 32'h00000000;
		RAM[443] = 32'h00000000;
		RAM[444] = 32'h00000000;
		RAM[445] = 32'h00000000;
		RAM[446] = 32'h00000000;
		RAM[447] = 32'h00000000;
		RAM[448] = 32'h00000000;
		RAM[449] = 32'h00000000;
		RAM[450] = 32'h00000000;
		RAM[451] = 32'h00000000;
		RAM[452] = 32'h00000000;
		RAM[453] = 32'h00000000;
		RAM[454] = 32'h00000000;
		RAM[455] = 32'h00000000;
		RAM[456] = 32'h00000000;
		RAM[457] = 32'h00000000;
		RAM[458] = 32'h00000000;
		RAM[459] = 32'h00000000;
		RAM[460] = 32'h00000000;
		RAM[461] = 32'h00000000;
		RAM[462] = 32'h00000000;
		RAM[463] = 32'h00000000;
		RAM[464] = 32'h00000000;
		RAM[465] = 32'h00000000;
		RAM[466] = 32'h00000000;
		RAM[467] = 32'h00000000;
		RAM[468] = 32'h00000000;
		RAM[469] = 32'h00000000;
		RAM[470] = 32'h00000000;
		RAM[471] = 32'h00000000;
		RAM[472] = 32'h00000000;
		RAM[473] = 32'h00000000;
		RAM[474] = 32'h00000000;
		RAM[475] = 32'h00000000;
		RAM[476] = 32'h00000000;
		RAM[477] = 32'h00000000;
		RAM[478] = 32'h00000000;
		RAM[479] = 32'h00000000;
		RAM[480] = 32'h00000000;
		RAM[481] = 32'h00000000;
		RAM[482] = 32'h00000000;
		RAM[483] = 32'h00000000;
		RAM[484] = 32'h00000000;
		RAM[485] = 32'h00000000;
		RAM[486] = 32'h00000000;
		RAM[487] = 32'h00000000;
		RAM[488] = 32'h00000000;
		RAM[489] = 32'h00000000;
		RAM[490] = 32'h00000000;
		RAM[491] = 32'h00000000;
		RAM[492] = 32'h00000000;
		RAM[493] = 32'h00000000;
		RAM[494] = 32'h00000000;
		RAM[495] = 32'h00000000;
		RAM[496] = 32'h00000000;
		RAM[497] = 32'h00000000;
		RAM[498] = 32'h00000000;
		RAM[499] = 32'h00000000;
		RAM[500] = 32'h00000000;
		RAM[501] = 32'h00000000;
		RAM[502] = 32'h00000000;
		RAM[503] = 32'h00000000;
		RAM[504] = 32'h00000000;
		RAM[505] = 32'h00000000;
		RAM[506] = 32'h00000000;
		RAM[507] = 32'h00000000;
		RAM[508] = 32'h00000000;
		RAM[509] = 32'h00000000;
		RAM[510] = 32'h00000000;
		RAM[511] = 32'h00000000;

	end
endmodule
