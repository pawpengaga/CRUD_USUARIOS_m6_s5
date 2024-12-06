package com.pawpengaga.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pawpengaga.modelo.Usuario;
import com.pawpengaga.servicios.IUsuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioControlador {

  @Autowired
  IUsuario userService;

  @GetMapping("/inicio")
  public ModelAndView inicio(){
    ModelAndView mav = new ModelAndView("inicio");
    List<Usuario> listado = userService.listaUsuarios();
    mav.addObject("listado", listado);
    return mav;
  }


}
