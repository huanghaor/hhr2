package com.hhr.springbootstudy.service.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JavaConfiA.class,JavaConfiB.class})
public class ParentConfig {
}
