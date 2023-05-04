package com.ug9.eTransactionProject;

public class Ovo extends MobileWallet{
    private final long ovoFeeTransferBank = 2000;

    public Ovo(String nama, long saldo, String noHp){
        super(nama, saldo, noHp);
    }

    public void transfer(DigitalPayment dp, long nominal){
        this.setFeeTransferBank(this.ovoFeeTransferBank);
        if(dp instanceof Dana){
            System.out.println("Transfer gagal, akun OVO tidak valid");
        }
        else{
            super.transfer(dp, nominal);
        }
    }
}