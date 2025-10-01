package com.example.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.model.Images;
import com.example.reposetory.UploadImageRep;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UploadImageRep uploadImageRep;

	@GetMapping("/")
	public String index(HttpSession session, Model model) {
		List<Images> list = uploadImageRep.findAll();
		model.addAttribute("list", list);
	    String msg = (String) session.getAttribute("msg");
	    if (msg != null) {
	    	model.addAttribute("msg", msg);
	    	session.removeAttribute("msg"); // ek baar show hone ke baad hata do
	    }
	    return "index"; // index.html render karega
	}


	@PostMapping("/imageUpload")
	public String imageUpload(@RequestParam MultipartFile img, HttpSession session) {
	    try {
	        Images im = new Images();
	        im.setImageName(img.getOriginalFilename());
	        Images uploadedImage = uploadImageRep.save(im);

	        if (uploadedImage != null) {
	            String uploadDir = System.getProperty("user.dir") + "/uploads";
	            File saveFile = new File(uploadDir);
	            if (!saveFile.exists()) {
	                saveFile.mkdirs();
	            }
	            Path path = Paths.get(uploadDir, img.getOriginalFilename());
	            Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

	            session.setAttribute("msg", "Image uploaded successfully!");
	        } else {
	            session.setAttribute("msg", "Failed to upload image.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.setAttribute("msg", "Error while uploading image: " + e.getMessage());
	    }
	    return "redirect:/";
	}
}