package com.udacity.rw.course1.cloudstorage.controllers;

import com.udacity.rw.course1.cloudstorage.models.Note;
import com.udacity.rw.course1.cloudstorage.models.User;
import com.udacity.rw.course1.cloudstorage.services.NoteService;
import com.udacity.rw.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

  private NoteService noteService;
  private UserService userService;

  public NoteController(NoteService noteService,UserService userService){
    this.noteService = noteService;
    this.userService = userService;
  }

  @PostMapping("/save")
  public String createNote(Authentication auth, Note note, Model model){
    // set the id for the currently logged in user.
    note.setUserId(userService.getLoggedInUserId(auth));

    noteService.insertNote(note);

    return "redirect:/home";
  }

  @GetMapping("/delete")
  public String deleteNote(@RequestParam("id") int id){
    noteService.deleteNote(id);
    return "redirect:/home";
  }


}
