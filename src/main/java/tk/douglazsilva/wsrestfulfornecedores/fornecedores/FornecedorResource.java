package tk.douglazsilva.wsrestfulfornecedores.fornecedores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
