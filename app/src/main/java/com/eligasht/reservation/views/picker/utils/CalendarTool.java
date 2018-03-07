package com.eligasht.reservation.views.picker.utils;

import java.util.GregorianCalendar;

public class CalendarTool {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;

    public CalendarTool() {
        GregorianCalendar var1 = new GregorianCalendar();
        this.m18351b(var1.get(1), var1.get(2) + 1, var1.get(5));
    }

    public CalendarTool(int var1, int var2, int var3) {
        this.m18351b(var1, var2, var3);
    }

    private int m18342c(int var1, int var2, int var3) {
        return ((var2 - 8) / 6 + var1 + 100100) * 1461 / 4 + ((var2 + 9) % 12 * 153 + 2) / 5 + var3 - 34840408 - (var1 + 100100 + (var2 - 8) / 6) / 100 * 3 / 4 + 752;
    }

    private void m18343l() {
        int[] var8 = new int[]{-61, 9, 38, 199, 426, 686, 756, 818, 1111, 1181, 1210, 1635, 2060, 2097, 2192, 2262, 2324, 2394, 2456, 3178};
        this.d = this.a + 621;
        int var3 = -14;
        int var5 = var8[0];
        int var2 = 1;

        int var1;
        int var4;
        int var6;
        int var7;
        do {
            var6 = var8[var2];
            var7 = var6 - var5;
            var4 = var5;
            var1 = var3;
            if (this.a >= var6) {
                var1 = var3 + var7 / 33 * 8 + var7 % 33 / 4;
                var4 = var6;
            }

            ++var2;
            if (var2 >= 20) {
                break;
            }

            var5 = var4;
            var3 = var1;
        } while (this.a >= var6);

        var2 = this.a - var4;
        var3 = var2 / 33 * 8 + (var2 % 33 + 3) / 4 + var1;
        var1 = var3;
        if (var7 % 33 == 4) {
            var1 = var3;
            if (var7 - var2 == 4) {
                var1 = var3 + 1;
            }
        }

        this.l = var1 + 20 - (this.d / 4 - (this.d / 100 + 1) * 3 / 4 - 150);
        if (var7 - var2 < 6) {
            var1 = var2 - var7 + (var7 + 4) / 33 * 33;
        } else {
            var1 = var2;
        }

        this.j = ((var1 + 1) % 33 - 1) % 4;
        if (this.j == -1) {
            this.j = 4;
        }

    }

    private int m() {
        this.m18343l();
        return this.m18342c(this.d, 3, this.l) + (this.b - 1) * 31 - this.b / 7 * (this.b - 7) + this.c - 1;
    }

    private void m18345n() {
        this.m18347p();
        this.a = this.d - 621;
        this.m18343l();
        int var1 = this.m18342c(this.d, 3, this.l);
        var1 = this.k - var1;
        if (var1 >= 0) {
            if (var1 <= 185) {
                this.b = var1 / 31 + 1;
                this.c = var1 % 31 + 1;
                return;
            }

            var1 -= 186;
        } else {
            --this.a;
            int var2 = var1 + 179;
            var1 = var2;
            if (this.j == 1) {
                var1 = var2 + 1;
            }
        }

        this.b = var1 / 30 + 7;
        this.c = var1 % 30 + 1;
    }

    private void m18346o() {
        int var2 = this.k * 4 + 139361631;
        int var1 = var2 % 1461 / 4 * 5 + 308;
        this.i = var1 % 153 / 5 + 1;
        this.h = var1 / 153 % 12 + 1;
        this.g = var2 / 1461 - 100100 + (8 - this.h) / 6;
    }

    private void m18347p() {
        int var1 = this.k * 4 + 139361631 + ((this.k * 4 + 183187720) / 146097 * 3 / 4 * 4 - 3908);
        int var2 = var1 % 1461 / 4 * 5 + 308;
        this.f = var2 % 153 / 5 + 1;
        this.e = var2 / 153 % 12 + 1;
        this.d = var1 / 1461 - 100100 + (8 - this.e) / 6;
    }

    public int m18348a() {
        return this.a;
    }

    public void m18349a(int var1, int var2, int var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.k = this.m();
        this.m18345n();
        this.m18346o();
        this.m18347p();
    }

    public int m18350b() {
        return this.b;
    }

    public void m18351b(int var1, int var2, int var3) {
        this.d = var1;
        this.e = var2;
        this.f = var3;
        this.k = this.m18342c(var1, var2, var3);
        this.m18345n();
        this.m18346o();
        this.m18347p();
    }

    public int m18352c() {
        return this.c;
    }

    public int m18353d() {
        return this.d;
    }

    public int m18354e() {
        return this.e;
    }

    public int m18355f() {
        return this.f;
    }

    public String m18356g() {
        return this.a + "/" + this.b + "/" + this.c;
    }

    public String m18357h() {
        return this.d + "/" + this.e + "/" + this.f;
    }

    public String m18358i() {
        return this.g + "/" + this.h + "/" + this.i;
    }

    public String m18359j() {
        int var1 = this.m18360k();
        return (new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"})[var1];
    }

    public int m18360k() {
        return this.k % 7;
    }

    public String toString() {
        return this.m18359j() + ", Gregorian:[" + this.m18357h() + "], Julian:[" + this.m18358i() + "], Iranian:[" + this.m18356g() + "]";
    }
}
