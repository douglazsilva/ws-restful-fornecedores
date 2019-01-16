package tk.douglazsilva.wsrestfulfornecedores.fornecedores;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tk.douglazsilva.wsrestfulfornecedores.exceptions.FornecedorNotFoundException;

@RestController
public class FornecedorResource {
	
	@Autowired
	private FornecedorRepository fr;

	@GetMapping("/fornecedores")
	public List<Fornecedor> retrieveAllFornecedores(){
		return fr.findAll();
	}
	
	@GetMapping("/fornecedores/{id}")
	public Fornecedor retrieveFornecedor(@PathVariable long id) {
		Optional<Fornecedor> fornecedor = fr.findById(id);
		if (!fornecedor.isPresent()) {
			throw new FornecedorNotFoundException("id -" + id);
		}
		return fornecedor.get();
	}
	
	@DeleteMapping("/fornecedores/{id}")
	public void deleteFornecedor(@PathVariable long id) {
		fr.deleteById(id);
	}
	
	@PostMapping("/fornecedores")
	public ResponseEntity<Object> createFornecedor(@RequestBody Fornecedor fornecedor) {
		Fornecedor savedFornecedor = fr.save(fornecedor);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedFornecedor.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/fornecedores/{id}")
	public ResponseEntity<Object> updateFornecedor(@RequestBody Fornecedor fornecedor, @PathVariable long id) {

		Optional<Fornecedor> fornecedorOptional = fr.findById(id);

		if (!fornecedorOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		fornecedor.setId(id);		
		fr.save(fornecedor);

		return ResponseEntity.noContent().build();
	}
}
