package com.pawpengaga.servicios;

import java.util.List;

import com.pawpengaga.modelo.Usuario;

public interface IUsuario {

  List<Usuario> listaUsuarios();
  String agregaUsuario(Usuario user);
  Usuario buscarUsuario(String rut);

}
