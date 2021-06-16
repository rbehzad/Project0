package com.example.omarket.backend.handlers.commands;

import com.example.omarket.backend.Response;

public class SaveCommand implements Command{
    private static SaveCommand saveCommand;

    @Override
    public Response execute() {
        return null;
    }

    private SaveCommand(){
        saveCommand = this;
    }

    public static SaveCommand getInstance() {
        if (saveCommand != null){
            return saveCommand;
        }
        return new SaveCommand();
    }

}
