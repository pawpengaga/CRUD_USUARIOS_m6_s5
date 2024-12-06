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
      listaUsuarios.add(user);
      return "exito";
    }
  }

  @Override
  public Usuario buscarUsuario(String rut) {

    // Los stream suelen seguir la misma estructura...
    Usuario user = listaUsuarios.stream().filter(u -> u.getRut().equals(rut)).findFirst().orElse(null);
    return user;

  }

}
