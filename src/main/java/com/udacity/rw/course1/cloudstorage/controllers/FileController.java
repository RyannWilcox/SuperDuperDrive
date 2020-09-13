package com.udacity.rw.course1.cloudstorage.controllers;

import com.udacity.rw.course1.cloudstorage.models.File;
import com.udacity.rw.course1.cloudstorage.services.FileService;
import com.udacity.rw.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/file")
public class FileController {

  private UserService userService;
  private FileService fileService;

  public FileController(UserService userService, FileService fileService) {
    this.userService = userService;
    this.fileService = fileService;
  }

  @PostMapping("/upload")
  public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload,
                           Authentication auth, Model model) throws IOException {
    boolean isSuccess;

    if(fileUpload.isEmpty()){
      isSuccess = false;
    }
    else {
      isSuccess = fileService.insertFile(fileUpload, userService.getLoggedInUserId(auth)) > 0;
    }
    return "redirect:/result?isSuccess=" + isSuccess;
  }
}
