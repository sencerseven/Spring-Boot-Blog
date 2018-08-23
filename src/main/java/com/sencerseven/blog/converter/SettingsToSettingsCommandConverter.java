package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.SettingsCommand;
import com.sencerseven.blog.domain.Settings;
import com.sencerseven.blog.service.S3Services;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SettingsToSettingsCommandConverter implements Converter<Settings,SettingsCommand> {

    S3Services s3Services;

    public SettingsToSettingsCommandConverter(S3Services s3Services) {
        this.s3Services = s3Services;
    }

    @Override
    public SettingsCommand convert(Settings settings) {
        if(settings == null)
            return null;

        SettingsCommand settingsCommand = new SettingsCommand();

        settingsCommand.setId(settings.getId());
        settingsCommand.setStatus(settings.isStatus());
        settingsCommand.setTitle(settings.getTitle());

        if(settings.getSiteImageUrl() != null){
            settingsCommand.setSiteLogoUrl(s3Services.getSignUrl(settings.getSiteImageUrl(),60));
        }

        return settingsCommand;
    }
}
