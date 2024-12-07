package com.pawpengaga.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pawpengaga.modelo.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuario {

  // Lista de usuarios como atributo base
  List<Usuario> listaUsuarios;

  public UsuarioServiceImpl(){
    // Metodo constructor donde se inicializa la variable
    listaUsuarios = new ArrayList<>();
  }

  @Override
  public List<Usuario> listaUsuarios() {
    return listaUsuarios;
  }

  @Override
  public String agregaUsuario(Usuario user) {

    boolean existe = false;

    if (!listaUsuarios.isEmpty()) {
      // Sera solo verdadero cuando lo consiga
      existe = listaUsuarios.stream().anyMatch(u -> u.getRut().equals(user.getRut()));
    }

    if (existe) {
      System.out.println("**************************");
      System.out.println("Existe!!!!!!!!!!!");
      System.out.println("**************************");
      return "existe";
    } else {
      String regex = "^(\\d{1,3}(?:\\.\\d{1,3}){2}-[\\dkK])$";
      if (user.getRut().matches(regex) && calculoDigitoVerificador(user.getRut())) {
        listaUsuarios.add(user);
        return "exito";
      }
    }

    return "fracaso";
  }

  @Override
  public Usuario buscarUsuario(String rut) {

    // Los stream suelen seguir la misma estructura...
    Usuario user = listaUsuarios.stream().filter(u -> u.getRut().equals(rut)).findFirst().orElse(null);
    return user;

  }

  /* ************************** METODOS PRIVADOS ************************** */

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

      // Ya no tengo mas nombres para mas tipos de calculo
      int pasocinco = Math.round(resultadoSuma / 11) * 11;
      int pasoseis = resultadoSuma - pasocinco;
      int pasosiete = 11 - pasoseis;

      // Los reales
      String comprobador = rutDividido[1]; 
      char verificador = 'x';
      
      // Asignacion verificadora
      if (pasosiete < 11 && pasosiete >= 0) {
        if (pasosiete == 11) {
          verificador = 0;
        } else if (pasosiete == 10) {
          verificador = 'k';
        } else {
          verificador =  Character.forDigit(pasosiete, 10);
        }
      }

      // Comprobacion booleana final
      if (comprobador.equalsIgnoreCase(String.valueOf(verificador))) {
        return true;
      }

    }

    return false;
  }

}
