package com.ug9.eTransactionProject;

public class BRImo extends MobileBanking{
    public BRImo(String nama, long saldo, String noRekening){
        super(nama, saldo, noRekening);
    }

    public void transfer(DigitalPayment dp, long nominal){
        if(dp instanceof BNImo){
            super.setCheckFee(true);
            super.transfer(dp, nominal);
        }
        else{
            super.transfer(dp, nominal);
        }
    }

}