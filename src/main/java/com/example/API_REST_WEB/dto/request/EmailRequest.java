package com.example.API_REST_WEB.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    @NotBlank(message = "Le destinataire est obligatoire")
    @Email(message = "L'email du destinataire doit être valide")
    private String to;

    private List<String> cc;

    @NotBlank(message = "Le sujet est obligatoire")
    private String subject;

    @NotBlank(message = "Le contenu est obligatoire")
    private String content;

    private boolean isHtml;
    private String attachmentPath;

    private String body;
}