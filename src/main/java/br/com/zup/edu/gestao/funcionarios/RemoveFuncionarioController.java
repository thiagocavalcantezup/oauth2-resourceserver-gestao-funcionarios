package br.com.zup.edu.gestao.funcionarios;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RemoveFuncionarioController {

    private final FuncionarioRepository repository;

    public RemoveFuncionarioController(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @DeleteMapping("/api/funcionarios/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        Funcionario funcionario = repository.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(NOT_FOUND, "funcionário não encontrado");
        });

        repository.delete(funcionario);

        return ResponseEntity.noContent().build();
    }

}
