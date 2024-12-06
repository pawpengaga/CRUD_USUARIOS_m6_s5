package com.pawpengaga.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

  private String rut;
  private String nombre;
  private String clave;
  private String correo;
  private String usuario;

}
