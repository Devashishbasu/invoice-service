package com.example.invoice_service.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
public class TemplateService {

  private final SpringTemplateEngine templateEngine;

  public TemplateService(SpringTemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  public String render(String templateName, Map<String, Object> variables) {
    Context ctx = new Context();
    ctx.setVariables(variables);
    return templateEngine.process(templateName, ctx); // looks in src/main/resources/templates/*.html
  }
}
