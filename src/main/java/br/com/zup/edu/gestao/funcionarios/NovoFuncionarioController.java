package br.com.zup.edu.gestao.funcionarios;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class NovoFuncionarioController {

    private final FuncionarioRepository repository;

    public NovoFuncionarioController(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PostMapping("/api/funcionarios")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovoFuncionarioRequest request,
                                      UriComponentsBuilder uriBuilder) {
        if (repository.existsByCpf(request.getCpf())) {
            throw new ResponseStatusException(
                UNPROCESSABLE_ENTITY, "funcionário com CPF já existente"
            );
        }

        Funcionario funcionario = request.toModel();
        repository.save(funcionario);

        URI location = uriBuilder.path("/api/funcionarios/{id}")
                                 .buildAndExpand(funcionario.getId())
                                 .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Exception Handler para Unique Constraint de funcionário
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleUniqueConstraintError(ConstraintViolationException exception) {
        Map<String, Object> body = Map.of(
            "message", "funcionário já existente (db)", "timestamp", LocalDateTime.now()
        );
        return ResponseEntity.unprocessableEntity().body(body);
    }

}
