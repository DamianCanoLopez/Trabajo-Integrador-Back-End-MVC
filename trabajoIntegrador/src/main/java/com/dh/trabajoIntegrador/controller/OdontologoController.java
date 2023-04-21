package com.dh.trabajoIntegrador.controller;

import com.dh.trabajoIntegrador.entity.Odontologo;
import com.dh.trabajoIntegrador.exections.BadRequestException;
import com.dh.trabajoIntegrador.exections.ResourceNotFoundException;
import com.dh.trabajoIntegrador.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> registarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarOdontologos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        Optional<Odontologo> buscarOdontologo=odontologoService.buscar(odontologo.getId());
        if (buscarOdontologo.isPresent()){
            odontologoService.actualizar(odontologo);
            return ResponseEntity.ok("Se actualizo el odontologo con id= "+odontologo.getId());
        }
        else {
            throw new BadRequestException("la peticion que acaba de realizar tiene datos incorrectos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoId(@PathVariable Long id) throws BadRequestException {
        Optional<Odontologo> busacarOdontologo=odontologoService.buscar(id);
        if (busacarOdontologo.isPresent()){
            return ResponseEntity.ok(busacarOdontologo.get());
        }
        else {
            throw new BadRequestException("la peticion que acaba de realizar tiene datos incorrectos");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> buscarOdontologo=odontologoService.buscar(id);
        if (buscarOdontologo.isPresent()){
            odontologoService.eliminar(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se elimino el odontologo" +
                    " con id= "+id);
        }
        else {
            throw  new ResourceNotFoundException("Error. "+"No existe el odontologo con id= "+id);
        }
    }

    /*
    @GetMapping
    public String buscarOdontologo(Model model, @RequestParam("id") Integer id){
        Odontologo odontologoBuscado=odontologoService.buscar(id);
        model.addAttribute("nombre",odontologoBuscado.getNombre());
        model.addAttribute("apellido",odontologoBuscado.getApellido());
        return "busodontologo";
    }*/
}
