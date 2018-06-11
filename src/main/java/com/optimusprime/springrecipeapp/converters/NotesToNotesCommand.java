package com.optimusprime.springrecipeapp.converters;

import com.optimusprime.springrecipeapp.commands.NotesCommand;
import com.optimusprime.springrecipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes,NotesCommand>{

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {

        if(notes!=null){
            final  NotesCommand  notesCommand= new NotesCommand();
            notesCommand.setId(notes.getId());
            notesCommand.setRecipeNotes(notes.getRecipeNotes());
            return notesCommand;
        }
        return null;

    }
}
