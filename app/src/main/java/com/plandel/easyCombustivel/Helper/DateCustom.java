package com.plandel.easyCombustivel.Helper;

import java.text.SimpleDateFormat;

public class DateCustom {
    public static String dataAtual(){
        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format(date);
        return dataString;

    }

   public static CharSequence[] getNomeMeses(){
        CharSequence meses[] = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        return meses;

   }

   public static String formatarMesAno(String mes, String ano){

        String dataAtual;

       if (mes.length() < 2){
           String mesSoma = 0 + mes;
           dataAtual = mesSoma + ano;
       }else {
           dataAtual  = mes+ano;
       }

       return dataAtual;

   }

}
