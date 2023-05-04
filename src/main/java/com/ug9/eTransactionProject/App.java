package com.ug9.eTransactionProject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DigitalPayment angelBRI = new BRImo("Angel",5000000,"250071190490");
        DigitalPayment felixBNI = new BNImo("Felix",2500000,"250071190494");
        DigitalPayment joyceBRI = new BRImo("Joyce",1500000,"250071190495");
        DigitalPayment oliveDana = new Dana("Olive",0,"081234567891");
        DigitalPayment adrianOvo = new Ovo("Adrian",150000,"081578961543");
        DigitalPayment nolaDana = new Dana("Nola",550000,"081734566789");
        System.out.println("----------Skenario 1: Input Tidak Valid----------");
        joyceBRI.transfer(nolaDana,-150000);
        nolaDana.transfer(oliveDana,-100000);
        System.out.println("----------Skenario 2: Saldo Tidak Mencukupi----------");
        joyceBRI.transfer(oliveDana,2000000);
        nolaDana.transfer(oliveDana,1555000);
        System.out.println("----------Skenario 3: Transfer Sesama Bank----------");
        angelBRI.transfer(joyceBRI,200000);
        System.out.println("----------Skenario 4: Transfer Beda Bank----------");
        angelBRI.transfer(felixBNI,300000);
        System.out.println("----------Skenario 5: Bank Transfer ke e-Wallet----------");
        angelBRI.transfer(oliveDana,1000000);
        System.out.println("----------Skenario 6: OVO Transfer ke Bank----------");
        adrianOvo.transfer(felixBNI,100000);
        System.out.println("----------Skenario 7: DANA transfer ke Bank----------");
        nolaDana.transfer(angelBRI,100000);
        System.out.println("----------Skenario 8: Dana transfer ke Dana----------");
        oliveDana.transfer(nolaDana,150000);
        System.out.println("----------Skenario 9: Dana transfer ke OVO----------");
        oliveDana.transfer(adrianOvo,10000);
        System.out.println("----------Skenario 10: Saldo Akhir----------");
        printSaldo(angelBRI);
        printSaldo(felixBNI);
        printSaldo(joyceBRI);
        printSaldo(oliveDana);
        printSaldo(adrianOvo);
        printSaldo(nolaDana);
    }
    public static void printSaldo(DigitalPayment dp){
        System.out.println("=====Rincian Saldo=====");
        System.out.println("Nama\t\t: "+dp.getNama());
        if (dp instanceof BNImo || dp instanceof BRImo){
            System.out.println("No. Rekening: "+((MobileBanking) dp).getNoRekening());
        } else if (dp instanceof Dana || dp instanceof Ovo){
            System.out.println("No. HP\t\t: "+((MobileWallet) dp).getNoHp());
        }
        System.out.println("===> Saldo\t: Rp "+dp.getSaldo());
    }
}
