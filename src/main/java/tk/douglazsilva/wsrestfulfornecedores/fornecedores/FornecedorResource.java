package tk.douglazsilva.wsrestfulfornecedores.fornecedores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FornecedorResource {
	
	@Autowired
	private FornecedorRepository fr;

	@GetMapping("/fornecedores")
	public List<Fornecedor> retrieveAllFornecedores(){
		return fr.findAll();
	}
}
