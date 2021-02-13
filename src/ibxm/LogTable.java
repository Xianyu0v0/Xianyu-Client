/*
 * Decompiled with CFR 0.150.
 */
package ibxm;

public class LogTable {
    private static final int TABLE_SHIFT = 7;
    private static final int INTERP_SHIFT = 8;
    private static final int INTERP_MASK = 255;
    private static final int[] exp_2_table = new int[]{32768, 32945, 33124, 33304, 33485, 33667, 33850, 34033, 34218, 34404, 34591, 34779, 34968, 35157, 35348, 35540, 35733, 35927, 36122, 36319, 36516, 36714, 36913, 37114, 37315, 37518, 37722, 37926, 38132, 38339, 38548, 38757, 38967, 39179, 39392, 39606, 39821, 40037, 40254, 40473, 40693, 40914, 41136, 41359, 41584, 41810, 42037, 42265, 42494, 42725, 42957, 43190, 43425, 43661, 43898, 44136, 44376, 44617, 44859, 45103, 45347, 45594, 45841, 46090, 46340, 46592, 46845, 47099, 47355, 47612, 47871, 48131, 48392, 48655, 48919, 49185, 49452, 49720, 49990, 50262, 50535, 50809, 51085, 51362, 51641, 51922, 52204, 52487, 52772, 53059, 53347, 53636, 53928, 54220, 54515, 54811, 55108, 55408, 55709, 56011, 56315, 56621, 56928, 57238, 57548, 57861, 58175, 58491, 58809, 59128, 59449, 59772, 60096, 60423, 60751, 61081, 61412, 61746, 62081, 62418, 62757, 63098, 63440, 63785, 64131, 64479, 64830, 65182, 65536};
    private static final int[] log_2_table;

    static {
        int[] arrn = new int[129];
        arrn[1] = 367;
        arrn[2] = 732;
        arrn[3] = 1095;
        arrn[4] = 1454;
        arrn[5] = 1811;
        arrn[6] = 2165;
        arrn[7] = 2517;
        arrn[8] = 2865;
        arrn[9] = 3212;
        arrn[10] = 3556;
        arrn[11] = 3897;
        arrn[12] = 4236;
        arrn[13] = 4572;
        arrn[14] = 4906;
        arrn[15] = 5238;
        arrn[16] = 5568;
        arrn[17] = 5895;
        arrn[18] = 6220;
        arrn[19] = 6542;
        arrn[20] = 6863;
        arrn[21] = 7181;
        arrn[22] = 7497;
        arrn[23] = 7812;
        arrn[24] = 8124;
        arrn[25] = 8434;
        arrn[26] = 8742;
        arrn[27] = 9048;
        arrn[28] = 9352;
        arrn[29] = 9654;
        arrn[30] = 9954;
        arrn[31] = 10252;
        arrn[32] = 10548;
        arrn[33] = 10843;
        arrn[34] = 11136;
        arrn[35] = 11427;
        arrn[36] = 11716;
        arrn[37] = 12003;
        arrn[38] = 12289;
        arrn[39] = 12573;
        arrn[40] = 12855;
        arrn[41] = 13136;
        arrn[42] = 13414;
        arrn[43] = 13692;
        arrn[44] = 13967;
        arrn[45] = 14241;
        arrn[46] = 14514;
        arrn[47] = 14785;
        arrn[48] = 15054;
        arrn[49] = 15322;
        arrn[50] = 15588;
        arrn[51] = 15853;
        arrn[52] = 16117;
        arrn[53] = 16378;
        arrn[54] = 16639;
        arrn[55] = 16898;
        arrn[56] = 17156;
        arrn[57] = 17412;
        arrn[58] = 17667;
        arrn[59] = 17920;
        arrn[60] = 18172;
        arrn[61] = 18423;
        arrn[62] = 18673;
        arrn[63] = 18921;
        arrn[64] = 19168;
        arrn[65] = 19413;
        arrn[66] = 19657;
        arrn[67] = 19900;
        arrn[68] = 20142;
        arrn[69] = 20383;
        arrn[70] = 20622;
        arrn[71] = 20860;
        arrn[72] = 21097;
        arrn[73] = 21333;
        arrn[74] = 21568;
        arrn[75] = 21801;
        arrn[76] = 22034;
        arrn[77] = 22265;
        arrn[78] = 22495;
        arrn[79] = 22724;
        arrn[80] = 22952;
        arrn[81] = 23178;
        arrn[82] = 23404;
        arrn[83] = 23628;
        arrn[84] = 23852;
        arrn[85] = 24074;
        arrn[86] = 24296;
        arrn[87] = 24516;
        arrn[88] = 24736;
        arrn[89] = 24954;
        arrn[90] = 25171;
        arrn[91] = 25388;
        arrn[92] = 25603;
        arrn[93] = 25817;
        arrn[94] = 26031;
        arrn[95] = 26243;
        arrn[96] = 26455;
        arrn[97] = 26665;
        arrn[98] = 26875;
        arrn[99] = 27084;
        arrn[100] = 27292;
        arrn[101] = 27499;
        arrn[102] = 27705;
        arrn[103] = 27910;
        arrn[104] = 28114;
        arrn[105] = 28317;
        arrn[106] = 28520;
        arrn[107] = 28721;
        arrn[108] = 28922;
        arrn[109] = 29122;
        arrn[110] = 29321;
        arrn[111] = 29519;
        arrn[112] = 29716;
        arrn[113] = 29913;
        arrn[114] = 30109;
        arrn[115] = 30304;
        arrn[116] = 30498;
        arrn[117] = 30691;
        arrn[118] = 30884;
        arrn[119] = 31076;
        arrn[120] = 31267;
        arrn[121] = 31457;
        arrn[122] = 31646;
        arrn[123] = 31835;
        arrn[124] = 32023;
        arrn[125] = 32210;
        arrn[126] = 32397;
        arrn[127] = 32582;
        arrn[128] = 32768;
        log_2_table = arrn;
    }

    public static int log_2(int x) {
        int shift = 15;
        while (x < 32768) {
            x <<= 1;
            --shift;
        }
        while (x >= 65536) {
            x >>= 1;
            ++shift;
        }
        return 32768 * shift + LogTable.eval_table(log_2_table, x - 32768);
    }

    public static int raise_2(int x) {
        int y = LogTable.eval_table(exp_2_table, x & 0x7FFF) << 15;
        return y >> 15 - (x >> 15);
    }

    private static int eval_table(int[] table, int x) {
        int table_idx = x >> 8;
        int table_frac = x & 0xFF;
        int c = table[table_idx];
        int m = table[table_idx + 1] - c;
        int y = (m * table_frac >> 8) + c;
        return y >> 0;
    }
}

