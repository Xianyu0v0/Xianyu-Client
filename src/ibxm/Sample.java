/*
 * Decompiled with CFR 0.150.
 */
package ibxm;

public class Sample {
    public String name = "";
    public boolean set_panning;
    public int volume;
    public int panning;
    public int c2_rate = 8363;
    public int relative_note;
    public int fine_tune;
    private int loop_start;
    private int loop_length;
    private short[] sample_data;
    private static final int POINT_SHIFT = 4;
    private static final int POINTS = 16;
    private static final int OVERLAP = 8;
    private static final int INTERP_SHIFT = 11;
    private static final int INTERP_BITMASK = 2047;
    private static final short[] sinc_table;

    static {
        short[] arrs = new short[272];
        arrs[1] = -7;
        arrs[2] = 27;
        arrs[3] = -71;
        arrs[4] = 142;
        arrs[5] = -227;
        arrs[6] = 299;
        arrs[7] = 32439;
        arrs[8] = 299;
        arrs[9] = -227;
        arrs[10] = 142;
        arrs[11] = -71;
        arrs[12] = 27;
        arrs[13] = -7;
        arrs[18] = -5;
        arrs[19] = 36;
        arrs[20] = -142;
        arrs[21] = 450;
        arrs[22] = -1439;
        arrs[23] = 32224;
        arrs[24] = 2302;
        arrs[25] = -974;
        arrs[26] = 455;
        arrs[27] = -190;
        arrs[28] = 64;
        arrs[29] = -15;
        arrs[30] = 2;
        arrs[33] = 6;
        arrs[34] = -33;
        arrs[35] = 128;
        arrs[36] = -391;
        arrs[37] = 1042;
        arrs[38] = -2894;
        arrs[39] = 31584;
        arrs[40] = 4540;
        arrs[41] = -1765;
        arrs[42] = 786;
        arrs[43] = -318;
        arrs[44] = 105;
        arrs[45] = -25;
        arrs[46] = 3;
        arrs[49] = 10;
        arrs[50] = -55;
        arrs[51] = 204;
        arrs[52] = -597;
        arrs[53] = 1533;
        arrs[54] = -4056;
        arrs[55] = 30535;
        arrs[56] = 6977;
        arrs[57] = -2573;
        arrs[58] = 1121;
        arrs[59] = -449;
        arrs[60] = 148;
        arrs[61] = -36;
        arrs[62] = 5;
        arrs[64] = -1;
        arrs[65] = 13;
        arrs[66] = -71;
        arrs[67] = 261;
        arrs[68] = -757;
        arrs[69] = 1916;
        arrs[70] = -4922;
        arrs[71] = 29105;
        arrs[72] = 9568;
        arrs[73] = -3366;
        arrs[74] = 1448;
        arrs[75] = -578;
        arrs[76] = 191;
        arrs[77] = -47;
        arrs[78] = 7;
        arrs[80] = -1;
        arrs[81] = 15;
        arrs[82] = -81;
        arrs[83] = 300;
        arrs[84] = -870;
        arrs[85] = 2185;
        arrs[86] = -5498;
        arrs[87] = 27328;
        arrs[88] = 12263;
        arrs[89] = -4109;
        arrs[90] = 1749;
        arrs[91] = -698;
        arrs[92] = 232;
        arrs[93] = -58;
        arrs[94] = 9;
        arrs[96] = -1;
        arrs[97] = 15;
        arrs[98] = -86;
        arrs[99] = 322;
        arrs[100] = -936;
        arrs[101] = 2343;
        arrs[102] = -5800;
        arrs[103] = 25249;
        arrs[104] = 15006;
        arrs[105] = -4765;
        arrs[106] = 2011;
        arrs[107] = -802;
        arrs[108] = 269;
        arrs[109] = -68;
        arrs[110] = 10;
        arrs[112] = -1;
        arrs[113] = 15;
        arrs[114] = -87;
        arrs[115] = 328;
        arrs[116] = -957;
        arrs[117] = 2394;
        arrs[118] = -5849;
        arrs[119] = 22920;
        arrs[120] = 17738;
        arrs[121] = -5298;
        arrs[122] = 2215;
        arrs[123] = -885;
        arrs[124] = 299;
        arrs[125] = -77;
        arrs[126] = 12;
        arrs[129] = 14;
        arrs[130] = -83;
        arrs[131] = 319;
        arrs[132] = -938;
        arrs[133] = 2347;
        arrs[134] = -5671;
        arrs[135] = 20396;
        arrs[136] = 20396;
        arrs[137] = -5671;
        arrs[138] = 2347;
        arrs[139] = -938;
        arrs[140] = 319;
        arrs[141] = -83;
        arrs[142] = 14;
        arrs[145] = 12;
        arrs[146] = -77;
        arrs[147] = 299;
        arrs[148] = -885;
        arrs[149] = 2215;
        arrs[150] = -5298;
        arrs[151] = 17738;
        arrs[152] = 22920;
        arrs[153] = -5849;
        arrs[154] = 2394;
        arrs[155] = -957;
        arrs[156] = 328;
        arrs[157] = -87;
        arrs[158] = 15;
        arrs[159] = -1;
        arrs[161] = 10;
        arrs[162] = -68;
        arrs[163] = 269;
        arrs[164] = -802;
        arrs[165] = 2011;
        arrs[166] = -4765;
        arrs[167] = 15006;
        arrs[168] = 25249;
        arrs[169] = -5800;
        arrs[170] = 2343;
        arrs[171] = -936;
        arrs[172] = 322;
        arrs[173] = -86;
        arrs[174] = 15;
        arrs[175] = -1;
        arrs[177] = 9;
        arrs[178] = -58;
        arrs[179] = 232;
        arrs[180] = -698;
        arrs[181] = 1749;
        arrs[182] = -4109;
        arrs[183] = 12263;
        arrs[184] = 27328;
        arrs[185] = -5498;
        arrs[186] = 2185;
        arrs[187] = -870;
        arrs[188] = 300;
        arrs[189] = -81;
        arrs[190] = 15;
        arrs[191] = -1;
        arrs[193] = 7;
        arrs[194] = -47;
        arrs[195] = 191;
        arrs[196] = -578;
        arrs[197] = 1448;
        arrs[198] = -3366;
        arrs[199] = 9568;
        arrs[200] = 29105;
        arrs[201] = -4922;
        arrs[202] = 1916;
        arrs[203] = -757;
        arrs[204] = 261;
        arrs[205] = -71;
        arrs[206] = 13;
        arrs[207] = -1;
        arrs[209] = 5;
        arrs[210] = -36;
        arrs[211] = 148;
        arrs[212] = -449;
        arrs[213] = 1121;
        arrs[214] = -2573;
        arrs[215] = 6977;
        arrs[216] = 30535;
        arrs[217] = -4056;
        arrs[218] = 1533;
        arrs[219] = -597;
        arrs[220] = 204;
        arrs[221] = -55;
        arrs[222] = 10;
        arrs[225] = 3;
        arrs[226] = -25;
        arrs[227] = 105;
        arrs[228] = -318;
        arrs[229] = 786;
        arrs[230] = -1765;
        arrs[231] = 4540;
        arrs[232] = 31584;
        arrs[233] = -2894;
        arrs[234] = 1042;
        arrs[235] = -391;
        arrs[236] = 128;
        arrs[237] = -33;
        arrs[238] = 6;
        arrs[241] = 2;
        arrs[242] = -15;
        arrs[243] = 64;
        arrs[244] = -190;
        arrs[245] = 455;
        arrs[246] = -974;
        arrs[247] = 2302;
        arrs[248] = 32224;
        arrs[249] = -1439;
        arrs[250] = 450;
        arrs[251] = -142;
        arrs[252] = 36;
        arrs[253] = -5;
        arrs[258] = -7;
        arrs[259] = 27;
        arrs[260] = -71;
        arrs[261] = 142;
        arrs[262] = -227;
        arrs[263] = 299;
        arrs[264] = 32439;
        arrs[265] = 299;
        arrs[266] = -227;
        arrs[267] = 142;
        arrs[268] = -71;
        arrs[269] = 27;
        arrs[270] = -7;
        sinc_table = arrs;
    }

    public Sample() {
        this.set_sample_data(new short[0], 0, 0, false);
    }

    public void set_sample_data(short[] data, int loop_start, int loop_length, boolean ping_pong) {
        if (loop_start < 0) {
            loop_start = 0;
        }
        if (loop_start >= data.length) {
            loop_start = data.length - 1;
        }
        if (loop_start + loop_length > data.length) {
            loop_length = data.length - loop_start;
        }
        if (loop_length <= 1) {
            this.sample_data = new short[8 + data.length + 24];
            System.arraycopy(data, 0, this.sample_data, 8, data.length);
            for (int offset = 0; offset < 8; ++offset) {
                short sample = this.sample_data[8 + data.length - 1];
                this.sample_data[8 + data.length + offset] = sample = (short)(sample * (8 - offset) / 8);
            }
            loop_start = 8 + data.length + 8;
            loop_length = 1;
        } else {
            short sample;
            int offset;
            if (ping_pong) {
                this.sample_data = new short[8 + loop_start + loop_length * 2 + 16];
                System.arraycopy(data, 0, this.sample_data, 8, loop_start + loop_length);
                for (offset = 0; offset < loop_length; ++offset) {
                    this.sample_data[8 + loop_start + loop_length + offset] = sample = data[loop_start + loop_length - offset - 1];
                }
                loop_start += 8;
                loop_length *= 2;
            } else {
                this.sample_data = new short[8 + loop_start + loop_length + 16];
                System.arraycopy(data, 0, this.sample_data, 8, loop_start + loop_length);
                loop_start += 8;
            }
            for (offset = 0; offset < 16; ++offset) {
                this.sample_data[loop_start + loop_length + offset] = sample = this.sample_data[loop_start + offset];
            }
        }
        this.loop_start = loop_start;
        this.loop_length = loop_length;
    }

    public void resample_nearest(int sample_idx, int sample_frac, int step, int left_gain, int right_gain, int[] mix_buffer, int frame_offset, int frames) {
        sample_idx += 8;
        int loop_end = this.loop_start + this.loop_length - 1;
        int offset = frame_offset << 1;
        int end = frame_offset + frames - 1 << 1;
        while (offset <= end) {
            if (sample_idx > loop_end) {
                if (this.loop_length <= 1) break;
                sample_idx = this.loop_start + (sample_idx - this.loop_start) % this.loop_length;
            }
            int n = offset;
            mix_buffer[n] = mix_buffer[n] + (this.sample_data[sample_idx] * left_gain >> 15);
            int n2 = offset + 1;
            mix_buffer[n2] = mix_buffer[n2] + (this.sample_data[sample_idx] * right_gain >> 15);
            offset += 2;
            sample_idx += (sample_frac += step) >> 15;
            sample_frac &= 0x7FFF;
        }
    }

    public void resample_linear(int sample_idx, int sample_frac, int step, int left_gain, int right_gain, int[] mix_buffer, int frame_offset, int frames) {
        sample_idx += 8;
        int loop_end = this.loop_start + this.loop_length - 1;
        int offset = frame_offset << 1;
        int end = frame_offset + frames - 1 << 1;
        while (offset <= end) {
            if (sample_idx > loop_end) {
                if (this.loop_length <= 1) break;
                sample_idx = this.loop_start + (sample_idx - this.loop_start) % this.loop_length;
            }
            int amplitude = this.sample_data[sample_idx];
            amplitude += (this.sample_data[sample_idx + 1] - amplitude) * sample_frac >> 15;
            int n = offset;
            mix_buffer[n] = mix_buffer[n] + (amplitude * left_gain >> 15);
            int n2 = offset + 1;
            mix_buffer[n2] = mix_buffer[n2] + (amplitude * right_gain >> 15);
            offset += 2;
            sample_idx += (sample_frac += step) >> 15;
            sample_frac &= 0x7FFF;
        }
    }

    public void resample_sinc(int sample_idx, int sample_frac, int step, int left_gain, int right_gain, int[] mix_buffer, int frame_offset, int frames) {
        int loop_end = this.loop_start + this.loop_length - 1;
        int offset = frame_offset << 1;
        int end = frame_offset + frames - 1 << 1;
        while (offset <= end) {
            if (sample_idx > loop_end) {
                if (this.loop_length <= 1) break;
                sample_idx = this.loop_start + (sample_idx - this.loop_start) % this.loop_length;
            }
            int table_idx = sample_frac >> 11 << 4;
            int a1 = sinc_table[table_idx + 0] * this.sample_data[sample_idx + 0] >> 15;
            a1 += sinc_table[table_idx + 1] * this.sample_data[sample_idx + 1] >> 15;
            a1 += sinc_table[table_idx + 2] * this.sample_data[sample_idx + 2] >> 15;
            a1 += sinc_table[table_idx + 3] * this.sample_data[sample_idx + 3] >> 15;
            a1 += sinc_table[table_idx + 4] * this.sample_data[sample_idx + 4] >> 15;
            a1 += sinc_table[table_idx + 5] * this.sample_data[sample_idx + 5] >> 15;
            a1 += sinc_table[table_idx + 6] * this.sample_data[sample_idx + 6] >> 15;
            a1 += sinc_table[table_idx + 7] * this.sample_data[sample_idx + 7] >> 15;
            a1 += sinc_table[table_idx + 8] * this.sample_data[sample_idx + 8] >> 15;
            a1 += sinc_table[table_idx + 9] * this.sample_data[sample_idx + 9] >> 15;
            a1 += sinc_table[table_idx + 10] * this.sample_data[sample_idx + 10] >> 15;
            a1 += sinc_table[table_idx + 11] * this.sample_data[sample_idx + 11] >> 15;
            a1 += sinc_table[table_idx + 12] * this.sample_data[sample_idx + 12] >> 15;
            a1 += sinc_table[table_idx + 13] * this.sample_data[sample_idx + 13] >> 15;
            a1 += sinc_table[table_idx + 14] * this.sample_data[sample_idx + 14] >> 15;
            int a2 = sinc_table[table_idx + 16] * this.sample_data[sample_idx + 0] >> 15;
            a2 += sinc_table[table_idx + 17] * this.sample_data[sample_idx + 1] >> 15;
            a2 += sinc_table[table_idx + 18] * this.sample_data[sample_idx + 2] >> 15;
            a2 += sinc_table[table_idx + 19] * this.sample_data[sample_idx + 3] >> 15;
            a2 += sinc_table[table_idx + 20] * this.sample_data[sample_idx + 4] >> 15;
            a2 += sinc_table[table_idx + 21] * this.sample_data[sample_idx + 5] >> 15;
            a2 += sinc_table[table_idx + 22] * this.sample_data[sample_idx + 6] >> 15;
            a2 += sinc_table[table_idx + 23] * this.sample_data[sample_idx + 7] >> 15;
            a2 += sinc_table[table_idx + 24] * this.sample_data[sample_idx + 8] >> 15;
            a2 += sinc_table[table_idx + 25] * this.sample_data[sample_idx + 9] >> 15;
            a2 += sinc_table[table_idx + 26] * this.sample_data[sample_idx + 10] >> 15;
            a2 += sinc_table[table_idx + 27] * this.sample_data[sample_idx + 11] >> 15;
            a2 += sinc_table[table_idx + 28] * this.sample_data[sample_idx + 12] >> 15;
            a2 += sinc_table[table_idx + 29] * this.sample_data[sample_idx + 13] >> 15;
            a2 += sinc_table[table_idx + 30] * this.sample_data[sample_idx + 14] >> 15;
            int amplitude = (a1 += sinc_table[table_idx + 15] * this.sample_data[sample_idx + 15] >> 15) + (((a2 += sinc_table[table_idx + 31] * this.sample_data[sample_idx + 15] >> 15) - a1) * (sample_frac & 0x7FF) >> 11);
            int n = offset;
            mix_buffer[n] = mix_buffer[n] + (amplitude * left_gain >> 15);
            int n2 = offset + 1;
            mix_buffer[n2] = mix_buffer[n2] + (amplitude * right_gain >> 15);
            offset += 2;
            sample_idx += (sample_frac += step) >> 15;
            sample_frac &= 0x7FFF;
        }
    }

    public boolean has_finished(int sample_idx) {
        boolean finished = false;
        if (this.loop_length <= 1 && sample_idx > this.loop_start) {
            finished = true;
        }
        return finished;
    }
}

