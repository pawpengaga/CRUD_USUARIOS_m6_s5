package com.pawpengaga.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestGround {
  public static void main(String[] args) {
    System.out.println("\n");
    calculoDigitoVerificador("19.492.730-4");
    //calculoDigitoVerificador("27.962.409-2");
  }

  private static boolean calculoDigitoVerificador(String rut){

    String[] rutDividido = rut.split("-");

    if (rutDividido.length > 0) {

      // Obtencion del arreglo
      int cont = 0;
      int[] calculador = new int[8];
      for (char val : rutDividido[0].toCharArray()) {
        if (val != '.') {
          calculador[cont] = Character.getNumericValue(val);
          cont++;
        }
      }

      // Inversion del arreglo
      cont = 0;
      int[] calculadorInverso = new int[8];
      for (int i =  calculador.length - 1; i >= 0; i--) {
        calculadorInverso[cont] = calculador[i];
        cont++;
      }

      // Calculo en base al arreglo
      int digiCal = 2;
      int resultadoSuma = 0;
      for (int i = 0; i < calculadorInverso.length; i++) {
        resultadoSuma += calculadorInverso[i] * digiCal;
        digiCal++;

        if (digiCal > 7) {
          digiCal = 2;
        }
      }

      // Ya no tengo mas nombres para mas tipos de calculo 1
      int pasocinco = Math.round(resultadoSuma / 11) * 11;
      int pasoseis = resultadoSuma - pasocinco;
      int pasosiete = 11 - pasoseis;
      char verificador = 'x';
      
      if (pasosiete < 11 && pasosiete >= 0) {
        if (pasosiete == 11) {
          verificador = 0;
        } else if (pasosiete == 10) {
          verificador = 'k';
        } else {
          verificador =  Character.forDigit(pasosiete, 10);
        }
      }

      // Ya no tengo mas nombres para mas tipos de calculo 2

      System.out.println("EL RESULTADO 5 ES: " + pasocinco);
      System.out.println("EL RESULTADO 6 ES: " + pasoseis);
      System.out.println("EL RESULTADO 7 ES: " + verificador);

      // Comprobacion booleana final
      String comprobador = rutDividido[1];      

      if (comprobador.equalsIgnoreCase(String.valueOf(verificador))) {
        return true;
      }

    }

    return false;
  }
}
