package com.agriculture.development.contorllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agriculture.development.DAO.FramerDAO;
import com.agriculture.development.entities.Farmers;
import com.agriculture.development.utils.StorageService;

@Controller
@RequestMapping("/farmers")
public class FarmerController {

	@Autowired
	private StorageService storageservice;

	@Autowired
	private FramerDAO farmdao;

	@Value("${file.upload-dir}")
	private String uploadDir;

	@RequestMapping("/add")
	public String addFarmers(@ModelAttribute Farmers farmer, @RequestParam("docs") MultipartFile[] docs,
			@RequestParam("land") MultipartFile[] land) {
		System.out.println("In add FarmAdd controller");
		
		storageservice.uploadDocuments(docs, farmdao.addFarmer(farmer).getId(),"Doc");
		storageservice.uploadDocuments(land, farmdao.addFarmer(farmer).getId(),"Land");

		return "redirect:/farmers/showFarmers";
	}

	@RequestMapping("/showFarmers")
	public String addFarmers(Model model) {
		System.out.println("In add FarmShow controller");
		Farmers farmer = new Farmers();
		List<Farmers> farmers = farmdao.showFarmers();
		model.addAttribute("farmers", farmers);
		model.addAttribute("farmer", farmer);
		return "showFarmers";
	}

	@RequestMapping("/upload/{id}")
	public String uploadDocuments(@PathVariable("id") int id, Model model) {
		System.out.println("In Upload doc controller.." + id);
		Farmers farm = farmdao.getFarmerbyId(id);
		model.addAttribute("farm", farm);
		return "upload";
	}

	@RequestMapping("/uploadFiles")
	public String uploadDocuments(@RequestParam("docs") MultipartFile[] files, @RequestParam("id") String id,
			RedirectAttributes redirectAttrs) {
		System.out.println(files.toString());
		for (MultipartFile file : files) {
			Path copyLocation = Paths.get(uploadDir + File.separatorChar + id + File.separatorChar
					+ StringUtils.cleanPath(file.getOriginalFilename()));
			Farmers farms = farmdao.getFarmerbyId(Integer.parseInt(id));
			farms.setDocImagesPath(copyLocation.toString());
			farms.setVerificationStatus("NV");
			farmdao.update(farms);
			if (copyLocation.toFile().exists()) {
				continue;
			} else {
				try {
					Files.createDirectories(copyLocation);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Directory created");
			}
			System.out.println(copyLocation);
			try {
				Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		redirectAttrs.addFlashAttribute("message", "File has been uploaded successfully !");
		return "redirect:/farmers/showFarmers";
	}
}