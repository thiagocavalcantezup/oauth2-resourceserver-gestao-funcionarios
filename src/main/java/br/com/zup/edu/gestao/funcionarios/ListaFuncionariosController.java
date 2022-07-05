package br.com.zup.edu.gestao.funcionarios;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.Sort.by;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListaFuncionariosController {

    private final FuncionarioRepository repository;

    public ListaFuncionariosController(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/funcionarios")
    public ResponseEntity<?> lista() {
        List<FuncionarioResponse> funcionarios = repository.findAll(by("nome"))
                                                           .stream()
                                                           .map(FuncionarioResponse::new)
                                                           .collect(toList());

        return ResponseEntity.ok(funcionarios);
    }

}
