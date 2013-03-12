package org.openmrs.module.basicmodule.db;

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.module.basicmodule.Note;
import org.openmrs.module.basicmodule.NoteService;

/**
 * This is the DAO interface. This is never used by the developer, but instead
 * the {@link NoteService} calls it (and developers call the NoteService)
 */
public interface NoteDAO {

	Note getNote(Integer id);

	Note getNoteByUuid(String uuid);

	List<Note> getNotes(Patient patient);

	Note saveNote(Note note);

}
