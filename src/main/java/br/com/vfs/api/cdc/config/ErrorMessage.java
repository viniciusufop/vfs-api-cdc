package br.com.vfs.api.cdc.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ErrorMessage {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final List<String> errors;
    private final String path;
}
