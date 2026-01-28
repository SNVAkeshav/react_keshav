package com.ecommerce.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "drtwjhzrc");
        config.put("api_key", "747886645252297");
        config.put("api_secret", "HFrjB1FsLyH4g58fWCvQc5VTOpY");
        return new Cloudinary(config);
    }
}
