package br.com.arq.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="diagnosticos")
public class Diagnostico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idPaciente;
	private Long idMedico;
	private LocalDateTime DataHoraCadastro;
	private String diagnosticoPaciente;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	public Long getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}
	public LocalDateTime getDataHoraCadastro() {
		return DataHoraCadastro;
	}
	public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
		DataHoraCadastro = dataHoraCadastro;
	}
	public String getDiagnosticoPaciente() {
		return diagnosticoPaciente;
	}
	public void setDiagnosticoPaciente(String diagnosticoPaciente) {
		this.diagnosticoPaciente = diagnosticoPaciente;
	}
	@Override
	public String toString() {
		return "Diagnostico [id=" + id + ", idPaciente=" + idPaciente + ", idMedico=" + idMedico + ", DataHoraCadastro="
				+ DataHoraCadastro + ", diagnosticoPaciente=" + diagnosticoPaciente + "]";
	}
	
	public Diagnostico(Long id, Long idPaciente, Long idMedico, LocalDateTime dataHoraCadastro,
			String diagnosticoPaciente) {
		super();
		this.id = id;
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
		DataHoraCadastro = dataHoraCadastro;
		this.diagnosticoPaciente = diagnosticoPaciente;
	}
	
	public Diagnostico(Long idPaciente, Long idMedico, LocalDateTime dataHoraCadastro, String diagnosticoPaciente) {
		super();
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
		DataHoraCadastro = dataHoraCadastro;
		this.diagnosticoPaciente = diagnosticoPaciente;
	}
	
	public Diagnostico() {
		// TODO Auto-generated constructor stub
	}
	
	
}
