package com.example.library.service;

public interface MailService {
    void send(String emailTo, String subject, String message);
}
