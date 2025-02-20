package com.example.Memo.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Memo.Model.Domain;
import com.example.Memo.Repository.DomainRepo;

@Service
public class DomainService {

	@Autowired
	private DomainRepo repository;

	public List<Domain> getAllDomains() {
		return repository.findAll();
	}

	public Domain getDomainById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Domain not found with id " + id));
	}

	public Domain createDomain(Domain domain, MultipartFile logo, MultipartFile logo2) throws IOException {
		// Save logos and set paths
		if (logo != null && !logo.isEmpty()) {
			String logoPath = saveFile(logo);
			domain.setLogo(logoPath);
		}
		if (logo2 != null && !logo2.isEmpty()) {
			String logo2Path = saveFile(logo2);
			domain.setLogo2(logo2Path);
		}

		domain.setCreatedAt(LocalDateTime.now());
		domain.setUpdatedAt(LocalDateTime.now());
		return repository.save(domain);
	}

	public Domain updateDomain(Integer id, Domain updatedDomain, MultipartFile logo, MultipartFile logo2) throws IOException {
		Domain existingDomain = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Domain not found with id " + id));

		// Update fields as before...
		existingDomain.setName(updatedDomain.getName());
		existingDomain.setPanNo(updatedDomain.getPanNo());
		existingDomain.setAddress(updatedDomain.getAddress());
		existingDomain.setGstinno(updatedDomain.getGstinno());
		existingDomain.setLogo(updatedDomain.getLogo());
		existingDomain.setLogo2(updatedDomain.getLogo2());
		existingDomain.setEmail(updatedDomain.getEmail());
		existingDomain.setWebsite(updatedDomain.getWebsite());
		existingDomain.setMobile(updatedDomain.getMobile());
		existingDomain.setMobile2(updatedDomain.getMobile2());
		existingDomain.setLandline(updatedDomain.getLandline());
		existingDomain.setBankAccNo(updatedDomain.getBankAccNo());
		existingDomain.setBankname(updatedDomain.getBankname());
		existingDomain.setIfsc(updatedDomain.getIfsc());
		existingDomain.setCustomerPrefix(updatedDomain.getCustomerPrefix());
		existingDomain.setInvoicePrefix(updatedDomain.getInvoicePrefix());
		existingDomain.setIsDeleted(updatedDomain.getIsDeleted());
		existingDomain.setDeletedBy(updatedDomain.getDeletedBy());
		existingDomain.setUpdatedAt(LocalDateTime.now());

		// Handle image updates
		if (logo != null && !logo.isEmpty()) {
			String logoPath = saveFile(logo);
			existingDomain.setLogo(logoPath);
		}

		if (logo2 != null && !logo2.isEmpty()) {
			String logo2Path = saveFile(logo2);
			existingDomain.setLogo2(logo2Path);
		}

		existingDomain.setUpdatedAt(LocalDateTime.now());

		return repository.save(existingDomain);
	}



	public void deleteDomain(Integer id) {
		repository.deleteById(id);
	}
	private String saveFile(MultipartFile file) throws IOException {
		// Create directory if it doesn't exist
		final String uploadDir = "src/main/resources/images/";
		File directory = new File(uploadDir);
		if (!directory.exists()) {
			boolean created = directory.mkdirs(); // Create the directory
			if (!created) {
				throw new IOException("Failed to create directory: " + uploadDir);
			}
		}

		// Save file to the specified path
		Path path = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
		Files.write(path, file.getBytes());

		return path.toString(); // Return the path as a string for storage in DB
	}

}
