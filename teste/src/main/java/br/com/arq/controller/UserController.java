package br.com.arq.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.arq.model.Medico;
import br.com.arq.model.Paciente;
import br.com.arq.repository.MedicoRepository;
import br.com.arq.repository.PacienteRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private HttpSession session;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@GetMapping("/")
	public String inicio(Model model) {
		return "home";
	}

	@GetMapping("/cadastroMedico")
	public String cadastroMedicoForm(Model model) {
		model.addAttribute("medico", new Medico());
		return "cadastroMedico";
	}

	@GetMapping("/cadastroPaciente")
	public String cadastroPacienteForm(Model model) {
		model.addAttribute("paciente", new Paciente());
		return "cadastroPaciente-home";
	}

	@PostMapping("/cadastrarMedico")
	public String cadastrarMedico(@ModelAttribute("medico") Medico medico, BindingResult result, Model model) {
		try {
			medico.setSenha(medico.criptografarSenha(medico.getSenha()));
			Medico resposta = medicoRepository.save(medico);
			model.addAttribute("message", "Cadastro realizado com sucesso!");
			model.addAttribute("medico", new Medico());
			return "cadastroMedico";
		} catch (Exception ex) {
			model.addAttribute("message", "Erro: " + ex.getMessage());
			return "cadastroMedico";
		}
	}

	// Método de cadastro de pacientes
	@PostMapping("/cadastrarPaciente")
	public String cadastrarPaciente(@ModelAttribute("paciente") Paciente paciente, BindingResult result, Model model, RedirectAttributes redirectAttributes ) {

		if (pacienteRepository.findByCpf(paciente.getCpf()).isPresent()) {
			redirectAttributes.addFlashAttribute("message", "CPF já cadastrado para paciente. Tente outro.");
			// model.addAttribute("message", "CPF já cadastrado para paciente. Tente outro.");
			return "cadastroPaciente-home";
		}

		try {
			// model.addAttribute("nome", paciente.getNome());
			pacienteRepository.save(paciente);
			redirectAttributes.addFlashAttribute("message", "Paciente cadastrado com sucesso!");
			// redirectAttributes.addFlashAttribute("message", paciente.getNome());
			// redirectAttributes.addFlashAttribute("message", paciente.getNomeDoenca());
		    return "redirect:/home";
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("message", "Erro: " + ex.getMessage());
			// model.addAttribute("message", "Erro: " + ex.getMessage());
			return "cadastroPaciente-home";
		}
	}

	// Página de login
	@GetMapping("/login")
	public String loginForm(Model model) {
		return "login";
	}

	@GetMapping("/home")
	public String homeForm(Model model) {
	    List<Paciente> pacientes = pacienteRepository.findAll();
	    model.addAttribute("pacientes", pacientes);
		
		
		// Paciente paciente = new Paciente();
		// paciente.setNome((String) session.getAttribute("nomeSession"));
		return "home-page";
	}
	
	@GetMapping("/site")
	public String siteForm(Model model) {
		Paciente paciente = new Paciente();
		// paciente.setNome((String) session.getAttribute("nomeSession"));
		return "site-page";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String senha, Model model) throws Exception {
		Optional<Medico> optionalMedico = medicoRepository.findByEmail(email);

		if (optionalMedico.isPresent()
				&& optionalMedico.get().getSenha().equals(optionalMedico.get().criptografarSenha(senha))) {
			model.addAttribute("message", "Login realizado com sucesso!");
			return "redirect:/home";
		} else {
			model.addAttribute("message", "Email ou senha inválidos.");
			return "login";
		}
	}

	// Página inicial após login
	/*
	 * @GetMapping("/home") public String home(Model model) { List<Paciente>
	 * pacientes = pacienteRepository.findAll(); model.addAttribute("pacientes",
	 * pacientes); // Adiciona os pacientes no modelo return "home"; // Retorna a
	 * página home.html }
	 */

	@GetMapping("/relatorio")
	public String relatorioForm(Model model) {
		List<Paciente> pacientes = pacienteRepository.findAll();
	    model.addAttribute("pacientes", pacientes);
		return "relatorio";
	}
}