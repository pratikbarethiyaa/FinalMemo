package com.example.Memo.Controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Memo.Model.Domain;
import com.example.Memo.Service.DomainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RestController
@RequestMapping("/api/domains")
@CrossOrigin(origins="*")
public class DomainController {

	@Autowired
	private DomainService service;

	@GetMapping
	public List<Domain> getAllDomains() {
		return service.getAllDomains();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Domain> getDomainById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getDomainById(id));
	}

	@PostMapping
	public ResponseEntity<Domain> createDomain(
			@RequestParam("domain") String domainJson,
			@RequestParam("logo") MultipartFile logo,
			@RequestParam("logo2") MultipartFile logo2) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule()); // For handling LocalDateTime
		Domain domain = mapper.readValue(domainJson, Domain.class);

		return ResponseEntity.ok(service.createDomain(domain, logo, logo2));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Domain> updateDomain(
			@PathVariable Integer id,
			@RequestParam("domain") String updatedDomain,
			@RequestParam(value = "logo", required = false) MultipartFile logo,
			@RequestParam(value = "logo2", required = false) MultipartFile logo2) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule()); // For handling LocalDateTime
		Domain domain = mapper.readValue(updatedDomain, Domain.class);

		return ResponseEntity.ok(service.updateDomain(id, domain, logo, logo2));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDomain(@PathVariable Integer id) {
		service.deleteDomain(id);
		return ResponseEntity.noContent().build();
	}
}
