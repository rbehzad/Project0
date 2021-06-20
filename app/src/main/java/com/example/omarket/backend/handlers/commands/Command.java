package com.example.omarket.backend.handlers.commands;

import com.example.omarket.backend.response.Response;

public interface Command {
    Response execute();
}
