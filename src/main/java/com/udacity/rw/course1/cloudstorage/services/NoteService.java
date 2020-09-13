package com.udacity.rw.course1.cloudstorage.services;

import com.udacity.rw.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.rw.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

  private NoteMapper noteMapper;

  public NoteService(NoteMapper noteMapper){
    this.noteMapper = noteMapper;
  }

  public List<Note> getAllNotes(int userId){
    return noteMapper.getAllNotes(userId);
  }

  public void insertNote(Note note){
    if(note.getNoteId() == null){
      noteMapper.insertNote(note);
    }
    else{ // an existing note id means we just want to update a current note.
      noteMapper.updateNote(note);
    }
  }

  public void deleteNote(int id){
    noteMapper.deleteNote(id);
  }





}
