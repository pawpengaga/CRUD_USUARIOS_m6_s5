package com.pawpengaga.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pawpengaga.modelo.Usuario;
import com.pawpengaga.servicios.IUsuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioControlador {

  @Autowired
  IUsuario userService;

  String respuesta;

  @GetMapping("/inicio")
  public ModelAndView inicio(){
    String tipo = null;
    String mensaje = null;
    if (respuesta != null) {
      if (respuesta.equals("exito")) {
        tipo = "alert-success";
        mensaje = "Registro agregado con éxito!";
      } else {
        tipo = "alert-warning";
        mensaje = "Ya existe un usuario con este RUT...";
      }
    }
    
    ModelAndView mav = new ModelAndView("inicio");
    List<Usuario> listado = userService.listaUsuarios();

    mav.addObject("mensaje", mensaje);
    mav.addObject("tipo", tipo);
    mav.addObject("listado", listado);
    return mav;
  }

  @GetMapping("/formulario")
  public String formulario(){
    return "formulario";
  }

  @PostMapping("/grabar")
  public String grabarUsuario(@ModelAttribute("Usuario") Usuario user){
    respuesta = userService.agregaUsuario(user);
    return "redirect:/usuarios/inicio";
  }

}
