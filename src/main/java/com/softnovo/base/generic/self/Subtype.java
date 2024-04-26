package com.softnovo.base.generic.self;

class Subtype extends BasicHolder<Subtype> {
    public Subtype(int No) {
        this.No = No;
    }

    public static void main(String[] args) {
        Subtype a1 = new Subtype(1);
        Subtype a2 = new Subtype(2);
        Subtype a3 = new Subtype(3);
        Subtype a4 = new Subtype(4);

        a2.set(a1);
        a3.set(a2);
        a4.set(a3);

        Subtype subtype3 = a4.get();
        subtype3.f();
        System.out.println();

        Subtype subtype2 = subtype3.get();
        subtype2.f();
        System.out.println();

        Subtype subtype1 = subtype2.get();
        subtype1.f();
        System.out.println();

        Subtype subtype = subtype1.get();
        subtype.f();
        System.out.println();
    }
}

