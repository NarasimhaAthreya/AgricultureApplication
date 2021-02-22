package com.agriculture.development.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;

import org.springframework.web.multipart.MultipartFile;

import com.agriculture.development.DAO.FramerDAO;
import com.agriculture.development.entities.Farmers;

@Component
public class StorageService {

	@Autowired
	private FramerDAO farmdao;

	@Value("${file.upload-dir}")
	private String uploadDir;

	public void uploadDocuments(MultipartFile[] files, int id, String folderName) {
		System.out.println(files.toString());
		for (MultipartFile file : files) {
			System.out.println(file.toString());
			Path copyLocation = Paths.get(uploadDir + File.separatorChar + id + File.separatorChar + folderName
					+ File.separatorChar + StringUtils.cleanPath(file.getOriginalFilename()));
			Farmers farms = farmdao.getFarmerbyId(id);
			if (folderName.contains("Doc")) {
				farms.setDocImagesPath(copyLocation.getParent().toString());

			} else {
				farms.setLandImagesPath(copyLocation.getParent().toString());
			}
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
	}

}
