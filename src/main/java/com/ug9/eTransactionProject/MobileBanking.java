package com.ug9.eTransactionProject;

public class MobileBanking extends DigitalPayment{
    private boolean checkFee = false;
    private final long feeAntarBank = 6000;
    private String noRekening;

    public MobileBanking(String nama, long saldo, String noRekening){
        super(nama, saldo);
        this.noRekening=noRekening;
    }

    public String getNoRekening(){
        return this.noRekening;
    }

    public void transfer(DigitalPayment dp, long nominal){
        if(nominal< 0){
            System.out.println("Nominal yang Anda input tidak valid!");
        }
        else if(this.isCheckFee()){
                this.setSaldo(getSaldo()-(nominal+feeAntarBank));
                dp.setSaldo(getSaldo()+nominal);
                this.printBuktiTransfer(dp, nominal);
            }
        else {
            this.setSaldo(getSaldo()-(nominal));
            dp.setSaldo(getSaldo()+nominal);
            this.printBuktiTransfer(dp, nominal);
        }

    }

    public boolean isCheckFee(){
        return this.checkFee;
    }

    public void setCheckFee(boolean checkFee){
        this.checkFee=checkFee;
    }

}