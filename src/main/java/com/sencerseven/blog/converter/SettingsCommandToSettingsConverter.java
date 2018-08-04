package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.SettingsCommand;
import com.sencerseven.blog.domain.Settings;
import com.sencerseven.blog.service.S3Services;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SettingsCommandToSettingsConverter implements Converter<SettingsCommand, Settings> {

    S3Services s3Services;

    public SettingsCommandToSettingsConverter(S3Services s3Services) {
        this.s3Services = s3Services;
    }

    @Override
    public Settings convert(SettingsCommand settingsCommand) {
        if(settingsCommand == null)
            return null;


            Settings settings = new Settings();
            settings.setId(settingsCommand.getId());
            settings.setStatus(settingsCommand.isStatus());
            settings.setTitle(settingsCommand.getTitle());

            if(settingsCommand.getSiteLogo() != null && settingsCommand.getSiteLogo().getSize() >0)
            settings.setSiteImageUrl(s3Services.uploadFile(settingsCommand.getSiteLogo().getOriginalFilename(),"logo",settingsCommand.getSiteLogo()));

            return  settings;

    }
}
