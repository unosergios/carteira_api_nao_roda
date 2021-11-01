package br.com.alura.carteira.infra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alura.carteira.dto.Erro400Dto;
import br.com.alura.carteira.dto.Erro500Dto;

// como utilizamos o Rest usamos o RestControllerAdvice - se não tivermos
// usando o Rest era somente o ControllerAdvice
// essa anotação faz com que essa classe é acionado internamente qdo der um
// erro - mais ou menos parecida com On Error no Vb
// e assim podemos concentrar todos os tratamentos de erro aqui nessa
// classe
@RestControllerAdvice
public class TratamentoDeErros {

	// para devolver o erro correto usamos o respondestatus
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public List<Erro400Dto>  tratarErro400(MethodArgumentNotValidException ex) {
	//	
	//	return "erro";
		return ex
				.getFieldErrors()
			    .stream()
			    .map(erro -> new Erro400Dto(erro.getField(),erro.getDefaultMessage()))
	            .collect(Collectors.toList());
	}
	
//	@ExceptionHandler(NullPointerException)
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	public Erro500Dto  tratarErro500(Exception ex, HttpServletRequest req) {
	//	
	//	return "erro";
		return new Erro500Dto(
				LocalDateTime.now(),
				ex.getClass().toString(),
				ex.getMessage(),
				req.getRequestURI())
;
	}	
}
