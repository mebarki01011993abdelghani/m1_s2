// -*- coding: utf-8 -*-

class Tache1 implements Runnable{
    public void run(){
	for(int i = 1; i<=26;i++) System.out.print(i +" ");
    }
}
class Tache2 implements Runnable{
    public void run(){
	for(int i = 'a'; i<='z'; i++) System.out.print((char)i +" ");
    }
}
class MonProgramme {
    public static void main(String[] args) {
	Thread t1 = new Thread(new Tache1());
	Thread t2 = new Thread(new Tache2());
	t1.start();
	t2.start();
	try{ t1.join(); t2.join(); }
	catch(InterruptedException e){e.printStackTrace();}
	System.out.print("\n");
    }
}

