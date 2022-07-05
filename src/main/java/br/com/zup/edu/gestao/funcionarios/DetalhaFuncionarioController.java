package br.com.zup.edu.gestao.funcionarios;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DetalhaFuncionarioController {

    private final FuncionarioRepository repository;

    public DetalhaFuncionarioController(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/funcionarios/{id}")
    public ResponseEntity<?> detalha(@PathVariable("id") Long id) {
        Funcionario funcionario = repository.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(NOT_FOUND, "funcionário não encontrado");
        });

        return ResponseEntity.ok(new DetalhesDoFuncionarioResponse(funcionario));
    }

}
