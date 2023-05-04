package com.ug9.eTransactionProject;

public class Dana extends MobileWallet{
    private final long danaFeeTransferBank = 1000;

    public Dana(String nama, long saldo, String noHp){
        super(nama, saldo, noHp);
    }

    public void transfer(DigitalPayment dp, long nominal){
        this.setFeeTransferBank(this.danaFeeTransferBank);
        if(dp instanceof Ovo){
            System.out.println("Akun Dana tidak valid!");
        }
        else{
            super.transfer(dp, nominal);
        }
    }

}