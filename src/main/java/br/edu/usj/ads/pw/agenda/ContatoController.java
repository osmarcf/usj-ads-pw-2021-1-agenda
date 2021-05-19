package br.edu.usj.ads.pw.agenda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class ContatoController {

    @Autowired
    ContatoRepository contatoRepository;
    
    @GetMapping(value="/")
    public ModelAndView getIndex() {
        // criar um objeto lista
        List<Contato> lista = new ArrayList<>();

        // preencher esta lista com os dados do banco
        lista = contatoRepository.findAll();

        // instanciar um template
        ModelAndView modelAndView = new ModelAndView("index");

        // preencher o template com a lista
        modelAndView.addObject("lista", lista);

        // retornar o template
        return modelAndView;
    }

    @GetMapping(value="/detalhes/{id}")
    public ModelAndView getDetalhes(@PathVariable Long id) {
        // ler contato do banco pelo id
        Contato contato = new Contato();
        contato = contatoRepository.findById(id).get();

        // instanciar o template
        ModelAndView modelAndView = new ModelAndView("detalhes");

        // preencher o template com o contato selecionado
        modelAndView.addObject("contato", contato);

        // retornar o template
        return modelAndView;
    }
    

    @GetMapping(value="/cadastro")
    public ModelAndView getCadastro() {
        Contato contato = new Contato();

        ModelAndView modelAndView = new ModelAndView("cadastro");

        modelAndView.addObject("contato", contato);

        return modelAndView;
    }

    @PostMapping(value="/adicionar")
    public ModelAndView postAdicionar(Contato contato) {
        // salvar no banco (usando repository)
        contatoRepository.save(contato);

        // criar template
        ModelAndView modelAndView = new ModelAndView("detalhes");

        // popular o template
        modelAndView.addObject("contato", contato);

        // retornar
        return modelAndView;
    }

    @GetMapping(value="/editar/{id}")
    public ModelAndView getEditar(@PathVariable Long id) {
        // selecionar contato do banco pelo id
        Contato contato = new Contato();
        contato = contatoRepository.findById(id).get();

        // instanciar o template
        ModelAndView modelAndView = new ModelAndView("cadastro");

        // popular o template
        modelAndView.addObject("contato", contato);

        // retornar o template
        return modelAndView;
    }
    

    @GetMapping(value="/deletar/{id}")
    public String getDeletar(@PathVariable Long id) {
        // deletar o objeto com o id passado pelo parametro
        contatoRepository.deleteById(id);

        // retornar para / (raiz)
        return "redirect:/";
    }
    
    
}
