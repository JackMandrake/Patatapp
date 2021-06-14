package com.example.patatapp.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@NoArgsConstructor
@SessionScope
@Component
public class Error {

    private String errorMessage;

}
